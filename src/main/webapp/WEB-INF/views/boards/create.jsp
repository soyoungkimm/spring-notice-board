<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-28
  Time: 오후 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="../sections/header.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <c:import url="../sections/navigation.jsp"/>
            <br>
            <form action="/board" method="post">
                <table class="table" style="text-align: center;">
                    <thead>
                        <tr>
                            <td colspan="2"	style="background-color: #eeeeee; text-align: center">새글작성</td>
                        </tr>
                    </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input type="text" class="form-control" placeholder="제목" name="title" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <textarea class="form-control" placeholder="내용" name="content" maxlength="2048" style="height: 350px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">
                                <div class="form-check form-switch">
                                    <input class="form-check-input" name="state" type="checkbox" role="switch" id="state_slide" value="1" checked>
                                    <label class="form-check-label" id="state_label" for="state_slide">공개</label>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div style="text-align : center" >
                    <input type="submit" class="btn btn-success" value="저장">
                </div>
            </form>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<c:import url="../sections/footer.jsp"/>
</body>
</html>
