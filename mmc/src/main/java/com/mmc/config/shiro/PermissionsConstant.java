package com.mmc.config.shiro;

/**
 * @author chengang
 * @time 2018-5-25
 */
public class PermissionsConstant {
    /**
     * 系统管理权限 标识
     */
    public static final String SYSTEM_MANAGE = "sys:manage";

    /**
     * 用户修改密码权限
     */
    public static final String USER_CHANGE_PASSWORD = "user:changePassword";


    /**
     * 菜单管理list
     */
    public static final String SYS_MENU = "menu:list";
    /**
     * 菜单管理edit
     */
    public static final String SYS_MENU_EDIT = "menu:edit";
    /**
     * 添加根菜单
     */
    public static final String SYS_MENU_ADDROOT = "menu:addRoot";

    /**
     * 添加子菜单
     */
    public static final String SYS_MENU_ADDCHILD = "menu:addChild";
    /**
     * 菜单管理删除
     */
    public static final String SYS_MENU_DELETE = "menu:delete";
    /**
     * 菜单管理启用禁用
     */
    public static final String SYS_MENU_UPDATESTATUS = "menu:updateStatus";


    /**
     * 角色管理
     */
    public static final String SYS_ROLE = "role:list";
    /**
     * 编辑
     */
    public static final String SYS_ROLE_EDIT = "role:edit";
    /**
     * 新增
     */
    public static final String SYS_ROLE_ADD = "role:add";
    /**
     * 删除
     */
    public static final String SYS_ROLE_DELETE = "role:delete";
    /**
     * 启用禁用
     */
    public static final String SYS_ROLE_UPDATESTATUS = "role:updateStatus";
    /**
     * 设置权限
     */
    public static final String SYS_ROLE_SETPERMISSION = "role:setPermission";


    /**
     * 用户管理list
     */
    public static final String SYS_USER = "user:list";
    /**
     * 编辑
     */
    public static final String SYS_USER_EDIT = "user:edit";
    /**
     * 新增
     */
    public static final String SYS_USER_ADD = "user:add";
    /**
     * 删除
     */
    public static final String SYS_USER_DELETE = "user:delete";
    /**
     * 批量删除
     */
    public static final String SYS_USER_DELETESOME = "user:deleteSome";
    /**
     * 启用禁用
     */
    public static final String SYS_USER_UPDATESTATUS = "user:updateStatus";
    /**
     * 管理员重置用户密码
     */
    public static final String SYS_USER_RESETPASSWORD = "user:resetPassword";

    /**
     * 日志列表
     */
    public static final String SYS_LOG = "log:list";

    /**
     * 语言资源
     */
    public static final String SYS_LANGUAGE = "language:list";
    /**
     * 语言资源添加
     */
    public static final String SYS_LANGUAGE_ADD = "language:add";
    /**
     * 语言资源编辑
     */
    public static final String SYS_LANGUAGE_EDIT = "language:edit";
    /**
     * 语言资源删除
     */
    public static final String SYS_LANGUAGE_DELETE = "language:delete";
    /**
     * 语言资源编辑
     */
    public static final String SYS_LANGUAGE_DELETESOME = "language:deleteSome";
    /**
     * 语言资源导出
     */
    public static final String SYS_LANGUAGE_EXPORT = "language:export";
    /**
     * 语言资源导入
     */
    public static final String SYS_LANGUAGE_IMPORT = "language:import";

    /**
     * 字典检索
     */
    public static final String SYS_DICT = "dict:list";
    /**
     * 字典检索添加
     */
    public static final String SYS_DICT_ADD = "dict:add";
    /**
     * 字典检索添加
     */
    public static final String SYS_DICT_ADDTYPE = "dict:addType";
    /**
     * 字典检索添加type
     */
    public static final String SYS_DICT_EDIT = "dict:edit";
    /**
     * 字典检索编辑type
     */
    public static final String SYS_DICT_EDITTYPE = "dict:editType";
    /**
     * 字典检索删除
     */
    public static final String SYS_DICT_DELETE = "dict:delete";

    /**
     * 单位管理list
     */
    public static final String SYS_UNIT = "unit:list";
    /**
     * 编辑
     */
    public static final String SYS_UNIT_EDIT = "unit:edit";
    /**
     * 新增
     */
    public static final String SYS_UNIT_ADD = "unit:add";
    /**
     * 删除
     */
    public static final String SYS_UNIT_DELETE = "unit:delete";
}
