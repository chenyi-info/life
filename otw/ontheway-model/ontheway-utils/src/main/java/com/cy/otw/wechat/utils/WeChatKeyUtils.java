package com.cy.otw.wechat.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.cy.otw.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 获取微信key工具类
 * @author chenyi_info@126.com
 *
 */
public class WeChatKeyUtils {
	
	private static ResourceBundle weChatURLResource = ResourceBundle.getBundle("wechat_url");
	private static ResourceBundle weChatKeyResource = ResourceBundle.getBundle("wechat_key");//wechat_key为属性文件名，放在src下，直接用wechat_key即可    
	
	/**
	 * 获取微信 accessToken
	 * @return accessToken Map key:access_token,expires_in
	 * @author chenyi_info@126.com
	 */
	public static Map getAccessToken(){
		String appIDkey = weChatKeyResource.getString("AppID");
		String appSecretkey = weChatKeyResource.getString("AppSecret");
		String accessTokenURL = MessageFormat.format(weChatURLResource.getString("access_token"), appIDkey, appSecretkey);
		String resultInfo = HttpUtils.getContent(accessTokenURL);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap =  new Gson().fromJson(resultInfo, resultMap.getClass());
		return resultMap;
	}
}