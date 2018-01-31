package com.cy.otw.controller.face;

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

import com.cy.otw.service.face.FaceService;

@Controller
@RequestMapping("/face")
public class FaceController {
	
	@Resource private FaceService faceService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
		if(file.isEmpty()){
			return null;
		}
		String base64File = Base64.encodeBase64String(file.getBytes());
		Map map = this.faceService.getFaceDetect(base64File);
		map.put("img", "data:image/png;base64,"+base64File);
		return map;
	}
	
	@RequestMapping(value = "/getFaceDetect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> getFaceDetect(HttpServletRequest request, String baseImg) throws Exception {
		return this.faceService.getFaceDetect(baseImg);
	}

}
