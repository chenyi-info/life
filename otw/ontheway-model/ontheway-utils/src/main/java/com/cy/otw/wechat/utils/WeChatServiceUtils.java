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
public class WeChatServiceUtils {
	
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
		String sendURL = MessageFormat.format(weChatURLResource.getString("access_token"), appIDkey, appSecretkey);
		String resultInfo = HttpUtils.getContent(sendURL);
		return stringToMap(resultInfo);
	}
	
	/**
	 * 获取微信菜单
	 * @param accessToken
	 * @return
	 * @throws Exception
	 * @author chenyi_info@126.com
	 */
	public static Map getMenu(String accessToken) throws Exception{
		String sendURL = MessageFormat.format(weChatURLResource.getString("menu_get"), accessToken);
		String resultInfo = HttpUtils.getContent(sendURL);
		return stringToMap(resultInfo);
	}
	
	/**
	 * 删除微信菜单
	 * @param accessToken
	 * @return
	 * @throws Exception
	 * @author chenyi_info@126.com
	 */
	public static Map deleteMenu(String accessToken) throws Exception{
		String sendURL = MessageFormat.format(weChatURLResource.getString("menu_delete"), accessToken);
		String resultInfo = HttpUtils.getContent(sendURL);
		return stringToMap(resultInfo);
	}
	
	/**
	 * 创建菜单
	 * @param accessToken accessToken
	 * @param map 创建菜单的参数,参数格式见微信api:https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
	 * @return {"errcode":0,"errmsg":"ok"}
	 * @throws Exception
	 * @author chenyi_info@126.com
	 */
	public static Map createMenu(String accessToken, Map<String, Object> map) throws Exception{
		String sendURL = MessageFormat.format(weChatURLResource.getString("menu_create"), accessToken);
		String resultInfo = HttpUtils.sendPost(sendURL, map);
		return stringToMap(resultInfo);
	}
	
	/**
	 * 将微信返回的String结果组装成Map返回出去
	 * @param resultInfo
	 * @return
	 * @author chenyi@dtds.com.cn
	 */
	public static Map stringToMap(String resultInfo){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap =  new Gson().fromJson(resultInfo, resultMap.getClass());
		return resultMap;
	}
}