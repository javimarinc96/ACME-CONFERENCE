<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<display:table name="domps" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<spring:message code="domp.moment.pattern" var="momentPattern" />
	
	<spring:message code="domp.ticker" var="ticker" />
	<display:column property="ticker" title="${ticker}" />
	
	<spring:message code="domp.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="${momentPattern}" />
	
	<spring:message code="domp.body" var="body" />
	<display:column property="body" title="${body}" />
	
	<spring:message code="domp.picture" var="picture" />
	<display:column property="picture" title="${picture}" />
	

	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="domp.show">
			<input type="submit" name="show" value="<spring:message code="domp.show" />"
				onclick="javascript: relativeRedir('domp/administrator/show.do?dompId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="domp.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="domp.edit" />"
				onclick="javascript: relativeRedir('domp/administrator/edit.do?dompId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="domp.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="domp.delete">
		<jstl:if test="${row.draftMode eq true}">
			<input type="submit" name="delete" value="<spring:message code="domp.delete" />"
				onclick="javascript: relativeRedir('domp/administrator/delete.do?dompId=${row.id}');" />
				</jstl:if>
					<jstl:if test="${row.draftMode eq false}">
			<spring:message code="domp.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	
</display:table>

<br/>

<security:authorize access="hasRole('ADMIN')">
	<input type="submit" name="create"
		value="<spring:message code="domp.create" />"
		onclick="javascript: relativeRedir('domp/administrator/create.do');" />
</security:authorize>