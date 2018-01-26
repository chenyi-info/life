package com.cy.otw.service.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cy.otw.redis.utils.RedisSlave;
import com.cy.otw.wechat.utils.WeChatServiceUtils;
import com.cy.otw.wechat.vo.WxConsts;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class WeChatService {
	
	@Resource private RedisSlave redisSlave;
	private WeChatServiceUtils weChatApiUtils;
	/**
	 * 存储微信access token到redis的key
	 */
	private final static String OTW_ACCESSTOKEN = "otw-access_token";
	/**
	 * 避免多个服务向微信发送请求,如果 send_accessToken_request key存在就表示已经有服务向微信发送请求
	 */
	private final static String SEND_ACCESSTOKEN_REQUEST = "send_accessToken_request";
	private final static int EXPIRES_IN = 60 * 5;
	
	public void getWeChantMenu() throws Exception{
		String accessToken = this.getAccessToken();
		Map<String, Object> resultMap = weChatApiUtils.getMenu(accessToken);
	}
	
	public void createWeChantMenu() throws Exception{
		String accessToken = this.getAccessToken();
		JsonArray btnsArr = new JsonArray();
		JsonObject btnObj = new JsonObject();
		btnObj.addProperty("type", WxConsts.BUTTON_VIEW);
		btnObj.addProperty("name", "这是哪个");
		btnObj.addProperty("url", "https://chenyi.tsh365.cn/static/images/sj.mp4");
		btnsArr.add(btnObj);
		String bodyData = "{\"button\":"+btnsArr.toString()+"}";
		Map<String, Object> resultMap = weChatApiUtils.createMenu(accessToken, bodyData);
		System.out.println();
	}
	
	public void deleteWeChantMenu() throws Exception{
		String accessToken = this.getAccessToken();
		Map<String, Object> resultMap = weChatApiUtils.deleteMenu(accessToken);
	}
	
	public String getAccessToken(){
		String accessToken = redisSlave.getString(OTW_ACCESSTOKEN);
		//如果没有access token 或者token 生存时间低于五分钟  并且没有其它服务器向微信发送请求, 那么就获取新的access token
		if((StringUtils.isBlank(accessToken) || redisSlave.TTL(OTW_ACCESSTOKEN) < EXPIRES_IN) ){
			redisSlave.set(SEND_ACCESSTOKEN_REQUEST, SEND_ACCESSTOKEN_REQUEST, -1);
			Map<String, Object> resultMap = weChatApiUtils.getAccessToken();
			accessToken = MapUtils.getString(resultMap, "access_token");
			int expiresIn = MapUtils.getIntValue(resultMap, "expires_in");
			redisSlave.set(OTW_ACCESSTOKEN, accessToken, expiresIn);
			redisSlave.delete(SEND_ACCESSTOKEN_REQUEST);
		}
		return accessToken;
	}
	
	

}
