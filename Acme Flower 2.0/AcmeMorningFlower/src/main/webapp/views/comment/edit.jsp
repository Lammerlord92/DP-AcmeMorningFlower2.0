<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<form:form action="${requestURI }" modelAttribute="commentForm">
	<form:hidden path="parent"/>
	<form:hidden path="flower"/>

	<acme:textbox code="comment.nick" path="nick"/>	
	<acme:textarea code="comment.text" path="text"/>
	<acme:textbox code="comment.url" path="url"/>
	
	<acme:submit name="save" code="comment.save"/>

	<input type="button" name="return"
		value="<spring:message code="comment.return"/>"
		onClick="javascript: window.location.replace('${returnURI}');"
	/>
</form:form>