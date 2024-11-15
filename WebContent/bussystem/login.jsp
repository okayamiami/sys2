<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href='../bussystem/css/style22.css'>
  <title>登園・バス管理システム</title>
</head>
<body>
  <div class="header">
    <div>
      <h1>登園・バス管理システム</h1>
      <h2>ログイン</h2>
      <div>${errors.get("null")}</div>
    </div>
  </div>

  <form action="LoginExecute.action" method="post">
    <div class="log">
      <label>ID</label>
      <%-- value=T0000001 --%>
      <%-- value=P2400001 --%>
      <input type="text" name="user_id" maxlength="20" value="M0000001" required><br>
    </div>

    <div class="pas">
      <label>パスワード</label>
      <%-- value=kanasen01 --%>
      <%-- value=nohara111 --%>
      <input type="password" id="user_pass" name="user_pass" maxlength="20" value="kanakan01" required/><br>
      <input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
      <label for="showPassword">パスワードを表示</label>

      <script>
        function togglePasswordVisibility() {
          let passwordInput = document.getElementById("user_pass");
          let showPasswordCheckbox = document.getElementById("showPassword");

          if (showPasswordCheckbox.checked) {
            passwordInput.type = "text";
          } else {
            passwordInput.type = "password";
          }
        }
      </script><br>
    </div>
	<div class="fac">
    <label>facility_ID</label>
    <input type="text" name="facility_id" maxlength="20" value="KK000" required><br>
	</div>

	<div class="button">
    <input type="submit" name="login" value="ログイン"/>
    </div>

  </form>
  <c:import url="/common/footer.jsp" />
</body>
</html>
