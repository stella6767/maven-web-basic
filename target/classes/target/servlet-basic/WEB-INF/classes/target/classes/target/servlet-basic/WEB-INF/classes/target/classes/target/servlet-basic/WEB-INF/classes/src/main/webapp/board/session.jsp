<%--
  Created by IntelliJ IDEA.
  User: gangmingyu
  Date: 2022/02/06
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

  <h1>세션에 대해서 알아보자!</h1>


  <c:forEach var="board" items="${boards}">
      <div>${board.title}</div>
      <div>${board.content}</div>
  </c:forEach>


  <div>${sessionScope.principal}</div>

  <a href="/board?cmd=list">게시글 리스트</a>


</body>
</html>
