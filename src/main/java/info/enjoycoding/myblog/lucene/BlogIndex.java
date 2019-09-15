package info.enjoycoding.myblog.lucene;

import info.enjoycoding.myblog.model.Blog;
import info.enjoycoding.myblog.util.DateUtil;
import info.enjoycoding.myblog.util.StringUtil;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BlogIndex {

    private IndexWriter writer;

    private IndexReader reader;

    /**
     * 禁止在其他类中创建实例
     */
    private BlogIndex(){
        init();
    }

    private static BlogIndex getInstance(){
        return BlogIndexHolder.instance;
    }

    private static class BlogIndexHolder{
        private static final BlogIndex instance = new BlogIndex();
    }

    /**
     * 初始化索引
     *
     * @throws Exception
     */
    private void init(){
        try {
            Directory dir = FSDirectory.open(Paths.get(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/lucene"));
            this.writer = new IndexWriter(dir, new IndexWriterConfig(new SmartChineseAnalyzer()));
            this.reader = DirectoryReader.open(dir);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 添加博客索引
     *
     * @param blog
     * @throws Exception
     */
    public void addIndex(Blog blog) throws Exception {
        writer.addDocument(buildDoc(blog));
        writer.close();
    }

    /**
     * 更新博客索引
     *
     * @param blog
     * @throws Exception
     */
    public void updateIndex(Blog blog) throws Exception {
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), buildDoc(blog));
        writer.close();
    }

    /**
     * 删除博客索引
     *
     * @param blogId
     * @throws Exception
     */
    public void deleteIndex(String blogId) throws Exception {
        writer.deleteDocuments(new Term("id", blogId));
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }

    /**
     * 根据关键字查询博客
     *
     * @param queryWord 关键字
     * @return 博客简要信息列表
     * @throws Exception
     */
    public List<Blog> searchBlog(String queryWord) throws Exception {
        IndexSearcher searcher = new IndexSearcher(reader);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        // 指定查询范围，包括title和content
        QueryParser parser = new QueryParser("title", analyzer);
        Query query = parser.parse(queryWord);
        QueryParser parser2 = new QueryParser("content", analyzer);
        Query query2 = parser2.parse(queryWord);

        // 关键字存在方式：应该出现
        builder.add(query, BooleanClause.Occur.SHOULD);
        builder.add(query, BooleanClause.Occur.SHOULD);

        // 查询前100个匹配项
        TopDocs hits = searcher.search(builder.build(), 100);

        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);

        // 命中的关键字添加格式
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);

        List<Blog> blogList = new LinkedList<>();
        for (ScoreDoc scoreDoc : hits.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setId(Integer.parseInt(doc.get("id")));
            blog.setReleaseTime(DateUtil.formatString(doc.get("releaseDate"), "yyyy-MM-dd"));

            String title = doc.get("title");
            String content = StringUtil.stripHtml(doc.get("content"));

            if (title != null) {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                } else {
                    blog.setTitle(hTitle);
                }
            }
            if (content != null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isEmpty(hContent)) {
                    if (content.length() <= 200) {
                        blog.setContent(content);
                    } else {
                        blog.setContent(content.substring(0, 200));
                    }
                } else {
                    blog.setContent(hContent);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }

    /**
     * 将Blog构建为Document对象
     *
     * @param blog 博客
     * @return Document对象
     */
    private Document buildDoc(Blog blog) {
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        return doc;
    }
}
