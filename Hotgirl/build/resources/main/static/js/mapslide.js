// 슬라이드 패널
function showBoardPanel(placeName) {
    document.getElementById('slidePanel').style.width = '250px';
    document.getElementById('slidePanelContent').innerHTML = '<p>' + placeName + '<p> <button id="writeButton">글쓰기</button>';
    document.getElementById('writeButton').addEventListener('click', showWriteForm);
}
//패널 닫기
function closeSlidePanel() {
    document.getElementById('slidePanel').style.width = '0'; // 0으로설정->패널이 닫아짐
}
function showWriteForm() {
    document.getElementById('writeModal').style.display = 'block'; // 글쓰기 모달
}
function closeWriteModal() {
    document.getElementById('writeModal').style.display = 'none'; // 글쓰기 모달을 숨김
    document.getElementById('postTitle').value = ''; // 제목 입력창 초기화
    document.getElementById('writeTextarea').value = ''; // 입력창 초기화
}
function submitPost() {
    const postTitle = document.getElementById('postTitle').value;
    const postContent = document.getElementById('writeTextarea').value;

    // 제목과 내용을 객체로 저장
    const post = {
        title: postTitle,
        content: postContent
    };

    // 제목과 상세보기 버튼을 포함한 포스트 요소 생성
    const postDiv = document.createElement('div');
    const titleHeader = document.createElement('h3');
    const viewButton = document.createElement('button');

    titleHeader.textContent = postTitle;
    viewButton.textContent = '자세히 보기';

    postDiv.appendChild(titleHeader);
    postDiv.appendChild(viewButton);

    // 자세히 보기 버튼 클릭 이벤트
    viewButton.addEventListener('click', function() {
        showPostDetails(post.content);
    });

    // 새로운 포스트 요소를 slidePanelContent에 추가
    document.getElementById('slidePanelContent').appendChild(postDiv);

    // 입력창 초기화
    document.getElementById('writeTextarea').value = '';
    document.getElementById('postTitle').value = '';

    // 모달 닫기
    closeWriteModal();
}
function showPostDetails(content) {
    const detailsModal = document.createElement('div');
    detailsModal.className = 'modal';

    const modalContent = document.createElement('div');
    modalContent.className = 'modal-content';

    const closeButton = document.createElement('span');
    closeButton.className = 'close';
    closeButton.textContent = 'x';
    closeButton.onclick = function() {
        detailsModal.style.display = 'none';
    };

    const contentPara = document.createElement('p');
    contentPara.textContent = content;

     const chatButton = document.createElement('button');
        chatButton.textContent = '채팅하기';
        chatButton.onclick = function() {
            window.location.href = '/chater';
        };
    modalContent.appendChild(closeButton);
    modalContent.appendChild(contentPara);
    modalContent.appendChild(chatButton);
    detailsModal.appendChild(modalContent);

    document.body.appendChild(detailsModal);
    detailsModal.style.display = 'block';
}
// 등록 버튼 클릭 이벤트 리스너 추가
document.getElementById('submitButton').addEventListener('click', submitPost);

// 슬라이드 패널 내용에서 글쓰기 버튼 클릭 이벤트 리스너 추가
document.getElementById('slidePanelContent').addEventListener('click', function(event) {
    if (event.target && event.target.id === 'writeButton') {
        showWriteForm();
    }
});