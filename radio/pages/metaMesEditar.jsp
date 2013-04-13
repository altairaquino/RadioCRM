<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend class="red">
				&nbsp; <bean:write name="contato" property="ctcnome"/> > (Metas para <bean:write name="meta" property="mtnano"/>) > Cadastro de Nova Meta Mensal  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionMetaMes" focus="mmncgms">
			<html:hidden property="mmncgmt"/>
			<html:hidden property="mmncodg"/>
			
			<html:hidden property="m" value="update"/>
			
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Nova Meta Mensal
					</th>
				</tr>
				<tr>
					<td style="width: 120px;">
						Informe o Mês
					</td>
					<td  style="width: 380px;">
						<html:select property="mmncgms" style="width: 200px;">
							<html:optionsCollection name="ls_meses" value="msncodg" label="mscdesc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td style="width: 120px;">
						Valor
					</td>
					<td  style="width: 380px;">
						<html:text property="mmnvalr" size="10" maxlength="10" style="text-align: right" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Salvar Meta Mensal</html:submit>
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