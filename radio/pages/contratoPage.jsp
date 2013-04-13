	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
</head>	
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<fieldset>
			<legend>
				&nbsp; Opções do Contrato - <bean:write name="contrato" property="crcnmcl"/> (<bean:write name="contrato" property="crdinic"/> à <bean:write name="contrato" property="crdterm"/>) &nbsp; 
			</legend>
			
			
			<table width="100%">
			<tbody>
				<tr>
					<th>
						Opções do contato
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<tr>
					<td>
						Alterar Dados
					</td>
					<td>
						<input type="button" class="btn" value="Selecionar" onclick="window.location = 'actionContrato.do?m=editar&crncodg=<bean:write name="contrato" property="crncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Imprimir Contrato
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionContrato.do?m=imprimir&crncodg=<bean:write name="contrato" property="crncodg"/>&epncodg=<bean:write name="empresa" property="epncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Visualizar Dados
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionContrato.do?m=mostraDados&crncodg=<bean:write name="contrato" property="crncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionContrato.do?m=aberto';">
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