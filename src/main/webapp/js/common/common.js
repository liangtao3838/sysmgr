//input格式校验：只允许正整数
function onlyNumber(thisTextBox){
    thisTextBox.value = thisTextBox.value.replace(/\D/g,'');
}
//input格式校验：只允许浮点型（保留两位小数）
function onlyFloat(thisTextBox){
    thisTextBox.value = thisTextBox.value.replace(/[^\d.]/g,'')
        .replace(/^\./g,'').replace(/\.{2,}/g, ".")
        .replace(".", "$#$").replace(/\./g, "").replace("$#$", ".")
        .replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
}
function checkVenderId (venderId){
    var isExist = false;
    var params={};
    params.venderId=venderId;
    $.ajax({
        type: "POST",
        url: "/common/checkVenderId.do",
        data: params,	//传入已封装的参数
        async: false,
        cache: false,
        dataType: "json",
        success:function(data){
            data = JSON.parse(data);
            if(data.msg =='ok' && data.recNum > 0){
                isExist = true;
            }
        }
    });
    return isExist;
}

function checkCarrierId (carrierId){
    var isExist = false;
    var params={};
    params.carrierId=carrierId;
    $.ajax({
        type: "POST",
        url: "/common/checkCarrierIdExist.do",
        data: params,	//传入已封装的参数
        async: false,
        cache: false,
        dataType: "json",
        success:function(data){
            data = JSON.parse(data);
            if(data.msg =='ok' && data.recNum > 0){
                isExist = true;
            }
        }
    });
    return isExist;
}
String.prototype.endWith=function(s){
    if(s==null||s==""||this.length==0||s.length>this.length)
        return false;
    if(this.substring(this.length-s.length)==s)
        return true;
    else
        return false;
    return true;
}
