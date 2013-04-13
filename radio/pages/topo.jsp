<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt" lang="pt">

<logic:notPresent name="usuario" scope="session">
	<logic:redirect action="login.do"></logic:redirect>
</logic:notPresent>

<head>
	<title> 
		:: <bean:write name="empresa" property="epcnome"/> - <bean:write name="empresa" property="epcdccd"/>/<bean:write name="empresa" property="epcufcd"/> ::
		Sistema de Controle de Comissões e Contratos 
	</title>
	
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="content-style-type" content="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta name="language" content="pt-br">
		
	<link rel="stylesheet" href="stylesheet/style.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="stylesheet/print.css" type="text/css" media="print">
	<script type="text/javascript" src="jscalendar/calendar.js"></script>
	<script type="text/javascript" src="jscalendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="jscalendar/calendar-setup.js"></script>
	
	
	<link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue.css" title="blue"/>
		
	<script type="text/javascript" src="scripts/validacao.js"></script>