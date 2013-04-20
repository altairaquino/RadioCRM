<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Editação de dados do Contrato  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="actionContrato">
			<html:hidden property="m" value="update"/>
			<html:hidden property="crncodg"/>	
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Novo Contrato
					</th>
				</tr>
				<tr>
					<td style="width: 100px;">
						Cliente
					</td>
					<td style="color: red; font-weight: bold;width: 400px;">
						<bean:write name="formContrato" property="crcnmcl"/>
					</td>
				</tr>
				<tr>
					<td>
						Agência
					</td>
					<td>
						<html:select property="crncgag" style="width: 350px;">
							<html:optionsCollection name="ls_agencia" label="agcnome" value="agncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Período de Vigência
					</th>
				</tr>
				<tr>
					<td>
						Data Inicial
					</td>
					<td>
						<html:text property="crdinic" value="12/10/2008" size="11" maxlength="10"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Data Término
					</td>
					<td>
						<html:text property="crdterm" value="12/11/2008" size="11" maxlength="10"></html:text>						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2"> 
						Informações para Texto
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:textarea property="crmtext" cols="80" rows="3"></html:textarea>						
					</td>
				</tr>
				<tr>
					<td>
						Ramo de Atividade
					</td>
					<td>
						<html:text property="crcramo" size="63" maxlength="99"/>						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2"> 
						Informações para Pagamento
					</th>
				</tr>
				<tr>
					<td>
						Valor
					</td>
					<td>
						<html:text property="crdterm" size="10" maxlength="10" onkeydown="Formata(this,8,event,2)" style="text-align: right;"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Forma Pagamento
					</td>
					<td>
						<html:select property="crncgfp" style="width: 350px;">
							<html:optionsCollection name="ls_formapagamento" value="fpncodg" label="fpcdesc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<td>
						Cond. Pagamento
					</td>
					<td>
						<html:select property="crncgcp" style="width: 350px;">
							<html:optionsCollection name="ls_condicaopagamento" value="cpncodg" label="cpcdesc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionContrato.do?m=abertos';">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn">Salvar Contrato</html:submit>
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