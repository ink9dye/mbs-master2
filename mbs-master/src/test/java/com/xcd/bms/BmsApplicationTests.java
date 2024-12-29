package com.xcd.bms;

import com.xcd.pojo.AdminInfo;
import com.xcd.pojo.BookTypeInfo;
import com.xcd.service.AdminInfoService;
import com.xcd.service.BookTypeInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class BmsApplicationTests {
    @Autowired
    private AdminInfoService adminInfoService;

//    @Test
//    void contextLoads() {
//        List<BookTypeInfo> list = bookTypeInfoServie.list();
//        System.out.println(list.toString());
//
//    }
    @Test
    void contextLoads1() {
//        AdminInfo adminInfo = new AdminInfo();
//        String password = DigestUtils.md5DigestAsHex("root".getBytes());
//        adminInfo.setOperatorName("root");
//        adminInfo.setOperatorPass(password);
//        adminInfoService.save(adminInfo);

    }

}
