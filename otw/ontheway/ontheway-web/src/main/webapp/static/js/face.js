$(function(){
	var dataModel = {'url':location.href};
	
	var initWxConfig = function(){
		$.ajax({
			url:'/wechat/config',
			type:'POST',
			data:dataModel,
			success:function(data){
				try {
					wx.config({
						debug: false, // 是否开启调试模式
						appId: data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
						timestamp: data.timestamp, // 必填，生成签名的时间戳
						nonceStr: data.nonceStr, // 必填，生成签名的随机串
						signature: data.signature, // 必填，签名，见附录1
						jsApiList: [ // 必填，需要使用的JS接口列表
							'checkJsApi',
							'onMenuShareTimeline',
							'onMenuShareAppMessage',
							'onMenuShareQQ',
							'onMenuShareWeibo',
							'onMenuShareQZone',
							'hideMenuItems',
							'showMenuItems',
							'hideAllNonBaseMenuItem',
							'showAllNonBaseMenuItem',
							'translateVoice',
							'startRecord',
							'stopRecord',
							'onVoiceRecordEnd',
							'playVoice',
							'onVoicePlayEnd',
							'pauseVoice',
							'stopVoice',
							'uploadVoice',
							'downloadVoice',
							'chooseImage',
							'previewImage',
							'uploadImage',
							'downloadImage',
							'getNetworkType',
							'openLocation',
							'getLocation',
							'hideOptionMenu',
							'showOptionMenu',
							'closeWindow',
							'scanQRCode',
							'chooseWXPay',
							'openProductSpecificView',
							'addCard',
							'chooseCard',
							'openCard'
						]
					});

					wx.ready(function() {
						typeof(fn) == "function" && fn(wx);
					});

					wx.error(function(res) {
						typeof(errorFn) == "function" && errorFn(wx, res);
						alert('微信JSSDK配置失败：'+res.errMsg);
					});
				} catch(e) {
					alert('微信JSSDK对象获取失败');
				}
			}
		});
	}
	
	var genderEnum = {'Male':'男性', 'Female':'女性'}
	var emotionEnum = {'anger':'愤怒', 'disgust':'厌恶', 'fear':'恐惧', 'happiness':'高兴', 'neutral':'平静', 'sadness':'伤心', 'surprise':'惊讶'}
	
	var getMaxEmotion = function(emotionData){
		var score = 0;
		var emotionKey = "";
		for(var k in emotionData){
			if(score <= emotionData[k]){
				score = emotionData[k];
				emotionKey = k;
			}
		}
		return emotionEnum[emotionKey];
	}
	
	var showFaceDetect = function(data){
		var faceData = JSON.parse(data);
		var dataModel = {}
		dataModel.age = faceData.faces[0].attributes.age.value;
		dataModel.gender = genderEnum[faceData.faces[0].attributes.gender.value];
		dataModel.emotion = getMaxEmotion(faceData.faces[0].attributes.emotion);
		$('div.g-scrollview').append(Mustache.render($('#face-detect-template').html(), dataModel));
		$("div.face-content").animate({height:"100%"});
	}
	
	var faceInfo = function(baseImg){
		var dataModel = {'baseImg':baseImg};
		$.ajax({
			url:'/face/getFaceDetect',
			type:'POST',
			data:dataModel,
			success:function(data){
				showFaceDetect(data.result);
			}
		});
	}
	
	var chooseImage = function(){
		wx.chooseImage({
			count: 1, // 默认9
			sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			success: function (res) {
				var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				$('div.g-scrollview').html('<img src="'+localIds[0]+'" />');
				 wx.getLocalImgData({
				      localId: localIds[0],
				      success: function (res) {
				        var localData = res.localData;
				        faceInfo(localData);
				      }
				 });
			}
		});
	}
	var initUI = function(){
		$('footer.m-tabbar').delegate('a.btn-desk', 'click', chooseImage);
	}
	initWxConfig();
	initUI();
});