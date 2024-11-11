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

<h2>QRコード作成</h2>
<table>
    <tr>
        <th>ID</th>
        <th>名前</th>
        <th>クラス</th>
        <th>アクション</th>
    </tr>

    <c:forEach var="child" items="${child_set}">
        <tr>
            <td>${child.child_id}</td>
            <td>${child.child_name}</td>
            <td>${child.class_id}</td>
            <td>
                <!-- 作成ボタン -->
                <form action = "QrCreateExecute.action" method="post">
                    <input type="hidden" name="child_id" value="${child.child_id}" />
                    <input type="hidden" name="child_name" value="${child.child_name}" />
                    <input type="hidden" name="facility_id" value="${facility_id}" />
                    <button type="submit">作成</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>



</div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>