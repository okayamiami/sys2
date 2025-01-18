<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
<div class ="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="QrMenu.action">QR機能選択</a></li>
	    <li class="breadcrumb-item active" aria-current="page">バス選択（QR読み取り）</li>
	  </ol>
	</nav>


<h2 class="title">バス選択</h2>


<c:if test="${not empty bus_set}">
	<table>
    <tr>
        <th>施設ID</th>
        <th>バスID</th>
        <th>名前</th>
        <th>選択</th>
    </tr>
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
                    <button type="submit" class="button-send">選択</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>

</c:if>
<c:if test="${empty bus_set}">
    <p>バスが登録されていません。</p>
</c:if>





</div>
</div>


<c:import url="/common/footer.jsp" />
