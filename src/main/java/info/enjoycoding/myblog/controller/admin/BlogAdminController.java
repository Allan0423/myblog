package info.enjoycoding.myblog.controller.admin;

import info.enjoycoding.myblog.service.IBloggerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private IBloggerService bloggerService;

    @RequestMapping("/list")
    public void list(){

    }

    @RequestMapping("/save")
    public void saveBlog(){

    }

    @RequestMapping("/delete")
    public void deleteBlog(){

    }
}
