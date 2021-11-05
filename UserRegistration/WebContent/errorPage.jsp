<%@page import="userRegistration.model.SaveOrUpdateException"%>
<%@page  %>
<html>

<body>
<!-- error.jsp -->
<%
SaveOrUpdateException e = (SaveOrUpdateException)request.getAttribute("exception"); 
%>

<%="actual message" +e.getMessage()%>
<br>
<BR>
<%=e.getAppMessage() %>


</body>

</html>