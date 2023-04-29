const searchInput = document.getElementById("searchInput");
let searchvalue1 = "";

searchInput.addEventListener("input", () => {
  searchvalue1 = searchInput.value;
  console.log("Selected option: " + searchvalue1);
});

let searchvalue2 = ""; // 선택된 값을 저장할 변수

function selectJW(option) {
    searchvalue2 = option; // 선택된 값을 변수에 저장
    document.getElementById("searchvalue2").value = searchvalue2; // hidden input에 선택된 값을 설정
    console.log("Selected option: " + searchvalue2);
}

let searchvalue3 = ""; // 선택된 값을 저장할 변수

function selectbuilding(option) {
    searchvalue3 = option; // 선택된 값을 변수에 저장
      document.getElementById("searchvalue3").value = searchvalue3;
    console.log("Selected option: " + searchvalue3);
}

$("#searchBtn").off("click").on("click", function() {
    const searchvalue1 = document.getElementById("searchInput").value;
    const searchvalue2 = document.getElementById("searchvalue2").value;
    const searchvalue3 = document.getElementById("searchvalue3").value;
    var start = 1;
    var end = 5;

    $.ajax({
        type: 'GET',
        url: "/newzips/itemList_search",
        data: {
            searchvalue1: searchvalue1,
            searchvalue2: searchvalue2,
            searchvalue3: searchvalue3,
            start: start,
            end: end
        },
        success: function(data) {
            $(".listing-container .row").empty();
            // 이후의 코드들은 여기에 작성하시면 됩니다.
        }
    });
});

    
    


    
