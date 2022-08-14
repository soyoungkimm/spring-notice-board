<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-27
  Time: 오후 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <c:import url="../sections/header.jsp"/>
    <style>
        .board_tr:hover {
            background: #f3f3f3;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <c:import url="../sections/navigation.jsp"/>
            <br><br>
            <table class="table" style="text-align: center;">
                <thead>
                <tr>
                    <th style="background-color: #f5f5f5; text-align: center">글제목</th>
                    <th style="background-color: #f5f5f5; text-align: center">작성자</th>
                    <th style="background-color: #f5f5f5; text-align: center">작성일</th>
                </tr>
                </thead>
                <tbody class="table-group-divider">
                <c:forEach items="${boardList}" var="b">
                    <tr class="board_tr" onclick="location.href='/board/${b.id}'">
                        <td>${b.title}</td>
                        <td>${b.writerName}</td>
                        <td>${b.writeTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${fn:length(boardList) != 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${pagination.curPageNo > 1 }">
                                <li class="page-item">
                                    <a class="page-link" href="/board?p=${pagination.curPageNo - 1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="pageNo" begin="${pagination.beginPageNo}" end="${pagination.endPageNo}">
                            <c:choose>
                                <c:when test="${pageNo == pagination.curPageNo }">
                                    <li class="page-item active"><a class="page-link" href="">${pageNo}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="?p=${pageNo}">${pageNo}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pagination.curPageNo < pagination.totalPages }">
                                <li class="page-item">
                                    <a class="page-link" href="?p=${pagination.curPageNo + 1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </c:if>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<c:import url="../sections/footer.jsp"/>
</body>
</html>
