	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> <bean:write name="contato" property="ctcnome"/> > Clientes do Contato </legend>
				<div style="width: 100%; height: 350px; overflow: auto;">
				<table>
					<tbody>
					<tr>
						<th>
							Nome
						</th>
						<th>
							Documento
						</th>
						<th>
							Fone
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_cliente">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="clcnome"/>
						</td>
						<td>
							<bean:write name="b" property="clcdocm"/>
						</td>
						<td>
							<bean:write name="b" property="clcfone"/>
						</td>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionCliente.do?m=mostraDados&clncodg=<bean:write name="b" property="clncodg"/>'">														
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="4">
							<input type="button" value="Voltar" class="btn_hot" onclick="window.location = 'actionContato.do?m=opcoes&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
						</td>
					</tr>					
					</tbody>
				</table>
				</div>
			
			</fieldset>			
			
		</div>		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>