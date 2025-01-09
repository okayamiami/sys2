<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>保護者情報表示</title>
</head>

<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">

        <!-- 保護者情報の表示 -->
        <h2>保護者情報</h2>

        <a href="menu.jsp" class="button-save">戻る</a>

        <br>
        <table>
            <c:if test="${empty error}">
            <br>
                <tr>
                    <th>保護者ID</th>
                    <td>${userinfo.parents_id}</td>
                </tr>
                <tr>
                    <th>名前（保護者）</th>
                    <td>${userinfo.parents_name}</td>
                </tr>
                <tr>
                    <th>パスワード</th>
                    <td>${userinfo.parents_pass}</td>
                </tr>
                <tr>
                    <th>住所</th>
                    <td>${userinfo.parents_address}</td>
                </tr>
                <tr>
                    <th>電話番号</th>
                    <td>${userinfo.parents_tel}</td>
                </tr>
                <tr>
                    <th>メールアドレス１</th>
                    <td>${userinfo.parents_mail1}</td>
                </tr>
                <tr>
                    <th>メールアドレス２</th>
                    <td>${userinfo.parents_mail2}</td>
                </tr>
                <tr>
                    <th>メールアドレス３</th>
                    <td>${userinfo.parents_mail3}</td>
                </tr>
            </c:if>
        </table>
		 <tr>
		    <td colspan="2" style="text-align: center;">
		        <form action="ParentsEdit.action" method="post">
		            <input type="hidden" name="parents_id" value="${userinfo.parents_id}" />

		            <!-- ボタンを div タグでラップ -->
		            <div class="button-save">
		                <button type="submit">編集</button>
		            </div>

		        </form>
		    </td>
		</tr>


    </div>
</div>

<c:import url="/common/footer.jsp" />

</body>

</html>
