<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
<table>
<c:if test="${not empty fc}">
<tr><th>施設ID</th><td>${fc.facility_id}</td></tr>
<tr><th>施設名</th><td>${fc.facility_name}</td></tr>
<tr><th>住所</th><td>${fc.facility_address}</td></tr>
<tr><th>電話番号</th><td>${fc.facility_tel}</td></tr>
<tr><th>メールアドレス</th><td>${fc.facility_mail}</td></tr>
<tr><th>アプリパスワード</th><td>********v </td></tr>
<tr><th>プラン</th><td>
        	<c:choose>
				<c:when test="${fc.getFacility_plan()}">
					S
				</c:when>
				<c:otherwise>
					L
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:if>
</table>
<form action="FacilityInfoEdit.action" method="post">
    <input type="hidden" name="facility_id" value="${fc.facility_id}"/>
    <button type="submit">編集</button>
</form>
</div>
</div>
<c:import url="/common/footer.jsp" />
</html>