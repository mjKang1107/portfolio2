var likeResult = document.querySelector("#likeResult")
var likeBtn = document.querySelector("#likeBtn");
var content_num = document.querySelector("#content_num").value;
var content_type = document.querySelector("#content_type").value;

async function handleLike() {
	var url="./likeAddAction.lk";
	var sendBody = {
			content_num:content_num,
			content_type:content_type
	}
	
	var data = {
			method:"POST",
			body:JSON.stringify(sendBody)
	}
	
	var response = await (await fetch(url,data))
	
	likeBtn.innerHTML = `<i class="fas fa-heart"></i>좋아요 취소`
	likeBtn.removeEventListener("click", handleUnLike)
	likeBtn.addEventListener("click", handleUnLike)
}

async function handleUnLike(){
	var url="./likeDeleteAction.lk";
	var sendBody = {
			content_num:content_num,
			content_type:content_type
	}
	
	var data = {
			method:"POST",
			body:JSON.stringify(sendBody)
	}
	
	var response = await (await fetch(url,data))
	
	likeBtn.innerHTML = `<i class="far fa-heart"></i>좋아요`
	likeBtn.removeEventListener("click", handleLike)
	likeBtn.addEventListener("click", handleLike)
}

if(likeResult.value == 1){
	//likeBtn.innerHTML ==> html 코드 작성하면 됩니다
	likeBtn.innerHTML = `<i class="fas fa-heart"></i>좋아요 취소`
	likeBtn.addEventListener("click", handleUnLike)
		
}else{
	likeBtn.innerHTML = `<i class="far fa-heart"></i>좋아요`
	likeBtn.addEventListener("click", handleLike)	
}


