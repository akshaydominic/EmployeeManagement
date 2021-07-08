<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>

<head>
	<title>Save Employee</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="/Employee/resources/css/add-employee-style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="/Employee/resources/css/style.css">
	
	
	
</head>

<body>

	
	<div id="wrapper">
		<div id="header">
			<h2>Employee Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Employee</h3>
	
		<form:form action="signUp" modelAttribute="employee" method="POST"
		enctype="multipart/form-data">

			<!-- need to associate this data with employee id -->
			<form:hidden path="id" />
			
					
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" required="required"/></td>
					</tr>
				
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" required="required"/></td>
					</tr>

					<tr>
						<td><label>Salary:</label></td>
						<td><form:input path="salary" required="required"/></td>
						
					</tr>
					<tr>
						<td><label>Department:</label></td>
						<td><form:input path="department" required="required"/></td>
					</tr>
					<tr>
						<td><label>Position:</label></td>
						<td><form:input path="position" required="required"/></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" required="required"/></td>
					</tr>
					<tr>
						<td><label>Contact:</label></td>
						<td><form:input path="contact" required="required"/></td>
					</tr>
					<tr>
						
						<td><label>Picture</label></td>
						<td><form ><input type="file" name="picture" enctype="multipart/form-data" required></form>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"  /></td>
					</tr>
								
				</tbody>
			</table>
		</form:form>
		<div style="clear; both;"></div>
		
		<p>
			<a href="/Employee/list">Back to List</a>
		</p>
		
		</div>
