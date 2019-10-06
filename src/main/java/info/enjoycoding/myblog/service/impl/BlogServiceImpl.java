package info.enjoycoding.myblog.service.impl;

import info.enjoycoding.myblog.dao.BlogDao;
import info.enjoycoding.myblog.model.Blog;
import info.enjoycoding.myblog.service.IBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogSvc")
public class BlogServiceImpl implements IBlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public List<Blog> list(Map map) {
        return blogDao.list();
    }

    @Override
    public Integer getCount(Map<String, Object> map) {
        return blogDao.getCount();
    }

    @Override
    public List<Blog> countList() {
        return blogDao.countList();
    }

    @Override
    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    @Override
    public Integer add(Blog blog) {
        return blogDao.add(blog);
    }

    @Override
    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    @Override
    public Integer delete(Integer id) {
        return blogDao.delete(id);
    }
}
