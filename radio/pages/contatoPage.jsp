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
				&nbsp;Contatos > <bean:write name="contato" property="ctcnome"/> &nbsp; 
			</legend>
			
			<h3> <bean:write name="contato" property="ctcnome"/> </h3>
			
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
						Editar Dados
					</td>
					<td>
						<input type="button" class="btn" value="Selecionar" onclick="window.location = 'actionContato.do?m=editar&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Visualizar metas
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionMeta.do?m=lista&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Visualizar Clientes do Contato
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionCliente.do?m=clientesDoContato&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionContato.do?m=lista';">
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