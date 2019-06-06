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
<title>查看管理员</title>
<script type="text/javascript">
		
</script>
<style>
#div1 {
	 
	margin-top: 20px;
  }
#div9{
 
    /* 	background-image: url("tu/t6.jpg"); */
	background-size: cover;
 
	height: 840px;
 
}
 
h3{
margin-top: 30px;
}
td{
font-size: 16px;
color:#337AB7;
 
}
caption {
margin-left: 30%;
}
 
</style>
</head>
<body  >
<div class="container-fluid"  id="div9"   >
<div class="col col-md-5 col-md-offset-2" id="div1">
 
	<table bordercolor="#993300" border="1" align="center" width="500px" class="table table-bordered table-hover"
		height="300px" cellspacing="0">
		 <caption  ><font color="#337AB7" size="7" face="宋体">查看管理员</font></caption>
		<tr align="center" >
			<td ><br>头像：</td>
			<td id="touxiang" ><img alt="" src="${a.touxiang}" width="150" height="70">
			</td>
		</tr>
		<tr align="center">
			<td>编号：</td>
			<td><input type="text" id="aid" value="${a.aid}"/></td>
		</tr>
		<tr align="center">
			<td>姓名：</td>
			<td><input type="text" id="aname" value="${a.aname}" /></td>
		</tr>
		<tr align="center">
			<td>账号：</td>
			<td><input type="text" id="ausername" value="${a.ausername}"/></td>
		</tr>

		<tr align="center">
			<td>密码：</td>
			<td><input type="password" disabled="disabled" id="password1" value="${a.password}" /></td>
		</tr>
		<tr align="center">
			<td>手机：</td>
			<td><input type="text" id="phone" value="${a.phone}"/></td>
		</tr>
	</table>
	</div>
	</div>
</body>
</html>