$(document).ready(function() {
	$("#changecode").click(function() {
		var time = new Date();
		$("#img").attr("src","CheckImgServlet?time="+time.toLocaleString());
	});
	$("#email").blur(function() {
		$("#error_email").show();
		if($("#email").val()==""){
			$("#error_email").text("邮箱不能为空");
			$("#error_email").css({color:"red"});
			$("#error_email").addClass("error");
		}
		else{
			var tag = /^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9]+)+$/;
			if(tag.test($("#email").val())){
				$("#error_email").hide();
				$("#error_email").removeClass("error");
			}
			else{
				$("#error_email").text("邮箱格式不正确");
				$("#error_email").css({color:"red"});
				$("#error_email").addClass("error");
			}
		}
	});
	$("#email").focus(function() {
		$("#error_email").hide();
		$("#error_email").removeClass("error");
	});
	$("#username").blur(function() {
		$("#error_username").show();
		if($("#username").val()==""){
			$("#error_username").text("用户名不能为空");
			$("#error_username").css({color:"red"});
			$("#error_username").addClass("error");
		}
		else{
			var tag = /^[A-Za-z0-9_-]{6,}$/;
			if(tag.test($("#username").val())){
				$.ajax({
					url : "UsernameJudgeServlet",
					type : "post",
					data : {
						username : $("#username").val()
					},
					success : function(data) {
						if("success" == data){
							$("#error_username").hide();
							$("#error_username").removeClass("error");
						}
						else{
							$("#error_username").text("用户名已存在");
							$("#error_username").css({color:"red"});
							$("#error_username").addClass("error");
						}
					}
				});	
			}
			else{
				$("#error_username").text("用户名格式不正确");
				$("#error_username").css({color:"red"});
				$("#error_username").addClass("error");
			}
		}
	});
	$("#username").focus(function() {
		$("#error_username").hide();
		$("#error_username").removeClass("error");
	});
	$("#password").blur(function() {
		$("#error_password").show();
		if($("#password").val()==""){
			$("#error_password").text("密码不能为空");
			$("#error_password").css({color:"red"});
			$("#error_password").addClass("error");
		}
		else{
			var tag = /^[A-Za-z0-9._-]{6,}$/;
			if(tag.test($("#password").val())){
				$("#error_password").hide();
				$("#error_password").removeClass("error");
			}
			else{
				$("#error_password").text("密码格式不正确");
				$("#error_password").css({color:"red"});
				$("#error_password").addClass("error");
			}
		}
	});
	$("#password").focus(function() {
		$("#error_password").hide();
		$("#error_password").removeClass("error");
	});
	$("#repassword").blur(function() {
		$("#error_repassword").show();
		if($("#repassword").val()==""){
			if($("#password").val()!=""){
				$("#error_repassword").text("密码不一致");
				$("#error_repassword").css({color:"red"});
				$("#error_repassword").addClass("error");
			}
			else{
				$("#error_repassword").hide();
				$("#error_repassword").removeClass("error");
			}
		}
		else{
			if($("#repassword").val() != $("#password").val()){
				$("#error_repassword").text("密码不一致");
				$("#error_repassword").css({color:"red"});
				$("#error_repassword").addClass("error");
			}
			else{
				$("#error_repassword").hide();
				$("#error_repassword").removeClass("error");
			}
		}
	});
	$("#repassword").focus(function() {
		$("#error_repassword").hide();
		$("#error_repassword").removeClass("error");
	});
	$("#code").blur(function() {
		$("#error_code").show();
		if($("#code").val()==""){
			$("#error_code").text("验证码不能为空");
			$("#error_code").css({color:"red"});
			$("#error_code").addClass("error");
		}
		else{
			$.ajax({
				url : "CodeCheckServlet",
				type : "post",
				data : {
					code : $("#code").val()
				},
				success : function(data) {
					if("success" == data){
						$("#error_code").hide();
						$("#error_code").removeClass("error");
					}
					else{
						$("#error_code").text("验证码错误");
						$("#error_code").css({color:"red"});
						$("#error_code").addClass("error");
					}
				}
			});
		}
	});
	$("#code").focus(function() {
		$("#error_code").hide();
		$("#error_code").removeClass("error");
	});
	$("#submit").click(function() {
		if($("#email").val()==""){
			$("#error_email").text("邮箱不能为空");
			$("#error_email").css({color:"red"});
			$("#error_email").addClass("error");
		}
		else if($("#username").val()==""){
			$("#error_username").text("用户名不能为空");
			$("#error_username").css({color:"red"});
			$("#error_username").addClass("error");
		}
		else if($("#password").val()==""){
			$("#error_password").text("密码不能为空");
			$("#error_password").css({color:"red"});
			$("#error_password").addClass("error");
		}
		else if($("#repassword").val()==""){
			$("#error_repassword").text("密码不一致");
			$("#error_repassword").css({color:"red"});
			$("#error_repassword").addClass("error");
		}
		else if($("#code").val()==""){
			$("#error_code").text("验证码不能为空");
			$("#error_code").css({color:"red"});
			$("#error_code").addClass("error");
		}
		else{
			if($("td .error").length == 0){
				$.ajax({
					url : "UserRegisterServlet",
					type : "post",
					data : {
						email : $("#email").val(),
						username : $("#username").val(),
						password : $("#password").val(),
						gender : $("#gender").val(),
						telephone : $("#telephone").val(),
						introduce : $("#introduce").val()
					},
					success : function(data) {
						if("success" == data){
							window.location.href = "registersuccess.jsp?username="+$("#username").val();
						}
					}
				});
			}
		}
	});
});