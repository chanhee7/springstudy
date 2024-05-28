
import { fetchReplies } from "./getReply.js";
import { fetchReplyPost } from "./postReply.js";

// ======== 전역 변수 ========
export const BASE_URL = "http://localhost:8383/api/v1/replies";


// ======== 함수 정의 ========
function post(BASE_URL, payload) {
  const xhr = new XMLHttpRequest();
  xhr.open('POST', BASE_URL);

  xhr.setRequestHeader('content-type', 'application/json');
  xhr.send(JSON.stringify(payload));

  xhr.onload = e => {
    const replies = JSON.parse(xhr.response);
    console.log(replies);
  };
}


// ======== 실행 코드 ========

// 댓글 목록 서버에서 불러오기
fetchReplies();

// 댓글 작성 이벤트 등록
document.getElementById('replyAddBtn').addEventListener('click', e => {
  // 댓글 등록 로직
  fetchReplyPost();
});
