	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend>Programas que estão no ar</legend>
				<div style="width: 100%; height: 350px; overflow: auto;">
				<table>
					<tbody>
					<tr>
						<th>
							Nome
						</th>
						<th>
							Valor da Inserção
						</th>
						<th>
							Duração(seg)
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_programa">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="pgcdesc"/>
						</td>
						<td>
							R$ <bean:write name="b" property="pgyvalr"/>
						</td>
						<td>
							<bean:write name="b" property="pgndurc"/>&nbsp;Segundos
						</td>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionPrograma.do?m=opcoes&pgncodg=<bean:write name="b" property="pgncodg"/>'">							
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