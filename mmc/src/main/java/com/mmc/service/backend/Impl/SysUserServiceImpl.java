package com.mmc.service.backend.Impl;

import com.mmc.dao.backend.SysUserMapper;
import com.mmc.model.vo.SysUserVo;
import com.mmc.service.backend.SysUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by mengchuang on 2018/6/12
 **/
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{
    @Resource
    private SysUserMapper sysUserMapper;
    /**
     * 通过id获取后台用户
     * @param id
     * @return
     */
    @Override
    public SysUserVo selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
