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
				&nbsp;   Relat�rios Diversos   &nbsp;
			</legend>
			<html:form action="actionContato">
			<html:hidden property="m" value="relatorioHanking"/>
			<center>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relat�rios Diversos
					</th>
				</tr>
				<tr>
					<td>
						Data Inicial
					</td>
					<td>
						<input type="text" id="data1" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b1" src="jscalendar/img.gif">						
					</td>
				</tr>
				<tr>
					<td>
						Data Final
					</td>
					<td>
						<input type="text" name="data2" id="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
						<input type="image" id="b2" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<select name="tipo" style="width: 400px;">
							<option value="1">Relat�rio de Ranking de vendas - Somente Valores </option>
							<option value="2">Relat�rio de Ranking de vendas - Com Gr�fico </option>
							<option value="3">Relat�rio de Ranking de compra - Somente Valores </option>
							<option value="4">Relat�rio de Ranking de compra - Com Gr�fico </option>
							<option value="5">Relat�rio de Vendas Geral por Per�odo</option>
							<option value="6">Relat�rio de Ranking de Vendas por Ag�ncia</option>
							<option value="7">Relat�rio de Vendas Geral por Ag�ncia</option>
							<option value="8">Relat�rio de Vendas Geral por Contato</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						<html:submit styleClass="btn_hot">Visualizar Relat�rio</html:submit>
					</td>
				</tr>	
			
			</tbody>
			</table>
			</center>	
			</html:form>
			
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	
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
	</script>
	
</body>
</html>