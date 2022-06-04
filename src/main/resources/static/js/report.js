$(document).ready(function($) {
    $("#chat-btn").click(function () {
        document.getElementById("title").innerText = '학습 채팅방';
        document.getElementById("chat-bar").style.visibility = 'visible';
    })

    $("#report-btn").click(function () {
        document.getElementById("title").innerText = '보고서 관리';
        document.getElementById("chat-bar").style.visibility = 'hidden';

        let table = document.createElement('table');
        var tableId = "report-table";
        var tableClass = "table";
        table.setAttribute("id", tableId);
        table.setAttribute("class", tableClass);
        let thead = document.createElement('thead');
        let tbody = document.createElement('tbody');

        table.appendChild(thead);
        table.appendChild(tbody);

        let row_1 = document.createElement('tr');
        var trStyle = "text-align: center;";
        row_1.setAttribute("style", trStyle);
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = "No.";
        let heading_2 = document.createElement('th');
        heading_2.innerHTML = "제출일";
        let heading_3 = document.createElement('th');
        heading_3.innerHTML = "파일";

        row_1.appendChild(heading_1);
        row_1.appendChild(heading_2);
        row_1.appendChild(heading_3);
        thead.appendChild(row_1);

        let row_2 = document.createElement('tr');
        row_2.setAttribute("style", trStyle);
        let row_2_data_1 = document.createElement('td');
        row_2_data_1.innerHTML = "1";
        let row_2_data_2 = document.createElement('td');
        row_2_data_2.innerHTML = "2022-04-01";
        let row_2_data_3 = document.createElement('td');
        row_2_data_3.innerHTML = "도르마북-중간보고서.hwp";

        row_2.appendChild(row_2_data_1);
        row_2.appendChild(row_2_data_2);
        row_2.appendChild(row_2_data_3);
        tbody.appendChild(row_2);

        let row_3 = document.createElement('tr');
        row_3.setAttribute("style", trStyle);
        let row_3_data_1 = document.createElement('td');
        row_3_data_1.innerHTML = "2";
        let row_3_data_2 = document.createElement('td');
        row_3_data_2.innerHTML = "2022-06-13";
        let row_3_data_3 = document.createElement('td');
        row_3_data_3.innerHTML = "도르마북-최종보고서.hwp";

        row_3.appendChild(row_3_data_1);
        row_3.appendChild(row_3_data_2);
        row_3.appendChild(row_3_data_3);
        tbody.appendChild(row_3);

        let btnDiv = document.createElement('div');
        let reportBtn = document.createElement('button');
        reportBtn.innerText = "보고서 제출"
        var btnClass = "btn btn-outline-primary";
        var btnStyle = "float: right;";
        reportBtn.setAttribute("class", btnClass);
        reportBtn.setAttribute("style", btnStyle);
        btnDiv.appendChild(reportBtn);

        document.getElementById('main_container').appendChild(table);
        document.getElementById('main_container').appendChild(btnDiv);

    })

    $("#score-btn").click(function () {
        document.getElementById("title").innerText = '성적 제출';
        document.getElementById("chat-bar").style.visibility = 'hidden';
    })

    $("#cam-chat").click(function () {
        document.getElementById("title").innerText = '화상 회의';
        document.getElementById("chat-bar").style.visibility = 'hidden';
    })
});

function insertTr(){
    var insertTr = "";

    insertTr += "<tr>";
    insertTr += "<td>1</td>";
    insertTr += "<td>2022-05-31</td>";
    insertTr += "<td>중간보고서.hwp</td>";
    insertTr += "</tr>";

    $("#memDiv").append(insertTr);

}