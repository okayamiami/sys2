<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>QRコード読み取り</title>
    <script src="https://cdn.jsdelivr.net/npm/jsqr@1.4.0/dist/jsQR.js"></script>
</head>
<body>
    <h2>QRコード読み取りページ</h2>

    <button onclick="startQRCodeReader()">QRコード読み取りを開始</button>

    <!-- スキャン用のカメラビュー -->
    <div id="scannerContainer" style="display:none;">
        <video id="video" width="100%" height="auto" style="border:1px solid black;"></video>
        <canvas id="canvas" style="display:none;"></canvas>
    </div>

    <!-- QRコード読み取り後に送信するフォーム -->
    <form name="qrForm" action="QrReaderExecute.action" method="post">
        <input type="hidden" id="qrData" name="qrData" />

    <%
    // リクエストから必要なパラメータを取得
    String bus_id = request.getParameter("bus_id");
	%>

        <input type="hidden" name="bus_id" value="<%= bus_id %>">
    </form>

    <script>
        let video = document.getElementById('video');
        let canvas = document.getElementById('canvas');
        let ctx = canvas.getContext('2d');
        let scanning = false;

        // QRコードリーダーを開始
        function startQRCodeReader() {
            document.getElementById('scannerContainer').style.display = 'block';
            navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } })
                .then(function(stream) {
                    video.srcObject = stream;
                    video.setAttribute('playsinline', true); // for iOS Safari
                    video.play();
                    scanning = true;
                    scanQRCode();
                })
                .catch(function(error) {
                    alert('カメラを起動できませんでした: ' + error);
                });
        }

        // QRコードをスキャン
        function scanQRCode() {
            if (scanning) {
                canvas.width = video.videoWidth;
                canvas.height = video.videoHeight;
                ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
                let imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
                let code = jsQR(imageData.data, canvas.width, canvas.height, {
                    inversionAttempts: "dontInvert",
                });

                if (code) {
                    // QRコードが読み取れた場合
                    document.getElementById('qrData').value = code.data;
                    document.qrForm.submit();  // フォーム送信
                    scanning = false;  // スキャンを停止
                } else {
                    // QRコードが見つからない場合、再度スキャン
                    requestAnimationFrame(scanQRCode);
                }
            }
        }
    </script>
</body>
</html>

