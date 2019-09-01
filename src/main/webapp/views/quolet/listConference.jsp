<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<spring:message code="quolet.moment.pattern" var="momentPattern" />

<h2> <spring:message code="quolet.1Month"/> </h2>
	<display:table name="oneMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="quolet.ticker" var="ticker" />
		<display:column style="background-color: LightYellow" property="ticker" title="${ticker}" />
		
		<spring:message code="quolet.moment" var="moment" />
		<display:column style="background-color: LightYellow" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="quolet.body" var="body" />
		<display:column style="background-color: LightYellow" property="body" title="${body}" />
		
		
	</display:table>

<h2> <spring:message code="quolet.2Months"/> </h2>
	<display:table name="twoMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="quolet.ticker" var="ticker" />
		<display:column style="background-color: LawnGreen" property="ticker" title="${ticker}" />
		
		<spring:message code="quolet.moment" var="moment" />
		<display:column style="background-color: LawnGreen" property="moment" title="${moment}"  format="${momentPattern}"/>
		
		<spring:message code="quolet.body" var="body" />
		<display:column style="background-color: LawnGreen" property="body" title="${body}" />
			
	</display:table>
	
	<br/>
	
<h2> <spring:message code="quolet.3Months"/> </h2>
	<display:table name="threeMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="quolet.ticker" var="ticker" />
		<display:column style="background-color: FloralWhite" property="ticker" title="${ticker}" />
		
		<spring:message code="quolet.moment" var="moment" />
		<display:column style="background-color: FloralWhite" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="quolet.body" var="body" />
		<display:column style="background-color: FloralWhite" property="body" title="${body}" />
		
	</display:table>
	
	<br>
	<br>

	<button type="button" onclick="javascript: relativeRedir('welcome/index.do')">
	<spring:message code="quolet.cancel" />
	</button>