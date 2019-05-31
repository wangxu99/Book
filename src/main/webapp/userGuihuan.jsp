<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<title>查看图书</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<style>
#div3 {
	height: 500px;
	/* 	background-image: url("tu/t7.jpg"); */
	background-size: cover;
}
 #div4 {
	height: 500px;
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

		<div class="container" id="div4">
			<table id="t" class="table">
				<tr height="6%">
					<td align="center" colspan=2><br>
						<div class="col-md-5 col-md-offset-3">
							<font size="7" color="#337AB7" face="宋体">
							<strong>待还图书信息</strong></font><br>
							<font size="1" color="#337AB7" face="宋体" color="#D2463D">
							（温馨提示:待归还期限小于5天报红）</font>
						</div>
				</tr>

				<tr align="center">
					<td>
						<table class="table table-bordered table-hover " cellspacing="0"
							cellpadding="20" id="tb2">
							<tr align="center">
							    <td>编号</td>
								<td>图书编号</td>
								<td>分类名称</td>
								<td>图书名称</td>
								<td>图书价格</td>
								<td>图书出版社</td>
						        <td>图书作者</td>
								<td>借书时间</td>
								<td>归还期限</td>
								<td>归还</td>

							</tr>
							 
						  <c:forEach items="${pb.beanList }" var="s" varStatus="ss" >
								<tr align='center'>
								<td>${ss.index+1}</td>
										<td>${s.bid}</td>
										<td>${s.fenlei.fname}</td>
										<td>${s.bname}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td>${s.author}</td> 
								 <td>${s.bt.time}</td>
								 	 <c:if test="${s.date<5}">
							 <td><font color="#D2463D">${s.htime}</font></td>
								 </c:if>
								 <c:if test="${s.date>=5}">
								 <td>${s.htime}</td>
								 </c:if> 
							 <td><a
							 href=" "
							 class="btn  btn-info  btn-sm"> 还书 </a></td>
							 
							</c:forEach> 
						</table>
						 
								<p>  第${pb.pageNow }页/共${pb.pages }</p>
									<ul class="pagination "> 
									<li><a href="http://localhost/Book/showguihuan/${uid}/1">首页</a>
									</li>
								<c:if test="${pb.pageNow>1 }">
									<li><a aria-label="Previous"
											href="http://localhost/Book/showguihuan/${uid}/${pb.pageNow-1 }"><span
												aria-hidden="ture">上一页</span></a></li>
								</c:if>

								<!-- 分页2种情况
			               1.页数小于10
			                   2.页数大于10
			                         -->

									<c:choose>
										<c:when test="${pb.pages<=10 }">
											<c:set var="begin" value="1"></c:set>
											<c:set var="end" value="${pb.pages }"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="begin" value="${pb.pageNow-5 }"></c:set>
											<c:set var="end" value="${pb.pageNow+4 }"></c:set>
											<c:if test="${begin<=1 }">
												<c:set var="begin" value="1"></c:set>
												<c:set var="end" value="10"></c:set>
											</c:if>
											<c:if test="${end>=pb.pages }">
												<c:set var="begin" value="${pb.pages-9 }"></c:set>
												<c:set var="end" value="${pb.pages}"></c:set>
											</c:if>

										</c:otherwise>
									</c:choose>
									<!-- 每页面显示10页数 -->

									<c:forEach begin="${begin }" end="${end }" var="i">
										<c:choose>
											<c:when test="${pb.pageNow==i }">
												<li class="active"><span>${i }</span></li>
											</c:when>
											<c:otherwise>
												<li><a href="http://localhost/Book/showguihuan/${uid}/${i}">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>


									<c:if test="${pb.pageNow<pb.pages }">
										<li><a href="http://localhost/Book/showguihuan/${uid}/${pb.pageNow+1 }"
											aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
										</li>
									</c:if>
								<li><a href="http://localhost/Book/showguihuan/${uid}/${pb.pages}">尾页
								</a>
								</li>
								 </ul>
					 
					</td>
				</tr>
			</table>
			
           
		</div>
		<footer class="footer">
				<!-- 下 -->

				<h4>
					<font size="3" color="#b1b1b1">图书管理系统版权所有&copy;2025-2030</font>
				</h4>
			</footer>
	</div>
	 
</body>
</html>