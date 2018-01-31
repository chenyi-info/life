package com.cy.otw.controller.face;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.otw.service.friend.FriendService;
import com.cy.otw.utils.HttpUtils;

@Controller
@RequestMapping("/face")
public class FaceController {
	
	@Resource private FriendService friendService;
	private final String APIKEY = "uKy3MlNPSCbMun17rz294aCgXB4Ry2xW";
	private final String APISECRET = "fDQbEms2zDqvcWMe_lY2QzxwCzCkmoYG";
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
		if(file.isEmpty()){
			return null;
		}
		String base64File = Base64.encodeBase64String(file.getBytes());
		int return_landmark = 2;
		String return_attributes = "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus";
		String faceUrl = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		Map<String,Object> httpParams = new HashMap<String, Object>();
		httpParams.put("api_key", APIKEY);
		httpParams.put("api_secret", APISECRET);
		httpParams.put("return_landmark", return_landmark);
		httpParams.put("return_attributes", return_attributes);
		httpParams.put("image_base64", base64File);
		String resultParams =  HttpUtils.sendPost(faceUrl, httpParams);
		Map map = new HashMap<String, String>();
		map.put("result", resultParams);
		map.put("img", "data:image/png;base64,"+base64File);
		return map;
	}
	
	@RequestMapping(value = "/getFaceDetect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> getFaceDetect(HttpServletRequest request, String baseImg) throws Exception {
		baseImg = baseImg.split(";base64,").length > 0 ? baseImg.split(";base64,")[1] : baseImg;
		int return_landmark = 2;
		String return_attributes = "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus";
		String faceUrl = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		Map<String,Object> httpParams = new HashMap<String, Object>();
		httpParams.put("api_key", APIKEY);
		httpParams.put("api_secret", APISECRET);
		httpParams.put("return_landmark", return_landmark);
		httpParams.put("return_attributes", return_attributes);
		httpParams.put("image_base64", baseImg);
		String resultParams =  HttpUtils.sendPost(faceUrl, httpParams);
		Map map = new HashMap<String, String>();
		map.put("result", resultParams);
		map.put("img", "data:image/png;base64," + baseImg);
		return map;
	}

}
