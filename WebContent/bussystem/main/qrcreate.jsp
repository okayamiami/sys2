<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />

<div class="main">
<div class ="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="QrMenu.action">QR機能選択</a></li>
	    <li class="breadcrumb-item active" aria-current="page">QRコード作成</li>
	  </ol>
	</nav>


<h2 class="title">QRコード作成</h2>
<table>
    <tr>
        <th>ID</th>
        <th>名前</th>
        <th>クラス</th>
        <th>アクション</th>
    </tr>

<c:if test="${not empty child_set}">
    <c:forEach var="child" items="${child_set}">
        <tr>
            <td>${child.child_id}</td>
            <td>${child.child_name}</td>
            <td>
                <c:if test="${not empty class_set}">
                    <c:forEach var="classItem" items="${class_set}">
                        <c:if test="${child.class_id eq classItem.class_id}">
                            ${classItem.class_name}
                        </c:if>
                    </c:forEach>
                </c:if>
            </td>
            <td>
                <form action="QrCreateExecute.action" method="post">
                    <input type="hidden" name="child_id" value="${child.child_id}" />
                    <input type="hidden" name="child_name" value="${child.child_name}" />
                    <input type="hidden" name="facility_id" value="${child.facility_id}" />
                    <button type="submit" class="button-send">作成</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</c:if>


</table>



</div>
</div>


<c:import url="/common/footer.jsp" />