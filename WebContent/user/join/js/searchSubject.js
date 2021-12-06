import { getPostRequest } from "./api.js";
const majorInput = document.querySelector("#major");
const interInput = document.querySelector("#inter");
const searchBox1 = document.querySelector("#searchBox1");
const searchBox2 = document.querySelector("#searchBox2");

export const searchSubject = async(event) => {
    const $target = event.target;
    const $term = $target.value;

    const URL = "SearchSubject.json";
    const data = await (await fetch(URL,getPostRequest($term))).json();
    console.log(data);

    return data.map((item,idx) => {
        return `<div class="searchNodeContainer"><a class="searchNode" data-id="${idx}" tabindex="${idx}"
         href="#">${item.subName}</a></div>`
    }).join('');
}

const findSubjectName = (name, list) => {
    for (var i = 0; i < list.length; i++) {
        if (list[i].childNodes[0].value === name) {
            return true;
        }
    }
    return false;
}

const handleSearchBox = async(event) => {
    const searchBox = event.target.parentElement.parentElement.querySelector(".searchBox");
    const resultLen = searchBox.childNodes.length;

    if (event.key === 'Escape' || event.target.value === ''){
        searchBox.style.display = "none";
        return;
    }
    else if (event.key === 'ArrowUp') {
        searchBox.childNodes[resultLen-1].firstChild.focus();
    }else if (event.key === 'ArrowDown') {
        searchBox.childNodes[0].firstChild.focus();
    }else{
        searchBox.innerHTML = await searchSubject(event);   
    }
    searchBox.style.display = "flex";
}

const getKeyword = (event) => {
    const CONTAINER = event.target.parentElement.parentElement.parentElement.parentElement;
    const comfirmContainer = CONTAINER.querySelector(".cofirmContainer");
    const inputBox = event.target.parentElement.parentElement.parentElement.querySelector(".subjectInput");
    const clickValue = event.target.innerText;
    const serachBox = event.target.parentElement.parentElement;

    if (comfirmContainer.childNodes.length > 4) {
        alert("과목은 최대 5개까지 선택해주세요!");
        return event.preventDefault();
    }

    if(comfirmContainer.childNodes.length > 0 && findSubjectName(clickValue, comfirmContainer.childNodes)){
        return event.preventDefault();
    }

    const subSmallContainer = document.createElement("div");
    const inputElement = document.createElement("input");
    inputElement.type = "hidden";
    inputElement.name = CONTAINER.id;
    inputElement.value = clickValue;

    const targetItem = document.createElement("span");
    targetItem.className = "subjectItem"
    targetItem.dataset.id = comfirmContainer.childNodes.length;
    targetItem.dataset.value = clickValue;
    targetItem.innerText = clickValue;

    const remove = document.createElement("i");
    remove.dataset.role = "remove";
    remove.className = "fas fa-times-circle"

    targetItem.appendChild(remove);
    remove.addEventListener("click",e => e.target.parentElement.parentElement.remove())
    
    subSmallContainer.appendChild(inputElement);
    subSmallContainer.appendChild(targetItem);

    comfirmContainer.appendChild(subSmallContainer);
    console.log(targetItem.style.display);
    inputBox.value = "";
    inputBox.focus();

    serachBox.style.display="none"

    event.preventDefault();
}

const moveWord = (event) => {
    let $searchNode = event.target.closest('.searchNode');
    let nowIdx = Number($searchNode.dataset.id);
    const parent = event.target.parentElement.parentElement;
    const resultLen = parent.childNodes.length;
    const inputBox = event.target.parentElement.parentElement.parentElement.querySelector(".subjectInput");
    console.log("검색어 개수 => ",resultLen);

    if (event.key === 'ArrowUp') {
        nowIdx = nowIdx-1 < 0 ? resultLen - 1 : nowIdx - 1;
        parent.childNodes[nowIdx].firstChild.focus();

    } else if (event.key === 'ArrowDown') {
        
        nowIdx = nowIdx + 1 > resultLen - 1 ? 0 : nowIdx + 1;
        parent.childNodes[nowIdx].firstChild.focus();
    
    }else if (event.key === 'Escape') {
        inputBox.focus();
    }
}

window.addEventListener("keydown", function(e) {
    if(["Space","ArrowUp","ArrowDown","ArrowLeft","ArrowRight"].indexOf(e.code) > -1) {
        e.preventDefault();
    }
}, false);

majorInput.addEventListener("keyup", handleSearchBox);
interInput.addEventListener("keyup", handleSearchBox);

searchBox1.addEventListener("keyup", moveWord);
searchBox1.addEventListener("click", getKeyword);
searchBox2.addEventListener("keyup", moveWord);
searchBox2.addEventListener("click", getKeyword);