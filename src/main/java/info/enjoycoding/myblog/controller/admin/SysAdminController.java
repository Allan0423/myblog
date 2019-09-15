package info.enjoycoding.myblog.controller.admin;

import info.enjoycoding.myblog.model.Blog;
import info.enjoycoding.myblog.model.BlogType;
import info.enjoycoding.myblog.model.Blogger;
import info.enjoycoding.myblog.model.Link;
import info.enjoycoding.myblog.service.IBlogService;
import info.enjoycoding.myblog.service.IBlogTypeService;
import info.enjoycoding.myblog.service.IBloggerService;
import info.enjoycoding.myblog.service.ILinkService;
import info.enjoycoding.myblog.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/system")
public class SysAdminController {

    @Resource
    private IBloggerService bloggerService;

    @Resource
    private ILinkService linkService;

    @Resource
    private IBlogTypeService blogTypeService;

    @Resource
    private IBlogService blogService;

    public void refreshSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletContext context = Objects.requireNonNull(RequestContextUtils.findWebApplicationContext(request)).getServletContext();

        assert context != null;

        Blogger blogger = bloggerService.findBlogger();
        blogger.setPwd(null);

        context.setAttribute("blogger", blogger);
        List<Link> linkList = linkService.list();
        context.setAttribute("linkList", linkList);

        List<BlogType> blogTypeCountList = blogTypeService.countAll();
        context.setAttribute("blogTypeCountList", blogTypeCountList);

        List<Blog> blogCountList = blogService.countList();
        context.setAttribute("blogCountList", blogCountList);

        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
