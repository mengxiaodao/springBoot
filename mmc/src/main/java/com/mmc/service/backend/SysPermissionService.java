package com.mmc.service.backend;

import com.mmc.model.dto.SysPermissionDto;
import com.mmc.model.vo.SysPermissionVo;
import java.util.List;

/**
 * Created by mengchuang on 2018/6/13
 **/
public interface SysPermissionService {
    /**
     * 查询用户左侧权限列表
     * @param dto
     * @return
     */
    List<SysPermissionVo> selectMenuList(SysPermissionDto dto);
}
