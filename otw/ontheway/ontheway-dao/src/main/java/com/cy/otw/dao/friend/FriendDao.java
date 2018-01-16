package com.cy.otw.dao.friend;

import org.springframework.stereotype.Repository;

import com.cy.otw.hibernate.utils.HibernateDao;
import com.cy.otw.po.FriendPo;

@Repository
public class FriendDao extends HibernateDao<FriendPo, Long>{

	public void addFriend(FriendPo friendPo) {
		this.save(friendPo);
	}
}
