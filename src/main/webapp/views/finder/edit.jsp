<%--
 * edit.jsp
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


	<form:form action="finder/author/edit.do" modelAttribute="finder" method="post">
	
	
		<!--Ocultos-->
	    <form:hidden path="id" />
	    <form:hidden path="version" />
	    <form:hidden path="conferences" />
	    
	    <!--Key Word-->
		<form:label path="keyWord">
			<spring:message code="finder.keyWord"/>
	    </form:label>
		<form:input path="keyWord"/>
	    	<form:errors cssClass="error" path="keyWord" />
	    	<br />
	    	
	    
	    <!--Maximum Fee-->
		<form:label path="maximumFee">
			<spring:message code="finder.maximumFee"/>
	    	</form:label>
		<form:input path="maximumFee"/>
	    	<form:errors cssClass="error" path="maximumFee" />
	    	<br />
	    	
	    <!--Start moment-->
	    <form:label path="startDate">
			<spring:message code="finder.startDate"/>
	    	</form:label>
		<form:input path="startDate"/>
	    	<form:errors cssClass="error" path="startDate" />
	    	<br />
	    	
	    <!--End moment-->
	    <form:label path="endDate">
			<spring:message code="finder.endDate"/>
	    	</form:label>
		<form:input path="endDate"/>
	    	<form:errors cssClass="error" path="endDate" />
	    	<br />
	    	
	    <!--Category-->
		<form:label path="category">
  		<spring:message code="finder.category" />:
 		</form:label>
		 <form:select path="category">
  		<form:option value="0">----</form:option>
  		<form:options items="${categories}" itemLabel="name" itemValue="id" />
 		</form:select>
 		<form:errors cssClass="error" path="category" />
 		<br />
	    	
	    
	    <br/>	
	    
		<input type="submit" name="save" value="<spring:message code="finder.save"/>"/>
		
		<input type="button" name="cancel"
		value="<spring:message code="finder.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
		
		<br/>
	
	</form:form>
	
<h2> <spring:message code="finder.conferences"/> </h2>
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

</display:table>