package com.mmc.dao.backend;

import java.util.List;

import com.mmc.model.dto.SysUserDto;
import com.mmc.model.vo.SysUserVo;
import org.springframework.stereotype.Component;

/**
 * SysUser
 * @author  mc
 * @version  2018-06-12 16:21:57
 * table: sys_user
 */
@Component
public interface SysUserMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysUserDto record);

    int insertSelective(SysUserDto record);

    SysUserVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserDto record);

    int updateByPrimaryKey(SysUserDto record);

    /**
     * 根据登录账号，查询用户
     * @param record
     * @return
     */
    SysUserVo getSysUserByLoginName(SysUserDto record);
}


