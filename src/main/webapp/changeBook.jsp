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
<title>修改图书</title>
 
<script type="text/javascript" src="js/ajax.js"></script>
<script>
 
$(function() {
	var flId = document.register.flId;

	ajax({
		method : "POST",
		url : "FenleiServlet",
		params : "action=showOne2",
		type : "json",
		success : function(content) {

			for (var i = 0; i < content.length; i++) {

				var name = content[i];

				var opt = document.createElement("option");
				opt.value = name.id;
				opt.innerHTML = name.name;
				flId.appendChild(opt);

			}

		}
	});

		$("#fm").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				name : {
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
							url : "BookServlet",//验证地址
							 //提示消息
							delay : 500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
							type : 'POST',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzhengxg",
									name : $("#name").val(),
									flId : $("#flId").val(),
									oldflId:$("#oldflId").val()
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
				flId : {

					validators : {

						callback : {

							callback : function(value, validator) {

								if (flId.value == "----请选择----") {
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
				author: {
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
.zt{
 font-size: 10px;
}
hr{
border: 1px solid #D7E4E8;
 width: 400px;
}
#div9{
	/* background-image: url("tu/t6.jpg"); 
	background-size: cover;*/
	 
	height: 700px;
	margin-top: 20px;
}
</style>
<body  >

<div class="container-fluid"  id="div9">
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
		<c:if test="${!empty mag }">
		<script>
			alert("${mag}");
		</script>
	</c:if>
	<c:remove var="mag" />
	 	 
	<div class="col col-md-5 col-md-offset-2" id="div1">

		<h2 class="text-center text-info">修改图书</h2>
		<hr>

		<form action="BookServlet?action=update2&ausername=${ausername  }" method="post" id="fm"
			name="register"class="form-horizontal">
			<input type="hidden" name="id" value="${b.id }" /> <input
				type="hidden" name="pageNew" value="${pageNew }" />

			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书分类:</label>
				<div class="col-sm-4">
					<input   type="text" disabled="disabled" value="${b.flname }" class="form-control input-sm"/>
					<input id="oldflId" type="hidden"  value="${b.flId }" class="form-control input-sm"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">选择新分类:</label>
				<div class="col-sm-4">
				<select name="flId" class="form-control input-sm" id="flId">
							<option value="${b.flId}">${b.flname}</option>
							<option>----请选择----</option>
					</select> 
						</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书名称:</label>
				<div class="col-sm-4"><input type="text" name="name"  
							value="${b.name }" class="form-control input-sm"/>	</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">图书价格:</label>
				<div class="col-sm-4"><input type="text" name="money" value="${b.money }" class="form-control input-sm"/>	</div>
			</div>
					<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">出版社:</label>
				<div class="col-sm-4"><input type="text" name="press" value="${b.press }" class="form-control input-sm"/>	</div>
			</div>
			 
				<div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">作者:</label>
				<div class="col-sm-4"><input type="text"   name="author"
							value="${b.author }" class="form-control input-sm"/>	
							</div>
					</div>
				 <div class="form-group">
				<label class="col-sm-3 col-sm-offset-2 control-label text-info">库存:</label>
				<div class="col-sm-4"><input type="text"   name="stock"
							value="${b.stock }" class="form-control input-sm"/>	
							</div>
					</div>
				 
						<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">
							<button type="submit" class="btn   btn-success">
								修改 <span class="glyphicon glyphicon-cog"></span>
							</button>
						</div>

						<div class="col-sm-2  col-sm-offset-1">

							<a href="BookServlet?action=showPasgeBook&pageNew=${pageNew }&ausername=${ausername  }"
								class="btn btn-info ">返回 <span
								class="glyphicon glyphicon-repeat"></span></a>

						</div>
					</div>
		</form>
			<br>
					</div>
				
			</div>
</body>
</html>