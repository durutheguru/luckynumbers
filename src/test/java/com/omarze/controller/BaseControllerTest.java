package com.omarze.controller;


import com.omarze.util.JSONUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest {


    protected static String asJsonString(Object obj) {
        return JSONUtil.asJsonString(obj);
    }


}
