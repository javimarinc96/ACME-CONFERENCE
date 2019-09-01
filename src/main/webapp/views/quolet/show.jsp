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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

	<spring:message code="pattern" var="pattern" />
	
	<fieldset>

	<b><spring:message code="quolet.ticker"></spring:message>:</b><jstl:out value="${quolet.ticker}"></jstl:out>
	<br />
	
	<b><spring:message code="quolet.moment"></spring:message>:</b><fmt:formatDate pattern="${pattern}"  value="${quolet.moment}" />
	<br />
	
	<b><spring:message code="quolet.body"></spring:message>:</b><jstl:out value="${quolet.body}"></jstl:out>
	<br />
	
	<b><spring:message code="quolet.photo"></spring:message>:</b><jstl:out value="${quolet.photo}"></jstl:out>
	<br />
	
	<b><spring:message code="quolet.draftMode"></spring:message>:</b><jstl:out value="${quolet.draftMode}"></jstl:out>
	<br />
	
	<b><spring:message code="quolet.conference"></spring:message>:</b><jstl:out value="${quolet.conference.title}"></jstl:out>
	<br />
	
	<b><spring:message code="quolet.administrator"></spring:message>:</b><jstl:out value="${quolet.administrator.name}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('ADMIN')">	
				<button type="button" onclick="javascript: relativeRedir('quolet/administrator/list.do')">
				<spring:message code="quolet.cancel" />
				</button>
		</security:authorize>