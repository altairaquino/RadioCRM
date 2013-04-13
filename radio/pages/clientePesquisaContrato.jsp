	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Pesquisa Cliente - Novo Contrato &nbsp;
			</legend>
			<html:form method="action" action="actionCliente" focus="clcnome">
			<html:hidden property="m" value="pesquisaContrato"/>
			<table>
			<tbody>
				<tr>
					<th colspan="3">Use, no mínimo, 3 caracteres para a pesquisa.</th>
				</tr>
				<tr>
					<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Nome, CPF, CNPJ&nbsp;<html:text property="clcnome" size="60" maxlength="50"></html:text>&nbsp;
						<html:submit styleClass="btn_hot">Pesquisar</html:submit>
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table>
			<tbody>
				<logic:present name="ls_cliente">
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						Código
					</th>
					<th>
						Nome
					</th>
					<th>
						Documento
					</th>
				</tr>
				<logic:iterate name="ls_cliente" id="b">
				<tr>
					<td>
						<bean:write name="b" property="clncodg"/>
					</td>
					<td>
						<a href="actionContrato.do?m=novo&clncodg=<bean:write name="b" property="clncodg"/>">
							<bean:write name="b" property="clcnome"/>
						</a>						
					</td>
					<td>
						<bean:write name="b" property="clcdocm"/>
					</td>					
				</tr>
				</logic:iterate>
				</logic:present>				
			
			</tbody>			
			</table>
			</div>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>