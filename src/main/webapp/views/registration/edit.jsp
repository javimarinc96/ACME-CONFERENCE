<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="registration/author/save.do" modelAttribute="registration" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="author" />
	
	<form:label path="creditCard.brandName">
		<spring:message code="registration.brandName"/>
    	</form:label>
	<form:select path="creditCard.brandName" >
	<form:options items="${brands}" />
	</form:select>	
    <br>

	<form:label path="creditCard.holderName">
		<spring:message code="registration.holderName"/>
    	</form:label>
	<form:input path="creditCard.holderName"/>
    	<form:errors cssClass="error" path="creditCard.holderName" />
    <br>
    
    	<form:label path="creditCard.expirationYear">
		<spring:message code="registration.expirationYear"/>
    	</form:label>
	<form:input path="creditCard.expirationYear"/>
    	<form:errors cssClass="error" path="creditCard.expirationYear" />
    <br>
    
    <form:label path="creditCard.expirationMonth">
		<spring:message code="registration.expirationMonth"/>
    	</form:label>
	<form:input path="creditCard.expirationMonth"/>
    	<form:errors cssClass="error" path="creditCard.expirationMonth" />
    <br>
    
     <form:label path="creditCard.number">
		<spring:message code="registration.number"/>
    	</form:label>
	<form:input path="creditCard.number"/>
    	<form:errors cssClass="error" path="creditCard.number" />
    <br>
    
    <form:label path="creditCard.cvvCode">
		<spring:message code="registration.cvvCode"/>
    	</form:label>
	<form:input path="creditCard.cvvCode"/>
    	<form:errors cssClass="error" path="creditCard.cvvCode" />
    <br>
    
    <form:label path="conference">
	<spring:message code="registration.conference" />:
	</form:label>
	<form:select path="conference" >
	<form:options items="${conferences}" itemLabel="title" />
	</form:select>	
	<br>
    
    
	<input type="submit" name="save"
		value="<spring:message code="registration.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="registration.cancel" />"
		onclick="javascript: relativeRedir('registration/author/list.do');" />


</form:form>