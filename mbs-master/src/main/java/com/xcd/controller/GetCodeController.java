package com.xcd.controller;

import com.xcd.common.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

@Slf4j
@Controller
public class GetCodeController {
    @RequestMapping("/CodeImageServlet")
    public void getCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 在内存中创建图像
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        // 生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(Utils.getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        // 随机产生干扰线
        g.setColor(Utils.getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的验证码，通过下标
        String[] source = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = source[random.nextInt(62)];
            sRand += rand;
            // 将认证码显示到图像中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        // 将验证码存入 session
        log.info("生成的验证码={}", sRand);
        request.getSession().setAttribute("code", sRand);
        request.getSession().setMaxInactiveInterval(120);

        // 释放画笔
        g.dispose();

        // 输出图像到页面
        try (OutputStream out = response.getOutputStream()) {
            // 使用 ImageIO 输出图像
            javax.imageio.ImageIO.write(image, "jpeg", out);
        } catch (Exception e) {
            log.error("验证码图片生成失败", e);
        }
    }
}
