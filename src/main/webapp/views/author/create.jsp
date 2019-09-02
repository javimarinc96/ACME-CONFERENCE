<%--
 * create.jsp
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

<form:form action="author/create.do" modelAttribute="author" onsubmit="return checkPhoneNumber()">

	<!--Ocultos-->

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="finder" />
    <form:hidden path="userAccount.authorities" />


	<!--Name-->
	<form:label path="name">
		<spring:message code="author.name"/>
    	</form:label>
	<form:input path="name"/>
    	<form:errors cssClass="error" path="name" />
	<br>
	
    <!--MiddleName-->
	<form:label path="middleName">
		<spring:message code="author.middleName"/>
    	</form:label>
	<form:input path="middleName"/>
    	<form:errors cssClass="error" path="middleName" />
    <br>
    
    <!--Surname-->
	<form:label path="surname">
		<spring:message code="author.surname"/>
    	</form:label>
	<form:input path="surname"/>
    	<form:errors cssClass="error" path="surname" />
    <br>
    	
    <!--Photo-->
	<form:label path="photo">
		<spring:message code="author.photo"/>
    	</form:label>
	<form:input path="photo"/>
    	<form:errors cssClass="error" path="photo" />
    <br>
    	
    <!--Email-->
	<form:label path="email">
		<spring:message code="author.email"/>
    	</form:label>
	<form:input path="email"/>
    	<form:errors cssClass="error" path="email" />
    <br>
    	
    <!--PhoneNumber-->
	<form:label path="phoneNumber">
		<spring:message code="author.phoneNumber"/>
    	</form:label>
	<form:input id="phoneNumber" path="phoneNumber"/>
    	<form:errors cssClass="error" path="phoneNumber" />
    <br>
    	
    <!--Address-->
	<form:label path="address">
		<spring:message code="author.address"/>
    	</form:label>
	<form:input path="address"/>
    	<form:errors cssClass="error" path="address" />
    <br>
    	
    <!--Username-->
	<form:label path="userAccount.username">
		<spring:message code="author.username"/>
    	</form:label>
	<form:input path="userAccount.username"/>
    	<form:errors cssClass="error" path="userAccount.username" />
    <br>
    	
    <!--Password-->
	<form:label path="userAccount.password">
		<spring:message code="author.password"/>
    	</form:label>
	<form:password path="userAccount.password"/>
    	<form:errors cssClass="error" path="userAccount.password" />
	<br>
	<br>
	
	
	<input type="submit" name="save" value="<spring:message code="author.save"/>"/>
	<input type="button" name="cancel" onclick="javascript: relativeRedir('welcome/index.do')"
			value="<spring:message code="author.cancel" />" />
	<br>

</form:form>

<script>
console.log("loaded");
function checkPhoneNumber(){
	console.log("checking...");
	var elem = document.getElementById("phoneNumber");

    var re = /^(\+[1-9][0-9]*(\([0-9]*\)|-[0-9]*-))?[0]?[1-9][0-9\- ]*$/;

    if(!re.test(elem.value)){
    	console.log("no");
    	return confirm("Is this your phone number? \n" + elem.value);
    }
    else{
    	console.log("yes");
    	return true;
    }
}

</script>