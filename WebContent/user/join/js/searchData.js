import { getPostRequest } from "./api.js";

const emailInput = document.querySelector("#email");
const nicknameInput = document.querySelector("#nickname");
const joinForm = document.querySelector("#joinForm");

const allowResponse = (container) => {

    let inText = "";

    switch (container.id) {
        case "checkResultId":
            inText = '사용가능한 아이디 입니다!'
            break;
        case "checkResultNickname":
            inText = '사용가능한 닉네임 입니다!'
            break;
        default:
            break;
    }
    container.innerText = inText;
    container.style.color = "green";
}

const rejectResponse = (container) => {
    
    let inText = "";

    switch (container.id) {
        case "checkResultId":
            inText = '사용 불가능한 아이디 입니다!'
            break;
        case "checkResultNickname":
            inText = '사용 불가능한 닉네임 입니다!'
            break;
        default:
            break;
    }
    container.innerText = inText;
    container.style.color = "red";
}

const SearchData = async(event,url) => {
    const $target = event.target;
    const $term = $target.value;
    const URL = url;
    const state = await (await fetch(URL,getPostRequest($term))).json();
    
    if(state.exists || $term.length < 1){
        rejectResponse($target.parentElement.querySelector(".checkResult"))
        $target.classList.remove("permit")
    } else {
        allowResponse($target.parentElement.querySelector(".checkResult"))
        $target.classList.add("permit");
    }
}

const emailSearchHandler = async(event) => {
	if(event.target.readOnly)
		return;
    const URL = './SearchUserEmail.json';
    await SearchData(event, URL);
}

const nicknameSearchHandler = async(event) => {
    const URL = './SearchUserNickname.json';
    await SearchData(event, URL);
}

const checkAuthHandler = async(event) => {
    if (emailInput.readOnly)
        emailInput.classList.add("permit");
	
    if(!emailInput.classList.contains("permit") || !nicknameInput.classList.contains("permit")){
        return event.preventDefault();
    }

    const majorLen = document.querySelector("#majorContainer .cofirmContainer").childNodes.length;
    if(majorLen <= 0){
        alert("전문분야는 최소 1개 이상 선택해주세요!");
        document.querySelector("#searchBox1").focus();
        return event.preventDefault();
    }
}

if ((!emailInput.readOnly && emailInput.value.length > 0) && nicknameInput.value.length > 0) {
    emailInput.addEventListener("load",emailSearchHandler);
    nicknameInput.addEventListener("load",nicknameSearchHandler);
}

emailInput.addEventListener("keyup",emailSearchHandler);
nicknameInput.addEventListener("keyup",nicknameSearchHandler);
joinForm.addEventListener("submit", checkAuthHandler);