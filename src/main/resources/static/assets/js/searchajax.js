function searchData() {
  var header = $("meta[name='_csrf_header']").attr('content');
  var token = $("meta[name='_csrf']").attr('content');

  var start = 1;
  var end = 5;
  var lastId = null;  
  var searchvalue1 = document.getElementById("searchvalue1").value;
  var searchvalue2 = document.getElementById("searchvalue2").value;
  var searchvalue3 = document.getElementById("searchvalue3").value;
  
  console.log(`start: ${start}, end: ${end}, searchvalue1: ${searchvalue1}, 
  searchvalue2: ${searchvalue2}, searchvalue3: ${searchvalue3}`);
  
    $.ajax({
        type: 'post', 
      beforeSend: function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    url: '/search',
     data: {  start: start+1,
            end: end+1, searchvalue1: searchvalue1, searchvalue2: searchvalue2, 
            searchvalue3: searchvalue3 },
            success: function(data) {
             $(".listing-container .row").replacewith(data);
             }
             
             