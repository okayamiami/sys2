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
    </div>
  </div>
<div class="main">
<div class="con">
<div>${errors.get("null")}</div>
  <form action="LoginExecute.action" method="post">
  <dl>
      <dt><label>ID</label></dt>
      <%-- value=T0000001 --%>
      <%-- value=P2400001 --%>
      <dd><input type="text" name="user_id" maxlength="20" pattern="[a-zA-Z0-9]*" title="英数字のみ入力してください"required></dd>


      <dt><label>パスワード</label></dt>
      <%-- value=kanasen01 --%>
      <%-- value=nohara111 --%>
      <dd><input type="password" id="user_pass" name="user_pass" maxlength="20"  required/></dd>
      <dt><label for="showPassword">パスワードを表示</label>

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
      </script>
      </dt>
      <dd><input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" /></dd>


    <dt><label>facility_ID</label></dt>
    <dd><input type="text" name="facility_id" maxlength="20"  pattern="[a-zA-Z0-9]*" title="英数字のみ入力してください"required></dd>
	</dl>

	<div class="button">
	    <input type="submit" name="login" value="ログイン" class="button-save" />
	</div>


  </form>
  </div>
  </div>

    <div class="foo">
    <p>2024©</p>
    <p>大原学園teamD</p>
  </div>

</body>
</html>
