<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />


<div class="main">
<div class="conmenu">
		<%
		session.getAttribute("user_type");
		session.getAttribute("user_id");
		%>
<c:choose>
		<c:when test="${fc_plan}">
				<c:choose>
				    <c:when test="${user_type == 'M'}">
				        <h2>管理者</h2>
							<div class="menu-section">
							    <h3>情報関係</h3>
							    <div class="menu-links">
							        <a href="NewRegistMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/新規作成.png" alt="新規登録" width="50">
									    <span>新規登録</span>
									</a>
									<a href="ManageUser.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/ユーザー情報.png" alt="ユーザー情報" width="50">
									    <span>ユーザー情報</span>
									</a>
									<a href="Parents.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/保護者.png" alt="保護者情報" width="50">
									    <span>保護者情報</span>
									</a>
									<a href="ChildInfo.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/子供.png" alt="子供情報" width="50">
									    <span>子供情報</span>
									</a>

							        <c:if test="${user_id == 'M0000001'}">
								        <a href="FacilityInfoMenu.action" class="menu-links-sub">
										    <img src="${pageContext.request.contextPath}/images/施設・バス.png" alt="施設・バス情報" width="50">
										    <span>施設・バス情報</span>
										</a>
							        </c:if>
							    </div>
							</div>

							<div class="menu-section">
							    <h3>子供管理</h3>
							    <div class="menu-links">
							    	<a href="ChildList.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/子供情報一覧.png" alt="名簿情報一覧" width="50">
									    <span>名簿情報一覧</span>
									</a>
									<a href="AbsenceSelect.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/欠席.png" alt="欠席機能" width="50">
									    <span>欠席機能</span>
									</a>
									<a href="QrMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/QR.png" alt="QR機能" width="50">
									    <span>QR機能</span>
									</a>
							    </div>
							</div>

							<div class="menu-section">
							    <h3>お知らせ</h3>
							    <div class="menu-links">
							    	<a href="InfoMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/お知らせ.png" alt="お知らせ機能" width="50">
									    <span>お知らせ機能</span>
									</a>
							    </div>
							</div>
					</c:when>


				    <c:when test="${user_type == 'T'}">
				        <h2>先生</h2>
				        <div class="menu-section">
							    <h3>情報関係</h3>
							    <div class="menu-links">
							    	<a href="ManageUser.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/bus.png" alt="ユーザー情報" width="50">
									    <span>ユーザー情報</span>
									</a>

							    </div>
							</div>
				     		<div class="menu-section">
							    <h3>子供管理</h3>
							    <div class="menu-links">
							    	<a href="ChildList.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/bus.png" alt="名簿情報一覧" width="50">
									    <span>名簿情報一覧</span>
									</a>
									<a href="AbsenceSelect.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/bus.png" alt="欠席機能" width="50">
									    <span>欠席機能</span>
									</a>
									<a href="QrMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/bus.png" alt="QR機能" width="50">
									    <span>QR機能</span>
									</a>
							    </div>
							</div>

							<div class="menu-section">
							    <h3>お知らせ</h3>
							    <div class="menu-links">
							    	<a href="InfoMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/bus.png" alt="お知らせ機能" width="50">
									    <span>お知らせ機能</span>
									</a>
							    </div>
							</div>
				    </c:when>


				    <c:when test="${user_type == 'P'}">
				        <h2>保護者</h2>
				        	<div class="menu-section">
							    <h3>情報関係</h3>
							    <div class="menu-links">
							    	<a href="Parents.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/保護者.png" alt="保護者情報" width="50">
									    <span>保護者情報</span>
									</a>
									<a href="ChildInfo.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/子供.png" alt="子供情報" width="50">
									    <span>子供情報</span>
									</a>
							    </div>
							</div>

							<div class="menu-section">
							    <h3>欠席管理</h3>
							    <div class="menu-links">
							    	<a href="AbsenceSelect.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/欠席.png" alt="欠席機能" width="50">
									    <span>欠席機能</span>
									</a>
							    </div>
							</div>

							<div class="menu-section">
							    <h3>お知らせ</h3>
							    <div class="menu-links">
							    	<a href="InfoList.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/お知らせ.png" alt="お知らせ一覧" width="50">
									    <span>お知らせ一覧</span>
									</a>
							    </div>
							</div>
				    </c:when>


				</c:choose>
</c:when>
<c:otherwise>
<c:choose>
				    <c:when test="${user_type == 'M'}">
				        <h2>管理者</h2>
							<div class="menu-section">
							    <h3>情報関係</h3>
							    <div class="menu-links">
							    	<a href="NewRegistMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/新規作成.png" alt="新規登録" width="50">
									    <span>新規登録</span>
									</a>
									<a href="Parents.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/保護者.png" alt="保護者情報" width="50">
									    <span>保護者情報</span>
									</a>
									<a href="ChildInfo.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/子供.png" alt="子供情報" width="50">
									    <span>子供情報</span>
									</a>

							        <c:if test="${user_id == 'M0000001'}">
								        <a href="FacilityInfoMenu.action" class="menu-links-sub">
										    <img src="${pageContext.request.contextPath}/images/施設・バス.png" alt="施設・バス情報" width="50">
										    <span>施設・バス情報</span>
										</a>
							        </c:if>

							    </div>
							</div>

							<div class="menu-section">
							    <h3>子供管理</h3>
							    <div class="menu-links">
							    	<a href="QrMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/QR.png" alt="QR機能" width="50">
									    <span>QR機能</span>
									</a>
							    </div>
							</div>
					</c:when>


				    <c:when test="${user_type == 'T'}">
				        <h2>先生</h2>
				     		<div class="menu-section">
							    <h3>子供管理</h3>
							    <div class="menu-links">
							    	<a href="QrMenu.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/QR.png" alt="QR機能" width="50">
									    <span>QR機能</span>
									</a>
							    </div>
							</div>
				    </c:when>


				    <c:when test="${user_type == 'P'}">
				        <h2>保護者</h2>
				        	<div class="menu-section">
							    <h3>情報関係</h3>
							    <div class="menu-links">
							    	<a href="Parents.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/保護者.png" alt="保護者情報" width="50">
									    <span>保護者情報</span>
									</a>
									<a href="ChildInfo.action" class="menu-links-sub">
									    <img src="${pageContext.request.contextPath}/images/子供.png" alt="子供情報" width="50">
									    <span>子供情報</span>
									</a>
							    </div>
							</div>
				    </c:when>


				</c:choose>
</c:otherwise>
</c:choose>
</div>
</div>


<c:import url="/common/footer.jsp" />
