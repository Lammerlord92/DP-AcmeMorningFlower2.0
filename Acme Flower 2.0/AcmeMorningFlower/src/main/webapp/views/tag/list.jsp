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

<display:table name="tags" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<display:column title="actions">
			<jstl:if test="${row.cancelled==false }">
				<a href="tag/administrator/cancell.do?tagId=${row.id}"><spring:message code="tag.cancell"/></a>
				<br/>
			</jstl:if>
			<jstl:if test="${row.cancelled==true }">
				<a href="tag/administrator/uncancell.do?tagId=${row.id}"><spring:message code="tag.uncancell"/></a>
				<br/>
			</jstl:if>
			<a href="tag/administrator/edit.do?tagId=${row.id}"><spring:message code="tag.edit"/></a>
			<br/>
	</display:column>
	
	<spring:message code="tag.keywords" var="keywords"></spring:message>
	<display:column property="keywords" title="${keywords}" sortable="false"></display:column>
	
	<spring:message code="tag.cancelled" var="cancelled"></spring:message>
	<display:column property="cancelled" title="${cancelled}" sortable="false"></display:column>
	
</display:table>
	
<input type="button" name="create" value="<spring:message code="tag.create"/>" 
	onclick="javascript: window.location.replace('tag/administrator/create.do')"/>


