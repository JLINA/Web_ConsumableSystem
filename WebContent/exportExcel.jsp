<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
     
    String filename = (String)session.getAttribute("filename");
	response.setHeader("Content-disposition", "attachment; filename="+filename+".xls");
	session.removeAttribute("filename");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html xmlns:x="urn:schemas-microsoft-com:office:excel">
<body>
	<table cellpadding="1" cellspacing="1" border="1">
		<tr>
			<td colspan="5" align="center"></td>
		</tr>
		<tr class="dan_tr">
			<th>编号</th>
			<th>类型</th>
			<th>名字</th>
			<th>型号</th>
			<th>规格</th>
			<th>单价</th>
			<th>厂家</th>
			<th>出厂日期</th>
			<th>购买日期</th>
			<th>入库日期</th>
			<th>存放地点</th>
			<th>使用情况</th>
			<th>已使用数量</th>
			<th>库存数量</th>
			<th>是否缺标签</th>
		</tr>
		<c:forEach var="material" items="${list}">
			<tr align="center">
				<td width="100">${material.numbers}</td>
				<td width="100">${material.typeid}</td>
				<td width="100">${material.name}</td>
				<td width="100">${material.type}</td>
				<td width="100">${material.standstandard}</td>
				<td width="100">${material.price}</td>
				<td width="100">${material.factory}</td>
				<td width="100">${material.leavefactorydate}</td>
				<td width="100">${material.buydate}</td>
				<td width="100">${material.indate}</td>
				<td width="100">${material.place}</td>
				<td width="100">${material.usesituation}</td>
				<td width="100">${material.count}</td>
				<td width="100">${material.surplus}</td>
				<td width="100">${material.remarks}</td>
			</tr>
		</c:forEach>

	</table>
</body>

</html>