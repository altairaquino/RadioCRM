	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> <bean:write name="contrato" property="crcnmcl"/> - R$ <bean:write name="contrato" property="crnvalr"/> (<bean:write name="contrato" property="crdinic"/> à <bean:write name="contrato" property="crdterm"/>) > Lista de inserções </legend>
				<table>
					<tbody>
					<tr>
						<td colspan="7">
							<input type="button" value="Nova Inserção" class="btn" onclick="window.location = 'actionInsercao.do?m=novo'">	
						</td>
					</tr>			
					<tr>
						<th>
							Programa/Duração
						</th>
						<th>
							Data
						</th>
						<th>
							Valor
						</th>
						<th>
							Quant.
						</th>
						<th>
							Desconto
						</th>
						<th>
							Total
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_insercao">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="incdcpg"/> / <bean:write name="b" property="inndrpg"/> seg. 
						</td>
						<td>
							<bean:write name="b" property="inddata"/>
						</td>
						<td>
							R$ <bean:write name="b" property="innvalr"/>
						</td>
						<td>
							<bean:write name="b" property="innqtd"/>
						</td>
						<td>
							R$ <bean:write name="b" property="inndesc"/>
						</td>
						<td>
							R$ <bean:write name="b" property="innsoma"/>
						</td>
						<td>
							<input type="button" class="btn" value="Excluir" onclick="if (window.confirm('Deseja Excluir a inserção?')){window.location = 'actionInsercao.do?m=desativar&inncodg=<bean:write name="b" property="inncodg"/>&crncodg=<bean:write name="contrato" property="crncodg"/>'}">														
						</td>
					</tr>
					</logic:iterate>
					<logic:empty name="ls_insercao">
					<tr>
						<td colspan="7" style="color: red; font-size: 13px;">
							- Não há inserções para este contrato ainda.
						</td>
					</tr>
					</logic:empty>
					<logic:notEmpty name="ls_insercao">
					<tr>
						<td colspan="2">
							&nbsp;	
						</td>
						<td colspan="3">
							<strong> Valor Total Contratado </strong>
						</td>
						<td colspan="2">
							<strong> R$ <bean:write name="valor"/> </strong>
						</td>
					</tr>
					</logic:notEmpty>
					<tr>
						<td colspan="7">
							<input type="button" value="Nova Inserção" class="btn" onclick="window.location = 'actionInsercao.do?m=novo'">	
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionContrato.do?m=opcoes&crncodg=<bean:write name="contrato" property="crncodg"/>'">	
						</td>
					</tr>
					</tbody>
				</table>
			
			</fieldset>			
			
		</div>		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>