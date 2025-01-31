<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />
<div class="main">
<div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="AbsenceSelect.action">欠席機能選択</a></li>
	    <li class="breadcrumb-item active" aria-current="page">欠席状況</li>
	  </ol>
	</nav>

<h2 class="title">本日の欠席状況</h2>
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
							<th></th>
							<th></th>


						</tr>
						<c:forEach var="abs" items="${abs}">
						    <tr>
						        <td>${abs.absence_date}</td>
								<td>
								    <c:forEach var="classItem" items="${class_set}">
								        <!-- eq を使用して比較 -->
								        <c:if test="${classItem.class_id eq abs.class_id}">
								            ${classItem.class_name}
								        </c:if>
								    </c:forEach>
								</td>
						        <td>${abs.child_name}</td>
						        <td>${abs.absence_main}</td>
								<td><!-- 更新 -->
								    <form action="AbsenceInfoEdit.action" method="get">
								        <input type="hidden" name="absenceId" value="${abs.absence_id}">
								        <button type="submit" class="button-send">変更</button>
								    </form>
								</td>
								<td><!-- 削除 -->
								    <form action="AbsenceInfoDelete.action" method="get">
								        <input type="hidden" name="absenceId" value="${abs.absence_id}">
								        <input type="hidden" name="facilityId" value="${facility_id}">
								        <button type="submit" class="button-send">削除</button>
								    </form>
								</td>

						    </tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>欠席情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	<div>


	</div>
	</div>
</div>

<c:import url="/common/footer.jsp" />
