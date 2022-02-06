<%--
  Created by IntelliJ IDEA.
  User: gangmingyu
  Date: 2022/02/05
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시글 리스트</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

    <h1> 게시글 리스트</h1>

    <c:forEach var="board" items="${boards}">
        <div>${board.title}</div>
        <div>${board.content}</div>
    </c:forEach>



    <div>${sessionScope.principal}</div>


    <a href="/board">게시글 입력</a>


    <a href="/board?cmd=session">세션에 대해서 알아보자</a>



    <%--복잡한 연산은 보통 서블릿에서 처리를 하고, 뷰 단은 화면을 랜더링하는 데에면 역할을 분리시키기로 약속을 했잖아요
뷰단에서도 연산이 필요할 때가 있거든요. JSTL , EL 표현식을 쓰기 보다, JSTL + EL 을 많이 써야.

이게 기본적인 MVC MODEL 2 방식이라고 하는데,

서버사이드 랜더링 이라고 한다. 서버에서 랜더링한 데이터를 바로 브라우저에 뿌려주는 것.
요런 걸 또 템플릿 엔진이라고 한다.. JSP, MUSTACHE, Thymelef



--%>


</body>
</html>
