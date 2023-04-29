 

function allCheck() {
	
	var obj = document.getElementsByName("check");
	
	if (obj[0].checked){
		for (i=1; i<obj.length; i++){
			obj[i].checked = true;
		}
	}else{
		for (i=1; i<obj.length; i++){
			obj[i].checked = false;
		}
	}
	
}

function itemCheck(){
	
	var c = 0;
	var obj = document.getElementsByName("check");
	
	for(i=1; i<obj.length; i++){
	    if(obj[i].checked){
	    	c++;
	    }
	 }
	
	if (c==(obj.length-1)){
		obj[0].checked = true;
	}else{
		obj[0].checked = false;
	}
	
}



 function findId() {
    	
    	var url = "/newzips/findId";
    	
    	var header = $("meta[name='_csrf_header']").attr('content');
    	var token = $("meta[name='_csrf']").attr('content');
    	
    	var userName = $("#userName").val();
    	var userPhone = $("#userPhone").val();

    	//var params = "userName=" + userName + "&userPhone=" + userPhone;
    	
		$.ajax({
				
			url:url,
			type:"post",
			data:{userName:userName, userPhone:userPhone}, 
			success:function(mav){
			
				replaceHTML = "<div class='popup-form'><div style='margin-top: 40px'></div>";
				
				if ((mav.userId) != null) {
					replaceHTML += "<h3>아이디는 <span style='font-size:14pt; font-weight: bold;'>" + mav.userId + "</span>입니다.</h3>";
				} else {
					replaceHTML += "<h3>아이디를 찾을 수 없습니다.</h3>";
				}
				
				replaceHTML += "<div style='margin-bottom: 40px'></div><button type='button' class='btn2' id='popUp-Close-Button' ";
				replaceHTML += "onclick=\"location.href='http://localhost:8080/newzips/login';\">닫기</button></div>";
				
				$(".popup-form").replaceWith(replaceHTML);
				
				},
			beforeSend:function(xhr){
		        xhr.setRequestHeader(header, token);
		    }
				
		})

    }


function addWish(itemId){

	alert("addWish js");

	var url = '/newzips/addWish/'+itemId;
	
	alert(url);	

}
    

$(window).on("load", function() {
   
	$("#header_login").on('click', function(){
		location.href = "http://localhost:8080/newzips/login"
	})
	
	$("#header_logout").on('click', function(){
		location.href = "http://localhost:8080/newzips/logout"
	})
	
	$("#header_signin").on('click', function(){
		location.href = "http://localhost:8080/newzips/join"
	})
	
	$("#header_login_realtor").on('click', function(){
		location.href = "http://localhost:8080/newzips/realtor/login"
	})
	
	$("#header_signin_realtor").on('click', function(){
		location.href = "http://localhost:8080/newzips/realtor/join"
	})
	
	
	//하트 눌렀을때 관심목록에 등록
	$(".popular-listing .card .card-footer a .la-heart-o").on("click", function(){
    	
    	var itemId = $(this).next('.hiddenItemId').val();
    	//var itemId = $(this).next('input').val();
    	var url = '/newzips/addWish/'+itemId;
    	alert(url);
    	
    	$.ajax({
    		
    		url:url,
    		type:"get",
    		success:function(mav){
    			$(".alert-success").find("strong").text(mav.msg);
    			//strong_val = mav.msg;
    			$(".alert-success").addClass("active");
        		return false;
    		}
    	})
    	
    });
	
	//휴지통 눌렀을때 관심목록에서 삭제
	$(".popular-listing .card .card-footer a .la-trash-o").on("click", function(){
    	
    	console.log($(this).next());
    	
    	//var itemId = $(this).next('.hiddenItemId').val();
    	var itemId = $(this).next('input').val();
    	alert(itemId);
    	var url = '/newzips/deleteWish/'+itemId;
    	
    	$.ajax({
    		
    		url:url,
    		type:"get",
    		success:function(mav){
	    		$(".alert-success").find("strong").text(mav.msg);
	    		$(".alert-success").addClass("active");
        		return false;
    		}
    		
    	})
    	
    });
    
    
  var start = 1;
var end = 5;
var lastId = null;

$("#load-more-button").off("click").on("click", function() {
    var header = $("meta[name='_csrf_header']").attr('content');
    var token = $("meta[name='_csrf']").attr('content');
    start = end+1;
    end += 6;
    
    console.log(`start: ${start}, end: ${end}`);
			  
    $.ajax({
        type: 'post',
        url: '/newzips/itemList_user',
        data: {
            start: start+1,
            end: end+1,
        },
        success: function(result) {
             $(".listing-container .row").append(result);
            if (start >= 90 && end >= 95) {
                $("#load-more-button").hide(); // 버튼 숨기기
            } else {
                lastId = $(result).last().data("id");
            }
        },
        beforeSend:function(xhr){
            xhr.setRequestHeader(header, token);
        }
    });
});


	





})//$(window)끝