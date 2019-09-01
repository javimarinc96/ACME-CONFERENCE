<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="presentation/administrator/create.do?conferenceId=${conferenceId}" modelAttribute="presentation" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="schedule" />
	
	<form:label path="title">
		<spring:message code="presentation.title" />:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="speakers">
		<spring:message code="presentation.speakers" />:
	</form:label>
	<form:input path="speakers"/>
	<form:errors cssClass="error" path="speakers" />
	<br />
	
	<form:label path="startMoment">
		<spring:message code="presentation.startMoment" />:
	</form:label>
	<form:input path="startMoment"/>
	<form:errors cssClass="error" path="startMoment" />
	<br />
	
	<form:label path="duration">
		<spring:message code="presentation.duration" />:
	</form:label>
	<form:input path="duration"/>
	<form:errors cssClass="error" path="duration" />
	<br />
	
	<form:label path="room">
		<spring:message code="presentation.room" />:
	</form:label>
	<form:input path="room"/>
	<form:errors cssClass="error" path="room" />
	<br />
	
	<form:label path="summary">
		<spring:message code="presentation.summary" />:
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary" />
	<br />
	
	<form:label path="attachments">
		<spring:message code="presentation.attachments" />:
	</form:label>
	<form:input path="attachments"/>
	<form:errors cssClass="error" path="attachments" />
	<br />
	
	<br />
	
	<fieldset>
	<form:label path="cameraReady.title">
		<spring:message code="presentation.paperTitle"/>
    	</form:label>
	<form:input path="cameraReady.title"/>
    	<form:errors cssClass="error" path="cameraReady.title" />
    <br>

	<form:label path="cameraReady.authors">
		<spring:message code="presentation.paperAuthors"/>
    	</form:label>
	<form:input path="cameraReady.authors"/>
    	<form:errors cssClass="error" path="cameraReady.authors" />
    <br>
    
    <form:label path="cameraReady.summary">
		<spring:message code="presentation.paperSummary"/>
    	</form:label>
	<form:input path="cameraReady.summary"/>
    	<form:errors cssClass="error" path="cameraReady.summary" />
    <br>
    
    <form:label path="cameraReady.document">
		<spring:message code="presentation.paperDocument"/>
    	</form:label>
	<form:input path="cameraReady.document"/>
    	<form:errors cssClass="error" path="cameraReady.document" />
    <br>
	
	</fieldset>
	
		<br />
		<br />
    
	<input type="submit" name="save"
		value="<spring:message code="presentation.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="presentation.cancel" />"
		onclick="javascript: relativeRedir('conference/administrator/list.do');" />


</form:form>