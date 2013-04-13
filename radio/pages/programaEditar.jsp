<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Editar dados do Programa  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionPrograma" focus="pgcdesc">
			<html:hidden property="m" value="update"/>
			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Editar dados do Programa
					</th>
				</tr>
				<tr>
					<td>
						Nome do Programa
					</td>
					<td>
						<html:text property="pgcdesc" size="60" maxlength="55"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Valor da Inserção
					</td>
					<td>
						<html:text property="pgyvalr" style="text-align: right" maxlength="8" size="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Duração da Inserção
					</td>
					<td>
						<html:text property="pgndurc" size="3" maxlength="3"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Alterar Programa</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionPrograma.do?m=lista';">
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