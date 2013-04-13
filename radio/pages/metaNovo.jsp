<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend class="red">
				&nbsp; <bean:write name="contato" property="ctcnome"/> > Cadastro de Nova Meta  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionMeta" focus="mtnano">
			<input type="hidden" name="mtncgct" value="<bean:write name="contato" property="ctncodg"/>">
			<html:hidden property="m" value="cadastro"/>
			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Nova Meta
					</th>
				</tr>
				<tr>
					<td style="width: 120px;">
						Informe o Ano
					</td>
					<td  style="width: 380px;">
						<html:text property="mtnano" size="5" maxlength="4"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Salvar Meta</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionMeta.do?m=lista&ctncodg=<bean:write name="contato" property="ctncodg"/>';">
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