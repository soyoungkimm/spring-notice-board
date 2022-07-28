<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-27
  Time: 오후 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="./sections/header.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <c:import url="./sections/navigation.jsp"/>
                <br>
                <div style="text-align : center;"><h1>게시판 메인</h1></div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <c:import url="./sections/footer.jsp"/>
</body>
</html>
