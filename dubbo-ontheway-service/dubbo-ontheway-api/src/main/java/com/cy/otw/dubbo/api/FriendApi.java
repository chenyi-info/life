package com.cy.otw.dubbo.api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.cy.otw.vo.FriendVo;



public interface FriendApi {
	
	public List<FriendVo> findFriendList() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
