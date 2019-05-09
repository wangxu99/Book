<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>JavaScript控制页面5秒后自动跳转的代码</title>
<script type="text/javascript"> 
function countDown(secs,surl){ 
 //alert(surl); 
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs; 
 if(--secs>0){ 
  setTimeout("countDown("+secs+",'"+surl+"')",1000); 
 }
 else
 {  
   window.parent.location=surl; 
 } 
} 
</script>
<style type="text/css">
h3{
color: #107EBA;
margin-left: 450px;
margin-top: 250px;
}
</style>
</head>
<body><h3><span id="jumpTo">3</span>秒后自动跳转到   - - -> 登录界面</h3> 
<script type="text/javascript">
countDown(3,'http://39.96.216.117/LibraryManagementSystem/exit.jsp');
</script> 
</body>
</html>
