<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 欠席報告画面 （子供名前選択）-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欠席報告</title>
	<form action = "AbsenceReportExecute.action" method="post">
		<label>名前(子供)</label>
		<select name="child_name">
			<option value="0">--------</option>
			<c:forEach var="year" items="${cNamelist}">
			<!-- ここから作業する！！！！！！！！！！！！！ -->
				<%-- 現在のyearと選択されていたent_yearが一致していた場合selectedを追記 --%>
				<option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
			</c:forEach>
		</select>
		<div>${errors.get("ent_year")}</div>

		<label>学生番号</label>
		<input type="text" name="no"
			placeholder="学生番号を入力してください" maxlength="10" value="${no}" required />
		<div>${errors.get("no")}</div>

		<input type="submit" value="送信">
	</form>


	<%--<a href="StudentList.action">戻る</a>--%>

</head>
<body>

</body>
</html>