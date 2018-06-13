package com.mmc.controller;
import com.mmc.common.Result;
import com.mmc.common.VerifyCodeUtil;
import com.mmc.config.LocalContext;
import com.mmc.model.vo.SysUserVo;
import com.mmc.service.backend.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
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
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by mengchuang on 2018/6/12
 **/
@Controller
@RequestMapping("login")
public class LoginController extends BaseController{
    @Value("${com.mc.name}")
    private String name;
    /**
     * 验证码
     */
    public static final String VALIDATE_CODE = "validateCode";
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        // 已登陆则 跳到首页
        if (subject.isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping("/main")
    @ResponseBody
    public Result loginMain(HttpServletRequest request) {
        Result result = new Result(Boolean.FALSE,Result.INPUT);
        String username = request.getParameter("loginName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        if(session == null){
            result.setMessage("session超时");
            return result;
        }
        if (StringUtils.isEmpty(username)) {
            result.setMessage("账号不能为空");
            return result;
        }
        if (StringUtils.isEmpty(password)) {
            result.setMessage("密码不能为空");
            return result;
        }
        String trueCode =  (String)session.getAttribute(VALIDATE_CODE);
        if(StringUtils.isBlank(trueCode)){
            result.setMessage("验证码超时");
            return result;
        }
        if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
            result.setMessage("验证码错误");
            return result;
        } else {
            Subject user = SecurityUtils.getSubject();
            //UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                user.login(token);
                if (user.isAuthenticated()) {
                    result.addAttribute("url","index");
                    LocalContext.setSysUser(getCurrentUser());
                }
            }catch (IncorrectCredentialsException e) {
                result.setMessage("密码错误");
                return result;
            } catch (ExcessiveAttemptsException e) {
                result.setMessage("登陆失败的次数较多!");
                return result;
            } catch (LockedAccountException e) {
                result.setMessage("账户已被锁定");
                return result;
            } catch (DisabledAccountException e) {
                result.setMessage("账号已被禁用");
                return result;
            } catch (ExpiredCredentialsException e) {
                result.setMessage("账号已过期");
                return result;
            } catch (UnknownAccountException e) {
                result.setMessage("账户不存在");
                return result;
            } catch (UnauthorizedException e) {
                result.setMessage("你未被授权");
                return result;
            } catch (AuthenticationException e) {
                result.setMessage("用户名或者密码错误！");
                return result;
            }
        }
        result.setState(Result.SUCCESS);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    @GetMapping("logout")
    public String logOut(){
        SecurityUtils.getSubject().logout();
        LocalContext.clear();
        return "redirect:/login/toLogin";
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
