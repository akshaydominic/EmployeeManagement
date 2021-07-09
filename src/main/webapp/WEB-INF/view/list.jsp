<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Employee</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="/Employee/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Employee Relation Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Customer -->
		
			<input type="button" value="Add Employee"
				   onclick="window.location.href='/Employee/home'; return false;"
				   class="add-button"
			/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Picture</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempEmp" items="${employees}">
				
					<!-- construct an "update" link with employee id -->
					<c:url var="updateLink" value="/showFormForUpdate">
						<c:param name="employeeId" value="${tempEmp.id}" />
					</c:url>					

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/delete">
						<c:param name="employeeId" value="${tempEmp.id}" />
					</c:url>			
						
					
					<tr>
						<td> ${tempEmp.firstName} </td>
						<td> ${tempEmp.lastName} </td>
						<td> <img class="img-aot" width="300" height="300" src="/Employee/getEmployeePhoto/<c:out value='${tempEmp.id}'/>"> </td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
						</td>
						
						
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









