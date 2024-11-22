<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">

    <!-- 保護者情報の表示 -->
        <h2>保護者情報</h2>
        <a href="menu.jsp">戻る</a>
        <table>
            <tr>
                <th>ID</th>
                <th>名前(保護者)</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>メールアドレス１</th>
                <th>メールアドレス２</th>
                <th>メールアドレス３</th>
            </tr>

            <c:if test="${not empty user}">
                <tr>
                    <td>${user.parents_id}</td>
                    <td>${user.parents_name}</td>
                    <td>${user.parents_address}</td>
                    <td>${user.parents_tel}</td>
                    <td>${user.parents_mail1}</td>
                    <td>${user.parents_mail2}</td>
                    <td>${user.parents_mail3}</td>

                <td>
                <form action="ParentsEdit.action" method="post">
                    <input type="hidden" name="parents_id" value="${user.parents_id}"/>
                    <button type="submit">編集</button>
                </form>
            	</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>
