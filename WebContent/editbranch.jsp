<%@include file="librarian.html"%>
<%@ page import="com.gcit.lms.entity.Branch"%>
<%@ page import="com.gcit.lms.service.AdminService"%>
<%
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	System.out.println("cardno: "+branchId);
	Branch branch = null;
	AdminService service = new AdminService();
	branch = service.viewBranchByID(branchId);
	if(branch == null){
		System.out.println("errrrrrr");
	}
%>
<div align="center">
	<h2>Enter Branch Details to Edit:</h2>
	<h3></h3>
	<form action="editBranch" method="post">
		Enter Branch Name: <br />
			<input type="text" name="branchName"
			value="<%=branch.getBranchName()%>"><br/> 
			Enter Branch Address: <br />
			<input type="text" name="branchAddress"
			value="<%=branch.getBranchAddress()%>"><br/> 			
			<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>">
		<button type="submit">Edit Branch!</button>
	</form>
</div>