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


<!--Tabla-->

<display:table name="messages" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<!-- La lista con el botón de borrar en cada fila-->
	
	<spring:message code="message.subject" var="subjectHeader"/>
	<display:column property="subject" title="${subjectHeader}"/>
	
	<spring:message code="message.body" var="bodyHeader"/>
	<display:column property="body" title="${bodyHeader}"/>
	
	<spring:message code="message.topic" var="topicHeader"/>
	<display:column property="topic.englishName" title="${topicHeader}" sortable = "true" />
	
	<spring:message code="message.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}"/>
	
	<spring:message code="message.sender" var="senderHeader"/>
	<display:column property="sender.name" title="${senderHeader}" sortable = "true" />
	
	<spring:message code="message.recipient" var="recipientHeader"/>
	<display:column property="recipient.name" title="${recipientHeader}" sortable = "true" />
	
	<security:authorize access="isAuthenticated()">
	<display:column titleKey="message.delete">
			<input type="submit" name="delete" value="<spring:message code="message.delete" />"
				onclick="javascript: relativeRedir('message/actor/delete.do?messageId=${row.id}');" />
	</display:column>
	</security:authorize>
	
</display:table>

<!--Botón de crear debajo de la lista-->

<security:authorize access="isAuthenticated()">
		<input type="submit" name="create" value="<spring:message code="message.create" />"
		onclick="javascript: relativeRedir('message/actor/create.do');" />
</security:authorize>