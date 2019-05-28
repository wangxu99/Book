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

<script>
	$(function() {

		$(".not").click(function() {
			alert("亲，请先登录\(^-^)/");

		});

		$(".jieshu").click(function() {
			alert("亲,非常抱歉,本书以没有库存了,再看看别的吧(T-T)");

		});
		$(".huanshu").click(function() {
			alert("亲,这本书没有借出,难道你要送我一本($-$)");

		});

	});
</script>
<style>
#div3 {
	margin-top: 70px;
	width: 1300px;
	height: 800px;
	 
}

.footer {
	color: #777;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	padding: 40px 0;
	  margin-top: 200px;
}
 
.fm {
	margin-top: 10px;
}
#f1 {
	color: #337AB7;
	width: 400px;
}
#div2 {
    margin-top:20px;
	margin-left: 10px;
}

#t{
width:1000px;
height: 600px;
}
</style>
</head>
<body>

	<div class="container" id="div1">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag }");
			</script>
		</c:if>
		<c:remove var="mag" />
     	<div class="col col-md-5" id="div2">
					 <ul class="nav nav-tabs"> 
					 <li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">高级搜索<span class="caret"></span></a>
					<ul class="dropdown-menu dropdown-menu-left" role="menu">
						<li>
							<form action="http://localhost/Book/userForegroundGaoJiSs" class="form-horizontal" id="f1"
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
								<div class="control-group">

									<div class="controls ss"  >
										<button type="submit" class="btn btn-info col-sm-4  col-sm-offset-4 ">
											<span class="glyphicon glyphicon-search"></span>
											 开始搜索
										</button>
										<br> 
										<br>
									</div>
								</div>

							</form>
						</li>

			 </ul></li>
			</ul>
        </div>
		<div class="container-fluid " id="div3">
				<table id="t"  >
					<tr height="6%">
						<td align="center" colspan=2><br>
							<div class="col-md-3 col-xs-12 col-md-offset-4 ">
								<font size="7" color="#337AB7" face="宋体"><strong>图书信息</strong></font>
							</div> </td>
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
									<td>作者</td>
									<td>库存</td>
									<td>借阅</td>
								</tr>
								<c:forEach items="${pb.beanList }" var="s" varStatus="ss">
									<tr align='center'>
										<td>${ss.index+1}</td>
										<td>${s.bid}</td>
										<td>${s.fenlei.fname}</td>
										<td>${s.bname}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td>${s.author}</td>
										<td>${s.stock}</td>
										<td><c:choose>
												<c:when test="${empty qusername }">
													<a href="#" id="jieshu"
														class="  btn  btn-sm  btn-default not"> 借阅 </a> &nbsp;&nbsp;&nbsp;&nbsp;
						                       
												</c:when>
												<c:otherwise>
													<c:if test="${s.stock!=0}">
														<a
															href=" UserForegroundServlet?action=jieshu&pageNew=${pb.pageNew+1 }&id=${s.id }&qusername=${qusername }&name=${s.name}"
															id="jieshu" class="btn btn-info btn-sm "> 借阅 </a>
													<%-- 	<a
															href="<%=base%>UserForegroundServlet?action=huanshu&pageNew=${pb.pageNew+1 }&id=${s.id }&qusername=${qusername }"
															id="huanshu" class="btn  btn-info  btn-sm"> 还书 </a> --%>

													</c:if>
													<c:if test="${s.stock==0}">
														<a href="#" id="jieshu"
															class="btn btn-sm  btn-default jieshu"> 借阅 </a>
														<%-- <a href="<%=base%>UserForegroundServlet?action=huanshu&pageNew=${pb.pageNew+1 }&id=${s.id }&qusername=${qusername }"
															id="huanshu" class="btn  btn-info  btn-sm"> 还书 </a> --%>
													</c:if>
												</c:otherwise>
											</c:choose>
											</td>
									</tr>
								</c:forEach>
							</table> 
									 		</td>
									</tr>
						 <tr align="center">
						<td>
							<c:if test="${showPesge=='gao'}">
								<p>  第${pb.pageNow }页/共${pb.pages }</p> 
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

								<li><a href="http://localhost/Book/userForegroundBook/1">首页</a>
									</li>
								<c:if test="${pb.pageNow>1 }">
									<li><a aria-label="Previous"
											href="http://localhost/Book/userForegroundBook/${pb.pageNow-1 }"><span
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
											<li><a href="http://localhost/Book/userForegroundBook/${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNow<pb.pages }">
									<li><a
											href="http://localhost/Book/userForegroundBook/${pb.pageNow+1 }"
											aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								 
								<li><a
										href="http://localhost/Book/userForegroundBook/${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul> 
							</c:if> <br>

						</td>
					</tr>
				</table>
			</div>
				
			 <footer class="footer">
			<!-- 下 -->

			<h4>
				<font size="3" color="#b1b1b1">图书管理系统(wly)版权所有&copy;2019-2030</font>
			</h4>
		</footer>
		</div>
	 
 
</body>
</html>