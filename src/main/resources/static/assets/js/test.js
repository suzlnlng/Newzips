function check_ok(){
	
	alert("check_ok")
	
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	
	const checkboxes = document.querySelectorAll('input[name="checkbox"]:checked');
	
	const checkedValues = [];
	
	checkboxes.forEach(function(checkbox) {
		  checkedValues.push(checkbox.value);
		});
	
	$.ajax({
		
		url: '/newzips/suggestListRealtor_ok',
		type:'post',
		data:{
			checkedValues:checkedValues
		},
		success:{
			alert('success');
		},
		beforeSend:function(xhr){
			xhr.setRequestHeader(header, token);
		}
		
	})
	
	//window.location.href = "suggestListRealtor_ok?itemId=" + checkedValues
	
}



function btnActive() {
    var selectBtns = document.getElementsByName('selectBtn');
    for (var i = 0; i < selectBtns.length; i++) {
      var selectBtn = selectBtns[i];
      selectBtn.addEventListener('click', function() {
        this.disabled = !this.disabled;
        if (this.disabled) {
          this.classList.add('disabled');
        } else {
          this.classList.remove('disabled');
        }
      });
      // 초기에 비활성화된 버튼에 대해서도 클래스 추가
      if (selectBtn.disabled) {
        selectBtn.classList.add('disabled');
      }
    }
  }