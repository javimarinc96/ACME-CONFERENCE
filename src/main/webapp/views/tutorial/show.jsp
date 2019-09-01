<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<fieldset>

	<b><spring:message code="tutorial.speakers"></spring:message>:</b><jstl:out value="${tutorial.speakers}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.startMoment"></spring:message>:</b><jstl:out value="${tutorial.startMoment}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.title"></spring:message>:</b><jstl:out value="${tutorial.title}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.duration"></spring:message>:</b><jstl:out value="${tutorial.duration}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.summary"></spring:message>:</b><jstl:out value="${tutorial.summary}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.attachments"></spring:message>:</b><jstl:out value="${tutorial.attachments}"></jstl:out>
	<br />
	
	<b><spring:message code="tutorial.room"></spring:message>:</b><jstl:out value="${tutorial.room}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<h2> <spring:message code="tutorial.sections"/> </h2>
<display:table pagesize="5" name="sections" id="row" requestURI="${requestURI}" class="displaytag table">
	<spring:message code="tutorial.section.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="tutorial.section.summary" var="summaryHeader"/>
	<display:column property="summary" title="${summaryHeader}" />
	
	<spring:message code="tutorial.section.pictures" var="picturesHeader"/>
	<display:column property="pictures" title="${picturesHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="tutorial.delete">
		<input type="submit" name="delete" value="<spring:message code="tutorial.delete" />"
			onclick="javascript: relativeRedir('section/administrator/delete.do?sectionId=${row.id}');" />
	</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('ADMIN')">	
	<button type="button" onclick="javascript: relativeRedir('section/administrator/create.do?tutorialId=${tutorial.id}')">
				<spring:message code="tutorial.create" />
				</button>
</security:authorize>

	
<!-- Cancel -->

<security:authorize access="hasRole('ADMIN')">	
				<button type="button" onclick="javascript: relativeRedir('conference/administrator/list.do')">
				<spring:message code="tutorial.cancel" />
				</button>
</security:authorize>