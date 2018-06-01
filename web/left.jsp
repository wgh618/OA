<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>导航菜单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script language="JavaScript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/script/menu.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css"/>
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <c:forEach items="${topPrivilegesList}" var="topPrivileges">
            <li class="level1">
                <div onClick="menuClick(this);" class="level1Style"><img
                        src="${pageContext.request.contextPath}/style/images/MenuIcon/FUNC20082.gif" class="Icon"/>
                        ${topPrivileges.name}</div>
                <ul style="display: none;" class="MenuLevel2">
                    <c:forEach items="${topPrivileges.children}" var="firstPrivilege">
                        <li class="level2">
                            <div class="level2Style"><img
                                    src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif"/>
                                <a target="right" href="${pageContext.request.contextPath}${firstPrivilege.url}">
                                ${firstPrivilege.name}</a></div>
                        </li>
                    </c:forEach>

                </ul>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
