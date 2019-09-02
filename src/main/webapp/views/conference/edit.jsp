<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="conference/administrator/save.do" modelAttribute="conference" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="admin" />
	<form:hidden path="activities" />
	
	<form:label path="title">
		<spring:message code="conference.title" />:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="acronym">
		<spring:message code="conference.acronym" />:
	</form:label>
	<form:input path="acronym"/>
	<form:errors cssClass="error" path="acronym" />
	<br />
	
	<form:label path="venue">
		<spring:message code="conference.venue" />:
	</form:label>
	<form:input path="venue"/>
	<form:errors cssClass="error" path="venue" />
	<br />
	
	<form:label path="submissionDeadline">
		<spring:message code="conference.submissionDeadline" />:
	</form:label>
	<form:input path="submissionDeadline"/>
	<form:errors cssClass="error" path="submissionDeadline" />
	<br />
	
	<form:label path="notificationDeadline">
		<spring:message code="conference.notificationDeadline" />:
	</form:label>
	<form:input path="notificationDeadline"/>
	<form:errors cssClass="error" path="notificationDeadline" />
	<br />
	
	<form:label path="cameraDeadline">
		<spring:message code="conference.cameraDeadline" />:
	</form:label>
	<form:input path="cameraDeadline"/>
	<form:errors cssClass="error" path="cameraDeadline" />
	<br />
	
	<form:label path="startDate">
		<spring:message code="conference.startDate" />:
	</form:label>
	<form:input path="startDate"/>
	<form:errors cssClass="error" path="startDate" />
	<br />
	
	<form:label path="endDate">
		<spring:message code="conference.endDate" />:
	</form:label>
	<form:input path="endDate"/>
	<form:errors cssClass="error" path="endDate" />
	<br />
	
	<form:label path="summary">
		<spring:message code="conference.summary" />:
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary" />
	<br />
	
	<form:label path="fee">
		<spring:message code="conference.fee" />:
	</form:label>
	<form:input path="fee"/>
	<form:errors cssClass="error" path="fee" />
	<br />
	
	<form:label path="category">
	<spring:message code="conference.category" />:
	</form:label>
	<form:select path="category" >
	<form:options items="${categories}" itemLabel="name" />
	</form:select>	
	<br>
	
	<form:label path="draftMode">
		<spring:message code="conference.draftMode"/>
    	</form:label>
	<form:checkbox path="draftMode"/>
    	<form:errors cssClass="error" path="draftMode" />
    	<br/>


	<input type="submit" name="save"
		value="<spring:message code="conference.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="conference.cancel" />"
		onclick="javascript: relativeRedir('conference/administrator/list.do');" />


</form:form>