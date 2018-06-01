<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<html>
<head>
	<title>个人信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${pageContext.request.contextPath}/userSetting/editUserInfo" method="post" <%--enctype="multipart/form-data"--%>>
        <div class="ItemBlock_Title1">
            <!-- 信息说明-->
            <DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 个人信息 </DIV>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
                        <td width="150">用户ID（登录名）</td>
                        <td>${loginUser.loginName}</td>
						<td rowspan="5" align="right">
							<img src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
						</td>
                    </tr>
                    <tr>
                        <td>部门</td>
                        <td>${loginUser.department.name}</td>
                    </tr>
					<tr>
                        <td>岗位</td>
                        <td>
                            <c:forEach items="${userRoles}" var="userRole">
                                ${userRole.role.name} &nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td>联系电话</td>
                        <td><input type="text" name="telephone" value="${loginUser.telephone}" class="InputStyle"/></td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td><input type="email" name="email" value="${loginUser.email}" class="InputStyle"/></td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td><textarea name="description"  class="TextareaStyle">${loginUser.description}</textarea></td>
                    </tr>
					<%--<tr>
                        <td>头像</td>
                        <td><input type="file" name="resource" class="InputStyle" style="width: 400px;"/></td>
                    </tr>--%>
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

<%--<div class="Description">
	验证规则：<br />
	1，可以修改自已的头像，在右侧是头像的预览。<br />
</div>--%>

</body>
</html>
