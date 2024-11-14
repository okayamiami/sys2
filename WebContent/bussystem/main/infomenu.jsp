<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class ="con">

<h2>お知らせ機能選択</h2>

<a href="InfoList.action">お知らせ一覧</a>

<a href="InfoCreate.action">お知らせ作成</a>
</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>