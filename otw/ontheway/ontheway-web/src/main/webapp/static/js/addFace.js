﻿$(function(){
	var headUpload = function() {
		var maxLength = 1;
		var setHeader = function(object, data, headers) {
			if (document.all) {
				headers['Access-Control-Allow-Origin'] = '*';
				headers['Access-Control-Request-Headers'] = 'content-type';
				headers['Access-Control-Request-Method'] = 'POST';
			}
		}
		WebUploader.create({
			auto: true,
			duplicate:true, // 图片可以重复
			compress:null,
			swf: '../static/js/webuploader-0.1.5/Uploader.swf',
			server: '/face/add.do',
			threads:1,
			pick: {
				id: '#filePicker',
				multiple: false		//是否可以选择多张图片
			},
			accept: {
				title: 'Images',
				extensions: 'jpg,jpeg,bmp,png',
				mimeTypes: 'image/jpg,image/jpeg,image/bmp,image/png'			//弹窗会加快
			},
			fileSingleSizeLimit:5242880,
			onUploadBeforeSend: setHeader,
			onFilesQueued:function(files){
			  if(files.length > maxLength){
				$.messager.alert({
					title: '图片上传提示',
					msg: '<div class="content">最多同时上传'+maxLength+'张图</div>',
					ok: '<i class="i-ok"></i> 确定',
					icon: 'warning'
				});
				this.reset();
			  }
			},
			onUploadError: function(file, reason) {
				if (reason == "http") {
					$.messager.alert({
						title: '图片上传提示',
						msg: '<div class="content">网络异常!</div>',
						ok: '<i class="i-ok"></i> 确定',
						icon: 'warning'
					});
				} else {
					$.messager.alert({
						title: '图片上传提示',
						msg: '<div class="content">不支持的图片,换张图片试试!</div>',
						ok: '<i class="i-ok"></i> 确定',
						icon: 'warning'
					});
				}
				this.removeFile(file);
			},
			onUploadProgress: function(file, percentage) {},
			onUploadSuccess: function(file, response) {
				console.log(response.result);
				var ossImage = response.img;
				$('.friend-head').css({'background-image':'url('+ossImage+')','background-repeat':'no-repeat','background-size':'100% 100%','-moz-background-size':'100% 100%'});
				$('input[name=showImg]').val(ossImage);
				this.removeFile(file);
			},
			onError: function(file, response) {
				switch (file) {
					case "Q_EXCEED_NUM_LIMIT":
						$.messager.alert({
							title: '图片上传提示',
							msg: '<div class="content">最多同时上30张图片</div>',
							ok: '<i class="i-ok"></i> 确定',
							icon: 'warning'
						});
						break;
					case "F_DUPLICATE":
						$.messager.alert({
							title: '图片上传提示',
							msg: '<div class="content">文件重复!</div>',
							ok: '<i class="i-ok"></i> 确定',
							icon: 'warning'
						});
						break;
					case "F_EXCEED_SIZE":
						$.messager.alert({
							title: '图片上传提示',
							msg: '<div class="content">图片大小不超5M!</div>',
							ok: '<i class="i-ok"></i> 确定',
							icon: 'warning'
						});

						break;
					case "Q_TYPE_DENIED":
						$.messager.alert({
							title: '图片上传提示',
							msg: '<div class="content">请上传jpg,jpeg,bmp,png格式图片!</div>',
							ok: '<i class="i-ok"></i> 确定',
							icon: 'warning'
						});
						break;
					default:
						$.messager.alert({
							title: '图片上传提示',
							msg: '<div class="content">上传失败!</div>',
							ok: '<i class="i-ok"></i> 确定',
							icon: 'warning'
						});
						break;
				}
			}

		});
	};
	
	
	var resizeBG = function(){
		$('div.otw_background').css({'width':$(window).width(),'height':$(window).height()});
		$('.otw_background_img').css({'width':$(window).width(),'height':$(window).height()});
	}
	$(window).resize(function() {
		resizeBG();
	});
	
	
	var hiddenLabel = function(){
		$(this).parents('.form_item').find('label').css('display','none');
	}
	var showLabel = function(){
		if($.trim($(this).val()) === '')
			$(this).parents('.form_item').find('label').css('display','');
	}
	var addFriend = function(){
		debugger;
		 var friendData = $("form[name='friend-form']").serialize();
		 $.ajax({
			    type: "POST",
			    dataType: "json",
			    url:"/friend/add",
			    data:friendData,// 要提交表单的ID
			    success: function(msg){
			        alert(msg);
			    }
		});
	}
	var initializeUi = function(){
		$('div.main_container').delegate('input.ui_textinput','focus',hiddenLabel);
		$('div.main_container').delegate('input.ui_textinput','blur',showLabel);
		$("form[name='friend-form']").delegate('button','click',addFriend);
	}
	resizeBG();
	initializeUi();
	headUpload();
});