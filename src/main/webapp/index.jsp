<!DOCTYPE html>
<%@ page import = "com.servlet.RegistrationServlet" %>
<%RegistrationServlet rs = new RegistrationServlet(); %>
<html>
<head>
<title>Registration Form</title>
</head>
<body>
<fieldset style="position:absolute;">
<legend>Login</legend>
<form action="RegistrationServlet" method="POST">
	<div>
		<label>Name: </label>
		<input type="text" name="usr_name" placeholder="Enter your name"></input>
	</div>
	<br>
	<div>
		<label>Email: </label>
		<input type="email" name="usr_email" placeholder="Enter your email"></input>
	</div>
	<br>
	<div>
		<label>Password: </label>
		<input type="password" name="usr_passwd" placeholder="Enter your password"></input>
	</div>
	<br>cd
	<div>
		<label>Course: </label>
		<%!String value = "Java";
		int i = 1;	
		%>
		<% 
		while(i < 5){
			value+="a";	
			out.println("");
			i++;
		}
		%>
		<select name="usr_course">
			<option value=<%= value %>><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
			<option value="<%= value %>"><%= value %></option>
		</select>
	</div>
	<br>
	<div>
		<label>Gender: </label>
		<input type="radio" name="usr_gender" value="Male">Male</input>
		<input type="radio" name="usr_gender" value="Female">Female</input>
	</div>
	<div>
		<label>Select a file: </label>
		<input type="file" name="usr_image">
	</div>
	<div>
		<input type="checkbox" name = "usr_accept" value="checked">Accept terms and condition</input>
	</div>
	<br>
	<div>
		<input type="submit"></input>
		<input type="reset"></input>
	</div>
</form>
</fieldset>
</body>
</html>