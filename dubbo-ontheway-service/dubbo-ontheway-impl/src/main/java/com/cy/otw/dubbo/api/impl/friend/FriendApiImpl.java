package com.cy.otw.dubbo.api.impl.friend;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cy.otw.dubbo.api.FriendApi;
import com.cy.otw.dubbo.dao.friend.FriendDao;
import com.cy.otw.po.FriendPo;
import com.cy.otw.vo.FriendVo;

@SuppressWarnings({ "all" })
@Service
public class FriendApiImpl  implements FriendApi {
	public static final Logger logger=Logger.getLogger(FriendApiImpl.class);
	@Resource private FriendDao friendDao;

	public List<FriendVo> findFriendList() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<FriendVo> friendList = new ArrayList<FriendVo>();
		List<FriendPo> friendPoList = this.friendDao.findFriendList();
		if(CollectionUtils.isNotEmpty(friendPoList)){
			for (FriendPo friendPo : friendPoList) {
				FriendVo friendVo = new FriendVo();
				PropertyUtils.copyProperties(friendVo, friendPo);
				friendList.add(friendVo);
			}
		}
		return friendList;
	}
	
}
