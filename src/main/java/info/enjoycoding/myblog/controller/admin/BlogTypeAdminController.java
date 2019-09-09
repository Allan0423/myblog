package info.enjoycoding.myblog.controller.admin;

import info.enjoycoding.myblog.model.BlogType;
import info.enjoycoding.myblog.model.PageBean;
import info.enjoycoding.myblog.service.IBlogTypeService;
import info.enjoycoding.myblog.util.ResponseUtil;
import net.sf.json.JSON;
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

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private IBlogTypeService blogTypeService;

    private static final String KEY_START = "start";

    private static final String KEY_SIZE = "size";

    private static final String KEY_ROWS = "rows";

    private static final String KEY_TOTAL = "total";

    private static final String KEY_SUCCESS = "success";

    private static final String KEY_PROTECTED = "protected";


    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false)String page,
                     @RequestParam(value="rows", required=false)String rows,
                     HttpServletResponse response) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>(8);
        map.put(KEY_START, pageBean.getStart());
        map.put(KEY_SIZE, pageBean.getPageSize());

        List<BlogType> blogTypeList = blogTypeService.list(map);
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);

        Integer total = blogTypeService.getCount(map);

        JSONObject result = new JSONObject();
        result.put(KEY_ROWS, jsonArray);
        result.put(KEY_TOTAL, total);

        ResponseUtil.write(response, result);
    }


    @RequestMapping("/save")
    public void save(BlogType blogType, HttpServletResponse response) throws Exception {
        int saveResult;
        if (blogType.getId() == null){
            saveResult = blogTypeService.add(blogType);
        } else {
            saveResult = blogTypeService.update(blogType);
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
    public void delete(@RequestParam(value="ids", required = true) String ids,
                       HttpServletResponse response) throws Exception {
        String[] idArray = ids.split(",");
        JSONObject result = new JSONObject();
        for(String id : idArray){
            // TODO 增加对博客类别下是否存在博客的判断，存在时不允许删除
            blogTypeService.delete(Integer.parseInt(id));
        }
        result.put(KEY_SUCCESS, true);
        ResponseUtil.write(response, result);
    }
}
