// 이전 페이지로 돌아가는 함수
function goBack() {
    window.history.back();
}
//엔터키
function handleEnterKey(event) {
    if (event.keyCode === 13) {
        searchByKeyword();
    }
}

// 엔터키
function handleEnterKey(event) {
    if (event.keyCode === 13) {
        searchByKeyword();
    }
}

// 카테고리 클릭 시 실행되는 함수
function categoryClicked(category) {
    const subcategories = {
        '맛집': ['일식', '한식', '중식', '양식'],
        '관광지': ['박물관', '전시회', '명소'],
        '놀거리': ['볼링', '야구', '축구', 'pc방', '보드게임'],
        '카페': ['커피', '빵', '디저트'],
        '즐겨찾기': ['맛집', '관광지', '놀거리', '카페', '기타']
    };

    const modalText = document.getElementById('modalText');
    const modalCategories = document.getElementById('modalCategories');
    modalCategories.innerHTML = '';

    if (subcategories[category].length > 0) {
        modalText.innerText = `${category}`;
        subcategories[category].forEach(subcategory => {
            const p = document.createElement('p');
            const a = document.createElement('a');
            a.href = "javascript:void(0);";
            a.innerText = subcategory;
            a.onclick = function() { selectCategory(subcategory); };
            p.appendChild(a);
            modalCategories.appendChild(p);
        });
    } else {
        modalText.innerText = `${category} 선택`;
    }

    showModal();
}

// 모달을 표시하는 함수
function showModal() {
    var modal = document.getElementById('categoryModal');
    modal.style.display = 'block';
}

// 모달을 닫는 함수
function closeModal() {
    var modal = document.getElementById('categoryModal');
    modal.style.display = 'none';
}

// 서브카테고리 선택 시 실행되는 함수
function selectCategory(subcategory) {
    searchByKeyword(subcategory); // 서브카테고리로 검색
    closeModal();
}

// 메뉴 아이콘 클릭 시 메뉴를 토글하는 함수
function toggleMenu() {
    var menuList = document.getElementById('menuList');
    var menuIcon = document.getElementById('menuIcon');

    if (menuList.style.display === 'none' || menuList.style.display === '') {
        menuList.style.display = 'block';
        menuIcon.style.display = 'none';
    } else {
        menuList.style.display = 'none';
        menuIcon.style.display = 'block';
    }
}

// 특정 리스트를 화면에 표시하는 함수
function showList(listName) {
    switch (listName) {
        case 'list1':
            window.location.href = '../login/login';
            break;
        case 'list2':
            window.location.href = '../login/mypage';
            break;
        default:
            alert('알 수 없는 항목입니다.');
    }
}

// 사용자의 현재 위치로 지도를 이동하고 마커를 표시하는 함수
function panTo() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(pos) {
            var moveLatLon = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);
            map.setLevel(3); // 확대 레벨 조정
            map.panTo(moveLatLon);

            var currentLocationMarker = new kakao.maps.Marker({
                position: moveLatLon,
                image: new kakao.maps.MarkerImage(
                    'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
                    new kakao.maps.Size(24, 35),
                    {offset: new kakao.maps.Point(12, 35)}
                )
            });

            // 마커에 클릭 이벤트를 등록합니다.
            kakao.maps.event.addListener(currentLocationMarker, 'click', function() {
                // 마커의 위치 좌표를 기반으로 카카오맵 주소 링크를 열립니다.
                window.open(`https://map.kakao.com/link/to/${moveLatLon.getLat()},${moveLatLon.getLng()}`, '_blank');
            });

            if (window.currentLocationMarker) {
                window.currentLocationMarker.setMap(null);
            }
            currentLocationMarker.setMap(map);
            window.currentLocationMarker = currentLocationMarker;

        }, function(error) {
            // ... 에러 처리 코드 ...
        });
    } else {
        alert("이 브라우저는 Geolocation을 지원하지 않습니다.");
    }
}