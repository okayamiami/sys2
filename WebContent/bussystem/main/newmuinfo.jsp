<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href='../../bussystem/css/style.css'>
  <title>登園・バス管理システム</title>
</head>
<div class ="header">
<div>
  <h1>登園・バス管理システム</h1>
</div>
</div>
<div class="main">


    <div class="con">
        <h2>保護者情報</h2>
        <form action="NewInfoExecute.action" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <th>名前</th>
                    <th>パスワード</th>
                </tr>

                <c:if test="${not empty user}">
                    <tr>
                        <td>
                    <input type="hidden" name="user_id" value="${user.user_id}"/>
                    ${user.user_id} <!-- IDは表示する -->
                		</td>
                        <td>
                            <input type="text" name="user_name" pattern="^[^0-9]*$" required title="数字を含めないでください"/>
                        </td>
                        <td>
                            <input type="password" name="user_pass"
           					pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}"
           					required
           					title="8文字以上で、大文字・小文字・数字をそれぞれ1文字以上含めてください"/>
                        </td>
                    </tr>
                </c:if>
            </table>

            <button type="submit">保存</button>
        </form>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>