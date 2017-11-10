$(document).ready(function() {
	var max = 0;
	$(".checkedall").click(function() {
		$(".check").prop("checked", this.checked);
		max = $(".check:checked").length;
	});
	$(".check").click(function() {
		if (!this.checked) {
			$(".checkedall").prop("checked", false);
		} else {
			if ($(".check:checked").length == max) {
				$(".checkedall").prop("checked", true);
			}
		}
	});
	$("#deletesome").click(function() {
		var index = 0;
		for (index = 0; index < $(".check:checked").length; index++) {
			var che = $(".check:checked")[index];
			$.ajax({
				url : "DeleteSomeServlet",
				type : "post",
				data : {
					id : che.id
				},
				success : function(data) {
					if ("success" == data) {
						window.location.href = "ShopListServlet";
					}
				}
			});
		}
	});
});

