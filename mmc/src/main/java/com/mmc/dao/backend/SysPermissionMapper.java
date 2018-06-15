package com.mmc.dao.backend;

import java.util.List;

import com.mmc.model.dto.SysPermissionDto;
import com.mmc.model.vo.SysPermissionVo;
import org.springframework.stereotype.Component;

/**
 * SysPermission
 * @author  mc
 * @version  2018-06-12 16:22:59
 * table: sys_permission
 */
@Component
public interface SysPermissionMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysPermissionDto record);

    int insertSelective(SysPermissionDto record);

    SysPermissionVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermissionDto record);

    int updateByPrimaryKey(SysPermissionDto record);

    /**
     * 查询用户左侧权限列表
     * @param dto
     * @return
     */
    List<SysPermissionVo> selectMenuList(SysPermissionDto dto);
}


