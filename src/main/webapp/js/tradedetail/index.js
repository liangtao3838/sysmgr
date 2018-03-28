    $(function () {
        InitTradeDetailQueryTableParam();
    });
    $(function(){
        $('input[name="date"]').on('click', function () {
            WdatePicker({
                skin: 'default',
                dateFmt: 'yyyy-MM-dd',
            });
        });
    });

    function InitTradeDetailQueryTableParam() {
        $('#tradedetailQueryTable').bootstrapTable('destroy');
        $('#tradedetailQueryTable').bootstrapTable({
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
            url: "/tradedetail/list.do",
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
            idField : "id",
            columns: [{
                field: 'jkmc',
                title: '接口名称',
                width: '9%',
            },{
                field: 'qqxt',
                title: '请求系统',
                width: '9%',
            },{
                field: 'mbxt',
                title: '目标系统',
                width: '9%',
            },{
                field: 'mbjk',
                title: '目标接口名称',
                width: '9%',
            },{
                field: 'qqxml',
                title: '请求xml',
                width: '9%',
            },{
                field: 'fhxml',
                title: '返回xml',
                width: '9%',
                formatter : function (dataValue,row,index) {
                    return '<a href="#" onclick="queryqqxml('+row.id+')">查看</a>';
                }
            },{
                field: 'xyxml',
                title: '相应xml',
                width: '9%',
            },{
                field: 'ycxx',
                title: '异常信息',
                width: '9%',
            },{
                field: 'jdwz',
                title: '阶段位置',
                width: '9%',
            },{
                field: 'zt',
                title: '系统异常状态',
                width: '9%',
            },{
                field: 'zt',
                title: '业务异常状态',
                width: '9%',
            },{
                field: 'lrrq',
                title: '录入日期',
                width: '9%',
            }],
        });
    };
    //init end

    //查询
    function query() {
        InitTradeDetailQueryTableParam();
    }

    function queryqqxml(id){
        $.ajax({
            type: "POST",
            url: "/tradedetail/getxml.do",
            data: {
                id:id,
                type:"qqxml"
            },	//传入已封装的参数
            dataType: "json",
            success:function(data){
                if(data.code==1){
                    $("#responseInfo").val(data.result);
                    $("#tradedetailConfigModal").modal("show");
                }else{
                    $.dialog.error("查询失败");
                }
            }
        });
    }

