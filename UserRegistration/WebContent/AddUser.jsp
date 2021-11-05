<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function validateForm() {
  var x = document.forms["myForm"]["Name"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }
  var d = document.forms["myForm"]["Phonenum"].value;
  if(d.length == 10){
	  return true;
  }else{
	  alert("please enter 10 digit number");
	  return false;
  }
	var z = document.forms["myForm"]["Email"].value
	if(z == ""){
	    alert("Email must be filled out");
	    return false;

	}
		
	
}
</script>
<title>Add User</title>
</head>
<body>
<form name="myForm" action="registeruser" method="Post" onSubmit = "returnvalidateForm()">
 
	<table style="boarder-width: 0px">

		<%
		Object obj = request.getAttribute("nextId");
		String nextId = "";
			if (null != obj) {
				nextId = obj.toString();
			}
		%>
			


			<tr>
			<td><label for="id"> ID:</label></td>
			<td><input type="text" id="id" name="id" value="<%=nextId%>"></td>
			
		</tr>
		<tr>
			<td><label for="Name">Name:</label></td>
			<td><input type="text"  id="name" name="Name" pattern="[a-zA-z ]{2,}" > 
			 </td>
		</tr>
		<tr>
			<td><label for="Email"> Email :</label></td>
			<td><input type="email" id="Email" name="Email" required></td>
		</tr>
		<tr>
			<td><label for="Phonenum"> Phone Number :</label></td>
			<td><input type="text" id="Phonenum" name="Phonenum"></td>
		</tr>
		<tr>
			<td><label for="Country"> Country :</label></td>
			<td><input type="text" id="Country" name="Country"></td>
		</tr>
	</table>
	<input type="submit" value="ADD USER">
		</form>
		
		
		
 
</body>
</html>