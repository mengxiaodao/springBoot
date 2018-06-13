package com.mmc.controller;
import com.mmc.common.VerifyCodeUtil;
import com.mmc.model.vo.SysUserVo;
import com.mmc.service.backend.SysUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by mengchuang on 2018/6/12
 **/
@Controller
@RequestMapping("login")
public class LoginController {
    @Value("${com.chengang.name}")
    private String name;
    /**
     * 验证码
     */
    public static final String VALIDATE_CODE = "validateCode";
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("getTest")
    public String getTest(Model model){
        model.addAttribute("id", 1);
        return "backend/login";
    }

    @ResponseBody
    @RequestMapping("getString")
    public SysUserVo getString(HttpServletRequest request){
       String result = name;
       SysUserVo sysUserVo = sysUserService.selectByPrimaryKey(37L);
       return sysUserVo;
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(VALIDATE_CODE, verifyCode);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }
}
