package info.enjoycoding.myblog.model;

/**
 * 博客模型
 */
public class Blog {
    /**
     * 博客id
     */
    Integer id;

    /**
     * 博客类型id
     */
    Integer blogTypeId;

    /**
     * 标题
     */
    String title;

    /**
     * 摘要
     */
    String digest;

    /**
     * 内容
     */
    String content;

    /**
     * 关键字，逗号分隔的词组
     */
    String keywords;

    /**
     * 阅读量
     */
    Integer readCount;

    /**
     * 发布时间
     */
    String releaseDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogTypeId() {
        return blogTypeId;
    }

    public void setBlogTypeId(Integer blogTypeId) {
        this.blogTypeId = blogTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
