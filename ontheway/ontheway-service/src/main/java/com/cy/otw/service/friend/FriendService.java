package com.cy.otw.service.friend;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import com.cy.otw.dao.friend.FriendDao;
import com.cy.otw.po.FriendPo;
import com.cy.otw.vo.FriendVo;

@Service
public class FriendService {
	
	@Resource private FriendDao friendDao;
	public FriendService(){
		System.out.println();
	}
	
	public void addFriend(FriendVo friendVo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FriendPo friendPo = new FriendPo();
		PropertyUtils.copyProperties(friendPo, friendVo);
		
		this.friendDao.addFriend(friendPo);
	}

}
