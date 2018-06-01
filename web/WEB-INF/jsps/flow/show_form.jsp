<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/taglib.jsp" %>
<html>
<head>
<title>查看申请信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 申请信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
		<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 申请信息 </DIV>  -->
	</div>
	<div class="ItemBlockBorder" style="margin: 15px;">
		<div class="ItemBlock">
			<table cellpadding="0" cellspacing="0" class="mainForm">
				<tr>
					<td><a href="${pageContext.request.contextPath}/application/download/${applicationId}"
                           style="text-decoration: underline">[点击下载申请的文档]</a></td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 表单操作 -->
    <div id="InputDetailBar">
        <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
    </div>
</div>

</body>
</html>
