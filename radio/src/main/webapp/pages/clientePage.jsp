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
				&nbsp;Clientes > <bean:write name="cliente" property="clcnome"/> &nbsp; 
			</legend>
			
			<h3> <bean:write name="cliente" property="clcnome"/> </h3>
			
			<table width="100%">
			<tbody>
				<tr>
					<th>
						Opções do Cliente
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<tr>
					<td>
						Exibir Dados
					</td>
					<td>
						<input type="button" class="btn" value="Selecionar" onclick="window.location = 'actionCliente.do?m=mostraDados&clncodg=<bean:write name="cliente" property="clncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Visualizar Contratos Fechados
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionCliente.do?m=contratosDoCliente&clncodg=<bean:write name="cliente" property="clncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'clientePesquisa.do';">
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