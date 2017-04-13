<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  // response.setHeader("Content-disposition","inline; filename=test1.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
   response.setHeader("Content-disposition","attachment; filename=test2.xls"); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="left" border="2">
		<thead>
			<tr bgcolor="lightgreen">
				<td>序号</td>
				<td>用户名</td>
				<td>密码</td>
				<td>权限</td>
				<td>手机号码</td>
				<td>创建时间</td>
				<td>上次登录时间</td>
				<td>使用状态</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="users" items="${list}">
				<tr bgcolor="lightblue">
					<td align="center">${users.id}1</td>
					<td align="center">${users.username}2</td>
					<td align="center">${users.password}3</td>
					<td align="center">${users.phone}4</td>
					<td align="center">${users.role}</td>
					<td align="center">${users.createtime}</td>
					<td align="center">${users.lasttime}</td>
					<td align="center">${users.state}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>