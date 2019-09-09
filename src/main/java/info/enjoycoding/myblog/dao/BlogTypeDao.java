package info.enjoycoding.myblog.dao;

import info.enjoycoding.myblog.model.BlogType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface BlogTypeDao {

    @Select("SELECT t1.blogtype_id, t1.blogtype_name, COUNT(t2.blog_id) AS blogCount " +
            "FROM tb_blogtype t1 " +
            "    LEFT JOIN tb_blog t2 ON t1.blogtype_id = t2.blog_typeid " +
            "    GROUP BY t1.blogtype_name " +
            "    ORDER BY t1.blogtype_orderno")
    List<BlogType> countAll();


    @Select("SELECT * FROM tb_blogtype WHERE blogtype_id = #{blogTypeId}")
    BlogType findById(Integer blogTypeId);


    @Select("<script> SELECT * FROM tb_blogtype " +
            "<if test=\"#{map.start} != null\"> LIMIT #{map.start}, #{map.size}</if> </script>")
    List<BlogType> list(@Param("map") Map<String, Object> map);


    @Select("SELECT count(*) FROM tb_blogtype")
    Integer getCount(Map<String, Object> map);


    @Insert("INSERT INTO tb_blogtype VALUES(null, #{name}, #{orderNo})")
    Integer add(BlogType blogType);


    @Update("UPDATE tb_blogtype SET blogtype_name=#{name}, blogtype_orderNo=#{orderNo}, WHERE blogtype_id=#{id}")
    Integer update(BlogType blogType);


    @Delete("DELETE FROM tb_blogtype WHERE blogtype_id=#{id}")
    Integer delete(Integer id);
}
