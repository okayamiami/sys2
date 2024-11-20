<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
    <!-- 子供情報の表示 -->
        <a href="menu.jsp">戻る</a>
        <table>
            <tr>
                <th>ID</th>
                <th>クラス</th>
                <th>名前</th>
            </tr>

            <c:forEach var="user" items="${user}">
                <tr>
                    <li>${user.parents_id}</li>
                    <li>${user.class_id}</li>
                    <li>${user.child_name}</li>
                </tr>
                <td>
             <form action="" method="post">
                 <input type="hidden" name="parents_id" value="${user.parents_id}" />
                 <button type="submit">編集</button>
             </form>
             <form action="" method="post">
                 <input type="hidden" name="parents_id" value="${user.parents_id}" />
                 <button type="submit">追加</button>
             </form>
             </td>
            </c:forEach>


        </table>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>
