<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>节点配置管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-3.3.5/css/autosuggest.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-3.3.5/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-3.3.5/css/bootstrap-table.min.css">

    <link rel="stylesheet" href="<%=basePath%>/bootstrap/AdminLTE-2.3.0/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/AdminLTE-2.3.0/css/AdminLTE.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/AdminLTE-2.3.0/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/AdminLTE-2.3.0/plugins/font-awesome-4.4.0/css/font-awesome.min.css">
    <html>
    <head>
        <title>节点配置管理</title>
    </head>
<body>
<section class="content-header">
    <h1>
        节点配置管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="/home.do"><i class="fa fa-dashboard"></i>主页</a></li>
        <li class="active">界面展示</li>
        <li class="active">交易明细</li>
    </ol>
</section>
<div class="panel panel-primary" style="margin-top: 10px; margin-left: 4px; margin-right: 4px">
    <table class="table" width="100%">
        <table class="table" width="100%">
            <tr>
                <td>
                    <div class="input-group">
                        <span class="input-group-addon">接口名称</span>
                        <input name = "syscallname" id="syscallname" type="text" class="form-control" placeholder="接口名称">
                        <span class="input-group-addon">请求系统</span>
                        <input name = "qqxt" id="qqxt" type="text" class="form-control" placeholder="请求系统">
                        <span class="input-group-addon">业务异常状态</span>
                        <select id="zt" name="zt" style="width: 80px;" class="form-control">
                            <option value="0">成功</option>
                            <option value="1">失败</option>
                        </select>
                        <span class="input-group-addon">录入日期起</span>
                        <input name = "date" id="startDate" type="text" class="form-control">
                        <span class="input-group-addon">录入日期止</span>
                        <input name = "date" id="endDate" type="text" class="form-control">
                    </div>
                    <button type="button" class="btn btn-info" onclick="query()">查询</button>
                </td>
            </tr>
        </table>
    </table>
</div>

<!--添加页面model-begin-->
<div class="modal fade" id="tradedetailConfigModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
    <div class="modal-dialog modal-md" role="document">
        <div class="input-group" >
            <h3 class="modal-title" style="background-color: #3c8dbc" id="myModalLabel4">XML信息</h3>
            <textarea name = "responseInfo" readonly id="responseInfo" style="font-size: smaller;width:400px;height: 400px;" class="form-control" placeholder=""></textarea>
        </div>
    </div>
</div>

<div class="panel panel-primary"  style="margin-top: 20px; margin-left: 4px; margin-right: 4px">
    <div class="panel-body">
        <table  id="tradedetailQueryTable"></table>
    </div>
</div>



<script src="<%=basePath%>/js/common/jquery.js"></script>
<script src="<%=basePath%>/bootstrap/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/bootstrap/bootstrap-3.3.5/js/bootstrap-table.min.js"></script>
<script src="<%=basePath%>/bootstrap/bootstrap-3.3.5/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=basePath%>/bootstrap/bootstrap-3.3.5/js/autosuggest.js"></script>
<script src="<%=basePath%>/bootstrap/AdminLTE-2.3.0/plugins/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<script src="<%=basePath%>/js/common/common.js"></script>
<script src="<%=basePath%>/js/tradedetail/index.js"></script>
<script src="<%=basePath%>/js/common/wdatepicker/WdatePicker.js"></script>
</body>
</html>
<style>
    .table {
        table-layout: fixed;
        width: 100% !important;
    }
    .msgspan{
        font-family: "'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif";
        font-weight: bold;
        font-size: 14px;
        color:#3c8dbc;
    }
    .operDiv{
        text-align: left;
        margin: 0rem;
    }
</style>


