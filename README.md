# bogeum
Spring 5.3, Spring boot, JPA 등을 이용한 회사 일정관리, 커뮤니티 사이트 보금자리(Bogeum)

##Project Structure
- Spring 4.1
- Spring Boot
- Spring Security (인증 및 접근제한)
- JPA & QueryDSL (ORM & 동적SQL)
- OAuth2.0 & JWT (로그인)

> JPA를 사용하였으므로 객체 매핑을 위해 최대한 join을 사용하지 않도록 테이블을 설계하였습니다.
- ERD: https://www.erdcloud.com/d/pbLCwfGFHbdzvAypy

> 단순 뷰페이지를 응답하는것을 제외하고 대부분의 서버 통신은 ajax와 RESTful API를 이용하였습니다.
- API 명세서: https://yj-lee-1.gitbook.io/bogeum/
