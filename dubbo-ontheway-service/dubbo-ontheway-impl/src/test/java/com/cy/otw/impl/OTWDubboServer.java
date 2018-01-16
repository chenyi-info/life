package com.cy.otw.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cy.otw.utils.DateUtil;

public class OTWDubboServer {
 
	private static Log logger = LogFactory.getLog(OTWDubboServer.class);
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	        try {
	            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-dubbo.xml");
	            logger.info("dubbo provider is running, 启动时间:" + DateUtil.date2String(new Date())); 
	            System.in.read();
	        } catch (Exception ex) {
	        	logger.error("dubbo启动异常：" + ex.getMessage(), ex);
	        }
	}
}