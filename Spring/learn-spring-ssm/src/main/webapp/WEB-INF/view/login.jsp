<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎</title>
</head>
<body>
 <form action="/user/login" method="post">
     <div> <p style="color: red">${msg}</p></div>
     <div> 用户名：<input  type="text" name="username" ></div>
     <div>密码&&：<input  type="password" name="password" ></div>
     <div> <input type="submit" value="登录"></div>
 </form>


</body>
</html>