<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-28
  Time: 오후 3:53
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
            <table class="table" style="text-align: center;">
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #eeeeee; text-align: center">게시글 상세
                        </th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <tr>
                        <td style="width: 20%;">글 제목</td>
                        <td colspan="2" style="min-height: 200px; text-align: left;">${board.title}</td>
                    </tr>
                    <tr>
                        <td style="width: 20%;">작성자</td>
                        <td colspan="2" style="min-height: 200px; text-align: left;">${board.writer_name}</td>
                    </tr>
                    <tr>
                        <td style="width: 20%;">작성일자</td>
                        <td colspan="2" style="min-height: 200px; text-align: left;">${board.write_time}</td>
                    </tr>
                    <tr>
                        <td style="width: 20%;">내용</td>
                        <td colspan="2" style="height: 200px; text-align: left;">
                            <pre>${board.content}</pre>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="text-align : center" >
                <a href="/board/list" class="btn btn-success">목록으로</a>
                <a href="" class="btn btn-primary">수정</a>
                <a onclick="return confirm('정말로 삭제하시겠습니까?')"  href="" class="btn btn-danger">삭제</a>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<c:import url="../sections/footer.jsp"/>
</body>
</html>
