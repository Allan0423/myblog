package info.enjoycoding.myblog.dao;

import info.enjoycoding.myblog.model.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogDao {

    @Select("SELECT * FROM tb_blog")
    List<Blog> list();

    @Select("SELECT COUNT(*) FROM tb_blog")
    Integer getCount();

    @Select("SELECT " +
                "STRFTIME('%Y年%m月', blog_create_time) AS releaseDateStr," +
                "COUNT(*) AS blogCount " +
            "FROM tb_blog " +
            "GROUP BY STRFTIME('%Y年%m月', blog_create_time) " +
            "ORDER BY STRFTIME('%Y年%m月', blog_create_time) DESC")
    List<Blog> countList();

    @Select("SELECT * FROM tb_blog WHERE blog_id=#{id}")
    Blog findById(Integer id);

    @Insert("INSERT INTO tb_blog VALUES (null, #{title}, #{blogTypeId}, #{digest}, #{keywords}, #{content}, #{releaseTime}, #{readCount})")
    Integer add(Blog blog);

    @Update("UPDATE tb_blog SET " +
            "blog_title=#{title}, blog_type_id=#{blogTypeId}, blog_digest=#{digest}, blog_keywords=#{keywords}, " +
            "blog_content=#{content}, blog_release_date=#{releaseTime}, blog_read_count=#{readCount} WHERE blog_id=#{id}")
    Integer update(Blog blog);

    @Delete("DELETE FROM tb_blog WHERE blog_id=#{id}")
    Integer delete(Integer id);
}
