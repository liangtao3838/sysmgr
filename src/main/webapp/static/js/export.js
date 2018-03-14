function query(page,flag){
    $("[name='_f']","#queryForm").val(flag);
    if(page != 0){
        if(page < 0){
            page = 1;
        }
        $("[name='page']","#queryForm").val(page);
    }
    $("#queryForm").submit();
}

$(function() {
    $("#queryDataBtn").off('click').on('click',function(e){
        e.preventDefault();
        query(0, "Q");
    });
    $("#updateDataBtn").off('click').on('click',function(e){
        e.preventDefault();
        $("#isUpdate").val("1");

    });
    $("#exportDataBtn").off('click').on('click',function(e){
        e.preventDefault();
        query(0, "E");
    });
    $("input","#queryForm").on('keypress',function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            e.preventDefault();
            query(0, "Q");
        }
    });
    $("#nextPage","#queryForm").off('click').on('click',function(){
        query(parseInt($("[name='page']","#queryForm").val())+1, "Q");
    });
    $("#prervPage","#queryForm").off('click').on('click',function(){
        query(parseInt($("[name='page']","#queryForm").val())-1, "Q");
    });
    $("textarea[name=qlText]","#queryForm").on('keypress',function(e){
        var ev = document.all ? window.event : e;
        //console.log(ev.keyCode);
        //console.log(ev.ctrlKey);
        if(ev.ctrlKey && ev.keyCode==10){
            e.preventDefault();
            query(0, "Q");
        }
    });
});
