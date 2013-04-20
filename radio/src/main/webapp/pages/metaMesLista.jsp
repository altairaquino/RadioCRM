	<%@include file="topo.jsp" %>
	
</head>		
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
			<fieldset>
				<legend class="red"> <bean:write name="contato" property="ctcnome"/> > Metas de <bean:write name="meta" property="mtnano"/> </legend>
				<table>
					<tbody>
					<tr>
						<td colspan="3">
							<input type="button" value="Nova Meta Mensal" class="btn_hot" onclick="window.location = 'actionMetaMes.do?m=novo'">
						</td>
					</tr>
					<tr>
						<th>
							Mês
						</th>
						<th>
							Valor
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<% int c = 0; %>
					<logic:iterate id="b" name="ls_metames">
					<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
						<td>
							Mês de <bean:write name="b" property="mmcdcms"/>
						</td>
						<td>
							R$ <bean:write name="b" property="mmnvalr"/>
						</td>
						<td>
							<input type="button" class="btn" value="Editar" onclick="window.location = 'actionMetaMes.do?m=editar&mmncodg=<bean:write name="b" property="mmncodg"/>'">														
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="3">
							<input type="button" value="Nova Meta Mensal" class="btn_hot" onclick="window.location = 'actionMetaMes.do?m=novo'">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="button" value="Voltar" class="btn" onclick="window.location = 'actionMeta.do?m=lista&ctncodg=<bean:write name="contato" property="ctncodg"/>'">
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