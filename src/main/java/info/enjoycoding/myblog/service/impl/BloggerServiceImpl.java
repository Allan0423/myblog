package info.enjoycoding.myblog.service.impl;

import info.enjoycoding.myblog.dao.BloggerDao;
import info.enjoycoding.myblog.model.Blogger;
import info.enjoycoding.myblog.service.IBloggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BloggerServiceImpl implements IBloggerService {

    @Resource
    private BloggerDao bloggerDao;

    @Override
    public List<Blogger> selectBlogger(Blogger blogger) {
        return bloggerDao.selectByName(blogger);
    }

    @Override
    public Integer updatePwd(Blogger blogger) {
        return bloggerDao.updateBloggerPwd(blogger);
    }

    @Override
    public Integer updateInfo(Blogger blogger) {
        return bloggerDao.updateBloggerInfo(blogger);
    }
}
