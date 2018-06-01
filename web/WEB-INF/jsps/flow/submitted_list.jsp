<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/taglib.jsp" %>
<html>
<head>
    <title>我的申请查询</title>
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
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<%--<div id="QueryArea">
    <div style="height: 30px">
        <form action="${pageContext.request.contextPath}/application/search" method="post">
            <table border=0 cellspacing=3 cellpadding=5>
                <tr>
                    <td><select name="status" class="SelectStyle">
                        <option value="">查看全部状态</option>
                        <option value="审批中">审批中</option>
                        <option value="未通过">未通过</option>
                        <option value="已通过">已通过</option>
                    </select>
                    </td>
                    <td><a href=""><input type="IMAGE"
                                          src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>--%>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
        <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            <td width="250px">标题</td>
            <td width="115px">申请人</td>
            <td width="115px">申请日期</td>
            <td width="115px">当前状态</td>
            <td>相关操作</td>
        </tr>
        </thead>

        <!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
        <c:if test="${empty applicationList}">没有申请</c:if>
        <c:forEach items="${applicationList}" var="application">
            <!-- 正在审批或审批完成的表单显示示例 -->
            <tr class="TableDetail1 template">
                <td><a href="${pageContext.request.contextPath}/application/showForm/${application.id}">${application.title}</a></td>
                <td>${application.applicant.loginName}&nbsp;</td>
                <td><fmt:formatDate value="${application.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${application.status}&nbsp;</td>
                <td><a href="${pageContext.request.contextPath}/application/showForm/${application.id}">查看申请信息</a>
                    <a href="${pageContext.request.contextPath}/application/approvedHistory/${application.id}">查看流转记录</a>
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
