var pss = new kakao.maps.services.Places(map);
var markers = []; // 마커를 저장할 배열
var infowindows = []; // 정보창을 저장할 배열

function searchByKeyword() {
    console.log("function searchByKeyword");
    var keyword = document.getElementById("searchInput").value;
    if (keyword == "") {
        alert('키워드를 입력해주세요!');
        return false;
    }
    console.log(keyword);

    // 기존 마커들과 정보창, 검색창 텍스트를 초기화
    removeMarkers();
    clearSearchInput();

    pss.keywordSearch(keyword, placesSearchCB, { useMapBounds: true });
}

function placesSearchCB(data, status, pagination) {
    console.log("function placesSearchCB");
    if (status === kakao.maps.services.Status.OK) {

        for (var i = 0; i < data.length; i++) {
            console.log(data[i]);

            // 마커를 생성하고 지도에 표시합니다.
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(data[i].y, data[i].x)
            });

            // 마커에 클릭 이벤트를 등록합니다.
            kakao.maps.event.addListener(marker, 'click', getPlaceDetailsAndOpenUrl(data[i].id));

            // 마커에 정보창을 표시합니다.
            var infowindow = new kakao.maps.InfoWindow({
                content: "<div class='vertex'><a href='"+data[i].place_url+"'>"+data[i].place_name +"</a></div>>"
            });
            infowindow.open(map, marker);

            // 마커와 정보창을 배열에 저장합니다.
            markers.push(marker);
            infowindows.push(infowindow);
        }

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        return;
    }
}

function getPlaceDetailsAndOpenUrl(id) {
    return function() {
        pss.detailSearch(id, function(data, status) {
            if (status === kakao.maps.services.Status.OK) {
                var place_url = data.place_url;
                window.open(place_url, '_blank'); // 장소 웹페이지 새 탭에서 열기
            } else {
                console.log('장소 세부 정보 검색 실패');
            }
        });
    };
}
// 마커와 정보창 제거 함수
function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
        infowindows[i].close();
    }
    markers = [];
    infowindows = [];
}

// 검색창 텍스트 초기화 함수
function clearSearchInput() {
    document.getElementById("searchInput").value = "";
}