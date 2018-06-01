<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<html>
<head>
    <title>用户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js"
            charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js"
            charset="utf-8"></script>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/style/blue/pageCommon.css"/>
    <script type="text/javascript">
        function check() {
            if ($("#name").val() == "") {

                alert("登录名不能为空！");
                return false;
            }

            if ($("#role_ids").val() == null || $("#role_ids").val() == "") {
                alert("请选择岗位！");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${pageContext.request.contextPath}/user/edit" method="post" onsubmit="return check();">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="password" value="${user.password}">
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
            <div class="ItemBlock_Title1">
                <img border="0" width="4" height="7"
                     src="${pageContext.request.contextPath}/style/blue/images/item_point.gif"/> 用户信息
            </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">所属部门</td>
                        <td><select name="department_id" class="SelectStyle">
                            <option value="0" selected="selected">请选择部门</option>
                            <c:forEach items="${departmentList}" var="department">
                                <c:choose>
                                    <c:when test="${department.id eq user.department.id}">
                                        <option value="${department.id}" selected>${department.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.id}">${department.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td>登录名</td>
                        <td><input type="text" name="loginName" value="${user.loginName}" class="InputStyle"
                                   readonly="true"/> *
                            （登录名要唯一）
                        </td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.gender eq 'M'}">
                                    <input type="RADIO" name="gender" value="M" id="male" checked/><label for="male">男
                                </label>
                                    <input type="RADIO" name="gender" value="F" id="female"/><label
                                        for="female">女</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="RADIO" name="gender" value="M" id="male"/><label for="male">男</label>
                                    <input type="RADIO" name="gender" value="F" id="female" checked/><label
                                        for="female">女
                                </label>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                    <tr>
                        <td>联系电话</td>
                        <td><input type="text" name="telephone" value="${user.telephone}" class="InputStyle"/></td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td><input type="email" name="email" value="${user.email}" class="InputStyle"/></td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td><textarea name="description"  class="TextareaStyle">${user.description}</textarea></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="ItemBlock_Title1"><!-- 信息说明 -->
            <div class="ItemBlock_Title1">
                <img border="0" width="4" height="7"
                     src="${pageContext.request.contextPath}/style/blue/images/item_point.gif"/> 岗位设置
            </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">岗位</td>
                        <td><select id="role_ids" name="role_ids" multiple="true" size="10" class="SelectStyle">
                            <c:forEach items="${roleList}" var="role">
                                <c:set var="flag" value="false"></c:set>
                                <c:forEach items="${userRoles}" var="userRole">
                                    <c:if test="${flag eq 'false'}">
                                        <c:if test="${role.id eq userRole.role.id}">
                                            <option value="${role.id}" selected>${role.name}</option>
                                            <c:set var="flag" value="true"></c:set>
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                                <c:if test="${flag eq 'false'}">
                                    <option value="${role.id}">${role.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                            按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
    说明：<br/>
    1，用户的登录名要唯一，在填写时要同时检测是否可用。<br/>
    2，新建用户后，密码被初始化为"1234"。<br/>
    3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br/>
    4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br/>
    5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br/>
    6，修改用户信息时，登录名不可修改。
</div>

</body>
</html>
