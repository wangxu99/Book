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

<script>
	window.onload = function() {
		var chek = document.getElementsByName("ids");

		var selectAll = document.getElementById("selectAll");

		selectAll.onclick = function() {
			//全选

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = true;

			}

		}
		//全不选
		var selectNot = document.getElementById("selectNot");

		selectNot.onclick = function() {

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = false;

			}

		}

		//反选
		var fanxuan = document.getElementById("fanxuan");

		fanxuan.onclick = function() {

			for (i = 0; i < chek.length; i++) {
				if (chek[i].checked == true) {
					chek[i].checked = false;

				} else {
					chek[i].checked = true;

				}

			}

		}

		//删除	  
		var df = document.getElementById("dfd");

		df.onclick = function() {

			var flag = false;

			for (i = 0; i < chek.length; i++) {

				if (chek[i].checked == true) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				alert("请至少选一项");
				return;

			} else {

				var str = "";

				for (var i = 0; i < chek.length; i++) {

					if (chek[i].checked == true) {

						str += chek[i].value + ",";

					}
				}
				str = str.slice(0, str.length - 1);
				//alert(str);

				var flage = confirm("你确定删除所勾选的图书吗？");
				if (flage == true) {//确定
					//拿到请求地址
					var $url = "http://localhost/Book/deleteBook/" + str
							+ "/${pb.pageNow}";

					//alert($url);
					//拿到表单
					$("#deleteForm").attr("action", $url);

					//提交表单
					$("#deleteForm").submit();

					return false;
					//window.location.href = "http://localhost/Book/deleteBook/" + str + "/${pb.pageNow}";

				} else {//取消

					window.location.href = "http://localhost/Book/showBookHandler/${pb.pageNow}";

				}
			}
		}

		//导出部分	  
		var outIds = document.getElementById("outIds");

		outIds.onclick = function() {

			var flag = false;

			for (i = 0; i < chek.length; i++) {

				if (chek[i].checked == true) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				alert("请至少选一项");
				return;

			} else {

				var str = "";

				for (var i = 0; i < chek.length; i++) {

					if (chek[i].checked == true) {

						str += chek[i].value + ",";

					}
				}
				str = str.slice(0, str.length - 1);

				var flag = confirm("你确定导出所勾选的图书信息吗？");
				if (flag == true) {//确定
					window.location.href = " OutPutBookServlet?action=outids&ids="
							+ str;

				} else {//取消
					window.location.href = "http://localhost/Book/showBookHandler/${pb.pageNow}";
				}
			}
		};
		//导出全部
		var outAll = document.getElementById("outAll");
		outAll.onclick = function() {
			var flag = confirm("你确定导出全部的图书信息吗？");
			if (flag == true) {//确定
				window.location.href = " OutPutBookServlet?action=all";

			} else {//取消
				window.location.href = "http://localhost/Book/showBookHandler/${pb.pageNow}";
			}
		}

	};
</script>
<style>
.col {
	width: 1000px;
	height: 500px;
}

#div3 {
	margin-top: 30px;
	width: 1000px;
	height: 520px;
	margin-left: 30px;
}

#div2 {
	margin-left: 10px;
}

#div1 {
	height: 840px;
	/* background-image: url("tu/t6.jpg"); */
	background-size: cover;
}

#li {
	color: #337AB7;
	font-size: 17px;
}

#tb2 {
	width: 800px;
}

.r2 {
	margin-top: 3px;
}

#tb2 {
	margin-top: 5px;
	width: 860px;
}

#f1 {
	color: #337AB7;
	width: 400px;
}

