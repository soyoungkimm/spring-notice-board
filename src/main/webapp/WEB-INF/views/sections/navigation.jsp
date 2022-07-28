<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-28
  Time: 오전 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand">게시판</a>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/board">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/board/list">리스트</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/board/create">새글작성</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
