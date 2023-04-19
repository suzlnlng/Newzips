$(document).ready(function() {
  var offset = 0; // 초기 offset 값
  var limit = 10; // 한 번에 가져올 데이터 갯수

  loadMorePosts(limit); // 최초 데이터 로딩

  function loadMorePosts(limit) {
    $.ajax({
      url: "/listing-data", // 서버 요청 URL
      type: "GET", // 요청 방식
      data: { offset: offset, limit: limit }, // 요청 파라미터
      success: function(data) {
        // 서버 응답 처리
        offset += limit; // 다음 요청할 offset 값 계산
        // 가져온 데이터를 HTML에 추가
        $(data).appendTo("#listing-container");
      }
    });
  }

  $(".load-more-posts .btn2").click(function() {
    loadMorePosts(limit);
  });
});
