package info.enjoycoding.myblog.dao;

import info.enjoycoding.myblog.model.Blogger;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BloggerDao {

    @Results(id = "blogger", value = {
            @Result(property = "name", column = "blogger_name"),
            @Result(property = "email", column = "blogger_email"),
            @Result(property = "profile", column = "blogger_profile"),
            @Result(property = "signature", column = "blogger_signature"),
            @Result(property = "profilePicName", column = "blogger_profile_pic_name")
    })
    @Select("SELECT * FROM tb_blogger")
    Blogger findBlogger();

    /**
     * 查找博主
     * @param blogger
     * @return
     */
    @ResultMap("blogger")
    @Select("SELECT * FROM tb_blogger WHERE blogger_name=#{name} AND blogger_pwd=#{pwd}")
    List<Blogger> selectByName(Blogger blogger);

    /**
     * 更新密码
     * @param blogger
     * @return
     */
    @Update("UPDATE tb_blogger SET blogger_pwd=#{pwd} WHERE blogger_name=#{name}")
    Integer updateBloggerPwd(Blogger blogger);

    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    @Update("UPDATE tb_blogger SET blogger_email=#{email}," +
            "blogger_profile=#{profile}," +
            "blogger_signature=#{signature}," +
            "blogger_profile_pic_name=#{profilePicName}," +
            " WHERE blogger_name=#{name}")
    Integer updateBloggerInfo(Blogger blogger);
}
