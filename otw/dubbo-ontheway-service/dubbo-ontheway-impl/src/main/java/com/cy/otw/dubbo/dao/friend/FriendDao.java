package com.cy.otw.dubbo.dao.friend;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cy.otw.hibernate.utils.HibernateDao;
import com.cy.otw.po.FriendPo;

@Repository
public class FriendDao extends HibernateDao<FriendPo, Long>{

	public List<FriendPo> findFriendList() {
		String hql = "from FriendPo order by id desc";
		List<FriendPo> friendList = this.find(hql);
		return friendList;
	}
	
}
