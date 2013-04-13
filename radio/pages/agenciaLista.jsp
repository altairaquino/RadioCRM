	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> Agências de Marketing e Propaganda</legend>
				<div style="width: 100%; height: 350px; overflow: auto;">
				<table style="width: 100%;">
					<tbody>
					<tr>
						<td colspan="4">
							<input type="button" value="Nova Agência" class="btn_hot" onclick="window.location = 'actionAgencia.do?m=novo'">
						</td>
					</tr>
					<tr>
						<th>
							Código
						</th>
						<th>
							Nome
						</th>
						<th>
							CNPJ
						</th>
						<th>
							Fone
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_agencia">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="agncodg"/>
						</td>
						<td>
							<bean:write name="b" property="agcnome"/>
						</td>
						<td>
							<bean:write name="b" property="agccnpj"/>
						</td>
						<td>
							<bean:write name="b" property="agcfone"/>
						</td>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionAgencia.do?m=dados&agncodg=<bean:write name="b" property="agncodg"/>'">														
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="4">
							<input type="button" value="Nova Agência" class="btn_hot" onclick="window.location = 'actionAgencia.do?m=novo'">
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