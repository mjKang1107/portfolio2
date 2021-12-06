function bookmark(user_num, content_num, type){
	
		$.ajax({
			url: "./addBookmark.bm",
			type: "GET",
			async: true,
			data: {user_num: user_num,content_num: content_num, type: type},
			contentType : "charset=UTF-8",
			success: function(data){
				if($('#bmox').val() == "☆"){
					$('#bmox').val("★");
				}else if($('#bmox').val() == "★"){
					$('#bmox').val("☆");
				}
				
				if($("#bm_img").attr("src") == "./imgbm/bookmarko.png"){
					$("#bm_img").attr("src", "./imgbm/bookmarkx.png");
				}else if($("#bm_img").attr("src") == "./imgbm/bookmarkx.png"){
					$("#bm_img").attr("src", "./imgbm/bookmarko.png");
				}
				
			},
			error: function(){
				alert('통신실패!!');
			}
			
		})
	}