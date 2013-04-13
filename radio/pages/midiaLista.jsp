	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> Tipos de Contratos </legend>
				<table style="width: 400px;">
					<tbody>
					<tr>
						<td colspan="3">
							<input type="button" value="Novo Tipo de Contrato" class="btn_hot" onclick="window.location = 'actionMidia.do?m=novo'">
						</td>
					</tr>
				</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table  style="width: 100%">
				<tbody>
					<tr>
						<th style="width: 70px;">
							Código
						</th>
						<th style="width: 300px;">
							Descrição
						</th>
						<th style="width: 30px;">
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_midia">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="mdncodg"/>
						</td>
						<td>
							<bean:write name="b" property="mdcdesc"/>
						</td>
						<td style="text-align: center;">
							<input type="image" src="imagens/detalhe.gif" title="Detalhes" onclick="alert('<bean:write name="b" property="mdcdesc"/>')">								
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