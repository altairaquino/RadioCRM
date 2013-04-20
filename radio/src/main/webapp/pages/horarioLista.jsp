	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend> 
						&nbsp;Programas > <bean:write name="programa" property="pgcdesc"/> &nbsp;>&nbsp;Programação 
				</legend>
				<table>
					<tbody>
					<tr>
						<td colspan="4">
							<input type="button" class="btn" value="Nova Programação" onclick="window.location = 'actionHorario.do?m=novo';">
						</td>
					</tr>
					<tr>
						<th>
							Dia da Semana
						</th>
						<th>
							Horário Início
						</th>
						<th>
							Horário Término
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_horario">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="hrcdcds"/>
						</td>
						<td>
							<bean:write name="b" property="hrhinic"/>
						</td>
						<td>
							<bean:write name="b" property="hrhterm"/>
						</td>
						<td>
							<input type="button" class="btn" value="Editar" onclick="window.location = 'actionHorario.do?m=editar&hrncodg=<bean:write name="b" property="hrncodg"/>'">							
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="4">
							<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionPrograma.do?m=opcoes&pgncodg=<bean:write name="programa" property="pgncodg"/>';">
							<input type="button" class="btn" value="Nova Programação" onclick="window.location = 'actionHorario.do?m=novo';">
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