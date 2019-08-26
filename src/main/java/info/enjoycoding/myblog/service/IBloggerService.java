package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.Blogger;

import java.util.List;

public interface IBloggerService {

    List<Blogger> selectBlogger(Blogger blogger);

    Integer updatePwd(Blogger blogger);

    Integer updateInfo(Blogger blogger);
}
