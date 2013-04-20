	<%@include file="topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
			
					
			function preencheCidades(elem){			
				Mapping.getCidades(retorno,elem.value,'agncgcd');
			}
			
			function retorno(valor){
				DWRUtil.setValue("cidade",valor);
			}
			
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
			
			if (window.addEventListener) {
				window.addEventListener("load",init,true);
			}else if (window.attachEvent) {
				window.attachEvent("onload", init);
			}else {
				window.onload = init;
			}	
			
		</script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Alteração de Cadastro de Agência    &nbsp;
			</legend>
			<html:form action="actionAgencia" focus="agcnome">
			<html:hidden property="agncodg"/>
			<html:hidden property="m" value="update"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="2"> </td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Cadastro de Agência
					</th>
				</tr>
				<tr>
					<th>
						Nome
					</th>
					<td>
						<html:text property="agcnome" size="60" maxlength="50"></html:text>(*)
					</td>
				</tr>
				<tr>
					<th>
						Tipo
					</th>
					<td>
						<html:radio property="agncgtp" styleId="clncgtp1" disabled="true" value="1" onclick="ajustaTipoPessoa(this)">Pessoa Física</html:radio>&nbsp;&nbsp;
						<html:radio property="agncgtp" styleId="clncgtp2" disabled="true" value="2" onclick="ajustaTipoPessoa(this)">Pessoa Jurídica</html:radio>
					</td>
				</tr>
				<tr>
					<th>
						C.N.P.J.
					</th>
					<td>
						<html:text property="agccnpj" size="19" maxlength="18" onkeyup="criaMascara(this, '##.###.###/####-##');" disabled="true"></html:text>(*)
					</td>
				</tr>
				<tr>
					<th>
						Razão Social
					</th>
					<td>
						<html:text property="agcrzsc" size="50" maxlength="50"></html:text>(*)
					</td>
				</tr>
				<tr>
					<th>
						Comissão(%)
					</th>
					<td>
						<html:text property="agncoms" size="8" maxlength="8" onkeydown="Formata(this,8,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Contato </th>
				</tr>
				<tr>
					<th>
						TeleFone
					</th>
					<td>
						<html:text property="agcfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						E-mail
					</th>
					<td>
						<html:text property="agcmail" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> Endereço </th>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<html:select property="agncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="aglendr" size="35" maxlength="50"></html:text>
						,nº<html:text property="agcnumr" size="3" maxlength="5"></html:text>						
					</td>
				</tr>
				<tr>
					<th>
						Complemento
					</th>
					<td>
						<html:text property="agccomp" size="60" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<html:select property="agcufcd" onclick="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>										
					</td>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<td>
						<div id="cidade">
						<html:select property="agncgcd" style="width: 375px;">
							<html:option value="-1">Escolha o estado</html:option>
							<html:optionsCollection name="ls_cidade" label="cdcdesc" value="cdncodg"/>
						</html:select>
						</div>
					</td>
				</tr>				
				<tr>
					<th>
						CEP/Bairro
					</th>
					<td>
						<html:text property="agccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');" ></html:text>
						<html:text property="agcbair" size="47" maxlength="35"></html:text>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> Observações Gerais </th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="agmobs" cols="70" rows="3">
						</html:textarea>						
					</td>
				</tr>		
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salvar Agência</html:submit>
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