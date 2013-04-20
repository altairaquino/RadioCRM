	<%@include file="topo.jsp" %>
	
	<script type="text/javascript" src="dwr/interface/Mapping.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>

		<script type="text/javascript">
			
			function ajustaTipoPessoa(e){
				if(e.value == '1'){
					document.getElementById('doc').innerHTML = 'CPF';
					document.getElementById('pessoa1').style.display = 'inline';
					document.getElementById('pessoa2').style.display = 'none';
				}
				if(e.value == '2'){
					document.getElementById('doc').innerHTML = 'CNPJ';
					document.getElementById('pessoa2').style.display = 'inline;';
					document.getElementById('pessoa1').style.display = 'none';
				}
			}
			
		
			function preencheCidades(elem){
				Mapping.getCidades(retorno,elem.value,'clncgcd');
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
				&nbsp;   Cadastro de Cliente    &nbsp;
			</legend>
			<html:form action="actionCliente" focus="clcnome">
			<html:hidden property="m" value="cadastro"/>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td colspan="2"> </td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Cadastro de Cliente
					</th>
				</tr>
				<tr>
					<td style="width: 130px;">
						Nome
					</td>
					<td style="width: 420px;">
						<html:text property="clcnome" size="60" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Contato (rádio)
					</td>
					<td>
						<html:select property="clncgct" style="width: 300px;">
							<html:optionsCollection name="ls_contato" value="ctncodg" label="ctcnome"/>
						</html:select>												
					</td>
				</tr>
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<html:radio property="clncgtp" styleId="clncgtp1" value="1" onclick="ajustaTipoPessoa(this)">Pessoa Física</html:radio>&nbsp;&nbsp;
						<html:radio property="clncgtp" styleId="clncgtp2" value="2" onclick="ajustaTipoPessoa(this)">Pessoa Jurídica</html:radio>
					</td>
				</tr>
				<tr>
					<td>
						<div id="doc">
							CPF
						</div>						
					</td>
					<td>
						<html:text property="clcdocm" styleId="clcdocm" size="15" maxlength="14"></html:text>
					</td>
				</tr>
			<tbody>
			</table>
			<div id="pessoa1" style="display: none;">
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td style="width: 130px;">
						RG:
					</td>
					<td style="width: 420px;">
						<html:text property="clcrg" size="14" maxlength="15"></html:text>
						&nbsp;Orgão Expedidor: <html:text property="clcoerg" size="4" maxlength="5"></html:text>
						UF:
						<html:select property="clcufrg">
							<html:option value="  ">  </html:option>
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			
			<div id="pessoa2" style="display: none;">
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td style="width: 130px;">
						Inscrição Municipal:
					</td>
					<td style="width: 420px;">
						<html:text property="clcinmu" size="14" maxlength="15"></html:text>
						Inscrição Estadual: <html:text property="clcines" size="14" maxlength="15"></html:text>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<td style="width: 130px;">
						Ramo de Atividade
					</td>
					<td style="width: 420px;">
						<html:text property="clcramo" size="60" maxlength="70"/>
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
						<html:text property="clccont" size="60" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>
						Data de Nascimento
					</td>
					<td>
						<html:text property="clddnct" size="11" maxlength="10" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						TeleFone
					</td>
					<td>
						<html:text property="clcfone" size="14" maxlength="13" onkeyup="criaMascara(this, '(##)####-####');"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						E-mail
					</td>
					<td>
						<html:text property="clcmail" size="60" maxlength="50"></html:text>
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
						<html:select property="clncgtl">
							<html:optionsCollection name="ls_tipologradouro" value="tlncodg" label="tlcdesc"/>
						</html:select>
						<html:text property="cllendr" size="35" maxlength="50"></html:text>
						,nº <html:text property="clcnumr" size="3" maxlength="5"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Complemento
					</td>
					<td>
						<html:text property="clccomp" size="60" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						Estado
					</td>
					<td>
						<html:select property="clcufcd" onclick="preencheCidades(this)">
							<html:optionsCollection name="ls_estado" value="cdcuf" label="cdcuf"/>
						</html:select>										
					</td>
				</tr>
				<tr>
					<td>
						Cidade
					</td>
					<td>
						<div id="cidade">
						<html:select property="clncgcd" style="width: 375px;">
							<html:optionsCollection name="ls_cidade" label="cdcdesc" value="cdncodg"/>
						</html:select>
						</div>
					</td>
				</tr>				
				<tr>
					<td>
						CEP/Bairro
					</td>
					<td>
						<html:text property="clccep" size="10" maxlength="9" onkeyup="criaMascara(this, '#####-###');" ></html:text>
						<html:text property="clcbair" size="47" maxlength="35"></html:text>
					</td>
				</tr>
				<tr>
					<th valign="top" colspan="2" style="text-align: center;background-color: #DDDDDD;"> Observações Gerais </th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="clmobs" cols="70" rows="3">
						</html:textarea>						
					</td>
				</tr>		
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar Cliente</html:submit>
					</td>
				</tr>				
			
			</tbody>			
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	
	<script type="text/javascript">
	  	if (document.getElementById('clncgtp1').checked){
	  		ajustaTipoPessoa(document.getElementById('clncgtp1'));
	  	}
	  	
	  	if (document.getElementById('clncgtp2').checked){
	  		ajustaTipoPessoa(document.getElementById('clncgtp2'));
	  	}
		
		
	</script>
	
</body>
</html>