export default class reportInfo{
    data = null
    page = 1

    constructor({$target, contentNum, data, ClickSubmit}) {
        this.repoTypeContainer = document.createElement("div");
        this.repoTypeContainer.className = "ReportModal"
        $target.appendChild(this.repoTypeContainer);

        this.contentNum = location.herf;
        console.log(this.contentNum);

        this.ClickSubmit = ClickSubmit;
        this.data = data;
        this.contentNum = contentNum;
        this.contentType = 1; // <= 후에 팀프로젝트와 게시물 나눌 예정

        document.addEventListener('click', event => {
            if (event.target === this.repoTypeContainer) {
                this.repoTypeContainer.style.display = 'none';
            }
        })
        
       document.addEventListener('keyup', event => {
            if (event.key === 'Escape') {
                this.repoTypeContainer.style.display = 'none';
            }
        })

        this.render();        
    }

    setState(data) {
        this.data = data
        this.render();
    }

    setSuccess() {
        this.repoTypeContainer.querySelector(".repo__content-body").innerHTML =
            `
            <h1>신고 완료</h1>
            <button class="repo__success">완료</button>
            `
        this.repoTypeContainer.querySelector(".repo__success").addEventListener("click",(event) => this.repoTypeContainer.style.display = 'none')
    }

    handleSubmitRepo(contentData){
        const repoTypes = contentData.querySelectorAll(".report__type");
        const contentNum = document.getElementById("contentNum").value;
        const contentType = document.getElementById("contentType").value;

        let isChecked = false;
        let checkedType;

        for(var i = 0; i < repoTypes.length; ++i){
            if(repoTypes[i].checked){
                
                isChecked = true;
                checkedType = Number(repoTypes[i].dataset.type);
                break;
            }
        }

        if(!isChecked){
            alert("신고 유형을 선택해 주세요!");
            return;
        }

        let reqData = {
            type:checkedType,
            content: "",
            contentNum,
            contentType
        };

        //기타는 신고 내용을 받는다.
        if (checkedType === 4) {
            reqData.content = contentData.querySelector(".etcInput").value;
            console.log("타입 4 : 내용 -> ", reqData.content);
        }

        this.ClickSubmit(reqData)
    }

    handleClickEtc(typeBtn, etcInput){
        console.log(Number(typeBtn.dataset.type) === 4 && typeBtn.checked);
        if(Number(typeBtn.dataset.type) === 4 && typeBtn.checked){
            etcInput.style.display = "block"
        } else{
            etcInput.style.display = "none"
        }

    }

    render(){
        if(this.data.visible && this.page === 1){
            
            this.repoTypeContainer.innerHTML = 
            `
            <div class="report__content_wrapper">
                <div class="content">
                    <div class="title">
                        <span>게시물 신고하기</span>
                        <div class="close"><i class="fas fa-times"></i></div>
                    </div>
                    <div class="repo__content-body">
                        <input type="hidden" id="contentNum" name="contentNum" value="${this.contentNum}">
                        <input type="hidden" id="contentType" name="contentType" value="1">
                        <div class="content_input">
                            <input type="radio" id="report__type1" data-type="1" class="report__type" name="report-type__btn"><label for="report__type1">욕설, 비방</label>
                        </div>
                        <div class="content_input">
                            <input type="radio" id="report__type2" data-type="2" class="report__type" name="report-type__btn"><label for="report__type2">성적인 콘텐츠</label>
                        </div>
                        <div class="content_input">
                            <input type="radio" id="report__type3" data-type="3" class="report__type" name="report-type__btn"><label for="report__type3">폭력적 또는 혐오스러운 콘텐츠</label>
                        </div>
                        <div class="content_input">
                            <input type="radio" id="report__type4" data-type="4" class="report__type" name="report-type__btn"><label for="report__type4">기타</label>
                            <input type="text" class="etcInput" style="display:none;" placeholder="입력해주세요">
                        </div>
                        <div class="content_button-Wrapper">
                            <button id="repo__submit">제출</button>
                        </div>
                    </div>
                </div>
            </div>
            `
            const etcInput = this.repoTypeContainer.querySelector(".etcInput");

            this.repoTypeContainer.style.display = "block";
            this.repoTypeContainer.querySelector(".close").addEventListener("click", (event) => this.repoTypeContainer.style.display = "none");
            this.repoTypeContainer.querySelectorAll(".report__type").forEach(item => {
                item.addEventListener("change", (event) => this.handleClickEtc(event.target, etcInput))
            })
            this.repoTypeContainer.querySelector("#repo__submit").addEventListener("click", (event) => this.handleSubmitRepo(this.repoTypeContainer.querySelector(".repo__content-body")))
            
        } else if (this.page === 2) {
        
        } else {
            this.repoTypeContainer.style.display = "none";
        }
    }
}