package com.mmc.controller.sys;

import com.mmc.common.Result;
import com.mmc.controller.BaseController;
import com.mmc.model.SysUser;
import com.mmc.model.dto.SysPermissionDto;
import com.mmc.model.vo.SysPermissionVo;
import com.mmc.service.backend.SysPermissionService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author mc
 * @time 2018-5-24
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
    @Resource
    private SysPermissionService sysPermissionService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /***
     * 获得用户左侧菜单列表
     * @return
     */
    @RequestMapping("/selectMenuList")
    @ResponseBody
    public Result getUserMenuList(HttpServletRequest request){
        Result result = new Result(Boolean.TRUE);
        SysUser sysUser = getCurrentUser();
        SysPermissionDto dto = new SysPermissionDto();
        dto.setUserId(sysUser.getId());
        //获取菜单列表
        List<SysPermissionVo> vos = sysPermissionService.selectMenuList(dto);
        result.addAttribute("menuList",vos);
        return result;
    }


}
