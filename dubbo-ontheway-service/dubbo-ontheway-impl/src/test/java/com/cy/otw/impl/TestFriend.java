package com.cy.otw.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cy.otw.dubbo.api.FriendApi;
import com.cy.otw.vo.FriendVo;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/applicationContext-dubbo.xml"})
public class TestFriend {
	@Resource private FriendApi friendApi;
	@Test
	public void findFriendList() throws Exception {
		List<FriendVo> friendList = friendApi.findFriendList();
		for (FriendVo friendVo : friendList) {
			
		}
		System.out.println();
	}

}
