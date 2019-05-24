<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />

<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="iconfont/iconfont.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>查看图书</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<style>
#div3 {
	height: 840px;
	/* 	background-image: url("tu/t7.jpg"); */
	background-size: cover;
}

.footer {
	color: #777;
	margin-top: 500px;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	padding: 30px 0;
}

.fm {
	margin-top: 10px;
}
</style>
</head>
<body>


	<div class="container-fluid " id="div3">

		<c:if test="${!empty mag }">
			<script>
				alert("${mag }");
			</script>
		</c:if>
		<c:remove var="mag" />

		<div class="container">
			<table id="t" class="table">
				<tr height="6%">
					<td align="center" colspan=2><br>
						<div class="col-md-5 col-md-offset-3">
							<font size="7" color="#337AB7" face="宋体"><strong>待还图书信息</strong></font>
						</div>
				</tr>

				<tr align="center">
					<td>
						<table class="table table-bordered table-hover " cellspacing="0"
							cellpadding="20" id="tb2">
							<tr align="center">
								<td>图书编号</td>
								<td>分类名称</td>
								<td>图书名称</td>
								<td>图书价格</td>
								<td>图书出版社</td>
						        <td>图书编号</td>
								<td>借书时间</td>
								<td>归还期限</td>
								<td>归还</td>

							</tr>
							 
						  <c:forEach items="${pb.beanList }" var="s" varStatus="ss" >
								<tr align='center'>
									<td>${ss.index+1}</td>
									<td>${s.flname}</td>
									<td>${s.name}</td>
									<td>${s.money}</td>
									<td>${s.press}</td>
							         <td>${s.book_id}</td>
								 <td>${s.time}</td>
								 <c:if test="${s.date<5}">
							 <td><font color="#D2463D">${s.huan}</font></td>
								 </c:if>
								 <c:if test="${s.date>=5}">
								 <td>${s.huan}</td>
								 </c:if>
							 <td><a
							 href="<%=base%>UserForegroundServlet?action=huanshu&pageNew=${pb.pageNew+1 }&btid=${s.btid }&bookid=${s.id }&id=${s.i }&qusername=${qusername }"
							 class="btn  btn-info  btn-sm"> 还书 </a></td>
							 
							</c:forEach> 
						</table>
					</td>
				</tr>
			</table>
			<footer class="footer">
				<!-- 下 -->

				<h4>
					<font size="3" color="#b1b1b1">图书管理系统(wly)版权所有&copy;2025-2030</font>
				</h4>
			</footer>

		</div>
	</div>
	</div>
</body>
</html>