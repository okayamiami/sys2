<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="side">

<h2>メニュー</h2>


<form action = "Menu.action" method="post">
		<%
		session.getAttribute("user_type");
		%>

			<c:choose>
				<c:when test="${user_type == 'M'}">
					<h2>管理者</h2>
					<ul>
					<li><a href="NewRegist.action">新規登録</a></li>
					<li><a href="ChildList.action">名簿情報一覧</a></li>
					<li><a href="保護者情報">保護者情報</a></li>
					<li><a href="子供情報">子供情報</a></li>
					<li><a href="AbsenceSelect.action">欠席機能</a></li>
					<li><a href="InfoMenu.action">お知らせ機能</a></li>
					<li><a href="QrMenu.action">QR機能</a></li>
					</ul>
				</c:when>
				<c:when test="${user_type == 'T'}">
					<h2>先生</h2>
					<ul>
					<li><a href="ChildList.action">名簿情報一覧</a></li>
					<li><a href="AbsenceSelect.action">欠席機能</a></li>
					<li><a href="InfoMenu.action">お知らせ機能</a></li>
					<li><a href="QrMenu.action">QR機能</a></li>
					</ul>
				</c:when>
				<c:when test="${user_type == 'P'}">
					<h2>保護者</h2>
					<ul>
					<li><a href="保護者情報">保護者情報</a></li>
					<li><a href="子供情報">子供情報</a></li>
					<li><a href="AbsenceReport.action">欠席報告</a></li>
					<li><a href="InfoList.action">お知らせ一覧</a></li>
					</ul>
				</c:when>
			</c:choose>

		</form>
</div>