.ss {
	margin-left: 40%;
}
</style>
</head>
<body>

	<div class="container-fluid" id="div1">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag }");
			</script>
		</c:if>
		<c:remove var="mag" />
		<div class="col col-md-5 " id="div2">
			<ul class="nav nav-tabs">
				<li class="active"><a id="selectAll" href="#">全选</a></li>
				<li><a id="selectNot" href="#">全不选</a></li>
				<li><a id="fanxuan" href="#">反选</a></li>
				<li><a id="outIds" href="#">导出选中</a></li>
				<li><a id="outAll" href="#">导出全部</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">高级搜索<span class="caret"></span></a>
					<ul class="dropdown-menu dropdown-menu-right" role="menu">
						<li>
							<form action="http://localhost/Book/GaoJiSs" class="form-horizontal" id="f1"
								name="f1" method="GET">
								
								<!-- 
								<input type="hidden" name="_method" value="PUT">  -->
								<div class="control-group">
									<br> <label class="col-sm-4  control-label ">选择分类：
									</label>
									<div class="controls col-sm-6 ">
										<select name="flid" id="flid" style="color: #265C88;"
											class="form-control input-sm">
											<option value="0">----请选择----</option>
											<c:forEach items="${flist }" var="s">
												<option value="${s.fid }">${s.fname }</option>
											</c:forEach>
										</select> <br>
									</div>
								</div>

								<div class="control-group  ">
									<label class="col-sm-4  control-label  ">书名:</label>
									<div class="controls col-sm-6 ">
										<input type="text" name="bname" class="form-control  input-sm" /><br>
									</div>
								</div>

								<div class="control-group   ">
									<label class="col-sm-4 control-label  "> 出版社:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="press" class="form-control  input-sm" /><br>
									</div>
								</div>


								<div class="control-group   ">
									<label class="col-sm-4 control-label ">作者:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="author"
											class="form-control  input-sm" /><br>
									</div>
								</div>

							 	<div class="control-group ">
									<label class="control-label col-sm-4 "> 库存:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="stock" class="form-control  input-sm" /><br>
									</div>
								</div> 
                               <input type="hidden" name="pageNow" value="1">
								<div class="control-group  ">

									<div class="controls ss">
										<button type="submit" class="btn   btn-info ">
											<span class="glyphicon glyphicon-search"></span> 开始搜索
										</button>
										<br> <br>
									</div>
								</div>

							</form>
						</li>

					</ul></li>
			</ul>
			<div class="container-fluid " id="div3">
				<table id="t">
					<tr height="6%">
						<td align="center" colspan=2><br> <font size="7"
							color="#337AB7" face="宋体"><strong>查看图书</strong></font></td>
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
									<td>作者</td>
									<td>库存</td>
									<td><button id="dfd">删除</button></td>
									<td>修改</td>
								</tr>
								<c:forEach items="${pb.beanList }" var="s" varStatus="ss">
									<tr align='center'>
										<td>${ss.index+1}</td>
										<td>${s.fenlei.fname}</td>
										<td>${s.bname}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td>${s.author}</td>
										<td>${s.stock}</td>
										<td><input type="checkbox" name="ids" value="${s.bid}" /></td>
										<td><a
											href='http://localhost/Book/updateBookUl/${s.bid }/${pb.pageNow }'>
												<input type="button" value="修改" class="btn btn-info btn-sm" />
										</a></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<form action="" method="post" id="deleteForm">
								<input type="hidden" name="_method" value="DELETE">
							</form>
						</td>
					</tr>

					<tr>
						<td align="center">
						<c:if test="${showPesge=='gao'}">
								  第${pb.pageNow }页/共${pb.pages }
									<ul class="pagination "> 
									<li><a href="${pb.url }&pageNow=1">首页</a>
									</li>
								<c:if test="${pb.pageNow>1 }">
									<li><a aria-label="Previous"
											href="${pb.url }&pageNow=${pb.pageNow-1 }"><span
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
												<li><a href="${pb.url }&pageNow=${i}">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>


									<c:if test="${pb.pageNow<pb.pages }">
										<li><a href="${pb.url }&pageNow=${pb.pageNow+1 }"
											aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
										</li>
									</c:if>
								<li><a href="${pb.url }&pageNow=${pb.pages}">尾页
								</a>
								</li>
								 </ul>
								 
					  </c:if>
					  <c:if test="${showPesge=='showBook'}"> 
								<p>第${pb.pageNow }页/共${pb.pages } 
							
								<ul class="pagination ">

								<li><a href="http://localhost/Book/showBookHandler/1">首页</a>
									</li>
								<c:if test="${pb.pageNow>1 }">
									<li><a aria-label="Previous"
											href="http://localhost/Book/showBookHandler/${pb.pageNow-1 }"><span
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
											<li><a href="http://localhost/Book/showBookHandler/${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNow<pb.pages }">
									<li><a
											href="http://localhost/Book/showBookHandler/${pb.pageNow+1 }"
											aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								 
								<li><a
										href="http://localhost/Book/showBookHandler/${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul> 
					  </c:if>
				 
					  <br>

						</td>
					</tr>
	
				</table>
			</div>
		</div>
	</div>
</body>
</html>