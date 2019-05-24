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

<title>添加图书</title>
 
<script>
	$(function() {
	
		$("#fm").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				bname : {
					validators : {

						notEmpty : {

							message : '图书名不能为空'
						},
						stringLength : {
							min : 1,
							max : 10,
							message : '书名长度小于20'
						},

						// threshold :  6 , 有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
							url : "yanzhengAddBook",//验证地址
							//提示消息
							message : '该分类下已存在此图书',
							delay : 500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'GET',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									bname : $("#bname").val(),
									flid : $("#flid").val()
								}
							}

						}

					}

				},
				money : {

					validators : {

						notEmpty : {

							message : '价格不能为空'
						}
					}

				},
				flid : {

					validators : {

						callback : {

							callback : function(value, validator) {

								if (flid.value == 0) {
									return {
										valid : false,

										message : '必须选择',
									}

								}
								return true;

							}
						}
					}

				},
				press : {
					validators : {

						notEmpty : {

							message : '出版社不能为空'
						}
					}
				},
				author : {
					validators : {

						notEmpty : {

							message : '请填写作者'
						}
					}
				},
				stock : {
					validators : {
						notEmpty : {

							message : '请填写库存'
						}
					}
				}
			}

		})
	});
</script>
<style>
#div1 {
	margin-top: 20px;
	border: 1px solid #D7E4E8;
}

form {
	margin-top: 20px;
}

.btn {
	margin-top: 30px;
}

h3 {
	margin-top: 30px;
}

label {
	font-size: 15px;
}

hr {
	border: 1px solid #D7E4E8;
	width: 400px;
}

#div9 {
	/* 	background-image: url("tu/t6.jpg"); */
	background-size: cover;
	height: 800px;
	margin-top: 20px;
}
</style>
<body>
	<div class="container-fluid" id="div9">
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<div class="col col-md-5 col-md-offset-2" id="div1">

			<h2 class="text-center text-info">添加图书</h2>
			<hr>
			<form action="addBook" method="post" name="register" id="fm" class="form-horizontal">

				<div class="form-group">
					<label class="col-sm-3 col-sm-offset-2 control-label text-info">选择分类:</label>
					<div class="col-sm-4">
						<select name="flid" id="flid" style="color: #265C88;"
							class="form-control input-sm">
							<option value="0">----请选择----</option>
							<c:forEach items="${flist }" var="s">
								<option value="${s.fid }">${s.fname }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label col-sm-offset-2 text-info">图书名称:</label>
					<div class="col-sm-4">
						<input type="text" name="bname" class="form-control input-sm"
							id="name" />
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label col-sm-offset-2 text-info">图书价格:</label>
					<div class="col-sm-4">
						<input type="text" name="money" class="form-control input-sm" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label col-sm-offset-2 text-info">出版社:</label>
					<div class="col-sm-4">
						<input type="text" name="press" class="form-control input-sm" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label col-sm-offset-2 text-info">作者:</label>
					<div class="col-sm-4">
						<input type="text" name="author" class="form-control input-sm" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label col-sm-offset-2 text-info">库存:</label>
					<div class="col-sm-4">
						<input type="text" name="stock" class="form-control input-sm" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2  col-sm-offset-4 ">
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

		<!-- </marquee>-->
	</div>
</body>
</html>