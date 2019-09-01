<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="panel/administrator/create.do?conferenceId=${conferenceId}" modelAttribute="panel" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="schedule" />
	
	<form:label path="title">
		<spring:message code="panel.title" />:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="speakers">
		<spring:message code="panel.speakers" />:
	</form:label>
	<form:input path="speakers"/>
	<form:errors cssClass="error" path="speakers" />
	<br />
	
	<form:label path="startMoment">
		<spring:message code="panel.startMoment" />:
	</form:label>
	<form:input path="startMoment"/>
	<form:errors cssClass="error" path="startMoment" />
	<br />
	
	<form:label path="duration">
		<spring:message code="panel.duration" />:
	</form:label>
	<form:input path="duration"/>
	<form:errors cssClass="error" path="duration" />
	<br />
	
	<form:label path="room">
		<spring:message code="panel.room" />:
	</form:label>
	<form:input path="room"/>
	<form:errors cssClass="error" path="room" />
	<br />
	
	<form:label path="summary">
		<spring:message code="panel.summary" />:
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary" />
	<br />
	
	<form:label path="attachments">
		<spring:message code="panel.attachments" />:
	</form:label>
	<form:input path="attachments"/>
	<form:errors cssClass="error" path="attachments" />
	<br />
	
    
	<input type="submit" name="save"
		value="<spring:message code="panel.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="panel.cancel" />"
		onclick="javascript: relativeRedir('conference/administrator/list.do');" />


</form:form>