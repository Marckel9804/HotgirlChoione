var pss = new kakao.maps.services.Places(map);
function searchByKeyword() {
    console.log("function searchByKeyword");
    var keyword = document.getElementById("searchInput").value;
    if (keyword=="") {
        alert('키워드를 입력해주세요!');
        return false;
    }
    console.log(keyword);

    pss.keywordSearch(keyword,placesSearchCB, {useMapBounds:true});
}

function placesSearchCB(data, status, pagination) {
    console.log("function placesSearchCB");
    if (status === kakao.maps.services.Status.OK) {
        for(var i=0; i<data.length; i++){
            console.log(data[i]);
        }

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

