<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href=${pageContext.request.contextPath}/bussystem/css/style.css>
  <title>登園・バス管理システム</title>
</head>


  <div class="header">
    <h1>
        <a class="home-icon" href="Menu.action">
            <img src="${pageContext.request.contextPath}/images/kids.png" alt="ホーム">登園・バス管理システム
        </a>
    </h1>
    <!-- 日付表示 -->
    <div id="dateContainer">
        <span id="currentDate"></span>

    </div>
    <div class="links">
        <!-- 各ボタン間に間隔ができます -->
        <a href="Menu.action">
        	<img src="${pageContext.request.contextPath}/images/home_g.png" alt="メニュー">
        </a>
        <a href="Syslogout.action">
            <img src="${pageContext.request.contextPath}/images/logout.png" alt="ログアウト">
        </a>
    </div>
<script>
document.addEventListener("DOMContentLoaded", function() {
  var today = new Date();
  var days = ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日'];
  var date = today.getFullYear() + '年' + (today.getMonth() + 1) + '月' + today.getDate() + '日' + ' (' + days[today.getDay()] + ')';
  document.getElementById('currentDate').innerText = date;
});
</script>
</div>

  <div id="h_line">
    <img src="${pageContext.request.contextPath}/images/drop_line.png" alt="しずく線">
  </div>


<body>

<div class="main">

<div class ="con">
        <h2 class="title">保護者情報</h2>
        <form action="NewInfoExecute.action" method="post">
            <table>
            <c:if test="${not empty user}">
                <tr><th>ID</th><td>
                    <input type="hidden" name="parents_id" value="${user.parents_id}"/>
                    ${user.parents_id} <!-- IDは表示する -->
                		</td></tr>

                    <tr><th>名前(保護者)</th><td>
                             <input type="text" name="parents_name" pattern="^[^\d０-９]*$" required title="数字を含めないでください"/>
                        </td></tr>

                    <tr><th>住所</th><td>
                            <input type="text" name="parents_address"
           					pattern="^[\u4E00-\u9FFF\u3040-\u309F\u30A0-\u30FF0-9\s\-ー,、]*$"
           					required
           					title="住所は漢字、ひらがな、カタカナ、数字、一部の記号（-、ー、,）を使用してください"/>
                        </td></tr>

                    <tr><th>パスワード</th><td>
                            <input type="password" name="parents_pass"
           					pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}"
           					required
           					title="8文字以上で、大文字・小文字・数字をそれぞれ1文字以上含めてください"/>
                        </td></tr>

                    <tr><th>電話番号</th><td>
                            <input type="tel" name="parents_tel"
       						pattern="^\d{10,11}$"
       						required
       						title="10～11桁の数字で入力してください（例: 09012345678）"/>
                        </td></tr>

                    <tr><th>メールアドレス１</th><td>
                            <input type="email" name="parents_mail1" required/>
                        </td></tr>
                    <tr><th>メールアドレス２</th><td>
                            <input type="email" name="parents_mail2" />
                        </td></tr>

                    <tr><th>メールアドレス３</th> <td>
                            <input type="email" name="parents_mail3" />
                        </td></tr>
                </c:if>
            </table>

            <button type="submit" class="button-send">保存</button>
        </form>
    </div>
</div>

<c:import url="/common/footer.jsp" />