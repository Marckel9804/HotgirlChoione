var pss = new kakao.maps.services.Places(map);
var markers = []; // 마커를 저장할 배열


var infowindow = new kakao.maps.InfoWindow(); // 전역으로 하나의 인포윈도우 생성
// var infowindow = new kakao.maps.InfoWindow({ removable: true }); // 이거는 닫는 x창 열때


function searchByKeyword(keyword) {
    console.log("function searchByKeyword");
    if (!keyword) {
        keyword = document.getElementById("searchInput").value;
    }
    if (keyword == "") {
        alert('키워드를 입력해주세요!');
        return false;
    }
    console.log(keyword);


    // 기존 마커들과 검색창 텍스트를 초기화
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
            kakao.maps.event.addListener(marker, 'click', (function(marker, data) {
                return function() {

                    var infowindowContent =
                        '<div class="inner_tooltip" style="text-align: center;">' + // 가운데 정렬 스타일 적용
                            '<div>' +
                                '<a href="' + data.place_url + '" target="_blank" class="link_btn link_tit_tooltip">' + data.place_name + '</a>' +
                                '<span class="closeButton" style="margin-left: 5px; width: 10px; height: 10px"><button onclick="infowindow.close()">&times;</button></span>'+
                            '</div>' +
                            '<div class="button-container" style="margin-top: 5px">' +
                                '<button class="link_btn link_route" onclick="findRoute(' + data.y + ', ' + data.x + ')">리뷰</button> ' +
                                '<button class="link_btn link_another_action" onclick="anotherAction()">게시판</button>' +
                            '</div>' +
                        '</div>';


                    // 이미 열려있는 인포윈도우 닫기
                    infowindow.close();

                    infowindow.setContent('<div style="padding:10px; background-color:#fff; border-radius:10px; box-shadow: 0px 2px 10px rgba(0,0,0,0.2);">' + infowindowContent + '</div>');

                    // 클릭된 마커 위치로 인포윈도우 옮기기
                    infowindow.setPosition(marker.getPosition());

                    // 새로운 인포윈도우 열기
                    infowindow.open(map, marker);
                };
            })(marker, data[i]));

            // 마커를 배열에 저장합니다.
            markers.push(marker);
        }
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        return;
    } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        return;
    }
}

function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

function clearSearchInput() {
    document.getElementById("searchInput").value = "";
}