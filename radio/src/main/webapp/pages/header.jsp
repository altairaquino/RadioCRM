<table>
	<tbody>
		<tr>
			<th style="width:10%; padding: 5px;">			
				<img class="avatar" src="imagens/<bean:write name="empresa" property="epclogo"/>" width="110" height="80"/>
			</th>			
			<td style="vertical-align:middle; line-height: 200%;">
				<div id="usuario" style="font-size: 12px; padding: 5px; ">
						R¡ÅDIO:<b> <bean:write name="empresa" property="epcnome"/> - <bean:write name="empresa" property="epcdccd"/>/<bean:write name="empresa" property="epcufcd"/></b><br>
						USU¡ÅRIO:<b> <bean:write name="usuario" property="uscnome"/></b>
				<div>
				<br>
				<div id="agora" style="text-align: right;">
				<strong>
					<%=new com.grupooc.radiogrfm.utils.FormataObj().getDataPorExtensoComDia(new java.util.Date())%>
				</strong>
				</div>
			</td>
		</tr>
	</tbody>
</table>