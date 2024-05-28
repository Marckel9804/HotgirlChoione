var pss = new kakao.maps.services.Places(map);
var markers = []; // 마커를 저장할 배열

<<<<<<< HEAD
// 전역 인포윈도우 생성
var infowindow = new kakao.maps.InfoWindow({
    zIndex: 1,
    removable: true
});
=======
<<<<<<< HEAD

var infowindow = new kakao.maps.InfoWindow(); // 전역으로 하나의 인포윈도우 생성
// var infowindow = new kakao.maps.InfoWindow({ removable: true }); // 이거는 닫는 x창 열때

=======
var infowindow = new kakao.maps.InfoWindow({ removable: true }); // 전역으로 하나의 인포윈도우 생성
// var infowindow = new kakao.maps.InfoWindow({ removable: true }); // 이거는 닫는 x창 열때
>>>>>>> main
>>>>>>> map

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
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다.
        var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
            console.log(data[i]);

            // 마커를 생성하고 지도에 표시합니다.
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(data[i].y, data[i].x)
            });

            // 검색된 장소 위치를 기준으로 경계를 확장합니다.
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));

            // 마커에 클릭 이벤트를 등록합니다.
            (function(marker, data) {
                kakao.maps.event.addListener(marker, 'click', function() {
                    var infowindowContent =
                        '<div class="inner_tooltip" style="text-align: center;">' +
                        '<div>' +
                         '<a href="' + data.place_url + '" target="_blank" class="link_btn link_tit_tooltip">' + data.place_name + '</a>' +
                        '</div>' +
                        '<div class="button-container" style="margin-top: 5px">' +
                        '<button class="link_btn link_route" onclick="findRoute(' + data.y + ', ' + data.x + ')">리뷰</button> ' +
                        '<button class="link_btn link_another_action" onclick="showBoardPanel(\'' + data.place_name + '\')">채팅</button>' + // 가게 이름 전달
                        '</div>' +
                        '</div>';

                    infowindow.setContent('<div style="padding:10px; background-color:transparent; border:none; box-shadow:none;">' + infowindowContent + '</div>');
                    infowindow.setPosition(marker.getPosition());
                    infowindow.open(map, marker);
                });
            })(marker, data[i]);

            // 마커를 배열에 저장합니다.
            markers.push(marker);
        }
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다.
        map.setBounds(bounds);
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
