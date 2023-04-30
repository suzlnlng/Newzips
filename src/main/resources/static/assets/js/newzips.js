 

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
    	
    	$.ajax({
    		
    		url:url,
    		type:"get",
    		success:function(mav){
    			$(".alert-success").find("strong").text(mav.msg);
    			if (mav.msg == '로그인 후 이용하실 수 있어요.'){
    				$(".alert-success").find("a").text('로그인하기');
    				$(".alert-success").find("a").attr("href","http://localhost:8080/newzips/login");
    			}
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
				location.href = "http://localhost:8080/newzips/wish"
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
            start: start,
            end: end,
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