package info.enjoycoding.myblog.dao;

import info.enjoycoding.myblog.model.Blogger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BloggerDao {

    /**
     * 查找博主
     * @param blogger
     * @return
     */
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
