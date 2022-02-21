<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="<c:url value='/resources/css/signin/signin.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/signup/signup.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/common.css' />" rel="stylesheet" />

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/signup/signup.js' />"></script>
</head>
<body>
	<div class="signin-content">
		<div class="signin-logo">
			<a href="<c:url value='/' />"><img src="<c:url value='/resources/site_img/bogeum_logo.png' />" width="200px"></a>
		</div>
		<div class="signin-text">
			회원가입
		</div>
		<div class="signin-form-wrapper">
			<form action="<c:url value='/signup' />" method="post">
				<label for="input-email">이메일</label>
				<input class="input-signin" id="input-email" name="email" />
				<label for="input-passwd">비밀번호</label>
				<input class="input-signin" id="input-passwd" name="passwd" />
				<label for="input-passwd">비밀번호 확인</label>
				<input class="input-signin" id="input-passwd-repeat" name="passwdrp" />
				
				<button class="btn-signin" type="submit">회원가입</button>
			</form>
		</div>
	</div>
</body>
</html>