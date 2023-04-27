 
function findAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('userZipCode').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("userAddr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("userAddr").value = jibunAddr;
            }
        }
    }).open();
}

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

function sendIt() {
	
	var f = document.joinForm;
	
	var ct = checkTerms();
	
	var sel1 = document.getElementById("select1").checked;
	var sel2 = document.getElementById("select2").checked;
	var sel3 = document.getElementById("select3").checked;
	
	var emailReceive = document.getElementById("emailReceive");
	emailReceive.value = sel3;
	
	if(sel1 && sel2 && ct){
		f.submit();
	}

}


function checkTerms(){
	
	var f = document.joinForm;
	var sel1 = document.getElementById("select1").checked;
	var sel2 = document.getElementById("select2").checked;
	
	if(!(sel1 && sel2)){
		$("#userTermCheckError").html("이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.");
		return false;
	}
	
	return true;
	
}


 function findId() {
    	
    	var url = "/newzips/findId";
    	
    	var header = $("meta[name='_csrf_header']").attr('content');
    	var token = $("meta[name='_csrf']").attr('content');
    	
    	var userName = $("#userName").val();
    	var userPhone = $("#userPhone").val();

    	var params = "userName=" + userName + "&userPhone=" + userPhone;
    	
		$.ajax({
				
			url:url,
			type:"post",
			data:{userName:userName, userPhone:userPhone}, 
			success:function(mav){
				alert(mav.userId);
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
var end = 6;
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