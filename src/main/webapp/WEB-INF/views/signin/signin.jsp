<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="/resources/css/signin/signin.css" rel="stylesheet" />
</head>
<body>
	<div class="signin-content">
		<div class="signin-logo">
			<a href="/"><img src="/resources/site_img/bogeum_logo.png" width="200px"></a>
		</div>
		<div class="signin-text">
			로그인
		</div>
		<div class="signin-form-wrapper">
			<form action="/signin" method="post">
				<label for="input-email">이메일</label>
				<input class="signin-input" id="input-email" name="email" />
				<label for="input-passwd">비밀번호</label>
				<input class="signin-input" id="input-passwd" name="passwd" />
				<button class="signin-submit" type="submit">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>