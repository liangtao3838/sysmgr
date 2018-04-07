$(function () {
    InitSysServiceQueryTableParam();
});
var curField = 0;
var selectValue;
function InitSysServiceQueryTableParam() {
    $('#sysServiceQueryTable').bootstrapTable('destroy');
    $('#sysServiceQueryTable').bootstrapTable({
        method: 'post',
        height : 500,
        classes:'table table-hover',
        striped: true,
        cache: false,
        pagination: true,
        onPageChange : function (number,size) {
            currentPageSize = size;
            return{
                number : number,
                size : size
            };
        },
        sortable: true,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        sortOrder: "asc",
        pageSize:10,
        pageNumber:1,
        pageList: [10,25,50,100],
        url: "/sysservice/list.do",
        queryParams: function(params) {
            return {
                page: params.offset,
                rows: params.limit,
            };
        },
        sidePagination: "server",
        search: false,
        strictSearch: true,
        singleSelect:true,
        clickToSelect: true,
        responseHandler : function(res) {
            return res.result;
        },
        onCheck: function (row, $element) {
            if (curField === 0) {
                selectValue=row;
            } else {
                curField = 0;
            }
        },
        columns: [{
            field:'check',
            radio: 'true'
        },{
            field: 'serviceName',
            title: '服务名称',
            width: '15%',
        },{
            field: 'nodeCode',
            title: '节点编码',
            width: '15%',
        },{
            field: 'nodeName',
            title: '节点名称',
            width: '15%',
        },{
            field: 'serviceAddr',
            title: '服务地址',
            width: '15%',
        },{
            field: 'methodName',
            title: '方法名称',
            width: '15%',
        },{
            field: 'protocolType',
            title: '协议类型',
            width: '15%',
        }],
    });

    ////一：全选操作
    //$("#check-all").click(function(){
    //    var checked = $(this).is(":checked");
    //    $('input[name="chkbox_pop"]').each(function(){
    //        if(checked){
    //            this.checked = "checked";
    //        }else{
    //            this.checked = "";
    //        }
    //    });
    //});
};
//init end

//查询
function query() {
    InitSysServiceQueryTableParam();
}

//添加表单重置
function resetForm(){
    $("#serviceName").val("");
    $("#serviceAddr").val("");
    $("#nodeCode").val("");
    $("#nodeName").val("");
    $("#methodName").val("");
    $("#protocolType").val("");
};

//封装提交表单参数
function getFormData(){
    var form_data={};
    form_data.serviceName=$("#serviceName").val();
    form_data.serviceAddr=$("#serviceAddr").val();
    form_data.nodeCode=$("#nodeCode").val();
    form_data.nodeName=$("#nodeName").val();
    form_data.methodName=$("#methodName").val();
    form_data.protocolType=$("#protocolType").val();
    return form_data;
};

//封装提交表单参数
function getUpdateFormData(){
    var form_data={};
    form_data.id=$("#updateId").val();
    form_data.serviceName=$("#updateserviceName").val();
    form_data.serviceAddr=$("#updateserviceAddr").val();
    form_data.nodeCode=$("#updatenodeCode").val();
    form_data.nodeName=$("#updatenodeName").val();
    form_data.methodName=$("#updatemethodName").val();
    form_data.protocolType=$("#updateprotocolType").val();
    return form_data;
};

$("#resetBtn_sysservice").click(function(){
    resetForm();
});

//打开增加表单
function add(){
    $("#myModalLabel4").text("添加服务关系信息");
    $("#sysserviceConfigModal").modal("show");
    resetForm();
};

//添加表单提交事件
$("#submitBtn_sysservice").click(function(){
    var data=getFormData();
    $.ajax({
        type: "POST",
        url: "/sysservice/add.do",
        data: data,	//传入已封装的参数
        success:function(data){
            resetForm();
            $("#sysserviceConfigModal").modal('hide');
            var data=JSON.parse(data);
            if(data.code==1){
                $.dialog.success("添加成功");
                InitSysServiceQueryTableParam();
            }else{
                $.dialog.error("添加失败");
            }
        }
    });
});

function update(){
    if(typeof(selectValue) == "undefined"){
        $.dialog.error("请选中一行记录");
        return;
    }
    if (!selectValue && typeof(selectValue)!="undefined" && selectValue!=0)
    {
        $.dialog.error("请选中一行记录");
        return;
    }
    var id=selectValue.id;
    $.ajax({
        type: "POST",
        url: "/sysservice/query.do",
        data: {id:id},	//传入已封装的参数
        dataType: "json",
        success:function(data){
            if(data.code==1){
                $("#updateId").val(id);
                $("#updateserviceName").val(data.result.serviceName);
                $("#updateserviceAddr").val(data.result.serviceAddr);
                $("#updatenodeCode").val(data.result.nodeCode);
                $("#updatenodeName").val(data.result.nodeName);
                $("#updatemethodName").val(data.result.methodName);
                $("#updateprotocolType").val(data.result.protocolType);
                $("#updatemyModalLabel4").text("修调用服务信息");
                $("#updatesysserviceConfigModal").modal("show");
            }
        }
    });
}

//添加表单提交事件
$("#submitBtn_updatesysservice").click(function(){
    var data=getUpdateFormData();
    $.ajax({
        type: "POST",
        url: "/sysservice/update.do",
        data: data,	//传入已封装的参数
        success:function(data){
            var data=JSON.parse(data);
            if(data.code==1){
                $.dialog.success("修改成功");
                $("#updatesysserviceConfigModal").modal('hide');
                InitSysServiceQueryTableParam();
            }else{
                $("#updatesyserviceConfigModal").modal('hide');
                $.dialog.error("修改失败");
            }
        }
    });
});

//删除记录（修改状态）
function del(){
    if(typeof(selectValue) == "undefined"){
        $.dialog.error("请选中一行记录");
        return;
    }
    if (!selectValue && typeof(selectValue)!="undefined" && selectValue!=0)
    {
        $.dialog.error("请选中一行记录");
        return;
    }
    var id=selectValue.id;
    var information = "确定要删除该条数据";
    if(confirm(information)){
        idArr = idArr.toString();
        $.ajax({
            type: "POST",
            url: "/sysservice/delete.do",
            data: {
                idlist : id
            },
            dataType: "json",
            success: function(data){
                var rs = data;
                if(rs.code > 0){
                    InitSysServiceQueryTableParam();
                } else{
                    $.dialog.error("删除记录失败!")
                }
            }
        });
    }
};