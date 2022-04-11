window.onload = function() {
	let isValidEmail = false;
	let isValidPw = false;
	let isSamePw = false;
	
	$('#signup-form').submit(function() {
		let email = $('#input-email').val();
		let passwd = $('#input-passwd').val();
		let passwdrp = $('#input-passwd-repeat').val();
		
		if(!isValidEmail) {
			alert('이메일을 확인해주세요');
			return false;
		}
		
		if(!isValidPw) {
			alert('비밀번호 형식을 확인해주세요');
			return false;
		}
		
		if(!isSamePw) {
			alert('비밀번호 확인이 다릅니다');
			return false;
		}
		
		$.post("/bogeum/api/account", {
			email : email,
			passwd : passwd,
		})
		
		.done(function(data) {
			console.log(data.responseJSON);
		})
		
		.fail(function(data) {
			console.log(data.responseJSON);
		});
		
		return false;
	});
	
	$('#input-email').on('blur', function() {
		const input = $('#input-email');
		const msg1 = $('#invalid-email-pattern');
		const msg2 = $('#dup-email');
		const email = $(input).val();
		
		if(isValidEmailPattern(email)) {
			changeToValidState(input, msg1);
			isValidEmail = true;
			
			$.get("/bogeum/api/account/exists", {"email": email})
				.done(function(data) {
					//200 OK
					console.log(data.responseJSON);
					changeToValidState(input, msg2);
				})
				
				.fail(function(data) {
					//409 Conflict
					console.log(data.responseJSON);
					changeToInvalidState(input, msg2);
				});
			
		} else {
			changeToInvalidState(input, msg1);
			isValidEmail = false;
		}
	});
	
	$('#input-passwd').on('blur', function() {
		const input = $('#input-passwd');
		const msg = $('#invalid-pw-pattern');
		const pw = $(input).val();
		
		if(isValidPwPattern(pw)) {
			changeToValidState(input, msg);
			isValidPw = true;
		} else {
			changeToInvalidState(input, msg);
			isValidPw = false;
		}
		
	});
	
	$('#input-passwd-repeat').on('blur', function() {
		const input = $('#input-passwd-repeat');
		const msg = $('#not-same-pw');
		const pw = $('#input-passwd').val();
		const pwConfirm = $(input).val();
		
		if(pw == pwConfirm) {
			changeToValidState(input, msg);
			isSamePw = true;
		} else {
			changeToInvalidState(input, msg); 
			isSamePw = false;
		}
		
	});
	
	function isValidEmailPattern(email) {
		const pattern = new RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
		return pattern.test(email);
	}
	
	function isValidPwPattern(pw) {
		const pattern = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[\da-zA-Z$@$!%*#?&]{8,}/g);
		return pattern.test(pw);
	}
	
	//input : input 태그 테두리 빨간색으로 변경
	//message : 메시지 div 태그 표시
	function changeToInvalidState(input, message) {
		$(input).addClass('invalid-input');
		$(message).removeClass('none');
	}
	
	//input : input 태그 테두리 빨간색 제거
	//message : 메시지 div 태그 숨김
	function changeToValidState(input, message) {
		$(input).removeClass('invalid-input');
		$(message).addClass('none');
	}
}