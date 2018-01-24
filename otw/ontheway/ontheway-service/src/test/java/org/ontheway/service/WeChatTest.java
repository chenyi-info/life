package org.ontheway.service;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cy.otw.dao.friend.FriendDao;
import com.cy.otw.service.wechat.WeChatService;
import com.cy.otw.wechat.utils.WeChatKeyUtils;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})
public class WeChatTest {

	@Resource private WeChatService weChatService;
	
	@Test
	public void getAccessTokenTest(){
		weChatService.createWeChantMenu();
		System.out.println("");
	}

}
