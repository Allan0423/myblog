package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.BlogType;

import java.util.List;
import java.util.Map;

public interface IBlogTypeService {

    List<BlogType> countAll();

    List<BlogType> list(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer delete(Integer id);
}
