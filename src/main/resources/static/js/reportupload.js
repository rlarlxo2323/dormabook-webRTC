'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

function getRoomNo(){
    var url = window.location.href;
    var arr = url.split("/");
    return arr[4];
}

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var roomNo = getRoomNo();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/file/report/uploadFile/"+roomNo);

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>보고서가 제출되었습니다.</p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.style.display = "<P>200MB 초과되어 올리지 못합니다.</P>"
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);

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

$("#video-btn").click(function (){
    window.location.href = '/video-chat';
});