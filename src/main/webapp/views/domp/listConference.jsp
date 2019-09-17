<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<spring:message code="domp.moment.pattern" var="momentPattern" />

<h2> <spring:message code="domp.1Month"/> </h2>
	<display:table name="oneMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="domp.ticker" var="ticker" />
		<display:column style="background-color: Azure" property="ticker" title="${ticker}" />
		
		<spring:message code="domp.moment" var="moment" />
		<display:column style="background-color: Azure" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="domp.body" var="body" />
		<display:column style="background-color: Azure" property="body" title="${body}" />
		
		<spring:message code="domp.picture" var="picture" />
		<display:column style="background-color: Azure" property="picture" title="${picture}" />
		
		
	</display:table>

<h2> <spring:message code="domp.2Months"/> </h2>
	<display:table name="twoMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="domp.ticker" var="ticker" />
		<display:column style="background-color: Coral" property="ticker" title="${ticker}" />
		
		<spring:message code="domp.moment" var="moment" />
		<display:column style="background-color: Coral" property="moment" title="${moment}"  format="${momentPattern}"/>
		
		<spring:message code="domp.body" var="body" />
		<display:column style="background-color: Coral" property="body" title="${body}" />
		
		<spring:message code="domp.picture" var="picture" />
		<display:column style="background-color: Coral" property="picture" title="${picture}" />
			
	</display:table>
	
	<br/>
	
<h2> <spring:message code="domp.3Months"/> </h2>
	<display:table name="threeMonthOlds" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
		
		<spring:message code="domp.ticker" var="ticker" />
		<display:column style="background-color: LightCoral" property="ticker" title="${ticker}" />
		
		<spring:message code="domp.moment" var="moment" />
		<display:column style="background-color: LightCoral" property="moment" title="${moment}" format="${momentPattern}"/>
		
		<spring:message code="domp.body" var="body" />
		<display:column style="background-color: LightCoral" property="body" title="${body}" />
		
		<spring:message code="domp.picture" var="picture" />
		<display:column style="background-color: LightCoral" property="picture" title="${picture}" />
		
	</display:table>
	
	<br>
	<br>

	<button type="button" onclick="javascript: relativeRedir('welcome/index.do')">
	<spring:message code="domp.cancel" />
	</button>