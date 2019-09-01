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

	<b><spring:message code="presentation.speakers"></spring:message>:</b><jstl:out value="${presentation.speakers}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.startMoment"></spring:message>:</b><jstl:out value="${presentation.startMoment}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.title"></spring:message>:</b><jstl:out value="${presentation.title}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.duration"></spring:message>:</b><jstl:out value="${presentation.duration}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.summary"></spring:message>:</b><jstl:out value="${presentation.summary}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.attachments"></spring:message>:</b><jstl:out value="${presentation.attachments}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.room"></spring:message>:</b><jstl:out value="${presentation.room}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.paperTitle"></spring:message>:</b><jstl:out value="${presentation.cameraReady.title}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.paperAuthors"></spring:message>:</b><jstl:out value="${presentation.cameraReady.authors}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.paperDocument"></spring:message>:</b><jstl:out value="${presentation.cameraReady.document}"></jstl:out>
	<br />
	
	<b><spring:message code="presentation.paperSummary"></spring:message>:</b><jstl:out value="${presentation.cameraReady.summary}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('ADMIN')">	
				<button type="button" onclick="javascript: relativeRedir('conference/administrator/list.do')">
				<spring:message code="presentation.cancel" />
				</button>
</security:authorize>