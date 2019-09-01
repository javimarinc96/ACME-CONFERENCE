<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="tutorial/administrator/edit.do" modelAttribute="tutorial" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="schedule" />
	<form:hidden path="sections" />
	
	<form:label path="title">
		<spring:message code="tutorial.title" />:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="speakers">
		<spring:message code="tutorial.speakers" />:
	</form:label>
	<form:input path="speakers"/>
	<form:errors cssClass="error" path="speakers" />
	<br />
	
	<form:label path="startMoment">
		<spring:message code="tutorial.startMoment" />:
	</form:label>
	<form:input path="startMoment"/>
	<form:errors cssClass="error" path="startMoment" />
	<br />
	
	<form:label path="duration">
		<spring:message code="tutorial.duration" />:
	</form:label>
	<form:input path="duration"/>
	<form:errors cssClass="error" path="duration" />
	<br />
	
	<form:label path="room">
		<spring:message code="tutorial.room" />:
	</form:label>
	<form:input path="room"/>
	<form:errors cssClass="error" path="room" />
	<br />
	
	<form:label path="summary">
		<spring:message code="tutorial.summary" />:
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary" />
	<br />
	
	<form:label path="attachments">
		<spring:message code="tutorial.attachments" />:
	</form:label>
	<form:input path="attachments"/>
	<form:errors cssClass="error" path="attachments" />
	<br />
	
    
	<input type="submit" name="save"
		value="<spring:message code="tutorial.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="tutorial.cancel" />"
		onclick="javascript: relativeRedir('conference/administrator/list.do');" />


</form:form>