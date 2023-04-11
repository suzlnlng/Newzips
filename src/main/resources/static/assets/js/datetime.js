var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("Date").setAttribute("min", today);
document.getElementById("Date").setAttribute("max", "2023-04-24");




$('input').click(function(){
  $('input').removeClass("active");
  $(this).addClass("active");
});


 


