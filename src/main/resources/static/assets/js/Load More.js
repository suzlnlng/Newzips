$(document).ready(function() {

		var header = $("meta[name='_csrf_header']").attr('content');
    	var token = $("meta[name='_csrf']").attr('content');
    	
    	var start = 0;
		var end = 6;
    	var offset = 6;

	function loadMorePosts(strat,end,offset) {

		$.ajax({
		
			type: 'post',
			url: '/newzips/itemList_user',
			data: {
				start: start,
				end: end
			},
			success: function(result) {
				$(".listing-container").replaceWith(result);

				end = end + offset;
				alert(end);

			},
			beforeSend:function(xhr){
				xhr.setRequestHeader(header, token);
			}
			
		});
		
  }

    	loadMorePosts(start, end, offset);
  

  $("#load-more-button").on("click", function() {
  	alert("버튼 클릭")
    loadMorePosts(start,end,offset);
  });
  
});
