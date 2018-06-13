package com.mmc.config;


import com.mmc.model.SysUser;

/**
 * @author chengang
 * @time 2018-5-5
 */
public class LocalContext {

    //ThreadLocal的应用场合，最适合的是按线程多实例（每个线程对应一个实例）的对象的访问，并且这个对象很多地方都要用到
    private static ThreadLocal<SysUser> messageThreadLocal = new ThreadLocal<SysUser>();

    public static SysUser getSysUser(){
        return messageThreadLocal.get();
    }

    public static void setSysUser(SysUser value){
         messageThreadLocal.set(value);
    }

    public static Long getSysUserId(){
        return messageThreadLocal.get().getId();
    }
    public static void clear(){
        messageThreadLocal.remove();
    }

}
