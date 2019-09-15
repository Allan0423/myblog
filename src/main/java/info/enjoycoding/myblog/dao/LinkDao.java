package info.enjoycoding.myblog.dao;

import info.enjoycoding.myblog.model.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface LinkDao {

    @Results(id = "link", value = {
            @Result(property = "id", column = "link_id"),
            @Result(property = "name", column = "link_name"),
            @Result(property = "url", column = "link_url"),
            @Result(property = "orderNo", column = "link_order_no")
    })
    @Select("<script> SELECT * FROM tb_link " +
            "<if test=\"#{map.start} != null\"> LIMIT #{map.start}, #{map.size}</if> </script>")
    List<Link> list(Map map);

    @Select("SELECT COUNT(*) FROM tb_link")
    Integer getCount();

    @Insert("INSERT INTO tb_link VALUES (null, #{name}, #{url}, #{orderNo})")
    Integer add(Link link);

    @Update("UPDATE tb_link SET link_name=#{name}, link_url=#{url}, link_order_no=#{orderNo} WHERE link_id=#{id}")
    Integer update(Link link);

    @Delete("DELETE FROM tb_link WHERE link_id=#{id}")
    Integer delete(Integer id);
}
