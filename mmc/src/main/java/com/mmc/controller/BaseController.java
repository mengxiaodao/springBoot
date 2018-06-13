package com.mmc.controller;
import com.mmc.model.SysUser;
import com.mmc.service.backend.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
/**
 * @author chengang
 * @time 2018-5-5
 */
@Controller
public class BaseController {

    @Resource
    private SysUserService sysUserService;

    public SysUser getCurrentUser() {
        SysUser userVo = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(userVo == null) {
            return null;
        }
        userVo = sysUserService.selectByPrimaryKey(userVo.getId());
        return userVo;
    }

}
