<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Opções do Contratos - <bean:write name="contrato" property="crcnmcl"/> (<bean:write name="contrato" property="crdinic"/> à <bean:write name="contrato" property="crdterm"/>) > Nova Inserção do Contrato Contrato  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="actionInsercao">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="inncgcr" value="<bean:write name="contrato" property="crncodg"/>">
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Nova Inserção
					</th>
				</tr>
				<tr>
					<td style="width: 100px;">
						Programa
					</td>
					<td>
						<html:select property="inncgpg" style="width: 350px;">
							<html:optionsCollection name="ls_programa" label="pgcdesc" value="pgncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Data
					</td>
					<td>
						<html:text property="inddata" size="10" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
					</td>
				</tr>
				<tr>
					<td>
						Desconto(R$)
					</td>
					<td>
						<html:text property="inndesc" size="10" maxlength="10" onkeydown="Formata(this,8,event,2)" style="text-align: right;"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Quantidade
					</td>
					<td>
						<html:text property="innqtd" size="5" maxlength="5" onkeyup="criaMascara(this,'#####')"/>	
					</td>
				</tr>				
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionInsercao.do?m=lista&crncodg=<bean:write name="contrato" property="crncodg"/>';">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn">Salvar Inserção</html:submit>
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