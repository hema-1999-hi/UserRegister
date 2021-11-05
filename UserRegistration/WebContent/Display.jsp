<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    errorPage="errorPage.jsp" 
%>
    <%@page import = "java.util.List" %>
        <%@page import = "userRegistration.model.User" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display User</title>
</head>
<style>
<body>
<!-- Display user jsp -->

body {
  background-color: Yellow;
}
h2 {
  color: Green;
  text-align: center;
}

table, th, td   {
  border: 1px solid black;
  border-collapse: collapse;
  
}
th, td {
  padding: 15px;
  text-align: left;
}
#User td, #User th {
  border: 1px solid #ddd;
  padding: 8px;
}

#User th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: LightPink;
  color: black;
}

</style>
</head>
<body>
 <div>
<%
	String userAddMsg= (String)request.getAttribute("userAddMsg");
	if(null!=userAddMsg){
 %>
	<%= userAddMsg %>
<%} %>
</div>  
<div>
<%
	String userDeleteMsg= (String)request.getAttribute("userDeleteMsg");
	if(null!= userDeleteMsg){
 %>
	<%= userDeleteMsg %>
<%} %>
</div>
<div>
<%
	String UserUpdateMsg= (String)request.getAttribute("UserUpdateMsg");
	if(null!= UserUpdateMsg){
 %>
	<%= UserUpdateMsg %>
<%} %>
</div>
<h2>LIST OF USERS</h2>
<div>
	<a href="registeruser"> Add User</a>
</div>

  <table style="width:100%" id = "User" >
  <tr>
    <th>ID</th>
    <th>NAME</th> 
    <th>EMAIL</th>
    <th>PHONE NUMBER</th>
    <th>COUNTRY</th>
    <th>ACTION</th>
    </tr>
    
    <% 
    List<User> userlist = (List<User>) request.getAttribute("userlist");
    if(null != userlist){
        for ( User u : userlist ) { %>
    <tr>
        <td><%= u.getId() %> 	</td>
         <td><%= u.getName() %> 		</td>
		<td><%= u.getEmail() %>		</td>
		<td><%= u.getPhonenum() %>		</td>
		<td><%= u.getCountry()%>		</td>
		<td><a href="/UserRegistration/removeuser?id=<%= u.getId()%>" > Delete </a>&nbsp;<a href="/UserRegistration/getuser?id=<%= u.getId()%>" > Edit </a></td>
    </tr>
       
    <% } %>
    <% } %>
    </table>
</body>


</html>