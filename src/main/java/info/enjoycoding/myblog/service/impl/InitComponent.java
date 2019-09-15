package info.enjoycoding.myblog.service.impl;

import info.enjoycoding.myblog.model.Blog;
import info.enjoycoding.myblog.model.BlogType;
import info.enjoycoding.myblog.model.Blogger;
import info.enjoycoding.myblog.model.Link;
import info.enjoycoding.myblog.service.IBlogService;
import info.enjoycoding.myblog.service.IBlogTypeService;
import info.enjoycoding.myblog.service.IBloggerService;
import info.enjoycoding.myblog.service.ILinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class InitComponent implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitComponent.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

        // 加载博主信息
        IBloggerService bloggerService = (IBloggerService) applicationContext.getBean("bloggerSvc");
        Blogger blogger = bloggerService.findBlogger();
        blogger.setPwd(null);
        context.setAttribute("blogger", blogger);

        // 加载友链
        ILinkService linkService = (ILinkService) applicationContext.getBean("linkSvc");
        List<Link> links = linkService.list(null);
        context.setAttribute("linkList", links);

        // 加载博客类别与数量
        IBlogTypeService blogTypeService = (IBlogTypeService) applicationContext.getBean("blogTypeSvc");
        List<BlogType> blogTypeCountList = blogTypeService.countAll();
        context.setAttribute("blogTypeCountList", blogTypeCountList);

        // 根据日期分组查询博客数量
        IBlogService blogService = (IBlogService) applicationContext.getBean("blogSvc");
        List<Blog> blogList = blogService.countList();
        context.setAttribute("blogCountList", blogList);

        LOGGER.info("博主、博客类别、博客和友链等信息加载完毕！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // just keep void
    }
}
