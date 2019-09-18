package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.Blog;

import java.util.List;
import java.util.Map;

public interface IBlogService {

    List<Blog> list(Map map);

    Integer getCount(Map<String, Object> map);

    List<Blog> countList();

    Blog findById(Integer id);

    Integer add(Blog blog);

    Integer update(Blog blog);

    Integer delete(Integer id);
}
