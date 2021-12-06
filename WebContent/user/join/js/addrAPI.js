let callAddress = () =>{
	let inputTarget = document.querySelector("#addr");
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data.address);
            inputTarget.value = data.zonecode+','+data.address+','+data.jibunAddress;
            inputTarget.style.color='gray';
        }
    }).open();
    return false;
}
