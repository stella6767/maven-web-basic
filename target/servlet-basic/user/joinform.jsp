<%--
  Created by IntelliJ IDEA.
  User: gangmingyu
  Date: 2022/02/05
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>


    <div class="container">
        <h1>회원가입</h1>
        <form method="post" action="/user?cmd=save">
            <div class="form-group">
                <label>name:</label>
                <input type="text" class="form-control" name="name">
            </div>
            <div class="form-group">
                <label >password:</label>
                <input type="text" class="form-control"  name="password">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>


    <a href="/user?cmd=loginForm">로그인 화면</a>

</body>
</html>
