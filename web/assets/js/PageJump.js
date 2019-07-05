/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function employees() {
    document.getElementById("iframe").src = './employees.jsp';
}
;
function personalInformation() {
    document.getElementById("iframe").src = './personalInformation.jsp';
}
;

function station() {
    document.getElementById("iframe").src = './station.jsp';
}
;

function department() {
    document.getElementById("iframe").src = './department.jsp';
}
;

function punchCard() {
    document.getElementById("iframe").src = './punchCard.jsp';
}
;

function repair() {
    document.getElementById("iframe").src = './repair.jsp';
}
;

function leave() {
    document.getElementById("iframe").src = './leave.jsp';
}
;

function attendance() {
    document.getElementById("iframe").src = './attendance.jsp';
}
;

function classes() {
    document.getElementById("iframe").src = './classes.jsp';
}
;

function paySalary() {
    document.getElementById("iframe").src = './paySalary.jsp';
}
;

function welcome() {
    document.getElementById("iframe").src = './welcome.jsp';
}
;

function changeFrameHeight() {
    var ifm = document.getElementById("iframe");
    ifm.height = document.documentElement.clientHeight;
}

window.onresize = function () {
    changeFrameHeight();
};




