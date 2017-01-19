package com.wondersgroup.tpa;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.tpa.model.TpaHospital;
import com.wondersgroup.tpa.service.IHospitalService;
import com.wondersgroup.util.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TpaAgricultureWebApplicationTests {
	@Autowired
	private IHospitalService hospitalService;

	@Test
	public void contextLoads() {
		Page<TpaHospital> page = hospitalService.queryAllByPage(new Page<>());

		System.out.println(page);
		System.out.println(JSON.toJSONString(page));
	}

}
