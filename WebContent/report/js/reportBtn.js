export default class reportBtn {
    constructor({$target, OnClick}){
        this.$target = $target.querySelector("#reportBtnContainer");
        this.OnClick = OnClick;

        const reportIcon = document.createElement("i")
        reportIcon.className = "fas fa-flag";

        const span = document.createElement("span")
        span.className = "report__text"
        span.innerText = "신고하기"

        this.eventBtn = document.createElement('button');
        this.eventBtn.id = "reportBtn"
        this.eventBtn.className ="btn"
        this.eventBtn.appendChild(reportIcon);
        this.eventBtn.appendChild(span)
        this.eventBtn.addEventListener("click", this.OnClick)

        this.render()
    }

    render(){
        this.$target.appendChild(this.eventBtn);
    }
}