<%@include file="topo.jsp" %>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp; Dados do Contrato  &nbsp;
			</legend>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Dados Cadastrais do Contrato
					</th>
				</tr>
				<tr>
					<td>
						Nº da Nota Fiscal
					</td>
					<td>
						<bean:write name="contrato" property="crcnota"/>
					</td>
				</tr>
				<tr>
					<td>
						Data de Cadastro
					</td>
					<td>
						<bean:write name="contrato" property="crdcadt"/>			
					</td>
				</tr>
				<tr>
					<td style="width: 140px;">
						Cliente
					</td>
					<td style="color: red; font-weight: bold;width: 360px;">
						<bean:write name="contrato" property="crcnmcl"/>						
					</td>
				</tr>
				<tr>
					<td>
						Ramo de Atividade
					</td>
					<td>
						<b><bean:write name="contrato" property="crcramo"/></b>						
					</td>
				</tr>
				<tr>
					<td>
						Tipo de Contrato
					</td>
					<td>
						<bean:write name="contrato" property="crcdcmd"/>
					</td>
				</tr>
				<tr>
					<td>
						Contato
					</td>
					<td  style="color: blue; font-weight: bold;">
						<bean:write name="contrato" property="crcnmct"/>						
					</td>
				</tr>
				<tr>
					<td>
						Agência
					</td>
					<td>
						<bean:write name="contrato" property="crcnmag"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Período de Vigência
					</th>
				</tr>
				<tr>
					<td>
						Data Inicial
					</td>
					<td>
						<bean:write name="contrato"  property="crdinic" />						
					</td>
				</tr>
				<tr>
					<td>
						Data Término
					</td>
					<td>
						<bean:write name="contrato"  property="crdterm" />						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2"> 
						Informações para Texto
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<bean:write name="contrato" property="crmtext"/>						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2"> 
						Informações para Pagamento
					</th>
				</tr>
				<tr>
					<td>
						Valor
					</td>
					<td>
						R$ <bean:write name="contrato" property="crnvalr"/>						
					</td>
				</tr>
				<tr>
					<td>
						Líquido ou Bruto?
					</td>
					<td>
						<logic:equal name="contrato" property="crlliqu" value="T">
							Líquido
						</logic:equal>
						<logic:equal name="contrato" property="crlliqu" value="F">
							Bruto
						</logic:equal>		
					</td>
				</tr>				
				<tr>
					<td>
						Cond. Pagamento
					</td>
					<td>
						<bean:write name="contrato" property="crcdccp"/> (Permuta de <bean:write name="contrato" property="crnperm"/>%)						
					</td>
				</tr>
				<tr>
					<td>
						Forma Pagamento
					</td>
					<td>
						<bean:write name="contrato" property="crcdcfp"/> <bean:write name="contrato" property="crcpgto"/>						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2"> 
						Comissões
					</th>
				</tr>
				<tr>
					<td>
						Contato
					</td>
					<td>
						<bean:write name="contrato" property="crcnmct"/>						
					</td>
				</tr>
				<tr>
					<td>
						Comissão
					</td>
					<td>
					R$ <bean:write name="contrato" property="crycoms"/> (<bean:write name="contrato" property="crncoms"/> %)				
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionContrato.do?m=opcoes&crncodg=<bean:write name="contrato" property="crncodg"/>';">
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