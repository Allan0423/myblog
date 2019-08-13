package info.enjoycoding.myblog.controller;

import info.enjoycoding.myblog.model.TestModel;
import info.enjoycoding.myblog.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController (TestService testService){
        this.testService = testService;
    }

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("value", "测试值");
        return "test";
    }

    @RequestMapping("/tests")
    @ResponseBody
    public List<TestModel> allTest(){
        return testService.selectAll();
    }
}
