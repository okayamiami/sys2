<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class ="con">

<h2>QRコード作成</h2>
<p>作成が完了しました</p>
<p>${child_name}</p>
<!-- QRコード画像を表示 -->
    <div>
        <img src="${qrImagePath}" alt="QR Code" width="500" height="500" />
    </div>
<a href="QrCreate.action">QR作成画面に戻る</a>
</div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>