var pss = new kakao.maps.services.Places(map);
var markers = []; // 마커를 저장할 배열

// 마커 클릭시 해당 위치 상세정보 창
var infoOverlay = new kakao.maps.CustomOverlay({removable: true, yAnchor: 1.5 });


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

                    // 상세정보 창에 담길 html 내용
                    var infoOverlayContent =
                        '<div class="info_container"> '+
                            '<div style="width: auto">' +
                                '<a class="info_url" href="' + data.place_url + '" target="_blank" class="link_btn link_tit_tooltip">' + data.place_name + '</a>' +
                                '<button class="info_close" onclick="infoOverlay.setMap(null)">' +
                                    '&times;' +
                                '</button>'+
                            '</div>' +
                            '<div class="info_button_container">' +
                                '<button class="link_btn link_route" ' +
                                    'onclick="findRoute(' + data.y + ', ' + data.x + ')">' +
                                    '리뷰' +
                                '</button> ' +
                                '<button class="link_btn link_another_action" ' +
                                    'onclick="showBoardPanel(\'' + data.place_name + '\')">' +
                                    '채팅' +
                                '</button>' +
                            '</div>' +
                        '</div>';
                    // 상세정보 창 위치
                    var infoPosition = marker.getPosition();

                    // 이전에 올린 상세정보 창 삭제
                    infoOverlay.setMap(null);
                    // 상세정보 내용, 위치 세팅
                    infoOverlay.setContent(infoOverlayContent);
                    infoOverlay.setPosition(infoPosition);
                    // 지도에 세팅한 상세정보 창 띄우기
                    infoOverlay.setMap(map);

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
