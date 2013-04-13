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
				&nbsp;   Dados do Cliente    &nbsp;
			</legend>
			
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="2" style="text-align: right;"> 
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionCliente.do?m=editar&clncodg=<bean:write name="cliente"  property="clncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados Cadastrais do Cliente
					</th>
				</tr>
				<tr>
					<td>
						Nome
					</td>
					<td>
						<bean:write name="cliente"  property="clcnome" />
					</td>
				</tr>
				<tr>
					<td>
						Contato (rádio)
					</td>
					<td>
						<bean:write name="cliente" property="clcmtct"/>	- <bean:write name="cliente" property="clcnmct"/>
					</td>
				</tr>	
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<bean:write name="cliente" property="clcdctp"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="cliente" property="clctpdc"/>												
					</td>
					<td>
						<bean:write name="cliente" property="clcdocm"/>
					</td>
				</tr>
				<logic:equal name="cliente" property="clncgtp" value="1">
				<tr>
					<td style="width: 130px;">
						RG:
					</td>
					<td style="width: 420px;">
						<bean:write name="cliente" property="clcrg"/> -
						&nbsp;Orgão Expedidor: <bean:write name="cliente" property="clcoerg"/> - 
						UF:	<bean:write name="cliente" property="clcufrg"/>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="cliente" property="clncgtp" value="2">
				<tr>
					<td style="width: 130px;">
						Inscrição Municipal:
					</td>
					<td style="width: 420px;">
						<bean:write name="cliente" property="clcinmu"/> - 
						Inscrição Estadual: <bean:write name="cliente" property="clcines"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td>
						Ramo de Atividade
					</td>
					<td>
						<bean:write name="cliente" property="clcramo"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Dados para Contato </th>
				</tr>
				<tr>
					<td>
						Contato (cliente)
					</td>
					<td>
						<bean:write name="cliente" property="clccont"/>
					</td>
				</tr>
				<tr>
					<td>
						Data de Nascimento
					</td>
					<td>
						<bean:write name="cliente" property="clddnct"/>
					</td>
				</tr>
				<tr>
					<td>
						TeleFone
					</td>
					<td>
						<bean:write name="cliente"  property="clcfone"/>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<bean:write name="cliente"  property="clcmail" />
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Endereço </th>
				</tr>
				<tr>
					<td>
						Logradouro
					</td>
					<td>
						<bean:write name="cliente" property="clcdctl"/>
						<bean:write name="cliente"  property="cllendr" />
						,nº <bean:write name="cliente"  property="clcnumr"/>						
					</td>
				</tr>
				<tr>
					<td>
						Complemento
					</td>
					<td>
						<bean:write name="cliente"  property="clccomp" />
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<bean:write name="cliente" property="clcufcd"/>										
					</td>
				</tr>
				<tr>
					<td>
						Cidade
					</td>
					<td>
						<bean:write name="cliente" property="clcdccd"/>
					</td>
				</tr>				
				<tr>
					<td>
						CEP/Bairro
					</td>
					<td>
						<bean:write name="cliente"  property="clccep" />
						<bean:write name="cliente"  property="clcbair"/>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> Observações Gerais </th>
				</tr>
				<tr>
					<td colspan="2">
						<bean:write name="cliente" property="clmobs"/>						
					</td>
				</tr>		
				<tr>
					<td colspan="2">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionCliente.do?m=opcoes&clncodg=<bean:write name="cliente"  property="clncodg"/>'">
					</td>
				</tr>				
			
			</tbody>			
			</table>
					
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	
	<script type="text/javascript">
		ajustaTipoPessoa(document.getElementById('clncgtp1'));
		ajustaTipoPessoa(document.getElementById('clncgtp2'));
	</script>
	
</body>
</html>