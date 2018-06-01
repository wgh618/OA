<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/taglib.jsp" %>
<html>
<head>
    <title>待我审批</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js"
            charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css"/>
    <script type="text/javascript">
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 待我审批
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--
<div id="QueryArea">
	<div style="height: 30px">
		<form action="#">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>按条件查询：</td>
				<td><select name="arg1" class="SelectStyle">
						<option value="">查看全部模板</option>
						<option value="0">领款单</option>
						<option value="10">工作报告</option>
						<option value="13">设备采购单</option>
						<option value="21">员工请假单</option>
						<option value="22">加班申请</option>
					</select>
				</td>
				<td><a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
			</tr>
		</table>
	
		</form>
	</div>
</div>
-->
<%--<form>
    <input type="hidden" name="pageNum" value="1"/>
</form>--%>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
        <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            <td width="250">标题</td>
            <td width="115">申请人</td>
            <td width="115">申请日期</td>
            <td>相关操作</td>
        </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
        <c:if test="${empty applicationList}">没有申请信息</c:if>
        <c:forEach items="${applicationList}" var="application">
            <tr class="TableDetail1 template">
                <td>${application.title}</td>
                <td>${application.applicant.loginName}&nbsp;</td>
                <td><fmt:formatDate value="${application.applyTime}" pattern="yyyy-MM-dd"/>&nbsp;</td>
                <td><a href="${pageContext.request.contextPath}/approve/approve/${application.id}">审批处理</a>
                    <!-- <a href="show_form.jsp">查看申请信息</a> -->
                    <a href="${pageContext.request.contextPath}/application/approvedHistory/${application.id}">查看流转记录
                    </a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside"></div>
    </div>
</div>

</body>
</html>
