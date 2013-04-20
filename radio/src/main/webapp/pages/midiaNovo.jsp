<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend class="red">
				&nbsp; Cadastro de Novo Tipo de Contrato &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionMidia" focus="mdcdesc">
			<html:hidden property="m" value="cadastro"/>			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Novo Tipo de Contrato
					</th>
				</tr>
				<tr>
					<td style="width: 120px;">
						Tipo de Contrato
					</td>
					<td  style="width: 380px;">
						<html:text property="mdcdesc" size="35" maxlength="30"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Salvar Tipo de Contrato</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionMidia.do?m=lista';">
					</td>
				</tr>					
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>