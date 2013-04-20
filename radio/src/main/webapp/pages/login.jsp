<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>


<html:html>
<head>

	<title>
		Sistema de Controle de Contratos e Comissões de Rádios
	</title>
	<link rel="stylesheet" href="stylesheet/style.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="stylesheet/print.css" type="text/css" media="print">
	<link rel="shortcut icon" href="favicon.ico">	
	
</head>
	
<body style="background: url('imagens/background.gif');">
		
	<html:errors/>	
		<html:form method="POST" action="/actionLogin" focus="login">
		<html:hidden property="m" value="autenticaUsuario"/>
		
		<table style="width: 300px; height: 100px; background-color: #FFFAFA; border: 1px solid #F90;" border="1">
			<tr>
				<td colspan="2" style="text-align: center; font-size: 14px; font-weight: bold;">
					ENTRADA SICOM
				</td>
			</tr>
			<tr>
				<th colspan="2" style="background-color: #FC6; text-align: center;font-weight: bold; font-size: 13px;"> 
					Login do Usuário 
				</th>
			</tr>
			<tr>
				<th>
					Login
				</th>
				<td>
					<html:text property="login" />
				</td>
			</tr>
			<tr>
				<th>
					Senha
				</th>
				<td>
					<html:password property="senha"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right;">
					<html:submit styleClass="btn_hot">Entrar</html:submit>
				</td>
			</tr>
		</table>
		</html:form>
</body>
</html:html>