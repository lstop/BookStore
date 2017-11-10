$(document).ready(function(){
	//alert($(this).attr("id"));
	$(".del").click(function(){	
		$.ajax({
			url : "CartSnumServlet",
			type : "post",
			data : {
				str : $(this).attr("id")
			},
			success : function(data){
				if("success" == data){
					window.location.href="CartListServlet?uid="+$(".del").attr("id");
				}
			}
		});
	});
	$(".cou").click(function(){
		$.ajax({
			url : "CartSnumServlet",
			type : "post",
			data : {
				str : $(this).attr("id")
			},
			success : function(data){
				if("success" == data){
					window.location.href="CartListServlet?uid="+$(".del").attr("id");
				}
			}
		});
	});
	$(".snum").blur(function(){
		$.ajax({
			url : "SnumBlurServlet",
			type : "post",
			data : {
				snum : $(this).val(),
				str : $(this).attr("id")
			},
			success : function(data){
				if("success" == data){
					window.location.href="CartListServlet?uid="+$(".del").attr("id");
				}
			}
		});
	});
});