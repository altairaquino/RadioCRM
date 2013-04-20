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
				&nbsp;   Dados da Agência    &nbsp;
			</legend>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="2"  style="text-align: right;">
						<input type="button" class="btn_hot" value="Clientes" onclick="window.location = 'actionAgencia.do?m=listaClientes&agncodg=<bean:write name="agencia" property="agncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;font-size: 12px;">
						Dados Cadastrais da Agência
					</th>
				</tr>
				<tr>
					<th>
						Nome
					</th>
					<td>
						<bean:write name="agencia" property="agcnome"/>
					</td>
				</tr>
				<tr>
					<th>
						Tipo
					</th>
					<td>
						<bean:write name="agencia" property="agcdctp"/>
					</td>
				</tr>
				<tr>
					<th>
						<bean:write name="agencia" property="agctpdc"/>
					</th>
					<td>
						<bean:write name="agencia" property="agccnpj"/>
					</td>
				</tr>
				<tr>
					<th>
						Razão Social
					</th>
					<td>
						<bean:write name="agencia" property="agcrzsc" />
					</td>
				</tr>
				<tr>
					<th>
						Comissão(%)
					</th>
					<td>
						<bean:write name="agencia" property="agncoms" />
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;font-size: 12px;"> 
						Contato 
					</th>
				</tr>
				<tr>
					<th>
						TeleFone
					</th>
					<td>
						<bean:write name="agencia" property="agcfone"/>
					</td>
				</tr>
				<tr>
					<th>
						E-mail
					</th>
					<td>
						<bean:write name="agencia" property="agcmail"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD; font-size: 12px;"> 
						Endereço 
					</th>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<bean:write name="agencia" property="agcdctl"/>&nbsp;<bean:write name="agencia" property="aglendr"/>,nï¿½ <bean:write name="agencia" property="agcnumr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<bean:write name="agencia" property="agcufcd"/>										
					</td>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<td>
						<bean:write name="agencia" property="agcdccd" />						
					</td>
				</tr>				
				<tr>
					<th>
						CEP/Bairro
					</th>
					<td>
						<bean:write name="agencia" property="agccep"/> / <bean:write name="agencia" property="agcbair"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD; font-size: 12px;"> Observações Gerais </th>
				</tr>
				<tr>
					<td colspan="2">
						<bean:write name="agencia" property="agmobs"/>							
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;							
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionAgencia.do?m=lista'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn" value="Editar Dados" onclick="window.location = 'actionAgencia.do?m=editar&agncodg=<bean:write name="agencia" property="agncodg"/>'">
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