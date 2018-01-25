package org.ontheway.service;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cy.otw.dao.friend.FriendDao;
import com.cy.otw.service.wechat.WeChatService;
import com.cy.otw.wechat.utils.WeChatServiceUtils;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})
public class WeChatTest {

	@Resource private WeChatService weChatService;
	
	@Test
	public void getAccessTokenTest(){
		try {
			weChatService.deleteWeChantMenu();
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
	}

}
