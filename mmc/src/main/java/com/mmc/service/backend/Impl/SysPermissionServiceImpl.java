package com.mmc.service.backend.Impl;

import com.mmc.dao.backend.SysPermissionMapper;
import com.mmc.model.dto.SysPermissionDto;
import com.mmc.model.vo.SysPermissionVo;
import com.mmc.service.backend.SysPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengchuang on 2018/6/13
 **/
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService{
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    /**
     * 查询用户左侧权限列表
     * @param dto
     * @return
     */
    @Override
    public List<SysPermissionVo> selectMenuList(SysPermissionDto dto) {
        //返回菜单列表
        List<SysPermissionVo> allMenuList = new ArrayList<>();
        List<SysPermissionVo> vos = sysPermissionMapper.selectMenuList(dto);
        if (CollectionUtils.isNotEmpty(vos)){
            //循环所有菜单，递归添加子菜单
            for (SysPermissionVo menu : vos) {
                if (menu.getPid() == 0) {
                    allMenuList.add(this.findChild(vos, menu));
                }
            }
        }
        return allMenuList;
    }
    /**
     * 递归设置子菜单
     * @param rootMenus
     * @param menu
     * @return
     */
    private SysPermissionVo findChild(List<SysPermissionVo> rootMenus, SysPermissionVo menu) {
        List<SysPermissionVo> children = new ArrayList<>();
        for (SysPermissionVo child : rootMenus) {
            if (child.getPid().equals(menu.getId())) {
                children.add(this.findChild(rootMenus, child));
            }
        }
        menu.setChildren(children);
        return menu;
    }

}
