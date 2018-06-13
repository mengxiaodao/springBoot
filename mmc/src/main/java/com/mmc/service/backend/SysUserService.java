package com.mmc.service.backend;

import com.mmc.model.vo.SysUserVo;

/**
 * Created by mengchuang on 2018/6/12
 **/
public interface SysUserService {
    /**
     * 通过id获取后台用户
     * @param id
     * @return
     */
    SysUserVo selectByPrimaryKey(Long id);
}
