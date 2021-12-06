const emailInputBox = document.querySelector('.loginForm__emailInput');
const memoIdBox = document.querySelector('#memoId');
const cookieStr = document.cookie;
let cookies;
let userID = '';

const splitCookie = () => {
    cookies = cookieStr.split(';');
    cookies = cookies.map(cookieInfo => cookieInfo.split("="));
}

const memoBoxCheck = () => {
    memoIdBox.checked = true;
}

const getUserId = () => {
    
    console.log(cookies);
    for (var i = 0; i < cookies.length; ++i){

        const curCookieKey = cookies[i][0];

    	if(curCookieKey[0] === ' '){
            curCookieKey = curCookieKey.substring(1);
        }
        
        if (curCookieKey === 'userID') {
            memoBoxCheck();
            return cookies[i][1];
        }
    }
}

if (cookieStr.length > 0) {
    splitCookie();
    emailInputBox.value = getUserId();
}