<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欠席連絡</title>

</head>
<body>
<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
    <form action="AbsenceSelect.action" method="get">
        <button type="submit">戻る</button>
    </form>
		<!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>
		<form method="get">
			<label> 欠席日 </label>
			<select name="f1">
				<option value="0">--------</option>
				<c:forEach var="absdate" items="${datelist}">
					<%-- 現在のchildIdと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${absdate}" <c:if test="${absdate==f1}">selected</c:if>>${absdate}</option>
				</c:forEach>
			</select>

			<label> クラス </label>
			<select name="f2">
				<option value="0">--------</option>
				<c:forEach var="ClassName" items="${class_name_set}">
					<%-- 現在のchildNameと選択されていたf2が一致していた場合selectedを追記 --%>
					<option value="${ClassName}" <c:if test="${ClassName==f2}">selected</c:if>>${ClassName}</option>
				</c:forEach>
			</select>

			<label> 名前 </label>
			<select name="f3">
				<option value="0">--------</option>
				<c:forEach var="childName" items="${child_name_set}">
					<%-- 現在のclassNameと選択されていたf2が一致していた場合selectedを追記 --%>
					<option value="${childName}" <c:if test="${childName==f3}">selected</c:if>>${childName}</option>
				</c:forEach>
			</select>

			<button>絞込み</button>

			<div>${errors.get("f1")}</div>
		</form>

		<!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>

        <!-- 情報表示 -->
        <c:if test="${empty error}">

			<c:choose>
				<c:when test="${abs.size()>0}">
					<div>検索結果：${abs.size()}件</div>

					<table class="table table-hover">
						<tr>
							<th>日付</th>
							<th>クラス</th>
							<th>名前</th>
							<th>欠席理由</th>

						</tr>
						<c:forEach var="abs" items="${abs}">
						    <tr>
						        <td>${abs.absence_date}</td>
						        <td>
						            <!-- 子供名をchild_setから取得 -->
						            <c:if test="${not empty child_set}">
						                <c:forEach var="childItem" items="${child_set}">
						                    <c:if test="${abs.child_id eq childItem.child_id}">
						                        ${childItem.child_name}
						                    </c:if>
						                </c:forEach>
						            </c:if>
						        </td>
						        <td>${abs.absence_main}</td>
						    </tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>欠席情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	<a href="Menu.action">メニュー画面に戻る</a>
	</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>