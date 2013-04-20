	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> <bean:write name="contato" property="ctcnome"/> > Metas </legend>
				<table>
					<tbody>
					<tr>
						<td colspan="3">
							<input type="button" value="Novo Ano" class="btn_hot" onclick="window.location = 'actionMeta.do?m=novo'">
						</td>
					</tr>
					<tr>
						<th>
							Metas Anuais
						</th>
						<th>
							Valor Acumulado
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_meta">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							Meta para <bean:write name="b" property="mtnano"/>
						</td>
						<td>
							R$ <bean:write name="b" property="mtyacum"/>
						</td>
						<td>
							<input type="button" class="btn" value="Metas Mensais" onclick="window.location = 'actionMetaMes.do?m=lista&mtncodg=<bean:write name="b" property="mtncodg"/>'">&nbsp;&nbsp;
							<input type="button" class="btn" value="Editar" onclick="window.location = 'actionMeta.do?m=editar&mtncodg=<bean:write name="b" property="mtncodg"/>'">							
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="2">
							<input type="button" value="Novo Ano" class="btn_hot" onclick="window.location = 'actionMeta.do?m=novo'">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="Voltar" class="btn" onclick="window.location = 'actionContato.do?m=opcoes&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
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