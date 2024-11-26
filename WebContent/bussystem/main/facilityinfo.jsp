<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
<table>
<tr>
    <th>施設ID</th>
    <th>施設名</th>
    <th>住所</th>
    <th>電話番号</th>
    <th>メールアドレス</th>
    <th>プラン</th>
</tr>

<c:if test="${not empty fc}">
    <tr>
        <td>${fc.facility_id}</td>
        <td>${fc.facility_name}</td>
        <td>${fc.facility_address}</td>
        <td>${fc.facility_tel}</td>
        <td>${fc.facility_mail}</td>
        <td>
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