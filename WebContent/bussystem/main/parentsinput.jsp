<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <h2>保護者ID入力</h2>
</head>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <!-- 保護者ID入力フォーム -->
        <form action="ParentsInput.action" method="post">
            <label for="parents_id">保護者ID:</label>
            <input type="text" id="parents_id" name="parents_id" required/>
            <button type="submit">検索</button>
        </form>

        <a href="menu.jsp">戻る</a>
    </div>
</div>

</body>
</html>
