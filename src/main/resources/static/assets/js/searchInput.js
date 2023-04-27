const searchInput = document.getElementById("searchInput");
let searchvalue1 = "";

searchInput.addEventListener("input", () => {
  searchvalue1 = searchInput.value;
  console.log("Selected option: " + searchvalue1);
});


let searchvalue2 = ""; // 선택된 값을 저장할 변수

function selectJW(option) {
    searchvalue2 = option; // 선택된 값을 변수에 저장
    console.log("Selected option: " + searchvalue2);
}

let searchvalue3 = ""; // 선택된 값을 저장할 변수

function selectbuilding(option) {
    searchvalue3 = option; // 선택된 값을 변수에 저장
    console.log("Selected option: " + searchvalue3);
}


const searchInput = document.getElementById("searchInput");
const jwSelect = document.getElementById("jwSelect");
const buildingSelect = document.getElementById("buildingSelect");
const submitBtn = document.getElementById("submitBtn");


let searchValue1 = "";
let searchValue2 = "";
let searchValue3 = "";


searchInput.addEventListener("input", () => {
  searchValue1 = searchInput.value;
});


jwSelect.addEventListener("change", () => {
  searchValue2 = jwSelect.value;
});


buildingSelect.addEventListener("change", () => {
  searchValue3 = buildingSelect.value;
});


submitBtn.addEventListener("click", () => {
  // AJAX 호출 등으로 검색 데이터를 가져오는 코드 작성
  const start = 0; // 시작 rownum
  const end = 10; // 종료 rownum
  
  $.ajax({
    url: "/search",
    type: "POST",
    data: {
      searchValue1,
      searchValue2,
      searchValue3,
      start,
      end
    },
    
    success: search(data) => {
    
