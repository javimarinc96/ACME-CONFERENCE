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

<fieldset>
	<legend>
		<spring:message code="conference.searchText" />
	</legend>
	<form action="conference/administrator/searcher.do" method="GET">
		<input type="text" name="searcher" /> <input type="submit" value="<spring:message code = "conference.searchContent" />" />
	</form>
</fieldset>

<display:table name="conferences" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}"  />
	
	<spring:message code="conference.category" var="categoryHeader"/>
	<display:column property="category.name" title="${categoryHeader}"  />
	
	<spring:message code="conference.acronym" var="acronymHeader"/>
	<display:column property="acronym" title="${acronymHeader}" />
	
	<spring:message code="conference.venue" var="venueHeader"/>
	<display:column property="venue" title="${venueHeader}" />

	<spring:message code="conference.submissionDeadline" var="submissionHeader"/>
	<display:column property="submissionDeadline" title="${submissionHeader}" />

	<spring:message code="conference.notificationDeadline" var="notificationHeader"/>
	<display:column property="notificationDeadline" title="${notificationHeader}" />
	
	<spring:message code="conference.cameraDeadline" var="cameraHeader"/>
	<display:column property="cameraDeadline" title="${cameraHeader}" />

	<spring:message code="conference.startDate" var="startDateHeader"/>
    <display:column property="startDate" title="${startDateHeader}"  />
	
	<spring:message code="conference.endDate" var="endDateHeader"/>
    <display:column property="endDate" title="${endDateHeader}"  />
    
    <spring:message code="conference.summary" var="summaryHeader"/>
	<display:column property="summary" title="${summaryHeader}" />
	
	<spring:message code="conference.fee" var="feeHeader"/>
	<display:column property="fee" title="${feeHeader}" />
	
	<display:column titleKey="conference.activities">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
	</display:column>
	
	<display:column titleKey="conference.comments">
			<input type="submit" name="comments" value="<spring:message code="conference.comments" />"
				onclick="javascript: relativeRedir('comment/listConference.do?conferenceId=${row.id}');" />
	</display:column>


</display:table>