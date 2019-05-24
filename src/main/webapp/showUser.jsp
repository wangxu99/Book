<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

<title>查看用户</title>
 
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script>

	window.onload = function() {
		
		
	<%-- 	
	String path = request.getContextPath();
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
			+ path + "/";
 --%>
		
	 
		/*	 
			$("#table2 tr").mouseover(function(){
				$(this).css("background-color","#F8C7D4");
			})
			
			$("#table2 tr").mouseout(function(){
				$(this).css("background-color","#FFFBFC");
				 
			})
		 */
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
				 

				var flag = confirm("你确定删除所勾选的用户吗？");
				if (flage == true) {//确定
					//拿到请求地址
					var $url = "http://localhost/Book/deleteUser/" + str
							+ "/${pb.pageNow}";

					//拿到表单
					$("#deleteForm").attr("action", $url);

					//提交表单
					$("#deleteForm").submit();

					return false;
					

				} else {//取消

					window.location.href = "http://localhost/Book/showUserByPage/${pb.pageNow}";

				}
				
			}
		};
		

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
				 

				var flag = confirm("你确定导出所勾选的用户信息吗？");
				if (flag == true) {//确定
					window.location.href = "OutPutUserServlet?action=outids&ids="+str;

				} else {//取消
					window.location.href = "UserServlet?action=showPasgeUser&pageNew=${pb.pageNew}&ausername=${ausername  }";
				}
			}
		};
		
		var outAll=document.getElementById("outAll");
		outAll.onclick = function() { 
			var flag = confirm("你确定导出全部的用户信息吗？");
		 
			if (flag == true) {//确定
				window.location.href = "OutPutUserServlet?action=all";
			 
		} else {//取消
			window.location.href = "UserServlet?action=showPasgeUser&pageNew=${pb.pageNew }&ausername=${ausername  }";
		} 
			
		}
		};

 
	
		
</script>
<style>

#div2{
 margin-top:30px;
    width:1000px;
  height:520px;
 margin-left: 30px;
 
}
#div3{
 margin-left: 10px;
 
    
}
#div1{
	 
	  height: 700px;
		/* background-image: url("tu/t6.jpg"); */
	background-size: cover;  
	/* background-color: #EFF7F8; */
}

 
#li{
 color:#337AB7;
 font-size: 17px; 
 }
 #table2{
margin-top: 5px;
width: 860px;
}

</style>
</head>
<body  bgcolor=" " >
 

	<!-- <marquee align="texttop" behavior="slide" scrollamount="100" loop="1" direction="up"> -->
		<div class="container-fluid" id="div1">
	   	<c:if test="${!empty mag }">
		<script>
     alert("${mag }");

</script>
	</c:if>
	<c:remove var="mag" />
	 
		<div class="col col-md-8 " id="div3" >
		 <ul class="nav nav-tabs">			 
           <li role="presentation" class="active"><a id="selectAll"  href="#">全选</a></li>
           <li role="presentation"><a id="selectNot" href="#">全不选</a></li>
           <li role="presentation"><a id="fanxuan" href="#">反选</a></li>
           <li role="presentation"><a id="outIds"  href="#">导出选中</a></li>
           <li role="presentation"><a id="outAll" href="#">导出全部</a></li>
  			 
      </ul>  
        <div class="container-fluid " id="div2">  
			<table id="t"    cellspacing="0" cellpadding="0">
				<tr height="8%">
					<td align="center" valign="middle"> 
					<font size="7" color="#337AB7" face="宋体"><strong>查看用户</strong></font><br>
					 </td>
				</tr>


				<tr>
					<td  align="center">

						<table  class="table table-bordered table-hover " id="table2" >

							<tr align="center">
								<td>编号</td>
								<td>id</td>
								<td>头像</td>
								<td>姓名</td>
								<td>用户名</td>
								<td>密码</td>
								<td>手机</td>
								<td>注册时间</td>
								<td><button id="dfd">删除</button></td>
								<td>图书借阅情况</td>
								<td>修改</td>
							</tr>
							<c:forEach items="${pb.beanList  }" var="u" varStatus="uu">
								<tr align='center'>
									<td>${luu.index+1}</td>
									<td>${s.uid }</td>
									<td><img src="${u.touxiang }" width="30" height="30"></td>
									<td>${u.uname}</td>
									<td>${s.username}</td>
									<td>${s.password}</td>
									<td>${s.phone}</td>
									<td>${s.regtime}</td>
									<td><input type="checkbox" name="ids" value="${s.uid }"></td>
								<td><a href=UserServlet?action=showOneBooktime&id=${s.id}&pageNew=${pb.pageNew}&ausername=${ausername  }'>
								<input  type="button" value="查看"  class="btn btn-info btn-sm"/></a>
								</td>
								 
									<td><a
										href='updateUI/${s.uid}&pageNew=${pb.pageNew}'><input
											type="button" value="修改"  class="btn btn-info btn-sm"/></a>
											 </td>
								</tr>
							</c:forEach>

						</table>
						
						<td>
							<form action="" method="post" id="deleteForm">
								<input type="hidden" name="_method" value="DELETE">
							</form>
						</td>
				          <p>  
							 第${pb.pageNew }页/共${pb.pages }&nbsp;&nbsp;&nbsp;&nbsp;
							
						      
								  <ul class="pagination ">
								 
										<li> <a href="UserServlet?action=showPasgeUser&pageNew=1&ausername=${ausername  }">首页</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									 
								 
									</li>
						 	<c:if test="${pb.pageNew>1 }">
								 <li><a aria-label="Previous"
											href="UserServlet?action=showPasgeUser&pageNew=${pb.pageNew-1 }&ausername=${ausername  }"><span
												aria-hidden="ture">上一页</span></a>
												</li>
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
											<c:set var="begin" value="${pb.pageNew-5 }"></c:set>
											<c:set var="end" value="${pb.pageNew+4 }"></c:set>
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
											<c:when test="${pb.pageNew==i }">
												<li class="active"><span>${i }</span></li>
											</c:when>
											<c:otherwise>
												<li><a href="UserServlet?action=showPasgeUser&pageNew=${i}&ausername=${ausername  }">${i }</a>
													</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
                                

									<c:if test="${pb.pageNew<pb.pages }">
										<li><a
											href="UserServlet?action=showPasgeUser&pageNew=${pb.pageNew+1 }&ausername=${ausername  }"
											aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
										</li>
									</c:if>
									
									<li>
									 
										<li>&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="UserServlet?action=showPasgeUser&pageNew=${pb.pages}&ausername=${ausername  }" >尾页
										</a>
										</li>								 
							 </ul>
						 </p>
                   
						
						 <br>
					</td>
				</tr>
			</table>
			
		</div>
		
	
</body>
</html>