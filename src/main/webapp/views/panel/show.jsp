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

	<b><spring:message code="panel.speakers"></spring:message>:</b><jstl:out value="${panel.speakers}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.startMoment"></spring:message>:</b><jstl:out value="${panel.startMoment}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.title"></spring:message>:</b><jstl:out value="${panel.title}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.duration"></spring:message>:</b><jstl:out value="${panel.duration}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.summary"></spring:message>:</b><jstl:out value="${panel.summary}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.attachments"></spring:message>:</b><jstl:out value="${panel.attachments}"></jstl:out>
	<br />
	
	<b><spring:message code="panel.room"></spring:message>:</b><jstl:out value="${panel.room}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('ADMIN')">	
				<button type="button" onclick="javascript: relativeRedir('conference/administrator/list.do')">
				<spring:message code="panel.cancel" />
				</button>
</security:authorize>