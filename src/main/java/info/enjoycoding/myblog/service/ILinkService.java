package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.Link;

import java.util.List;

public interface ILinkService {

    List<Link> list();

    Integer addLink(Link link);

    Integer updateLink(Link link);

    Integer deleteLink(Integer id);
}
