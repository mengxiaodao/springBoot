package com.mmc.controller.sys;

import com.mmc.common.Result;
import com.mmc.controller.BaseController;
import com.mmc.model.dto.SysPermissionDto;
import com.mmc.model.vo.SysPermissionVo;
import com.mmc.service.backend.SysPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mengchuang on 2018/6/15
 **/
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {
    @Resource
    private SysPermissionService sysPermissionService;
    /**
     * 跳转菜单列表
     * @return
     */
    @GetMapping("list")
    public String list(HttpServletRequest request, Model model) {
        return "sys/menu/list";
    }

    /**
     * 获取所有未删除菜单权限，包含按钮功能子菜单
     * @return
     */
    @ResponseBody
    @PostMapping("treeList")
    public Result treeList() {
        Result result = new Result(true);
        SysPermissionDto dto = new SysPermissionDto();
        List<SysPermissionVo> menuList = sysPermissionService.selectAllMenuList(dto);
        result.addAttribute("menuList", menuList);
        return result;
    }
}
