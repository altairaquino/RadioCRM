<%@include file="topo.jsp" %>
<script type="text/javascript">
<!--
	function ajustaDIV(){
		var f = document.getElementById('formContrato');
		if (Number(f.crncgfp.value) == 5){
			document.getElementById('pagamento').style.visibility = 'visible';
		}else{
			document.getElementById('pagamento').style.visibility = 'hidden';
		}			
		if (Number(f.crncgcp.value) != 1 && Number(f.crncgcp.value) != 2){
			document.getElementById('permuta').style.visibility = 'visible';
		}else{
			document.getElementById('permuta').style.visibility = 'hidden';
		}
	}
//-->
</script>

</head>			
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
					
		<div id="content">		
		
		<fieldset>
			<legend>
				&nbsp; Cadastro de Novo Contrato  &nbsp;
			</legend>
			<html:errors/>
			<html:form action="actionContrato" focus="crncgag" styleId="formContrato" onsubmit="return window.confirm('Confirmar o cadastro do contrato?')">
			<html:hidden property="m" value="cadastro"/>	
			<table style="width: 600px;" align="center">
			<tbody>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Novo Contrato
					</th>
				</tr>
				<tr>
					<td>
						Nº da Nota Fiscal
					</td>
					<td>
						<html:text property="crcnota" size="11" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td style="width: 25%">
						Data de Cadastro
					</td>
					<td style="width: 75%">
						<html:text property="crdcadt" size="11" styleId="data1" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" value="<%= new FormataObj().formataData(new Date()) %>"/>
						<input type="image" id="b1" src="jscalendar/img.gif">		
					</td>
				</tr>
				<tr>
					<td>
						Cliente
					</td>
					<td style="color: red; font-weight: bold;">
						<bean:write name="cliente" property="clcnome"/>
						<input type="hidden" name="crncgcl" value="<bean:write name="cliente" property="clncodg"/>">
					</td>
				</tr>
				<tr>
					<td>
						Ramo de Atividade
					</td>
					<td>
						<b><bean:write name="cliente" property="clcramo"/></b>						
					</td>
				</tr>
				<tr>
					<td>
						Tipo de Contrato
					</td>
					<td>
						<html:select property="crncgmd" style="width: 350px;">
							<html:optionsCollection name="ls_midia" label="mdcdesc" value="mdncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						Contato
					</td>
					<td  style="color: blue; font-weight: bold;">
						<bean:write name="cliente" property="clcnmct"/>						
					</td>
				</tr>
				<tr>
					<td>
						Agência
					</td>
					<td>
						<html:select property="crncgag" style="width: 350px;">
							<html:optionsCollection name="ls_agencia" label="agcnome" value="agncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						PERIODO DE VEICULAÇÃO
					</th>
				</tr>
				<tr>
					<td>
						Data Inicial
					</td>
					<td>
						<html:text property="crdinic" size="11" styleId="data2" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"></html:text>
						<input type="image" id="b2" src="jscalendar/img.gif">						
					</td>
				</tr>
				<tr>
					<td>
						Data Término
					</td>
					<td>
						<html:text property="crdterm" size="11" styleId="data3" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"></html:text>
						<input type="image" id="b3" src="jscalendar/img.gif">						
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Informações para Texto
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:textarea property="crmtext" cols="80" rows="3"></html:textarea>						
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
						<html:text property="crnvalr" size="10" maxlength="10" onkeydown="Formata(this,8,event,2)" style="text-align: right;"></html:text>						
					</td>
				</tr>
				<tr>
					<td>
						Líquido ou Bruto?
					</td>
					<td>
						<html:radio property="crlliqu" value="T">Líquido</html:radio>&nbsp;&nbsp;
						<html:radio property="crlliqu" value="F">Bruto</html:radio>					
					</td>
				</tr>			
				<tr>
					<td>
						Cond. Pagamento
					</td>
					<td>
						<html:select property="crncgcp" style="width: 350px;" onchange="ajustaDIV()" onclick="ajustaDIV()">
							<html:optionsCollection name="ls_condicaopagamento" value="cpncodg" label="cpcdesc"/>
						</html:select>						
					</td>
				</tr>
				<tr id="permuta" style="visibility: hidden;">
					<td>
						Percentual de Permuta
					</td>
					<td>
						<html:text property="crnperm" size="8" maxlength="8" onkeydown="Formata(this,8,event,2)" style="text-align: right;"/>&nbsp;(%)
					</td>
				</tr>
				<tr>
					<td>
						Forma Pagamento
					</td>
					<td>
						<html:select property="crncgfp" style="width: 350px;" onchange="ajustaDIV()" onclick="ajustaDIV()">
							<html:optionsCollection name="ls_formapagamento" value="fpncodg" label="fpcdesc"/>
						</html:select>					
					</td>
				</tr>
				<tr id="pagamento" style="visibility: hidden;">
					<td>
						Outra Forma Pgto
					</td>
					<td>		
						<html:text property="crcpgto" size="50" maxlength="50"/>													
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="window.location = 'actionContrato.do?m=aberto';">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn">Salvar Contrato</html:submit>
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
		ajustaDIV();
	</script>
	
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    Calendar.setup({
	        inputField     :    "data3",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b3",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
</body>

<%@page import="com.grupooc.radiogrfm.utils.FormataObj"%>
<%@page import="java.util.Date"%></html>