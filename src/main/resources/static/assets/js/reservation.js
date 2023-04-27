$(window).on("load", function() {

    let selectedDate = '';
    let selectedTimes = [];

    $(function() {
      $("#datepicker").datepicker({
    	  minDate:0,
        onSelect: function(dateText) {
          const dateObject = $(this).datepicker('getDate');
          const year = dateObject.getFullYear();
          const month = ("0" + (dateObject.getMonth() + 1)).slice(-2);
          const day = ("0" + dateObject.getDate()).slice(-2);
          selectedDate = year + '-' + month + '-' + day;
        }
      });
    });

});


function makeReservation() {

    let selectedTimes = [];

	  const dateObject = $("#datepicker").datepicker('getDate');
	  const year = dateObject.getFullYear();
	  const month = ("0" + (dateObject.getMonth() + 1)).slice(-2);
	  const day = ("0" + dateObject.getDate()).slice(-2);
	  selectedDate = year + '-' + month + '-' + day;
    
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
	  }
	});
    
    	
      if (selectedDate === '') {
        alert('날짜를 선택해주세요.');
        return;
      }
      
      if(selectedTimes.length === 0){
    	  alert("시간을 선택해주세요.");
    	  return;
      }
  											  //join(', ')..?여러개 선택하면 ,로 구분해주는거야..
      const confirmMsg = `날짜: ${selectedDate}\n시간: ${selectedTimes.join(', ')}\n\n맞으면 확인눌러`;
      alert(confirmMsg)
      //const confirmed = confirm(confirmMsg)
      
      
      location.href = "http://localhost:8080/newzips/realtor/join"
      
      	$.ajax({
			method: 'post',
			url: '/newzips/reservation_resident_ok',
			data: {
				selectedDate: selectedDate,
				selectedTimes: selectedTimes
			},
			success: function(result) {
				alert("성공..")
			},
			beforeSend:function(xhr){
				xhr.setRequestHeader(header, token);
				alert("보내기전")
			},
			error:function(){
				alert("에러")
			}
			
	});
	
	alert("왜안되니")
      

      
   

    }
