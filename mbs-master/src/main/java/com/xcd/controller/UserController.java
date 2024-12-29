package com.xcd.controller;

import java.nio.charset.StandardCharsets;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xcd.common.CustomException;
import com.xcd.common.R;
import com.xcd.dto.UserDto;
import com.xcd.pojo.UserInfo;
import com.xcd.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public R<UserInfo> userLogin(@RequestBody UserDto userInfo, HttpServletRequest httpServletRequest) {
        // 从 session 中获取验证码
        String code = (String) httpServletRequest.getSession().getAttribute("code");
        log.info("[登录函数] 用户信息: {}", userInfo);
        log.info("[登录函数] 获取的验证码: {}", code);

        // 验证码匹配
        if (code.equalsIgnoreCase(userInfo.getCode())) {
            log.info("[登录函数] 验证码匹配成功");

            // 获取用户名和原始密码
            String username = userInfo.getUsername();
            String password = userInfo.getPassword();
            log.info("[登录函数] 用户名: {}", username);
            log.info("[登录函数] 原始密码: {}", password);

            // 使用 MD5 加密密码
            String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            log.info("[登录函数] 加密后的密码: {}", encryptedPassword);

            // 查询数据库中是否存在匹配的用户
            LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserInfo::getUsername, username);
            lambdaQueryWrapper.eq(UserInfo::getPassword, encryptedPassword);
            UserInfo one = userInfoService.getOne(lambdaQueryWrapper);

            // 打印查询结果
            if (one != null) {
                log.info("[登录函数] 数据库中匹配的用户信息: {}", one);

                if (one.getStatus() == 1) { // 判断用户状态是否正常
                    log.info("[登录函数] 用户状态正常，允许登录");

                    // 将用户信息存入 session
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("user", one.getUsername());
                    log.info("[登录函数] 登录成功，存入 session: {}", session.getAttribute("user"));

                    return R.success(one); // 返回成功信息
                } else {
                    log.info("[登录函数] 用户账号被锁定，禁止登录");
                    return R.error("账号被锁定，无法登录。请联系管理员！");
                }
            } else {
                log.info("[登录函数] 数据库中没有找到匹配的用户");
                return R.error("账号或密码错误");
            }
        } else {
            log.info("[登录函数] 验证码错误");
            return R.error("验证码错误");
        }
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody UserInfo user) {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getUsername, user.getUsername());
        UserInfo one = userInfoService.getOne(lambdaQueryWrapper);
        if (one != null) {
            throw new CustomException("用户名已存在,无法注册");
        }

        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);

        user.setPassword(password);

        userInfoService.save(user);

        return R.success("注册成功");
    }

    @GetMapping("/checkUsername")
    public R<String> checkUsername(String username) {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getUsername, username);
        UserInfo userInfo = userInfoService.getOne(lambdaQueryWrapper);
        if (userInfo == null) {
            return R.success("用户名可用");
        }
        return R.error("用户名已存在");
    }

    @GetMapping("/page")
    public R<Page<UserInfo>> page(Integer page,Integer pageSize,String name,String username){
        Page<UserInfo> userInfoPage = new Page<>(page,pageSize);

        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),UserInfo::getName,name);
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(username),UserInfo::getUsername,username);


        userInfoService.page(userInfoPage,lambdaQueryWrapper);


        return R.success(userInfoPage);
    }

    @DeleteMapping
    public R<String> delete(Long[] aids){
        userInfoService.deleteArray(aids);
        return R.success("删除成功");

    }

    @PostMapping("/status")
    public  R<String> updateStatus(@RequestBody UserInfo userInfo){

        userInfoService.updateById(userInfo);
        return  R.success("修改状态成功");
    }

    @PostMapping("/edit")
    public R<String> edit(@RequestBody UserInfo userInfo) {
        // 打印接收到的前端用户信息
        System.out.println("[修改函数] 接收到的前端用户信息: " + userInfo);

        // 查询数据库中原始用户信息
        UserInfo originalUserInfo = userInfoService.getById(userInfo.getAid());
        if (originalUserInfo == null) {
            // 如果用户不存在，返回错误信息并打印日志
            System.out.println("[修改函数] 未找到对应的用户信息，用户ID: " + userInfo.getAid());
            return R.error("用户信息不存在");
        }

        // 打印原始用户信息
        System.out.println("[修改函数] 数据库中原始用户信息: " + originalUserInfo);

        // 获取前端传递的密码
        String password = userInfo.getPassword();
        System.out.println("[修改函数] 前端传递的密码: " + password);

        // 判断前端传递的密码是否为占位符
        if ("<原密码不显示>".equals(password)) {
            // 如果密码为占位符 "<原密码不显示>"，保留原始密码
            System.out.println("[修改函数] 检测到密码占位符，保留原始密码: " + originalUserInfo.getPassword());
            userInfo.setPassword(originalUserInfo.getPassword());
        } else {
            // 如果不是占位符，则对新密码进行加密
            System.out.println("[修改函数] 检测到新密码，对新密码进行加密");
            password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            System.out.println("[修改函数] 加密后的新密码: " + password);
            userInfo.setPassword(password);
        }

        // 打印更新后的用户信息
        System.out.println("[修改函数] 更新后的用户信息: " + userInfo);

        // 调用服务层方法更新用户信息
        try {
            userInfoService.updateById(userInfo);
            System.out.println("[修改函数] 用户信息更新成功");
        } catch (Exception e) {
            // 如果更新失败，打印异常信息并返回错误
            System.out.println("[修改函数] 用户信息更新失败: " + e.getMessage());
            return R.error("修改失败");
        }

        // 返回成功的响应信息
        System.out.println("[修改函数] 修改操作完成，返回成功消息");
        return R.success("修改成功");
    }

    @GetMapping("/logout")
    public R<String> logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("user");
        return R.success("退出登录");
    }
}
