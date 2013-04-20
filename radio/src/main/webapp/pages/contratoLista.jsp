	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> Lista de contratos em vigência </legend>
				<div style="width: 100%; height: 350px; overflow: auto;">
				<table  style="width: 100%">
					<tbody>
					<tr>
						<th>
							Número
						</th>
						<th>
							Cliente
						</th>
						<th>
							Data Início
						</th>
						<th>
							Data Término
						</th>
						<th>
							Valor
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_contrato">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							<bean:write name="b" property="crncodg"/>
						</td>
						<td>
							<bean:write name="b" property="crcnmcl"/>
						</td>
						<td>
							<bean:write name="b" property="crdinic"/>
						</td>
						<td>
							<bean:write name="b" property="crdterm"/>
						</td>
						<th>
							R$ <bean:write name="b" property="crnvalr"/>
						</th>
						<td>
							<input type="button" class="btn" value="Detalhes" onclick="window.location = 'actionContrato.do?m=opcoes&crncodg=<bean:write name="b" property="crncodg"/>'">														
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