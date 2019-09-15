package info.enjoycoding.myblog.service.impl;

import info.enjoycoding.myblog.dao.LinkDao;
import info.enjoycoding.myblog.model.Link;
import info.enjoycoding.myblog.service.ILinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("linkSvc")
public class LinkServiceImpl implements ILinkService {

    @Resource
    LinkDao linkDao;

    @Override
    public List<Link> list() {
        return linkDao.list();
    }

    @Override
    public Integer addLink(Link link) {
        return linkDao.add(link);
    }

    @Override
    public Integer updateLink(Link link) {
        return linkDao.update(link);
    }

    @Override
    public Integer deleteLink(Integer id) {
        return linkDao.delete(id);
    }
}
