package info.enjoycoding.myblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

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
