$(function(){
    const uuidInput = document.querySelector('input#uuid');

    if (localStorage.getItem("uuid") === null) {
        localStorage.setItem("uuid", guid());
    }
    uuidInput.value = localStorage.getItem("uuid");
    console.log("local.uuid:" + localStorage.getItem("uuid"));
    // console.log("input.value:" + uuidInput.value);
});

function addUuidToButtonLink(button) {
    let id = 'button-link-' + button.value;
    let ref = document.getElementById(id).href;
    document.getElementById(id).href = ref + '/user/' + localStorage.getItem("uuid");
    console.log("link.href:" + document.getElementById(id).href);
}

$("#report-btn").click(function () {
    var roomNo = sessionStorage.getItem('roomNo');
    window.location.href = '/study-room/' + roomNo + '/report';
});

$("#score-btn").click(function () {
    var roomNo = sessionStorage.getItem('roomNo');
    window.location.href = '/study-room/' + roomNo + '/mentee-ts';
});

$("#chat-btn").click(function () {
    var roomNo = sessionStorage.getItem('roomNo');
    var addr = sessionStorage.getItem('addr');
    var jwt = sessionStorage.getItem('jwt');

    var url = '/study-room/' + roomNo + '/' + addr;

    var form = document.createElement('form');
    form.setAttribute('method', 'POST');
    form.setAttribute('action', url);

    var hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden'); //값 입력
    hiddenField.setAttribute('name', 'jwt');
    hiddenField.setAttribute('value', jwt);
    form.appendChild(hiddenField);

    document.body.appendChild(form);
    form.submit();
});