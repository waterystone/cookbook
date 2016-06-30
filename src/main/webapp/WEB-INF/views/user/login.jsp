<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>登陆</title>
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${error!=null}">
		<font color="red">${error}</font>
	</c:if>

	<form  class="form-control" action="/user/login" method="post">
		<table>
			<tr>
				<td><input type="text" name="account" class="text-input" placeholder="登陆账户" /></td>
			</tr>
			<tr>
				<td><input type="password" name="password" class="password-field" placeholder="密码" /></td>
			</tr>
			<c:if test="${srcUrl!=null}">
				<input type="hidden" name="srcUrl" value="${srcUrl}" />
			</c:if>
			<tr>
				<td colspan="2"><input type="submit" name="登陆" /></td>
				<td colspan="2"><input type="reset" name="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>