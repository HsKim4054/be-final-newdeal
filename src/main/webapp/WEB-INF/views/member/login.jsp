<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/logins/login-12/assets/css/login-12.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<style>
    #kakao-login-btn img, #naver-login-btn img {
        width: 100%;
    }

    #kakao-login-btn:hover, #naver-login-btn:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 적당한 그림자 효과 */
    }

    /* 반응형 처리 */
    @media (max-width: 768px) {
        #kakao-login-btn, #naver-login-btn {
            max-width: 100%;
            border-radius: 10px;
        }
        #kakao-login-btn img, #naver-login-btn img {
            width: 100%;
            height: auto;
            object-fit: contain; /* 이미지 비율을 유지하며 크기 조정 */
        }
    }
</style>
<body>
    <!-- Login -->
    <section class="py-3 py-md-5 py-xl-8">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="mb-5" align="center">
                        <img src="${contextPath}/images/logo.png" alt="로고" width="250" onclick="location.href='${contextPath}/'">
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-12 col-lg-10 col-xl-8">
                    <div class="row gy-5 justify-content-center">
                        <div class="col-12 col-lg-5">
                            <form action="#" method="post">
                                <div class="row gy-3 overflow-hidden">
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="email" class="form-control border-0 border-bottom rounded-0" name="email" id="email" placeholder="name@example.com" required>
                                            <label for="email" class="form-label">이메일</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" value="" placeholder="Password" required>
                                            <label for="password" class="form-label">비밀번호</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn btn-lg btn-dark rounded-10 fs-6" style="color: white; background-color: #0D31B2" type="submit">로그인</button>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="text-end">
                                            <a href="${contextPath}/member/register" class="link-secondary text-decoration-none" >회원가입</a>
                                            <a href="${contextPath}/member/passwordUpdate" class="link-secondary text-decoration-none">비밀번호 찾기</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12 col-lg-2 d-flex align-items-center justify-content-center gap-3 flex-lg-column">
                            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                            <div>or</div>
                            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                        </div>
                        <div class="col-12 col-lg-5 d-flex align-items-center">
                            <div class="d-flex gap-3 flex-column" >
                                <a id="kakao-login-btn"><img src="${contextPath}/images/kakao_login.png" height="50" alt="카카오 로그인 버튼"/></a>
                                <a id="naver-login-btn"><img src="${contextPath}/images/naver_login.png" height="50" alt="네이버 로그인 버튼"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
