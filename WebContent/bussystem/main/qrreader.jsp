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

    <c:import url="/common/header.jsp" />

    <div class="main">
        <c:import url="/common/navi.jsp" />

        <div class="con">

            <!-- QRコード読み取り開始ボタン -->
            <button onclick="startQRCodeReader()">QRコード読み取りを開始</button>

            <!-- スキャン用のカメラビュー -->
            <div id="scannerContainer" style="display:none;">
                <video id="video" width="100%" height="auto" style="border:1px solid black;" autoplay playsinline></video>
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
                const ctx = canvas.getContext('2d');
                let scanning = false;

                // QRコードリーダーを開始
                async function startQRCodeReader() {
                    try {
                        document.getElementById('scannerContainer').style.display = 'block';

                        // カメラアクセスをリクエスト
                        const stream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } });
                        video.srcObject = stream;

                        video.addEventListener('loadedmetadata', () => {
                            scanning = true;
                            scanQRCode();
                        });
                    } catch (error) {
                        console.error("カメラ起動エラー: ", error);
                        alert("カメラを起動できませんでした。");
                    }
                }

                // QRコードをスキャン
                function scanQRCode() {
                    if (!scanning) return;

                    try {
                        if (video.videoWidth === 0 || video.videoHeight === 0) {
                            console.warn("カメラの解像度が取得できません。");
                            return setTimeout(scanQRCode, 100); // 少し待って再試行
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

                            // QRコードの位置に枠を描画
                            drawQRCodeOutline(code);

                            // スキャン停止してフォーム送信
                            scanning = false;
                            video.srcObject.getTracks().forEach(track => track.stop()); // カメラを停止
                            document.qrForm.submit(); // フォーム送信
                        } else {
                            // QRコードが見つからない場合、再度スキャン
                            requestAnimationFrame(scanQRCode);
                        }
                    } catch (error) {
                        console.error("QRコード読み取りエラー: ", error);
                        alert("QRコードの読み取り中にエラーが発生しました。");
                    }
                }

                // QRコードの位置に枠を描画
                function drawQRCodeOutline(code) {
                    ctx.beginPath();
                    ctx.moveTo(code.topLeft.x, code.topLeft.y);
                    ctx.lineTo(code.topRight.x, code.topRight.y);
                    ctx.lineTo(code.bottomRight.x, code.bottomRight.y);
                    ctx.lineTo(code.bottomLeft.x, code.bottomLeft.y);
                    ctx.closePath();
                    ctx.lineWidth = 4;
                    ctx.strokeStyle = "#FF0000"; // 赤色の枠
                    ctx.stroke();
                }
            </script>
        </div>
    </div>
</body>
</html>
