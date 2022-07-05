<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HỆ THỐNG HỖ TRỢ TÌM KIẾM ĐỊA ĐIỂM DU LỊCH</title>
<link rel="stylesheet" href= "${cssUrl}">

<style><%@include file="/resources/css/login.css"%></style>

</head>
<body>
<c:if test="${message == 'Tài khoản đã tồn tại !' }">
	<script type="text/javascript">alert("Tài khoản đã tồn tại !")</script>
</c:if>
<div class="login__form__container">
  <div class="login__form">
    <div class="side__image" onclick = "">
      <img id="#img01" src="https://cdn.pixabay.com/photo/2020/02/23/15/34/vietnam-4873680_960_720.jpg" alt="">
  </div>
    <form class="side__form" method ="POST" id = "signup_form" action ="./signup">
      <div class="form__header">
       <span class="big__text_style1">MYTOUR</span><br>
        <span class="big__text_style2">Xin chào!</span>
      </div>
    <input type="text" placeholder="Tài khoản" name="name" class="form__input" >
    <input type="password" id="password" name="password" placeholder="Mật khẩu" class ="form__input" >
    <input type="password" name="retypepassword" placeholder="Nhập lại mật khẩu" class ="form__input" >
    <input type="submit" value="Đăng ký" class="login__button">
    
    <a class="nav_link" href="/login">Đăng nhập</a>
 </form>
  </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
<script type="text/javascript"><%@include file="/resources/js/validate.js"%></script>
</body>
</html>