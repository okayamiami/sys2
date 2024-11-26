<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class ="con">

<h2>QRコード作成</h2>
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
                    <button type="submit">作成</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</c:if>


</table>


<a href="QrMenu.action">QR機能選択画面に戻る</a>
</div>
</div>


<c:import url="/common/footer.jsp" />