<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>节点管理配置界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/js/bootstrap-3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/js/bootstrap-3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/js/bootstrap-3.3.5/css/bootstrap-table.min.css">

    <link rel="stylesheet" href="/js/AdminLTE-2.3.0/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="/js/AdminLTE-2.3.0/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/js/AdminLTE-2.3.0/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/js/AdminLTE-2.3.0/plugins/font-awesome-4.4.0/css/font-awesome.min.css">
    <html>
    <head>
        <title>节点管理配置界面</title>
    </head>
<body>
<section class="content-header">
    <h1>
        节点管理配置界面
    </h1>
    <ol class="breadcrumb">
        <li><a href="/home.do"><i class="fa fa-dashboard"></i>主页</a></li>
        <li class="active">系统管理</li>
        <li class="active">节点管理配置界面</li>
    </ol>
</section>
<div class="panel panel-primary" style="margin-top: 10px; margin-left: 4px; margin-right: 4px">
    <div class="panel-body">
        <div class="operDiv">
            <span class="msgSpan"></span>
            <button type="button" class="btn btn-info"  onclick="addinfo()">新增</button>
            <button type="button" class="btn btn-warning"  onclick="updateinfo()">修改</button>
            <button type="button" class="btn btn-primary"  onclick="deleteinfo()">删除</button>
        </div>
    </div>
    <!--[添加黑名单]添加页面model-begin-->
    <div class="modal fade" id="venderRouteAgingConfigModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
        <div class="modal-dialog modal-md" role="document">
            <form id="" method="post" action="">
                <input type="hidden" id="id_venderRoute_edit"/>
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel4">添加节点信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <span class="input-group-addon" style="width:93px;"><b>承运商ID</b></span>
                            <div class="col-xs-6" style="padding:0">
                                <input type="text" id="carrierIdAdd" onkeyup="onlyNumber(this);" class="form-control"/>
                            </div>
                            <span class="input-group-addon"><b>承运商名称</b></span>
                            <div class="col-xs-10" style="padding:0;">
                                <input type="text" id="carrierNameAdd" class="form-control" />
                            </div>
                        </div>
                        <div class="input-group" style="margin-top:10px">
                            <span class="input-group-addon" style="width:93px;"><b>发货地址</b></span>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="shipFirstAddrAdd" onchange="getChildNodes(this,'ship',2,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="shipSecondAddrAdd" onchange="getChildNodes(this,'ship',3,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="shipThridAddrAdd" onchange="getChildNodes(this,'ship',4,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0" >
                                <select class="form-control" id="shipForthAddrAdd">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="input-group" style="margin-top:10px">
                            <span class="input-group-addon" style="width:93px;"><b>收货地址</b></span>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="delvFirstAddrAdd"  onchange="getChildNodes(this,'delv',2,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="delvSecondAddrAdd"  onchange="getChildNodes(this,'delv',3,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="delvThridAddrAdd" onchange="getChildNodes(this,'delv',4,'Add');">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                            <div class="col-xs-3" style="padding:0">
                                <select class="form-control" id="delvForthAddrAdd" >
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="submitBtn_venderRoute">保存</button>
                        <button type="button" class="btn btn-warning" id="resetBtn_venderRoute">重置</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--[添加黑名单]添加页面model-begin-->
</div>
<div class="panel panel-primary"  style="margin-top: 20px; margin-left: 4px; margin-right: 4px">
    <div class="panel-body">
        <table  id="carrierRouterBlackQueryTable"></table>
    </div>
</div>
<script src="/js/jquery.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap-table.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/bootstrap/bootstrap-3.3.5/js/autosuggest.js"></script>
<script src="/bootstrap/AdminLTE-2.3.0/plugins/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<script src="/js/carrierrouterblack/carrierrouterblack.js"></script>
<script src="/js/common.js"></script>


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
        text-align: right;
        margin: 1rem;
    }
</style>

