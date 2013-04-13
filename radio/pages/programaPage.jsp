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
				&nbsp;Programa > <bean:write name="programa" property="pgcdesc"/> &nbsp; 
			</legend>
			
			<h3> <bean:write name="programa" property="pgcdesc"/> </h3>
			
			<table width="100%">
			<tbody>
				<tr>
					<th> 
						Opções do Programa
					</th>
					<th> 
						&nbsp;
					</th>
				</tr>
				<tr>
					<td>
						Visualiza toda a programação
					</td>
					<td>
						<input type="button" class="btn" value="Visualizar" onclick="window.location = 'actionHorario.do?m=lista&pgncodg=<bean:write name="programa" property="pgncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Alterar
					</td>
					<td>
						<input type="button" class="btn" value="Selecionar" onclick="window.location='actionPrograma.do?m=editar&pgncodg=<bean:write name="programa" property="pgncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionPrograma.do?m=lista';">
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