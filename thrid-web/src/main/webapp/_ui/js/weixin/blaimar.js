	var remainTime;
	var interval1;

	function countdown(){
		remainTime = remainTime - 1;
		if( remainTime <= 0 ){
			window.clearInterval(interval1);
			$('#verf_btn').removeClass("disabled");
			$('#verf_btn').text('获取验证码');
		} else {
			$('#verf_btn').text('已发送(' + remainTime + ')');
		}
	}
	
	function enableSMSButton(){
		$('#verf_btn').removeClass("disabled");
		$('#verf_btn').text('获取验证码');
	}
	
	function show_error( err_str ){
		$("#err_div").css('display', 'block');
		$("#err_msg").text(err_str);
	}
	
	function hide_error(){
		$("#err_div").css('display', 'none');
		$("#err_msg").text("");
	}