<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="InfoMenu.action">お知らせ機能選択</a></li>
	    <li class="breadcrumb-item active" aria-current="page">お知らせ作成</li>
	  </ol>
	</nav>


        <h2 class="title">お知らせ作成</h2>

        <form action="InfoCreateExecute.action" method="post">
            <!-- ジャンルの選択 -->
            <label>ジャンル</label>
            <select name="info_genre" required>
                <option value="">---</option>
                <!-- 9つのジャンルを直接指定 -->
                <option value="行事関連">行事関連</option>
                <option value="日常活動">日常活動</option>
                <option value="健康・安全">健康・安全</option>
                <option value="給食・アレルギー">給食・アレルギー</option>
                <option value="保護者会・面談">保護者会・面談</option>
                <option value="お知らせ・お願い">お知らせ・お願い</option>
                <option value="緊急連絡">緊急連絡</option>
                <option value="入園・退園・進級">入園・退園・進級</option>
                <option value="園だより">園だより</option>
                <!-- その他 -->
                <option value="その他">その他</option>
            </select>
            <div>${errors.get("info_genre")}</div>

            <!-- タイトルの入力 -->
            <label>タイトル</label>
            <input type="text" name="title" value="${title}" maxlength="20" required>
            <div>${errors.get("title")}</div>

            <!-- 本文の入力 -->
			<label>本文</label>
			<textarea name="content" rows="5" cols="40" maxlength="300" required>${content}</textarea>
			<div>${errors.get("content")}</div>



            <div>
               <button type="submit" class="button-send">投稿</button>
            </div>
        </form>
    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />
