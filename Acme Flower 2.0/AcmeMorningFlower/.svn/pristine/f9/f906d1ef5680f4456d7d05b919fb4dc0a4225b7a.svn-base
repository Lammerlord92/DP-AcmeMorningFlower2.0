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

<display:table name="flowers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<display:column title="actions">
		<security:authorize access="isAnonymous()">
			<a href="comment/listByFlower.do?flowerId=${row.id}"><spring:message code="flower.listComment"/></a>
			<br/>
			<a href="comment/post.do?flowerId=${row.id}"><spring:message code="flower.newComment"/></a>
			<br/>
			<a href="order/new.do?flowerId=${row.id}"><spring:message code="flower.newOrder"/></a>
			<br/>
		</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${row.deleted==false }">
				<a href="flower/administrator/delete.do?flowerId=${row.id}"><spring:message code="flower.delete"/></a>
				<br/>
			</jstl:if>
			<jstl:if test="${row.deleted==true }">
				<a href="flower/administrator/undelete.do?flowerId=${row.id}"><spring:message code="flower.undelete"/></a>
				<br/>
			</jstl:if>
			<a href="flower/administrator/edit.do?flowerId=${row.id}"><spring:message code="flower.edit"/></a>
			<br/>
		</security:authorize>
	</display:column>
	
	<spring:message code="flower.vulgarName" var="vulgarName"></spring:message>
	<display:column property="vulgarName" title="${vulgarName}" sortable="false"></display:column>
	
	<spring:message code="flower.scientificName" var="scientificName"></spring:message>
	<display:column property="scientificName" title="${scientificName}" sortable="false"></display:column>
	
	<spring:message code="flower.unitPrice" var="unitPrice"></spring:message>
	<display:column property="unitPrice" title="${unitPrice}" sortable="true"></display:column>
		
	<spring:message code="flower.url" var="url"></spring:message>
	<display:column property="url" title="${url}" sortable="false"></display:column>
	
	<spring:message code="flower.tag.keywords" var="keywords"></spring:message>
	<display:column property="tag.keywords" title="${keywords}" sortable="false"></display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="flower.deleted" var="deleted"></spring:message>
		<display:column property="deleted" title="${deleted}" sortable="false"></display:column>
	</security:authorize>
</display:table>

<security:authorize access="isAnonymous()">
	<input type="text" value="" id="textSearch"/>
	<input type="button" id="buttonSearch" value="<spring:message code="flower.search"/>" />		
</security:authorize>
	
<security:authorize access="hasRole('ADMIN')">
		<input type="button" name="create" value="<spring:message code="flower.create"/>" 
		onclick="javascript: window.location.replace('flower/administrator/create.do')"/>
		<br />
</security:authorize>	

<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('flower/searchByKeyword.do?key=' + $("#textSearch").val());
		});
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('flower/searchByKeyword.do?key=' + $("#textSearch").val());
		});
	});
</script>
