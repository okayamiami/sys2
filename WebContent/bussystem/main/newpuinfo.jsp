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
                    <th>名前(保護者)</th>
                    <th>住所</th>
                    <th>パスワード</th>
                    <th>電話番号</th>
                    <th>メールアドレス１</th>
                    <th>メールアドレス２</th>
                    <th>メールアドレス３</th>
                </tr>

                <c:if test="${not empty user}">
                    <tr>
                        <td>
                    <input type="hidden" name="parents_id" value="${user.parents_id}"/>
                    ${user.parents_id} <!-- IDは表示する -->
                		</td>
                        <td>
                             <input type="text" name="parents_name" pattern="^[^0-9]*$" required title="数字を含めないでください"/>
                        </td>
                        <td>
                            <input type="text" name="parents_address"
           					pattern="^[\u4E00-\u9FFF\u3040-\u309F\u30A0-\u30FF0-9\s\-ー,、]*$"
           					required
           					title="住所は漢字、ひらがな、カタカナ、数字、一部の記号（-、ー、,）を使用してください"/>
                        </td>
                        <td>
                            <input type="password" name="parents_pass"
           					pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}"
           					required
           					title="8文字以上で、大文字・小文字・数字をそれぞれ1文字以上含めてください"/>
                        </td>
                        <td>
                            <input type="tel" name="parents_tel"
       						pattern="^\d{10,11}$"
       						required
       						title="10～11桁の数字で入力してください（例: 09012345678）"/>
                        </td>
                        <td>
                            <input type="email" name="parents_mail1" required/>
                        </td>
                        <td>
                            <input type="email" name="parents_mail2" />
                        </td>
                        <td>
                            <input type="email" name="parents_mail3" />
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