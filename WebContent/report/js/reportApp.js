import reportBtn from "./reportBtn.js"
import reportInfo from "./reportInfo.js"

export default class reportApp{
    constructor($target) {
        this.reportBtn = new reportBtn({
            $target,
            OnClick: async() => {
                    const url = "./haveUserReport.repo"; // "./reportAction.repo";
                    const option = {
                        method: 'POST',
                        body: JSON.stringify({ 
                            contentNum: this.params["contentNum"],
                            contentType: 1 // => 후에 다른 타입 여부
                        }),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                    const haveResponse = await(await fetch(url, option))
                    
                    if(haveResponse.status === 404){
                    	alert("잘못된 경로입니다.");
                        return;
                    }
                    
                    //if ==> 신고한 사람이면 다시 중복X
                    if (haveResponse.status !== 204) {
                        alert("이미 신고한 게시물입니다.");
                        return;
                    }

                    this.reportInfo.setState({
                        visible: true
                    })
                }
        });
        this.getParam();
        this.reportInfo = new reportInfo({
            $target, 
            contentNum:this.params["contentNum"],
            data:{
                visible: false
            },
            ClickSubmit: async(reqData) => {
                const url = "./reportAction.repo"; // "./reportAction.repo";
                const option = {
                    method: 'POST',
                    body: JSON.stringify(reqData),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }

                console.log(reqData);

                const response = await (await fetch(url, option).catch(handleError));
                
                console.log(response);

                if (response.status === 204) {
                    
                    this.reportInfo.setSuccess();
                }
            }
        });
    }

    getParam(){
        this.params = [];

        //encode감안 decode
        const curUrl = decodeURIComponent(location.href);

        let paramStr = curUrl.substring(curUrl.indexOf('?') + 1, curUrl.length);
        console.log("? 이후 ==> ", paramStr);
        
        paramStr = paramStr.split("&");
        console.log(paramStr);

        for(var i = 0; i < paramStr.length; ++i){
            let splited = paramStr[i].split("=");
            let key = splited[0];
            let value = splited[1];

            this.params[key] = value;
        }
        
        if(!this.isValidPage()){
            alert("잘못된 게시물 정보입니다!");
            history.back();
        }
    }

    isValidPage(){
        if(this.params["contentNum"] == undefined){
            return false;
        }
        return true;
    }
}

const handleError = () => {
    console.warn("데이터 전송 에러");
}