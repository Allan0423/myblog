package info.enjoycoding.myblog.controller.admin;

import info.enjoycoding.myblog.model.Link;
import info.enjoycoding.myblog.model.PageBean;
import info.enjoycoding.myblog.service.ILinkService;
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

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private ILinkService linkService;

    /**
     * 查询友链
     *
     * @param page
     * @param rows
     * @param response
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(String page, String rows, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>(8);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Link> linkList = linkService.list(map);
        int total = linkService.getCount();
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }


    /**
     * 添加或更新友链
     *
     * @param link
     * @param response
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(Link link, HttpServletResponse response) throws Exception {
        int saveCount;
        // 没有id，视为新链接记录，使用add方法
        if(link.getId() == null){
            saveCount = linkService.addLink(link);
        }else{
            saveCount = linkService.updateLink(link);
        }
        JSONObject result = new JSONObject();
        if(saveCount > 0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }


    /**
     * 删除友链
     *
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] toBeDeleted = ids.split(",");
        for (String id : toBeDeleted){
            linkService.deleteLink(Integer.parseInt(id));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
