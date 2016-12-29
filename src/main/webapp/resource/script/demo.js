function verify(){

    var userName = $("#userName").val();

    var passWord = $("#passWord").val();
    console.log("userName=["+userName+"]"+"passWord=["+passWord+"]");
    $.post("/login/auth",{ userName: userName, passWord: passWord });
}

function getUser(){
    console.log('getUser');
    $.get("getUser",{ userNameId: 1 },getUserCallback);
}

function callback(data){
    $("#result").html(data);
}

function getUserCallback(data){
    var userString = JSON.stringify(data);
    $("#result").html(userString);
}