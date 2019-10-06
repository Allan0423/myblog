package info.enjoycoding.myblog.controller.admin;

import info.enjoycoding.myblog.model.Blog;
import info.enjoycoding.myblog.model.PageBean;
import info.enjoycoding.myblog.service.IBlogService;
import info.enjoycoding.myblog.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static info.enjoycoding.myblog.constant.WebConstants.*;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private IBlogService blogService;

    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false)String page,
                     @RequestParam(value="rows", required=false)String rows,
                     HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>(8);
        map.put(KEY_START, pageBean.getStart());
        map.put(KEY_SIZE, pageBean.getPageSize());

        List<Blog> blogList = blogService.list(map);
        JSONArray jsonArray = JSONArray.fromObject(blogList);

        Integer total = blogService.getCount(map);

        JSONObject result = new JSONObject();
        result.put(KEY_ROWS, jsonArray);
        result.put(KEY_TOTAL, total);

        ResponseUtil.write(response, result);
    }

    @RequestMapping("/save")
    public void saveBlog(Blog blog, HttpServletResponse response) throws Exception {
        int saveResult;
        if (blog.getId() == null){
            saveResult = blogService.add(blog);
        } else {
            saveResult = blogService.update(blog);
        }
        JSONObject result = new JSONObject();
        if (saveResult > 0){
            result.put(KEY_SUCCESS, true);
        } else {
            result.put(KEY_SUCCESS, false);
        }
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/delete")
    public void deleteBlog(@RequestParam(value="ids", required = true) String ids,
                           HttpServletResponse response) throws Exception {
        String[] idArray = ids.split(",");
        JSONObject result = new JSONObject();
        for(String id : idArray){
            blogService.delete(Integer.parseInt(id));
        }
        result.put(KEY_SUCCESS, true);
        ResponseUtil.write(response, result);
    }
}
