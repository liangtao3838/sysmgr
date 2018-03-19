$(function () {
    InitSysCallRelaQueryTableParam();
});
function InitSysCallRelaQueryTableParam() {
    $('#sysCallrelaQueryTable').bootstrapTable('destroy');
    $('#sysCallrelaQueryTable').bootstrapTable({
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
        url: "/syscallrela/list.do",
        queryParams: function(params) {
            return {
                page: params.offset,
                rows: params.limit,
            };
        },
        sidePagination: "server",
        search: false,
        strictSearch: true,
        responseHandler : function(res) {
            return res.result;
        },
        columns: [{
            field: 'id',
            title: "<input type='checkbox' id='check-all' name='check-all' >全选",
            width: '10%',
            formatter:function(value,row,index){
                var html;
                var html = "<input type=\"checkbox\" name=\"chkbox_pop\" value='"+row.id+"'/>";
                return html;
            }
        },{
            field: 'routeUuid',
            title: '路由UUID',
            width: '20%',
        },{
            field: 'nowRouteNode',
            title: '当前路由节点',
            width: '30%',
        },{
            field: 'nextRouteNode',
            title: '下一个路由节点',
            width: '30%',
        },{
            field: 'protocolUuid',
            title: '协议UUID',
            width: '30%',
        }],
    });

    //一：全选操作
    $("#check-all").click(function(){
        var checked = $(this).is(":checked");
        $('input[name="chkbox_pop"]').each(function(){
            if(checked){
                this.checked = "checked";
            }else{
                this.checked = "";
            }
        });
    });
};
//init end

//查询
function query() {
    InitSysCallRelaQueryTableParam();
}

//添加表单重置
function resetForm(){
    $("#routeUuid").val("");
    $("#nowRouteNode").val("");
    $("#nextRouteNode").val("");
    $("#protocolUuid").val("");
};

//封装提交表单参数
function getFormData(){
    var form_data={};
    form_data.routeUuid=$("#routeUuid").val();
    form_data.nowRouteNode=$("#nowRouteNode").val();
    form_data.nextRouteNode=$("#nextRouteNode").val();
    form_data.protocolUuid=$("#protocolUuid").val();
    return form_data;
};

//封装提交表单参数
function getUpdateFormData(){
    var form_data={};
    form_data.id=$("#updateId").val();
    form_data.routeUuid=$("#updaterouteUuid").val();
    form_data.nowRouteNode=$("#updatenowRouteNode").val();
    form_data.nextRouteNode=$("#updatenextRouteNode").val();
    form_data.protocolUuid=$("#updateprotocolUuid").val();
    return form_data;
};

$("#resetBtn_syscallrela").click(function(){
    resetForm();
});

//打开增加表单
function add(){
    $("#myModalLabel4").text("添加调用关系信息");
    $("#syscallrelaConfigModal").modal("show");
    resetForm();
};

//添加表单提交事件
$("#submitBtn_syscallrela").click(function(){
    var data=getFormData();
    $.ajax({
        type: "POST",
        url: "/syscallrela/add.do",
        data: JSON.stringify(data),	//传入已封装的参数
        contentType:"application/json;charset=utf-8",
        dataType: "json",
        success:function(data){
            resetForm();
            $("#syscallrelaConfigModal").modal('hide');
            if(data.code==1){
                $.dialog.success("添加成功");
                InitSysCallRelaQueryTableParam();
            }else{
                $.dialog.error("添加失败");
            }
        }
    });
});

function update(){
    var idArr = new Array();
    $('input[name="chkbox_pop"]:checked').each(function(){
        idArr.push($(this).val());
    });
    var id = idArr[0];
    $.ajax({
        type: "POST",
        url: "/syscallrela/query.do",
        data: {id:id},	//传入已封装的参数
        dataType: "json",
        success:function(data){
            if(data.code==1){
                console.log(data)
                $("#updateId").val(id);
                $("#updaterouteUuid").val(data.result.routeUuid);
                $("#updateprotocolUuid").val(data.result.protocolUuid);
                $("#updatenowRouteNode").val(data.result.nowRouteNode);
                $("#updatenextRouteNode").val(data.result.nextRouteNode);
                $("#updatemyModalLabel4").text("修改节点信息");
                $("#updatesyscallrelaConfigModal").modal("show");
            }
        }
    });
}

//添加表单提交事件
$("#submitBtn_updatesyscallrela").click(function(){
    var data=getUpdateFormData();
    $.ajax({
        type: "POST",
        url: "/syscallrela/update.do",
        data: JSON.stringify(data),	//传入已封装的参数
        contentType:"application/json;charset=utf-8",
        dataType: "json",
        success:function(data){
            if(data.code==1){
                $.dialog.success("修改成功");
                $("#updatesyscallrelaConfigModal").modal('hide');
                InitSysCallRelaQueryTableParam();
            }else{
                $("#updatesyscallrelaConfigModal").modal('hide');
                $.dialog.error("修改失败");
            }
        }
    });
});

//删除记录（修改状态）
function del(){
    var idArr = new Array();
    $('input[name="chkbox_pop"]:checked').each(function(){
        idArr.push($(this).val());
    });
    if(idArr.length == 0){
        $.dialog.alert("提示：没有记录选中!");
        return ;
    }
    var information = "确定要删除该"+idArr.length+"条数据";
    if(confirm(information)){
        idArr = idArr.toString();
        $.ajax({
            type: "POST",
            url: "/syscallrela/delete.do",
            data: {
                idlist : idArr
            },
            dataType: "json",
            success: function(data){
                var rs = data;
                if(rs.code > 0){
                    InitSysCallRelaQueryTableParam();
                } else{
                    $.dialog.error("删除记录失败!")
                }
            }
        });
    }
};