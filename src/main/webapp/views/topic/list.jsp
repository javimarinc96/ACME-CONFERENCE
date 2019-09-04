<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<display:table name="topics" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<jstl:set var = "used" value = "${topicsUsed}"/>
	 <jstl:set var = "fila" value = "${row}"/>
	
	<spring:message code="topic.spanishName" var="spanishHeader"/>
	<display:column property="spanishName" title="${spanishHeader}" />
	
	<spring:message code="topic.englishName" var="englishHeader"/>
	<display:column property="englishName" title="${englishHeader}" />

	
	<display:column titleKey="topic.show">
		<input type="submit" name="show" value="<spring:message code="topic.show" />"
			onclick="javascript: relativeRedir('topic/administrator/show.do?topicId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="topic.edit">
		<input type="submit" name="edit" value="<spring:message code="topic.edit" />"
			onclick="javascript: relativeRedir('topic/administrator/edit.do?topicId=${row.id}');" />
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="topic.delete">
	  <jstl:choose>
   		<jstl:when test="${!fn:contains(used, fila)}">
		<input type="submit" name="delete" value="<spring:message code="topic.delete" />"
			onclick="javascript: relativeRedir('topic/administrator/delete.do?topicId=${row.id}');" />
			</jstl:when>
			   <jstl:otherwise>
    		<spring:message code="topic.no.delete" />
   </jstl:otherwise>
     </jstl:choose>
	</display:column>
	</security:authorize>

</display:table>


<security:authorize access="hasRole('ADMIN')">
	<input type="submit" name="create" value="<spring:message code="topic.create" />"
		onclick="javascript: relativeRedir('topic/administrator/create.do');" />
</security:authorize>