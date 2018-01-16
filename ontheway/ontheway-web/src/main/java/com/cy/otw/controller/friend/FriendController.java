package com.cy.otw.controller.friend;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cy.otw.service.friend.FriendService;
import com.cy.otw.vo.FriendVo;

@Controller
@RequestMapping("/friend")
public class FriendController {
	
	@Resource private FriendService friendService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request, FriendVo friendVo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		this.friendService.addFriend(friendVo);
	}

}
