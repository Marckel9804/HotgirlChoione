var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.54699, 127.09598),
        level: 4
    };

var customOverlay = new kakao.maps.CustomOverlay({
    map: map,
    position: position,
    content: content,
    yAnchor: 1
});

var map = new kakao.maps.Map(mapContainer, mapOption);
var imageSrc = 'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png',
    imageSize = new kakao.maps.Size(40, 40),
    imageOption = {offset: new kakao.maps.Point(24, 69)};
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598);
var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage
});
marker.setMap(map);
var content = '<div class="customoverlay">' +
    '  <a href="https://place.map.kakao.com/11394059" target="_blank">' +
    '    <span class="title">구의야구공원</span>' +
    '  </a>' +
    '</div>';
var position = new kakao.maps.LatLng(37.54699, 127.09598);
