// 이전 페이지
function goBack() {
    window.history.back();
}

//카테고리 클릭 함수 생성
function categoryClicked(category) {
     //객체로 정의
    const subcategories = {
        '맛집': ['일식', '한식', '중식','양식'],
        '관광지': ['박물관', '전시회','명소','시장'],
        '놀거리': ['볼링', '야구', '축구', 'pc방', '보드게임'],
        '카페': ['커피','빵','디저트','아이스크림'],
        '즐겨찾기': ['맛집','관광지','놀거리','카페','기타']
    };
    // 모달 내의 텍스트와 카테고리 목록을 담을 요소를 가져옴
    const modalText = document.getElementById('modalText');
    const modalCategories = document.getElementById('modalCategories');
    modalCategories.innerHTML = '';
    // 선택된 카테고리에 서브카테고리 확인
    if (subcategories[category].length > 0) {
        modalText.innerText = `${category} 선택`; // 모달 텍스트를 설정
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
        modalText.innerText = `${category} 선택`;// 서브카테고리가 없는 경우 텍스트를 단순히 설정
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
    alert(subcategory + ' 선택');
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
// 특정 리스트를 화면에 표시하는 함수 (예시: 수정필요)
function showList(listName) {
    var listContainer = document.createElement('div');
    listContainer.style.position = 'absolute';
    listContainer.style.bottom = '10px';
    listContainer.style.left = '10px';
    listContainer.style.backgroundColor = 'pink';
    listContainer.style.padding = '10px';
    listContainer.style.borderRadius = '10px';
    listContainer.style.boxShadow = '0 2px 4px rgba(0,0,0,0.1)';
    listContainer.style.zIndex = '1';
    //메뉴판에서 누르면 리스트 생성 (예시: 수정필요)
    switch (listName) {
        case 'list1':
            listContainer.innerHTML = '<ul><li>리스트 1 항목 1</li></ul>';
            break;
        case 'list2':
            listContainer.innerHTML = '<ul><li>리스트 2 항목 1</li></ul>';
            break;
        case 'list3':
            listContainer.innerHTML = '<ul><li>리스트 3 항목 1</li></ul>';
            break;
    }
    // 기존에 존재하는 리스트 컨테이너를 제거하고 새로운 리스트 컨테이너를 추가
    var existingListContainer = document.querySelector('#map > div');
    if (existingListContainer) {
        mapContainer.removeChild(existingListContainer);
    }
    mapContainer.appendChild(listContainer);
}
// 사용자의 현재 위치로 지도를 이동하는 함수 panTo()
function panTo() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(pos) {
            var moveLatLon = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);
            map.panTo(moveLatLon);
        }, function(error) {
            // 위치 정보를 가져오는 데 실패한 경우
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    alert("위치 정보 사용에 동의하지 않았습니다.");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("위치 정보를 가져올 수 없습니다.");
                    break;
                case error.TIMEOUT:
                    alert(" 시간이 초과");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("알 수 없는 오류");
                    break;
            }
        });
    } else {
        alert("이 브라우저는 Geolocation을 지원하지 않습니다.");
    }
}
