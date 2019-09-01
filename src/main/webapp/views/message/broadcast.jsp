<%--
 * action-1.jsp
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


<security:authorize access="isAuthenticated()">

<form:form action="${action}" method="POST" modelAttribute="mess">

<!--Ocultos-->

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="moment" />
    <form:hidden path="sender" />
    <form:hidden path="recipient"/>


	<!--Subject-->
	<form:label path="subject">
		<spring:message code="message.subject"/>
    	</form:label>
	<form:input path="subject"/>
    	<form:errors cssClass="error" path="subject" />
    <br/>

    <!--Body-->
    <form:label path="body">
        <spring:message code="message.body"/>
        </form:label>
    <form:input path="body"/>
        <form:errors cssClass="error" path="body" />
    <br/>
        
 	 <!--Topics -->
	<form:label path="topic">
			<spring:message code="message.topic" />:
		</form:label>
		<form:select id="topics" path="topic">
			<form:option value="0" label="----" />
			<jstl:forEach items="${topics}" var="t">
				<form:option value="${t.id}" label="${t.englishName}" />
			</jstl:forEach>
		</form:select>
		<form:errors cssClass="error" path="topic" />
		<br />


	
	<input type="submit" name="save" value="<spring:message code="message.save"/>"/>

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('message/actor/list.do');" />	
	<br/>

</form:form>
</security:authorize>
