$(window).on("load", function() {

    let selectedDate = '';
    let selectedTimes = [];

	//중복되는 아작스 정리해야됨
	
    $(function() {
      	$("#datepicker").datepicker({
    	  	minDate:0,
    	  	onSelect: function(dateText) {
    	  	
    	  	$.ajax({
			method: 'get',
			url: '/newzips/reservation_resident_data',
			data: {
				selectedDate: getDate()
			},
			success: function(result) {
				readyToggle(result.dtoRR)
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"error:"+error);
			}
			
		});
    	  	

        }
		})
	})
	
	$.ajax({
			method: 'get',
			url: '/newzips/reservation_resident_data',
			data: {
				selectedDate: getDate()
			},
			success: function(result) {
				readyToggle(result.dtoRR)
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"error:"+error);
			}
			
		});
	

});


function getDate(){

	const dateObject = $("#datepicker").datepicker('getDate');
	const year = dateObject.getFullYear();
	const month = ("0" + (dateObject.getMonth() + 1)).slice(-2);
	const day = ("0" + dateObject.getDate()).slice(-2);
	selectedDate = year + '-' + month + '-' + day;

	return selectedDate;
	
}


function readyToggle(availableData){

	const timeSlots = document.querySelectorAll('.time-slot');
	
	timeSlots.forEach(timeSlot => {
		const toggleSwitch = timeSlot.querySelector('.toggle-switch input[type="checkbox"]');
		toggleSwitch.checked = false;
	})

	for (i in availableData) {
		if (availableData[i].available == 'T') {
			var checkbox = document.getElementById(availableData[i].availableTime);
			checkbox.checked = true;
		}
	}
	

} 


function makeReservation() {

    let selectedTimes = [];
    let unselectedTimes = [];
    
    		var header = $("meta[name='_csrf_header']").attr('content');
    	var token = $("meta[name='_csrf']").attr('content');

	selectedDate = getDate();
	 
    
	     // time-slot 클래스를 가진 모든 요소를 선택
	const timeSlots = document.querySelectorAll('.time-slot');
	
	// 각 time-slot 요소를 순회하며 처리
	timeSlots.forEach(timeSlot => {
	  // toggle-switch 클래스를 가진 체크박스 요소를 선택
	  const toggleSwitch = timeSlot.querySelector('.toggle-switch input[type="checkbox"]');
	  
	  // 체크박스의 상태가 true인 경우
	  if (toggleSwitch.checked) {
	    // 해당 time-slot 요소의 span 요소에서 9:00 값을 받아옴
	    const time = timeSlot.querySelector('span').textContent;
	    console.log(time); // 9:00 (또는 필요한 처리를 수행)
	    selectedTimes.push(time)
	  }else if (!toggleSwitch.checked) {
	    const time = timeSlot.querySelector('span').textContent;
	    console.log(time); // 9:00 (또는 필요한 처리를 수행)
	    unselectedTimes.push(time)
	  }
	  
	  
	  
	});
    
    	
      if (selectedDate === '') {
        alert('날짜를 선택해주세요.');
        return;
      }

  											  //join(', ')..?여러개 선택하면 ,로 구분해주는거야..
      const confirmMsg = `날짜: ${selectedDate}\n시간: ${selectedTimes.join(', ')}\n\n맞으면 확인눌러`;
      //const confirmed = confirm(confirmMsg)
      
      	$.ajax({
			method: 'post',
			url: '/newzips/reservation_resident',
			data: {
				selectedDate: selectedDate,
				selectedTimes: selectedTimes,
				unselectedTimes: unselectedTimes
			},
			success: function(result) {
				$(".alert-success").find("strong").text("저장되었습니다.");
	    		$(".alert-success").addClass("active");
			},
			beforeSend:function(xhr){
				xhr.setRequestHeader(header, token);
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"error:"+error);
			}
			
	});
      

    }
