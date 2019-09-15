package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.Blog;

import java.util.List;
import java.util.Map;

public interface IBlogService {

    List<Blog> list();

    Integer getCount(Map<String, Object> map);

    List<Blog> countList();

    Blog findById(Integer id);

    Integer addBlog(Blog blog);

    Integer updateBlog(Blog blog);

    Integer deleteBlog(Integer id);
}
