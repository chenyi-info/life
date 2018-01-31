package com.cy.otw.service.face;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cy.otw.face.utils.FaceServiceUtils;

@Service
public class FaceService {
	
	private FaceServiceUtils faceServiceUtils;
	
	/**
	 * getFaceDetect
	 * @throws Exception
	 * @author chenyi_info@126.com
	 */
	public Map<String, String> getFaceDetect(String baseImg) throws Exception{
		return this.faceServiceUtils.faceDetect(baseImg);
	}

	

}
