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
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="iconfont/iconfont.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>Insert title here</title>
<style type="text/css">
.icon {
	width: 1em;
	height: 1em;
	vertical-align: -0.15em;
	fill: currentColor;
	overflow: hidden;
	margin-top: 10px;
}

h4 {
	margin-top: 40px;
}

div {
	height: 110px;
	background-color: #337AB7;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row header" id="divt">
			<!-- 上 -->

			<div class="col col-md-7 col-xs-12 col-md-offset-1 ">
				<!-- <marquee hspace="20" align="texttop" behavior="slide"
						scrollamount="20" direction="left" width=500>
				  <svg class="icon" aria-hidden="true" style="font-size: 50px;">
              <use xlink:href="#icon-tushu" ></use>
                 </svg> 	</marquee>
                  -->

				<font size="7" color="#FFFFFF" face='华文新魏'>图书管理系统</font><font
					size="4" color="#F2F2F2"> | </font><font size="3" color="#F2F2F2">用户</font>


			</div>
			<div class="col col-md-4  col-xs-12">
				<h4 id="userlogin">

					<c:if test="${empty username  }">

						<a href='userlogin.jsp' target="_top"><font
							size="3px;" color="#F2F2F2">请登录 </font></a>&nbsp;&nbsp;&nbsp;&nbsp;
						 <a href="userindex.jsp" target="_top"><font size="2px" color="#F2F2F2">返回首页</font></a>
						
					</c:if>
					<c:if test="${!empty username  }">
						<font color="#FFFFFF" size="4"> ${username},欢迎您  | </font>
							<a href='tc.jsp' target="_top">
							<font size="2" color="#FFFFFF">退出</font>
							</a> &nbsp;&nbsp;
							<a href="userindex.jsp" target="_top">
							<font size="2" color="#FFFFFF">首页</font>
							</a> &nbsp;&nbsp;&nbsp;
							<a href="showguihuan/${uid}/1" target="down">
							<font size="3" color="#E10005"  >待归还图书</font>
							</a> 
					</c:if>
				</h4>
			</div>
		</div>
	</div>

</body>
</html>