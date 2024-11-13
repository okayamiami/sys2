<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>QRコード読み取り</title>
</head>
<body>
    <h2>QRコード読み取りページ</h2>
    <button onclick="startQRCodeReader()">QRコード読み取りを開始</button>
    <script>
        function startQRCodeReader() {
            // Androidのカメラを起動し、QRコード読み取りを開始
            window.location.href = "qrreader://start";  // カメラを起動
        }

        function onQRCodeScanned(data) {
            // QRコード読み取り後の処理
            // dataには読み取ったQRコードの内容が含まれている
            document.getElementById('qrData').value = data;
            document.qrForm.submit();  // フォーム送信
        }
    </script>

    <%
    // リクエストから必要なパラメータを取得
    String bus_id = request.getParameter("bus_id");
	%>

    <form name="qrForm" action="QrReaderExecute.action" method="post">
        <input type="hidden" id="qrData" name="qrData" />
        <input type="hidden" name="bus_id" value="<%= bus_id %>">
    </form>
</body>
</html>