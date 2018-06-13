package com.mmc.config.shiro;

import com.mmc.common.util.Encodes;
import com.mmc.model.SysUser;
import com.mmc.model.dto.SysUserDto;
import com.mmc.model.vo.SysUserVo;
import com.mmc.service.backend.SysPermissionService;
import com.mmc.service.backend.SysRoleService;
import com.mmc.service.backend.SysUserRoleService;
import com.mmc.service.backend.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mc
 * @time 2018-5-24
 */
@Component(value = "authRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser)principalCollection.getPrimaryPrincipal();

        /*//1.根据用户id查找用户所对应的角色
        List<SysUserRoleVo> sysUserRoleVoList = sysUserRoleService.getUserRoleInfoByUserId(sysUser.getId());
        //用于保存角色的key
        Set<String> userRoleKeySet = new HashSet<>();
        Set<String> userPermissionSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(sysUserRoleVoList)){
            List<Long> roles = CommonUtil.getValueList(sysUserRoleVoList,"roleId");
            if (CollectionUtils.isNotEmpty(roles)){
                //查找所有角色的信息
                List<SysRoleVo> sysRoleVoList = sysRoleService.getRoleListByRoleIds(roles);
                if (CollectionUtils.isNotEmpty(sysRoleVoList)){
                    for (SysRoleVo sysRoleVo:sysRoleVoList){
                        userRoleKeySet.add(sysRoleVo.getRoleKey());
                    }
                }

                SysPermissionDto sysPermissionDto = new SysPermissionDto();
                sysPermissionDto.setUserId(sysUser.getId());
                //查询用户所有权限list
                List<SysPermissionVo> sysPermissionVoList = sysPermissionService.getUserPermissionList(sysPermissionDto);
                if (CollectionUtils.isNotEmpty(sysPermissionVoList)){
                    for (SysPermissionVo sysPermissionVo:sysPermissionVoList){
                        userPermissionSet.add(sysPermissionVo.getPermission());
                    }
                }
            }
        }
        authorizationInfo.addRoles(userRoleKeySet);
        authorizationInfo.addStringPermissions(userPermissionSet);*/

        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        SysUserDto userDto = new SysUserDto();
        userDto.setLoginName(username);
        final SysUserVo authentication = sysUserService.getSysUserByLoginName(userDto);
        if (authentication == null) {
            throw new AuthenticationException("用户名或者密码错误");
        }
        byte[] salt = Encodes.decodeHex(authentication.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new SysUser(authentication.getId(),authentication.getLoginName(),
                authentication.getNameZh(),authentication.getIcon(),authentication.getEmail()),authentication.getPassword(),   ByteSource.Util.bytes(salt),getName());
        return authenticationInfo;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Encodes.SHA1);
        matcher.setHashIterations(Encodes.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }
}
