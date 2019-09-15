package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.Link;

import java.util.List;
import java.util.Map;

public interface ILinkService {

    List<Link> list(Map map);

    Integer getCount();

    Integer addLink(Link link);

    Integer updateLink(Link link);

    Integer deleteLink(Integer id);
}
