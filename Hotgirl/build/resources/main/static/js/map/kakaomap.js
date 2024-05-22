var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.54699, 127.09598),
        level: 4
    };
var map = new kakao.maps.Map(mapContainer, mapOption);
var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',
    imageSize = new kakao.maps.Size(64, 69),
    imageOption = {offset: new kakao.maps.Point(27, 69)};
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598);
var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage
});
marker.setMap(map);
var content = '<div class="customoverlay">' +
    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
    '    <span class="title">구의야구공원</span>' +
    '  </a>' +
    '</div>';
var position = new kakao.maps.LatLng(37.54699, 127.09598);
var customOverlay = new kakao.maps.CustomOverlay({
    map: map,
    position: position,
    content: content,
    yAnchor: 1
});

var pss = new kakao.maps.services.Places();
function searchByKeyword() {
    console.log("function searchByKeyword");
    var keyword = document.getElementById("searchInput").value;
    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    pss.keywordSearch(keyword,placesSearchCB);
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