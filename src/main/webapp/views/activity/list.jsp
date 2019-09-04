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

<jsp:useBean id="now" class="java.util.Date" />

<h2> <spring:message code="activity.tutorials"/> </h2>
<display:table name="tutorials" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="activity.speakers" var="speakersHeader"/>
	<display:column property="speakers" title="${speakersHeader}"  />
	
	<spring:message code="activity.duration" var="durationHeader"/>
	<display:column property="duration" title="${durationHeader}" />
	
	<spring:message code="activity.startMoment" var="startMomentHeader"/>
	<display:column property="startMoment" title="${startMomentHeader}" />
	
	<spring:message code="activity.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="activity.room" var="roomHeader"/>
	<display:column property="room" title="${roomHeader}" />
	
	<spring:message code="activity.summary" var="summaryHeader"/>
	<display:column property="summary" title="${summaryHeader}" />
	
	<spring:message code="activity.attachments" var="attachmentsHeader"/>
	<display:column property="attachments" title="${attachmentsHeader}" />
	
	
	<display:column titleKey="activity.show">
		<input type="submit" name="show" value="<spring:message code="activity.show" />"
			onclick="javascript: relativeRedir('tutorial/administrator/show.do?tutorialId=${row.id}');" />
	</display:column>
	
	<display:column titleKey="activity.comments">
			<input type="submit" name="comments" value="<spring:message code="activity.comments" />"
				onclick="javascript: relativeRedir('comment/listActivity.do?activityId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.edit">
	<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="edit" value="<spring:message code="activity.edit" />"
			onclick="javascript: relativeRedir('tutorial/administrator/edit.do?tutorialId=${row.id}');" />
			</jstl:if>
				<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.delete">
		<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="delete" value="<spring:message code="activity.delete" />"
			onclick="javascript: relativeRedir('tutorial/administrator/delete.do?tutorialId=${row.id}');" />
  	 </jstl:if>
			<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
			</display:column>
	</security:authorize>

</display:table>


	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="create" value="<spring:message code="activity.create" />"
			onclick="javascript: relativeRedir('tutorial/administrator/create.do?conferenceId=${conferenceId}');" />
	</security:authorize>

<h2> <spring:message code="activity.panels"/> </h2>
<display:table name="panels" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="activity.speakers" var="speakersHeader"/>
	<display:column property="speakers" title="${speakersHeader}"  />
	
	<spring:message code="activity.duration" var="durationHeader"/>
	<display:column property="duration" title="${durationHeader}" />
	
	<spring:message code="activity.startMoment" var="startMomentHeader"/>
	<display:column property="startMoment" title="${startMomentHeader}" />
	
	<spring:message code="activity.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="activity.room" var="roomHeader"/>
	<display:column property="room" title="${roomHeader}" />
	
	<spring:message code="activity.summary" var="summaryHeader"/>
	<display:column property="summary" title="${summaryHeader}" />
	
	<spring:message code="activity.attachments" var="attachmentsHeader"/>
	<display:column property="attachments" title="${attachmentsHeader}" />
	
	<display:column titleKey="activity.show">
		<input type="submit" name="show" value="<spring:message code="activity.show" />"
			onclick="javascript: relativeRedir('panel/administrator/show.do?panelId=${row.id}');" />
	</display:column>
	
	<display:column titleKey="activity.comments">
			<input type="submit" name="comments" value="<spring:message code="activity.comments" />"
				onclick="javascript: relativeRedir('comment/listActivity.do?activityId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.edit">
	<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="edit" value="<spring:message code="activity.edit" />"
			onclick="javascript: relativeRedir('panel/administrator/edit.do?panelId=${row.id}');" />
			</jstl:if>
			<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
	</display:column>
	</security:authorize>
	
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.delete">
	<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="delete" value="<spring:message code="activity.delete" />"
			onclick="javascript: relativeRedir('panel/administrator/delete.do?panelId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
	</display:column>
	</security:authorize>
	
	
</display:table>

	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="create" value="<spring:message code="activity.create" />"
			onclick="javascript: relativeRedir('panel/administrator/create.do?conferenceId=${conferenceId}');" />
	</security:authorize>


<h2> <spring:message code="activity.presentations"/> </h2>
<display:table name="presentations" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="activity.speakers" var="speakersHeader"/>
	<display:column property="speakers" title="${speakersHeader}"  />
	
	<spring:message code="activity.duration" var="durationHeader"/>
	<display:column property="duration" title="${durationHeader}" />
	
	<spring:message code="activity.startMoment" var="startMomentHeader"/>
	<display:column property="startMoment" title="${startMomentHeader}" />
	
	<spring:message code="activity.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="activity.room" var="roomHeader"/>
	<display:column property="room" title="${roomHeader}" />
	
	<spring:message code="activity.summary" var="summaryHeader"/>
	<display:column property="summary" title="${summaryHeader}" />
	
	<spring:message code="activity.attachments" var="attachmentsHeader"/>
	<display:column property="attachments" title="${attachmentsHeader}" />
	
	<display:column titleKey="activity.show">
		<input type="submit" name="show" value="<spring:message code="activity.show" />"
			onclick="javascript: relativeRedir('presentation/administrator/show.do?presentationId=${row.id}');" />
	</display:column>
	
	<display:column titleKey="activity.comments">
			<input type="submit" name="comments" value="<spring:message code="activity.comments" />"
				onclick="javascript: relativeRedir('comment/listActivity.do?activityId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.edit">
		<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="edit" value="<spring:message code="activity.edit" />"
			onclick="javascript: relativeRedir('presentation/administrator/edit.do?presentationId=${row.id}');" />
			</jstl:if>
			<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="activity.delete">
	<jstl:if test="${conference.startDate gt now}">
		<input type="submit" name="delete" value="<spring:message code="activity.delete" />"
			onclick="javascript: relativeRedir('presentation/administrator/delete.do?presentationId=${row.id}');" />
			</jstl:if>
			<jstl:if test="${conference.startDate lt now}">
		<spring:message code="activity.no.edit" />
			</jstl:if>
	</display:column>
	</security:authorize>
	
</display:table>


	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="create" value="<spring:message code="activity.create" />"
			onclick="javascript: relativeRedir('presentation/administrator/create.do?conferenceId=${conferenceId}');" />
	</security:authorize>
	
	