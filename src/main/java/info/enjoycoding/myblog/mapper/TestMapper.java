package info.enjoycoding.myblog.mapper;

import info.enjoycoding.myblog.model.TestModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TestMapper {

    /**
     * 插入数据
     * @param model
     * @return
     */
    @Insert("INSERT INTO tb_test(key, value) VALUES (#{key}, #{value})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'tb_test')", before = false, keyProperty = "id", resultType = Integer.class)
    Integer insert(TestModel model);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM tb_test WHERE id = #{id}")
    TestModel select(Integer id);

    /**
     * 查询所有数据
     * @return
     */
    @Select("SELECT * FROM tb_test")
    List<TestModel> selectAll();

    /**
     * 更新数据
     * @param model
     * @return
     */
    @Update("UPDATE tb_test SET value=#{value} WHERE id = #{id}")
    Integer updateValue(TestModel model);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Delete("DELETE FROM tb_test WHERE id=#{id}")
    Integer delete(Integer id);
}
