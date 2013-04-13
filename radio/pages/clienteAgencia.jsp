	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Clientes da Agência &nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table>
			<tbody>
				<logic:present name="ls_cliente">
				<tr>
					<td colspan="3">
						&nbsp;
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
						Documento
					</th>
				</tr>
				<% int c = 0; %>
				<logic:iterate name="ls_cliente" id="b">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td>
						<bean:write name="b" property="clncodg"/>
					</td>					
					<td>
						<a href="actionCliente.do?m=opcoes&clncodg=<bean:write name="b" property="clncodg"/>">
							<bean:write name="b" property="clcnome"/>
						</a>
					</td>
					<td>
						<bean:write name="b" property="clcdocm"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3">
						<input type="button" class="btn_hot" value="Dados da Agência" onclick="window.location = 'actionAgencia.do?m=dados&agncodg=<bean:write name="agncodg"/>'">
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