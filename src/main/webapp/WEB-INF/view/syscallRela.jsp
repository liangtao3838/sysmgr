<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>系统调用关系配置管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/autosuggest.min.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap-table.min.css">

    <link rel="stylesheet" href="/bootstrap/AdminLTE-2.3.0/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="/bootstrap/AdminLTE-2.3.0/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/bootstrap/AdminLTE-2.3.0/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/bootstrap/AdminLTE-2.3.0/plugins/font-awesome-4.4.0/css/font-awesome.min.css">
    <html>
    <head>
        <title>系统调用关系配置管理</title>
    </head>
<body>
<section class="content-header">
    <h1>
        系统调用关系配置管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="/home.do"><i class="fa fa-dashboard"></i>主页</a></li>
        <li class="active">拓扑管理</li>
        <li class="active">系统调用关系配置管理</li>
    </ol>
</section>
<div class="panel panel-primary" style="margin-top: 10px; margin-left: 4px; margin-right: 4px">
    <div class="operDiv">
        <span class="msgSpan"></span>
        <button type="button" class="btn btn-info"  onclick="query()">查询</button>
        <button type="button" class="btn btn-warning"  onclick="add()">新增</button>
        <button type="button" class="btn btn-primary"  onclick="update()">修改</button>
        <button type="button" class="btn btn-danger"   onclick="del()">删除</button>
    </div>
    <!--添加页面model-begin-->
    <div class="modal fade" id="syscallrelaConfigModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
        <div class="modal-dialog modal-md" role="document">
            <form id="addsyscallrela" method="post" action="">
                <input type="hidden" id="addId"/>
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel4">系统调用关系信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <span class="input-group-addon"><b>路由UUID</b></span>
                            <div class="col-xs-6" style="padding:0">
                                <input type="text" id="routeUuid" class="form-control"/>
                            </div>
                            <span class="input-group-addon"><b>当前路由节点</b></span>
                            <div class="col-xs-6" style="padding:0;">
                                <input type="text" id="nowRouteNode" class="form-control" />
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><b>协议UUID</b></span>
                            <div class="col-xs-6" style="padding:0;">
                                <input type="text" id="protocolUuid" class="form-control" />
                            </div>
                            <span class="input-group-addon"><b>下个路由节点</b></span>
                            <div class="col-xs-6" style="padding:0">
                                <input type="text" id="nextRouteNode" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="hanging-punctuation: none;margin-left: auto">
                        <button type="button" class="btn btn-primary" id="submitBtn_syscallrela">保存</button>
                        <button type="button" class="btn btn-warning" id="resetBtn_syscallrela">重置</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

<!--修改页面model-begin-->
<div class="modal fade" id="updatesyscallrelaConfigModal" tabindex="-1" role="dialog" aria-labelledby="updatemyModalLabel4">
    <div class="modal-dialog modal-md" role="document">
        <form id="updatesyscallrela" method="post" action="">
            <input type="hidden" id="updateId"/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="updatemyModalLabel4">节点信息</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><b>路由UUID</b></span>
                        <div class="col-xs-6" style="padding:0">
                            <input type="text" id="updaterouteUuid" class="form-control"/>
                        </div>
                        <span class="input-group-addon"><b>当前路由节点</b></span>
                        <div class="col-xs-6" style="padding:0;">
                            <input type="text" id="updatenowRouteNode" class="form-control" />
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><b>协议UUID</b></span>
                        <div class="col-xs-6" style="padding:0;">
                            <input type="text" id="updateprotocolUuid" class="form-control" />
                        </div>
                        <span class="input-group-addon"><b>下个路由节点</b></span>
                        <div class="col-xs-6" style="padding:0">
                            <input type="text" id="updatenextRouteNode" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="hanging-punctuation: none;margin-left: auto">
                    <button type="button" class="btn btn-primary" id="submitBtn_updatesyscallrela">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="panel panel-primary"  style="margin-top: 20px; margin-left: 4px; margin-right: 4px">
    <div class="panel-body">
        <table  id="sysCallrelaQueryTable"></table>
    </div>
</div>
<script src="/js/common/jquery.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap-table.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/autosuggest.js"></script>
<script src="/bootstrap/AdminLTE-2.3.0/plugins/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<script src="/js/syscallrela/index.js"></script>
<script src="/js/common/common.js"></script>


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


