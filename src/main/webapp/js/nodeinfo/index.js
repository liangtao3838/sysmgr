    $(function () {
        InitNodeInfoQueryTableParam();
    });
    var curField = 0;
    var selectValue;
    function InitNodeInfoQueryTableParam() {
        $('#nodeInfoQueryTable').bootstrapTable('destroy');
        $('#nodeInfoQueryTable').bootstrapTable({
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
            url: "/nodeinfo/list.do",
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
                field: 'nodeCode',
                title: '节点编码',
                width: '20%',
            },{
                field: 'nodeName',
                title: '节点名称',
                width: '20%',
            },{
                field: 'ipAddr',
                title: 'ip地址',
                width: '20%',
            },{
                field: 'callAddr',
                title: '调用地址',
                width: '20%',
            },{
                field: 'yn',
                title: '有效表示',
                width: '20%',
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
        InitNodeInfoQueryTableParam();
    }

    //添加表单重置
    function resetForm(){
        $("#nodeCode").val("");
        $("#nodeName").val("");
    };

    //封装提交表单参数
    function getFormData(){
        var form_data={};
        form_data.nodeCode=$("#nodeCode").val();
        form_data.nodeName=$("#nodeName").val();
        form_data.ipAddr=$("#ipAddr").val();
        form_data.callAddr=$("#callAddr").val();
        return form_data;
    };

    //封装提交表单参数
    function getUpdateFormData(){
        var form_data={};
        form_data.id=$("#updateId").val();
        form_data.nodeCode=$("#updatenodeCode").val();
        form_data.nodeName=$("#updatenodeName").val();
        form_data.ipAddr=$("#updateipAddr").val();
        form_data.callAddr=$("#updatecallAddr").val();
        return form_data;
    };

    $("#resetBtn_nodeinfo").click(function(){
        resetForm();
    });

    //打开增加表单
    function insert(){
        $("#myModalLabel4").text("添加节点信息");
        $("#nodeInfoConfigModal").modal("show");
        resetForm();
    };

    //添加表单提交事件
    $("#submitBtn_nodeinfo").click(function(){
        var data=getFormData();
        $.ajax({
            type: "POST",
            url: "/nodeinfo/add.do",
            data: data,	//传入已封装的参数
            success:function(data){
                resetForm();
                $("#nodeInfoConfigModal").modal('hide');
                var data=JSON.parse(data);
                if(data.code==1){
                    $.dialog.success("添加成功");
                    InitNodeInfoQueryTableParam();
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
            url: "/nodeinfo/query.do",
            data: {id:id},	//传入已封装的参数
            dataType: "json",
            success:function(data){
                if(data.code==1){
                    $("#updateId").val(id);
                    $("#updatenodeCode").val(data.result.nodeCode);
                    $("#updatenodeName").val(data.result.nodeName);
                    $("#updateipAddr").val(data.result.ipAddr);
                    $("#updatecallAddr").val(data.result.callAddr);
                    $("#updatemyModalLabel4").text("修改节点信息");
                    $("#updatenodeInfoConfigModal").modal("show");
                }
            }
        });
    }

    //添加表单提交事件
    $("#submitBtn_updatenodeinfo").click(function(){
        var data=getUpdateFormData();
        $.ajax({
            type: "POST",
            url: "/nodeinfo/update.do",
            data: data,	//传入已封装的参数
            success:function(data){
                var data=JSON.parse(data);
                if(data.code==1){
                    $.dialog.success("修改成功");
                    $("#updatenodeInfoConfigModal").modal('hide');
                    InitNodeInfoQueryTableParam();
                }else{
                    $("#updatenodeInfoConfigModal").modal('hide');
                    $.dialog.error("修改失败");
                }
            }
        });
    });


    //删除记录（修改状态）
    function deleteRecord(){
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
            $.ajax({
                type: "POST",
                url: "/nodeinfo/delete.do",
                data: {
                    idlist : id
                },
                dataType: "json",
                success: function(data){
                    var rs = data;
                    if(rs.code > 0){
                        InitNodeInfoQueryTableParam();
                    } else{
                        $.dialog.error("删除记录失败!")
                    }
                }
            });
        }
    };