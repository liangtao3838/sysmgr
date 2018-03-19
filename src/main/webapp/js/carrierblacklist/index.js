var _cbl = {
    initDataTable : function(){
        var oper = $("#oper").html();
        //预编译模板
        var template_oper = Handlebars.compile(oper);
        _cbl.datatable = $('#data-table').dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION,{
            ajax : function(data, callback, settings) {//ajax配置为function,手动调用异步查询
                //封装请求参数
                var param = _cbl.getQueryCondition(data);
                $.ajax({
                    type: "POST",
                    url: "/carrierBl/list.do",
                    cache : false,	//禁用缓存
                    data: param,	//传入已封装的参数
                    dataType: "json",
                    success:function(result){
                        result = JSON.parse(result);
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.recordsTotal;
                        returnData.recordsFiltered = result.recordsTotal;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data;
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }
                });
            },
            columns:[
                {data:"carrierId"},
                {data:"carrierName"},
                {
                    data : "carrierStatus",
                    render : function(data,type, row, meta) {
                        return (data=='black'?"黑名单":"白名单");
                    }
                },
                {data:"id"},
                {data:"updatePin"},
                {data:"updateTimeStr"}
            ],
            columnDefs: [
                {
                    targets: 3,
                    bSortable : false,
                    render: function (a, b, c, d) {
                        var status = c.carrierStatus;
                        var checkedFlag = "";
                        if(status=='black'){
                            checkedFlag = "checked";
                        }
                        var context =
                        {
                            func: [
                                {"checkedFlag":checkedFlag,"fn": "_cbl.changeStatus(this,\'" + c.id + "\')"}
                            ]
                        };
                        var html = template_oper(context);
                        return html;
                    }
                }
            ]
        })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
    },
    getQueryCondition : function(data) {
        var param = {};
        //组装查询参数
        param.carrierId = $("#carrierIdTxt_query").val();
        param.carrierName = $("#carrierNameTxt_query").val();

        //组装分页参数
        param.start = data.start;
        param.length = data.length;

        param.draw = data.draw;
        return param;
    },
    query:function() {
        _cbl.datatable.draw();
    },
    changeStatus:function(obj,id){
        var status_zh_cn ;
        var params={};
        params.id=id;
        if($(obj).is(":checked")){
            params.status = 'black';
            status_zh_cn = '黑名单';
        }else{
            params.status = 'white';
            status_zh_cn = '白名单';
        }
        $.ajax({
            type: "POST",
            url: "/carrierBl/changeStatus.do",
            data: params,	//传入已封装的参数
            async: false,
            cache: false,
            dataType: "json",
            success:function(data){
               data = JSON.parse(data);
               if(data.msg=='ok'){
                   $(obj).parent().prev().text(status_zh_cn);
                   $.dialog.tips("修改成功！");
               }else{
                   $.dialog.alert("修改失败，请稍后重试！");
               }
            }
        });
    },
    resetQueryForm: function(){
        $("#carrierIdTxt_query").val("");
        $("#carrierNameTxt_query").val("");
    },
    initAutosuggest:function(type){
        //承运商ID支持联想输入，输入完毕后自动带出商家名称：
        $("#carrierIdTxt"+type).autosuggest({
            url: '/common/queryTenCarrierId.do',
            method: 'post',
            queryParamName: 'carrierId',
            onSelect: function (data) {
                //根据承运商ID查询名称：
                _cbl.setCarrierName(data.data("value"),type);
            }
        }).blur(function(){
            _cbl.setCarrierName($(this).val(),type);
        });
        //商家名称支持联想输入：
        $("#carrierNameTxt"+type).autosuggest({
            url: '/common/queryTenCarrierName.do',
            method: 'post',
            queryParamName: 'carrierName',
            onSelect: function (data) {
                $("#carrierIdTxt"+type).val(data.data('id'));
            }
        }).blur(function(){
            _cbl.setCarrierId($(this).val(),type);
        });
    },
    setCarrierName : function(carrierId,type){
        $.post("/common/queryCarrierNameById.do",
            {carrierId:carrierId},
            function(data){
                data = JSON.parse(data);
                var carrierName = data.carrierName;
                if(!carrierName) carrierName="";
                $("#carrierNameTxt"+type).val(carrierName);
            },'json');
    },
    setCarrierId : function(carrierName,type){
        $.post("/common/getCarrierIdByName.do",
            {carrierName:carrierName},
            function(data){
                data = JSON.parse(data);
                var carrierId = data.carrierId;
                if(!carrierId) carrierId="";
                $("#carrierIdTxt"+type).val(carrierId);
            },'json');
    }
}
$(function () {
    //渲染数据表格：
    _cbl.initDataTable();
    //初始化[查询页面]联想输入控件：
    _cbl.initAutosuggest("_query");

    //立即生效按钮点击事件：
    $("#freshBtn").click(function(){
        $.post("/carrierBl/refreshCache.do",
            function(data){
                $.dialog.tips(JSON.parse(data).msg)
            });
    });
});