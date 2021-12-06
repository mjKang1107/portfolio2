const phoneBox = document.querySelector("#phone");

const autoHypen = (event) => {
        const curInput = event.target;
        curInput.value = curInput.value.replace(/[^0-9]/g, '');

        if (curInput.value.length < 7) {
            curInput.value = `${curInput.value.substr(0,3)}-${curInput.value.substr(3)}`;

        } else if (curInput.value.length < 11) {
            curInput.value = `${curInput.value.substr(0,3)}-${curInput.value.substr(3,3)}-${curInput.value.substr(6)}`

        } else {
            curInput.value = `${curInput.value.substr(0,3)}-${curInput.value.substr(3,4)}-${curInput.value.substr(7)}`
        }
}

phoneBox.addEventListener("keyup", autoHypen);