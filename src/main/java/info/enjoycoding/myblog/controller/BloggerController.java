package info.enjoycoding.myblog.controller;

import info.enjoycoding.myblog.model.Blogger;
import info.enjoycoding.myblog.model.TestModel;
import info.enjoycoding.myblog.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloggerController.class);

    @RequestMapping("login")
    public String bloggerLogin(Blogger blogger, HttpServletRequest request){
        return "login";
    }

}
