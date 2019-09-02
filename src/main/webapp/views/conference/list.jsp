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

<jsp:useBean id="now" class="java.util.Date" />

<h2> <spring:message code="conference.all"/> </h2>
<display:table name="all" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
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
	
	<spring:message code="conference.draftMode" var="draftHeader"/>
	<display:column property="draftMode" title="${draftHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.activities">
			<jstl:if test="${row.draftMode eq false and row.startDate gt now}">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.draftMode eq true or row.startDate lt now}">
			<spring:message code="conference.no.activities" />
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.submissions">
			<input type="submit" name="submissions" value="<spring:message code="conference.submissions" />"
				onclick="javascript: relativeRedir('submission/author/listByConference.do?conferenceId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="conference.edit" />"
			onclick="javascript: relativeRedir('conference/administrator/edit.do?conferenceId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="conference.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<h2> <spring:message code="conference.submission"/> </h2>
<display:table name="submission" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}"  />
	
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
	
	<spring:message code="conference.draftMode" var="draftHeader"/>
	<display:column property="draftMode" title="${draftHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.activities">
			<jstl:if test="${row.draftMode eq false and row.startDate gt now}">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.draftMode eq true or row.startDate lt now}">
			<spring:message code="conference.no.activities" />
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.submissions">
			<input type="submit" name="submissions" value="<spring:message code="conference.submissions" />"
				onclick="javascript: relativeRedir('submission/author/listByConference.do?conferenceId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="conference.edit" />"
			onclick="javascript: relativeRedir('conference/administrator/edit.do?conferenceId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="conference.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<h2> <spring:message code="conference.notification"/> </h2>
<display:table name="notification" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}"  />
	
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
	
	<spring:message code="conference.draftMode" var="draftHeader"/>
	<display:column property="draftMode" title="${draftHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.activities">
			<jstl:if test="${row.draftMode eq false and row.startDate gt now}">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.draftMode eq true or row.startDate lt now}">
			<spring:message code="conference.no.activities" />
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.submissions">
			<input type="submit" name="submissions" value="<spring:message code="conference.submissions" />"
				onclick="javascript: relativeRedir('submission/author/listByConference.do?conferenceId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="conference.edit" />"
			onclick="javascript: relativeRedir('conference/administrator/edit.do?conferenceId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="conference.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<h2> <spring:message code="conference.camera"/> </h2>
<display:table name="camera" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}"  />
	
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
	
	<spring:message code="conference.draftMode" var="draftHeader"/>
	<display:column property="draftMode" title="${draftHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.activities">
			<jstl:if test="${row.draftMode eq false and row.startDate gt now}">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.draftMode eq true or row.startDate lt now}">
			<spring:message code="conference.no.activities" />
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.submissions">
			<input type="submit" name="submissions" value="<spring:message code="conference.submissions" />"
				onclick="javascript: relativeRedir('submission/author/listByConference.do?conferenceId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="conference.edit" />"
			onclick="javascript: relativeRedir('conference/administrator/edit.do?conferenceId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="conference.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<h2> <spring:message code="conference.start"/> </h2>
<display:table name="start" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="conference.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}"  />
	
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
	
	<spring:message code="conference.draftMode" var="draftHeader"/>
	<display:column property="draftMode" title="${draftHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.activities">
			<jstl:if test="${row.draftMode eq false and row.startDate gt now}">
			<input type="submit" name="activities" value="<spring:message code="conference.activities" />"
				onclick="javascript: relativeRedir('activity/administrator/list.do?conferenceId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.draftMode eq true or row.startDate lt now}">
			<spring:message code="conference.no.activities" />
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.submissions">
			<input type="submit" name="submissions" value="<spring:message code="conference.submissions" />"
				onclick="javascript: relativeRedir('submission/author/listByConference.do?conferenceId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="conference.edit" />"
			onclick="javascript: relativeRedir('conference/administrator/edit.do?conferenceId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="conference.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('ADMIN')">
	<input type="submit" name="create" value="<spring:message code="conference.create" />"
		onclick="javascript: relativeRedir('conference/administrator/create.do');" />
</security:authorize>