<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<html>
<head>
    <title>版块列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
    <style type="text/css">
        .disableA{
            color: gray;
            /**text-decoration: underline ;下划线*/
            cursor: pointer;
        }
    </style>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">版块名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        <c:forEach items="${forumList}" var="forum" varStatus="status">
			<tr class="TableDetail1 template">
                    <td>${forum.name}&nbsp;</td>
                    <td>${forum.description}&nbsp;</td>
                    <td><a onClick="return delConfirm()"
                           href="${pageContext.request.contextPath}/forum/delete/${forum.id}">删除</a>
                        <a href="${pageContext.request.contextPath}/forum/editUI/${forum.id}">修改</a>
                        <c:choose>
                            <c:when test="${status.first}">
                                <span class="disableA">上移</span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/forum/moveUp/${forum.id}">上移</a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${status.last}">
                                <span class="disableA">下移</span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/forum/moveDown/${forum.id}">下移</a>
                            </c:otherwise>
                        </c:choose>

                    </td>
			</tr>
        </c:forEach>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="${pageContext.request.contextPath}/forum/addUI"><img
                    src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，显示的列表按其position值升序排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>

</body>
</html>
