import { getPostRequest } from "./api.js";

const reSendBtn = document.querySelector("#reSendBtn");

const handleSendEmail = async(event) => {
    const email = document.querySelector("#email").value;
    const state = await (await fetch("changeEmailCode.us",getPostRequest(email))).status;

    console.log(state);
    if(state !== 200){
        alert("재발급에 실패했습니다!");
        return;
    }
    alert("인증코드를 재발급했습니다!");
}

reSendBtn.addEventListener("click",handleSendEmail);