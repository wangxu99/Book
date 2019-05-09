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

<title>添加工号</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script>
 
	$(function() {
	$("#register").bootstrapValidator({

		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'

		},
		fields : {
			
			
			gonghao : {
				validators : {

					notEmpty : {

						message :  '工号不能为空'
					},
					stringLength : {
						min : 1,
						max : 10,
						message : '工号长度小于10'
					},

					// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
					remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
						url : "GHServlet",//验证地址
						 //提示消息
						delay : 500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
						type : 'POST',//请求方式

						//自定义提交数据，默认值提交当前input value
						data : function(validator) {
							return {
								action : "yanzheng",
								name : $("#gonghao").val() 
							 
							}
						}

					}

				}

			},
			tname : {
				validators : {

					notEmpty : {

						message : '姓名不能为空'
					},
					stringLength : {
						min : 2,
						max : 15,
						message : '姓名必须是2-15个汉字'
					},
					regexp : {
						regexp : /^[\u0391-\uFFE5]{2,15}$/,
						message : '姓名必须是2-15个汉字'
					}

				}
			},
		}
	});
	});
</script>
<style type="text/css">
#div1 {
 
	margin-top: 20px;
   border:1px solid #D7E4E8; 
}

form {
	margin-top: 20px;
}


.btn {
	margin-top: 30px;
}
h3{
margin-top: 30px;
}
label{
   font-size: 15px;
}
hr{
border: 1px solid #D7E4E8;
 width: 400px;
 
 
}
#div9{
	/* background-image: url("tu/t6.jpg"); */
	background-size:cover;
	height: 840px;
	margin-top: 60px;
}
</style>
</head>
<body>
 <div class="container-fluid"  id="div9">
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
			<div class="col col-md-5 col-md-offset-2" id="div1">

				<h2 class="text-center text-info">添加工号</h2>
             
             <hr>
			<form action="GHServlet?action=addgonghao&ausername=${ausername }" method="post" id="register"
			  class="form-horizontal">

				<div class="form-group">
					<label for="Input1" class="col col-sm-5   control-label  text-info">
						请输入工号: </label>
					<div class="col-sm-5">
						<input type="text" name="gonghao"  
							class="form-control" id="Input1" />

					</div>
				 
				</div>
				<div class="form-group">
					<label for="Input1" class="col col-sm-5   control-label  text-info">
						请输入教师名字: </label>
					<div class="col-sm-5">
						<input type="text" name="tname"  
							class="form-control" id="Input1" />

					</div>
				 
				</div>
                 <div class="form-group">
						<div class="col-sm-2  col-sm-offset-3 ">
							<button type="submit" class="btn btn-success">
								添加 <span class="glyphicon glyphicon-ok"></span>
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



		</div>
	</div>
 


</body>
</html>