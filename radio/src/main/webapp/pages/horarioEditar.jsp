<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Programas > <bean:write name="programa" property="pgcdesc"/> > Editar Programação    &nbsp;
			</legend>
			<html:errors/>
			<html:form action="/actionHorario" focus="hrncgds">
			<html:hidden property="hrncodg"/>
			<html:hidden property="hrncgpg"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2">
						Editar Programação
					</th>
				</tr>
				<tr>
					<td>
						Dia da Semana
					</td>
					<td>
						<html:select property="hrncgds" style="width: 200px;">
							<html:optionsCollection name="ls_diasemana" label="dscdesc" value="dsncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Horário Inicial
					</td>
					<td>
						<html:text property="hrhinic" size="8" maxlength="8" onkeyup="criaMascara(this, '##:##:##');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Horário Final
					</td>
					<td>
						<html:text property="hrhterm" size="8" maxlength="8" onkeyup="criaMascara(this, '##:##:##');"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit styleClass="btn">Alterar Programação</html:submit>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionHorario.do?m=lista&pgncodg=<bean:write name="programa" property="pgncodg"/>';">
					</td>
				</tr>					
			</tbody>			
			</table>	
			</html:form>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>