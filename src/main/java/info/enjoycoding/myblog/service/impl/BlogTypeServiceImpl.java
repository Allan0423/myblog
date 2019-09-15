package info.enjoycoding.myblog.service.impl;

import info.enjoycoding.myblog.dao.BlogTypeDao;
import info.enjoycoding.myblog.model.BlogType;
import info.enjoycoding.myblog.service.IBlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogTypeSvc")
public class BlogTypeServiceImpl implements IBlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;

    @Override
    public List<BlogType> countAll() {
        return blogTypeDao.countAll();
    }

    @Override
    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeDao.list(map);
    }

    @Override
    public Integer getCount(Map<String, Object> map) {
        return blogTypeDao.getCount(map);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }
}
