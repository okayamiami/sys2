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

<h2>バス選択</h2>
<table>
    <tr>
        <th>施設ID</th>
        <th>バスID</th>
        <th>名前</th>
        <th>選択</th>
    </tr>

<c:if test="${not empty bus_set}">
    <c:forEach var="bus" items="${bus_set}">
        <tr>
            <td>${bus.facility_id}</td>
            <td>${bus.bus_id}</td>
            <td>${bus.bus_name}</td>
            <td>
                <form action="QrReader.action" method="post">
                    <input type="hidden" name="facility_id" value="${bus.facility_id}" />
                    <input type="hidden" name="bus_id" value="${bus.bus_id}" />
                    <input type="hidden" name="bus_name" value="${bus.bus_name}" />
                    <button type="submit">選択</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</c:if>


</table>


<a href="QrMenu.action">QR機能選択画面に戻る</a>
</div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>