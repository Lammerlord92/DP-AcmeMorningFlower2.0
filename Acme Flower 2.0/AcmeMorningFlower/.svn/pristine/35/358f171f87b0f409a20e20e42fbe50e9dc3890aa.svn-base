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

<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column title="actions">
		<security:authorize access="isAnonymous()">
			<a href="comment/reply.do?commentId=${row.id}"><spring:message code="comment.reply"/></a>
			<br/>
		</security:authorize>
		<a href="comment/listReplies.do?commentId=${row.id}"><spring:message code="comment.listReplies"/></a>
		<br/>
	</display:column>
	
	<spring:message code="comment.nick" var="nick"></spring:message>
	<display:column property="nick" title="${nick}" sortable="false"></display:column>
	
	<spring:message code="comment.text" var="text"></spring:message>
	<display:column property="text" title="${text}" sortable="false"></display:column>
	
	<spring:message code="comment.creationMoment" var="creationMoment"></spring:message>
	<display:column property="creationMoment" title="${creationMoment}" sortable="false"></display:column>
	
	<spring:message code="comment.url" var="url"></spring:message>
	<display:column property="url" title="${url}" sortable="false"></display:column>
	
</display:table>

	<input type="button" name="return" value="<spring:message code="comment.return"/>" 
		onclick="javascript: window.location.replace('${returnURI}')"/>
		<br />
		
<security:authorize access="hasRole('ORGANISER')">
		<input type="button" name="create" value="<spring:message code="contest.create"/>" 
		onclick="javascript: window.location.replace('contest/organiser/create.do')"/>
		<br />
</security:authorize>	
