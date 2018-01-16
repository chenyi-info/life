$(function(){
	var headImg = [];
	headImg[0] = '/static/images/image01.jpg'
	headImg[1] = '/static/images/image02.jpg'
	headImg[2] = '/static/images/image03.jpg'
	headImg[3] = '/static/images/image04.jpg'
	var activeHead = function(){
		$('.headChoose li').removeClass('active');
		$(this).addClass('active');
		var nowHead = headImg[$(this).index()];
		$('.head').css({'background':'url("'+nowHead+'") center center'});
	}
	var resizeBG = function(){
		$('div.head').css({'width':$(window).width()-17,'height':$(window).height()-17});
		$('.otw_background_img').css({'width':$(window).width(),'height':$(window).height()});
	}
	$(window).resize(function() {
		resizeBG();
	});
	var initializeHead = function(){
		var activeIndex = $('.headChoose ul').find('li.active');
		var index  = $('.headChoose ul li').index(activeIndex);
		if(index+1>=$('.headChoose ul li').length)
			index = 0;
		else
			index += 1
		$('.headChoose li').removeClass('active');
		$($('.headChoose ul li').eq(index)).addClass('active');
		var nowHead = headImg[index];
		$('.head').css({'background':'url("'+nowHead+'") center center'});
	}
	var flipShow = function(){
		$(this).find('.motto').show();
		$(this).find('.motto').flip({
			direction: 'rl',
			speed:300
		})
	}
	var flipHide = function(){
		$(this).find('.motto').hide();
	}
	var initializeUi = function(){
		$('.headChoose').delegate('li','click',activeHead);
		$('.friends-main').delegate('.friend-head','hover',flipShow);
		$('.friends-main').delegate('.friend-head','mouseout',flipHide);
		$('.motto').hide();
	}
	resizeBG();
	initializeUi();
	setInterval(initializeHead, 10000);
})