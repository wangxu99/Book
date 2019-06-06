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
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>修改密码</title>

<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
 
	$(function() { 
		$(".form-horizontal").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				newpassword : {
					validators : {
						notEmpty : {

							message : '密码不能为空'
						},
						different : {
							field : 'password2',
							message : '不能与原密码一致'
						},
						regexp : {
							regexp : /^(?=.*\d)(?=.*[A-z])[A-z\d]{6,15}$/,
							message : '密码是6-15位，必须含有字母和数字'
						},
					}
				},
				repassword : {
					validators : {
						notEmpty : {

							message : '确认密码不能为空'
						},
						identical : {
							field : 'newpassword',
							message : '与密码不一致，请重新输入!'
						}
					}
				},
				password2: {
					validators : {

						notEmpty : {

							message : '密码不能为空'
						},
						 

						// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "adminpasswordYZ",//验证地址
							 //提示消息
							delay : 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'GET',//请求方式
							message : '原密码错误',
							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									
									password:$('input[name=password2]').val(),
									ausername:"${ausername}"
								}
							}

						}

					}

				}
			}
		});
	
  
		
		});
 
</script>
<style>
#div1 {
 
	margin-top: 20px;
	  border:1px solid #D7E4E8; 
}
#div9{
	/* background-image: url("tu/t6.jpg"); */
	background-size: cover;
	height: 840px;
	margin-top: 40px;
}
form {
	margin-top: 20px;
}

.container {
	margin-top: 100px;
}

.btn {
	margin-top: 30px;
}
h3{
margin-top: 30px;
}
label{
   font-size: 16px;
}
hr{
border: 1px solid #D7E4E8;
 width: 400px;
}


 
</style>
</head>
<body >

	<div class="container-fluid"  id="div9">
	 	<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
           
		</c:if>
		<c:remove var="mag" />
		 
		<div class="col col-md-5 col-md-offset-2" id="div1">
	<h2 class="text-center text-info">修改密码</h2>
             <hr >
			<table    >
				<tr>
					<td valign="top" width="60%">
		<form name="register" action="updatePassword"
			method="post" class="form-horizontal">
			<input type="hidden" name="_method" value="PUT"> 
		 
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-danger ">原密码：</label>
				<div class="col-sm-4">
					<input type="password" name="password2" class="form-control text-info"/>
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info ">新密码：</label>
				<div class="col-sm-4">
					<input type="password" name="newpassword"
						 class="form-control input-sm"/>
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">确认新密码：</label>
				<div class="col-sm-4">
					<input type="password" name="repassword" class="form-control input-sm"/>
				</div>
			</div>
		 
				<div class="form-group">
								<div class="col-sm-2 col-sm-offset-4 ">
									<button type="submit" class="btn btn-success">
										  修改  <span class="glyphicon glyphicon-ok"></span>
									</button>
								</div>
								<div class="col-sm-2  col-sm-offset-1">
									<button type="reset" class="btn btn-info">
										重置 <span class="glyphicon glyphicon-repeat"></span>
									</button>
								</div>
							</div>
			<br>
		</form>
			 	</td>

				</tr>

			</table>
	 
		</div>

 </div>
</body>
</html>