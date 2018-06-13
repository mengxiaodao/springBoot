package com.mmc.controller;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mmc.model.SysUser;
import com.mmc.model.vo.SysPermissionVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by mc on 2018/5/23.
 */
@Controller
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String indexPage(Model model) {
        //防止空指针建议使用 Optional<VO>
        SysUser userVo = getCurrentUser();
        model.addAttribute("currentUser",userVo);
        return "index";
    }

    @RequestMapping("/main")
    public String mainPage(Model model){
        Optional<List<SysPermissionVo>> mainMenu = getMainMenuList();
        model.addAttribute("mainMenu",mainMenu.get());
        return "main";
    }

    private Optional<List<SysPermissionVo>> getMainMenuList(){
        List<SysPermissionVo> list = Lists.newArrayList();
        SysPermissionVo vo = new SysPermissionVo();
        vo.setIcon("&#xe613;");
        vo.setUrl("/xxx/xxx");
        vo.setName("系统用户数");
        vo.setDataCount(100);
        list.add(vo);

        SysPermissionVo vo1 = new SysPermissionVo();
        vo1.setIcon("&#xe6af;");
        vo1.setUrl("/xxx/xxx");
        vo1.setName("待审核询盘数");
        vo1.setDataCount(100);
        list.add(vo1);


        SysPermissionVo vo2 = new SysPermissionVo();
        vo2.setIcon("&#xe6c6;");
        vo2.setUrl("/xxx/xxx");
        vo2.setName("待审核商品(基础)");
        vo2.setDataCount(100);
        list.add(vo2);

        SysPermissionVo vo3 = new SysPermissionVo();
        vo1.setIcon("&#xe6c6;");
        vo1.setUrl("/xxx/xxx");
        vo1.setName("待审核商品(语言)");
        vo1.setDataCount(100);
        list.add(vo1);

        SysPermissionVo vo4 = new SysPermissionVo();
        vo4.setIcon("&#xe65e;");
        vo4.setUrl("/xxx/xxx");
        vo4.setName("今日订单数");
        vo4.setDataCount(100);
        list.add(vo4);

        return Optional.fromNullable(list);
    }
}
