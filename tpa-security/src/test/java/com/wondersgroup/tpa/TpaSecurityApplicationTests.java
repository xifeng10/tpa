package com.wondersgroup.tpa;

import com.wondersgroup.tpa.controller.RoleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TpaSecurityApplicationTests {
    @Autowired
    private RoleController roleController;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void contextLoads() throws Exception {

        RequestBuilder request = null;

        request = get("/system/role/1");
        mvc.perform(request)
                .andExpect(status().isOk());

        request = put("/system/role/1")
                .param("name", "测试终极大师");
        // 4、put修改id为1的user
        mvc.perform(request);

    }

}
