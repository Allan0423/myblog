package info.enjoycoding.myblog.controller;

import info.enjoycoding.myblog.model.Blogger;
import info.enjoycoding.myblog.service.IBloggerService;
import info.enjoycoding.myblog.util.CryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private IBloggerService bloggerService;

    private static final String ADMIN_MAIN = "redirect:/admin/main";
    private static final String LOGIN = "/login";

    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/login")
    public String bloggerLogin(Blogger blogger, HttpServletRequest request){
        LOGGER.info("Trying to login:{}", blogger.toString());
        blogger.setPwd(CryptoUtil.getDigest(blogger.getPwd()));
        List<Blogger> bloggerInSystem = bloggerService.selectBlogger(blogger);
        if (bloggerInSystem.isEmpty()){
            return LOGIN;
        } else {
            return ADMIN_MAIN;
        }
    }

    @RequestMapping("/main")
    public String adminMainPage(HttpServletRequest request){
        return "/admin/main";
    }

}
