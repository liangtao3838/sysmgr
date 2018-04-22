<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统监控</title>
    <script src="/dist/echarts.js"></script>
	<script src="/static/lib/esl.js"></script>
	<script src="/static/lib/config.js"></script>
	<script src="/static/lib/jquery.min.js"></script>
	<script src="/static/lib/facePrint.js"></script>
	<script src="/static/lib/testHelper.js"></script>
	<script src="/js/gui/relaindex.js"></script>
	<link rel="stylesheet" href="/static/lib/reset.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap-theme.css">
</head>
<body style="height: 100%; margin: 0">
<div id="main">
    <div id="left" style="float:left ; background: #D1EEEE; width:40%;  height:100%;padding-top: 10px;">
        <span style="font-size: 15px;" class="label label-info">监控周期</span>
        <select id="monitortime" onblur="monitor()"  style="height: 25px;border-radius:25px;border: 0px">
            <option value ="hour">小时</option>
            <option value ="minutes">五分钟</option>
            <option value ="day">天</option>
        </select>
        <div id="sysrole" style="margin-top: 100px;">
        </div>
    </div>
    <div id="right" style="float:left ; background-color: #D1EEEE; width:60%; height:100%;"></div>
</div>
<script type="text/javascript">

</script>
</body>
</html>