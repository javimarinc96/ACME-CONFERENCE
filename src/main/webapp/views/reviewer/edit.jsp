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

<security:authorize access="hasRole('REVIEWER')">

<form:form action="reviewer/edit.do" modelAttribute="reviewer" onsubmit="return checkPhoneNumber()">

<!--Ocultos-->

    <form:hidden path="id" />
    <form:hidden path="version" /> 
    <form:hidden path="submissions" /> 
	<form:hidden path="userAccount" /> 
                

	<!--Name-->
	<form:label path="name">
		<spring:message code="reviewer.name"/>
    	</form:label>
	<form:input path="name"/>
    	<form:errors cssClass="error" path="name" />
    	<br>

    <!--MiddleName-->
	<form:label path="middleName">
		<spring:message code="reviewer.middleName"/>
    	</form:label>
	<form:input path="middleName"/>
    	<form:errors cssClass="error" path="middleName" />
    	    	<br>
    	
    <!--Surname-->
	<form:label path="surname">
		<spring:message code="reviewer.surname"/>
    	</form:label>
	<form:input path="surname"/>
    	<form:errors cssClass="error" path="surname" />
    	    	<br>
    	
    <!--Photo-->
	<form:label path="photo">
		<spring:message code="reviewer.photo"/>
    	</form:label>
	<form:input path="photo"/>
    	<form:errors cssClass="error" path="photo" />
    	    	<br>
    	
    <!--Email-->
	<form:label path="email">
		<spring:message code="reviewer.email"/>
    	</form:label>
	<form:input path="email"/>
    	<form:errors cssClass="error" path="email" />
    	    	<br>
    	
    <!--PhoneNumber-->
	<form:label path="phoneNumber">
		<spring:message code="reviewer.phoneNumber"/>
    	</form:label>
	<form:input id="phoneNumber" path="phoneNumber"/>
    	<form:errors cssClass="error" path="phoneNumber" />
    	    	<br>
    	
    <!--Address-->
	<form:label path="address">
		<spring:message code="reviewer.address"/>
    	</form:label>
	<form:input path="address"/>
    	<form:errors cssClass="error" path="address" />
      	<br>
      	
     <!--Keywords-->
	<form:label path="keywords">
		<spring:message code="reviewer.keywords"/>
    	</form:label>
	<form:input path="keywords"/>
    	<form:errors cssClass="error" path="keywords" />
    <br>
  

	<input type="submit" name="save" value="<spring:message code="reviewer.save"/>"/>

	<input type="button" name="cancel"
		value="<spring:message code="reviewer.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />	
	<br/>

</form:form>
</security:authorize>


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
