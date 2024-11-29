<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>QRコード読み取り</title>
    <script src="https://cdn.jsdelivr.net/npm/jsqr@1.4.0/dist/jsQR.js"></script>
</head>
<body>
    <h2>QRコード読み取り</h2>

    <c:import url="/common/header.jsp" />

    <div class="main">
        <c:import url="/common/navi.jsp" />

        <div class="con">
            <!-- QRコード読み取り開始ボタン -->
			<button class="button-qr" onclick="startQRCodeReader()" aria-label="QRコード読み取りを開始">QRコード読み取りを開始</button>
			<!-- QRコード読み取り中断ボタン -->
			<button class="button-qr" onclick="stopQRCodeReader()" aria-label="QRコード読み取りを中断">QRコード読み取りを中断</button>

            <!-- スキャン用のカメラビュー -->
            <div id="scannerContainer">
                <video id="video" width="100%" height="auto" style="border:1px solid black;" autoplay playsinline aria-label="QRコードスキャン用のビデオフィード"></video>
                <canvas id="canvas" style="display:none;"></canvas>
            </div>

            <!-- QRコード読み取り後に送信するフォーム -->
            <form name="qrForm" action="QrReaderExecute.action" method="post">
                <input type="hidden" id="qrData" name="qrData" />
                <input type="hidden" id="bus_id" name="bus_id" value="${bus_id}">
            </form>

            <script>
            const video = document.getElementById('video');
            const canvas = document.getElementById('canvas');
            const ctx = canvas.getContext('2d', { willReadFrequently: true });
            let scanning = false;
            let isCameraActive = false;

            // QRコードリーダーを開始
            async function startQRCodeReader() {
                if (isCameraActive) {
                    alert("カメラは既に動作中です。");
                    return;
                }
                isCameraActive = true;
                scanning = true;

                try {
                    document.getElementById('scannerContainer').style.display = 'block';

                    // カメラアクセスをリクエスト
                    const stream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } });
                    video.srcObject = stream;

                    video.addEventListener('loadedmetadata', () => {
                        scanQRCode();
                    });
                } catch (error) {
                    console.error("カメラ起動エラー: ", error);
                    alert("カメラを起動できませんでした。エラー内容: " + error.message);
                    isCameraActive = false;
                }
            }

            // QRコードをスキャン
            function scanQRCode() {
                if (!scanning) return;

                try {
                    if (video.videoWidth === 0 || video.videoHeight === 0) {
                        console.warn("カメラの解像度が取得できません。");
                        return setTimeout(scanQRCode, 100);
                    }

                    canvas.width = video.videoWidth;
                    canvas.height = video.videoHeight;
                    ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

                    const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
                    const code = jsQR(imageData.data, canvas.width, canvas.height, {
                        inversionAttempts: "dontInvert",
                    });

                    if (code) {
                        // QRコードが読み取れた場合
                        console.log("QRコード内容: ", code.data);
                        document.getElementById('qrData').value = code.data;

                        // bus_idの値をチェック
                        console.log("bus_id: ", document.getElementById('bus_id').value);

                        // スキャン停止してフォーム送信
                        scanning = false;
                        stopQRCodeReader();
                        document.getElementById('scannerContainer').innerHTML = "<p>QRコードのデータを送信中...</p>";
                        document.qrForm.submit();
                    } else {
                        setTimeout(scanQRCode, 100);
                    }
                } catch (error) {
                    console.error("QRコード読み取りエラー: ", error);
                    alert("QRコードの読み取り中にエラーが発生しました。");
                }
            }

            // QRコードリーダーを停止
            function stopQRCodeReader() {
                scanning = false;
                if (video.srcObject) {
                    video.srcObject.getTracks().forEach(track => track.stop());
                    video.srcObject = null;
                }
                document.getElementById('scannerContainer').style.display = 'none';
                isCameraActive = false;
            }
            </script>

            <c:if test="${not empty child_name}">
                <div>
                    <strong style="color:black;">${child_name}${getting_status}</strong>
                </div>
            </c:if>
            <a href="QrReaderSelectBus.action">バス選択に戻る</a>
            <a href="GetListInfo.action">乗降状況を確認</a>
        </div>
    </div>
</body>
</html>
