	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend>Lista de Contatos Cadastrados</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
			<tbody>
					<tr>
						<th>
							Matricula
						</th>
						<th>
							Nome
						</th>
						<th>
							Dt. Nasc.
						</th>
						<th>
							Fone Fixo
						</th>
						<th>
							Celular
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_contato">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="ctcmatr"/>
						</td>
						<td>
							<bean:write name="b" property="ctcnome"/>
						</td>
						<td>
							<bean:write name="b" property="ctdnasc"/>
						</td>
						<td>
							<bean:write name="b" property="ctcfone"/>
						</td>
						<td>
							<bean:write name="b" property="ctccell"/>
						</td>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionContato.do?m=opcoes&ctncodg=<bean:write name="b" property="ctncodg"/>'">							
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