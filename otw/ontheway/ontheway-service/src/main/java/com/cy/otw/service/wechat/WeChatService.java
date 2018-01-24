package com.cy.otw.service.wechat;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cy.otw.redis.utils.RedisSlave;
import com.cy.otw.wechat.utils.WeChatKeyUtils;

@Service
public class WeChatService {
	
	@Resource private RedisSlave redisSlave;
	@Resource private WeChatKeyUtils weChatKeyUtils;
	private final static String OTW_ACCESSTOKEN = "otw-access_token";
	private final static int EXPIRES_IN = 60 * 5;
	
	public void createWeChantMenu(){
		redisSlave.set("af", "af1", 30000);
		String af = redisSlave.getString("af");
		System.out.println("");
	}
	
	public String getAccessToken(){
		String accessToken = redisSlave.getString(OTW_ACCESSTOKEN);
		if(StringUtils.isBlank(accessToken)){
			Map<String, Object> resultMap = weChatKeyUtils.getAccessToken();
			accessToken = MapUtils.getString(resultMap, "access_token");
			int expiresIn = MapUtils.getIntValue(resultMap, "expires_in");
			redisSlave.set(OTW_ACCESSTOKEN, accessToken, expiresIn);
		}
		return accessToken;
	}
	
	

}
