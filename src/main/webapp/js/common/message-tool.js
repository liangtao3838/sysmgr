function showWarning(msg){
    $(".msgSpan") .css("color","orange");
    showMsg(msg);
}
function showSuccess(msg){
    $(".msgSpan") .css("color","green");
    showMsg(msg);
}
function showError(msg){
    $(".msgSpan") .css("color","red");
    showMsg(msg);
}
function showMsg(msg){
    $(".msgSpan").text(msg)
        .css("display","inline-block");
    setTimeout(function(){//定时器
        $(".msgSpan").css("display","none");//将信息提示SPAN的display属性设置为none
    },2000);//设置两千毫秒即2秒
}