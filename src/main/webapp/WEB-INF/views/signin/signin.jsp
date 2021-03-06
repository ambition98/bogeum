<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="<c:url value='/resources/css/signin/signin.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/common.css' />" rel="stylesheet" />

<script type="text/javascript" src="<c:url value='/resources/js/signin/signin.js' />"></script>
</head>
<body>
	<div class="signin-content">
		<div class="signin-logo">
			<a href="<c:url value='/' />"><img src="<c:url value='/resources/site_img/bogeum_logo.png' />" width="200px"></a>
		</div>
		<div class="signin-text">
			로그인
		</div>
		<div class="invalid-auth">
			이메일 또는 비밀번호가 틀립니다.
		</div>
		<div class="signin-form-wrapper">
			<form action="<c:url value='/signin' />" method="post">
				<label for="input-email">이메일</label>
				<input class="input-signin" id="input-email" name="email" />
				<div>
					<label for="input-passwd">비밀번호</label>
					<span class="find-pw"><a href="<c:url value='' />">비밀번호 찾기</a></span>
				</div>
				<input class="input-signin" id="input-passwd" name="passwd" />
				<button class="btn-signin" type="submit">로그인</button>
			</form>
		</div>
		<div class="signup-wrapper">
			아직 회원이 아니신가요? <a href="<c:url value='/signup' />">회원가입</a>
		</div>
	</div>
</body>
</html>