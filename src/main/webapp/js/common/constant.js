var CONSTANT = {
    DATA_TABLES : {
        DEFAULT_OPTION : { //DataTables初始化选项
            "paging": true,
            "bLengthChange": true,
            "pageLength":10,
            "searching": false,
            "ordering": false,
            "info": true,
            "retrieve": true,
            "processing":true,
            "sPaginationType" : "full_numbers",
            "serverSide":true,
            "sServerMethod":"POST",
            "bAutoWidth": true,
            language: {
                lengthMenu : "每页显示 _MENU_记录",
                zeroRecords : "没有匹配的数据",
                sProcessing:   "数据加载中...",
                info : "第_PAGE_页/共 _PAGES_页",
                infoEmpty : "没有符合条件的记录",
                search : "搜索",
                infoFiltered : "(从 _MAX_条记录中过滤)",
                paginate : { "first" : "首页 ", "last" : "末页", "next" : "下一页", "previous" : "上一页"}
            }
        }
    },
    CONTEXT_PATH:"http://main.alpha.jd.com"
};