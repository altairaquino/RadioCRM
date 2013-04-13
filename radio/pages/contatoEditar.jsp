<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Cadastro de Novo Contato  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionContato" focus="ctcnome">
			<html:hidden property="m" value="update"/>
			<html:hidden property="ctncodg"/>
			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Novo Contato
					</th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<html:text property="ctcnome" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Matrícula
					</td>
					<td>
						<html:text property="ctcmatr" size="10" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Função
					</td>
					<td>
						<html:text property="ctcfunc" size="60" maxlength="50"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Dt. Nascimento
					</td>
					<td>
						<html:text property="ctdnasc" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Sexo
					</td>
					<td>
						<html:radio property="ctcsexo" value="M">Masculino</html:radio>&nbsp;
						<html:radio property="ctcsexo" value="F">Feminino</html:radio>						
					</td>
				</tr>
				<tr>
					<td>
						Fone Fixo
					</td>
					<td>
						<html:text property="ctcfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Celular
					</td>
					<td>
						<html:text property="ctccell" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<html:text property="ctcmail" size="60" maxlength="50"></html:text>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Alterar Contato</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionContato.do?m=lista';">
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