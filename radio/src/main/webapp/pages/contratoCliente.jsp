	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> Contratos Fechados > <bean:write name="cliente" property="clcnome"/> </legend>
				<div style="width: 100%; height: 350px; overflow: auto;">
				<table>
					<tbody>
					<logic:notEmpty name="ls_contrato">
					<tr>
						<th>
							Número
						</th>
						<th>
							Agência
						</th>
						<th>
							Data Início
						</th>
						<th>
							Data Término
						</th>
						<th>
							Valor
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					</logic:notEmpty>
					<logic:empty name="ls_contrato">
					<tr>
						<td colspan="6" style="color: red; font-weight: bold;">
							Não há contratos para este cliente.
						</td>
					</tr>
					
					</logic:empty>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_contrato">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="crncodg"/>
						</td>
						<td>
							<bean:write name="b" property="crcnmag"/>
						</td>
						<td>
							<bean:write name="b" property="crdinic"/>
						</td>
						<td>
							<bean:write name="b" property="crdterm"/>
						</td>
						<th>
							R$ <bean:write name="b" property="crnvalr"/>
						</th>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionContrato.do?m=opcoes&crncodg=<bean:write name="b" property="crncodg"/>'">
							<input type="image" class="btn_hot" title="Excluir Contrato" src="imagens/delete.gif" onclick="if (window.confirm('Confirmar a exclusão do contrato?')){window.location = 'actionContrato.do?m=exclui&crncodg=<bean:write name="b" property="crncodg"/>'}">														
						</td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
				</div>
			</fieldset>			
			
		</div>		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>