/*
 * Metodos JavaScript
 * (c) 2006, EDUCON
 */

/*
 * Inicializações básicas.
 */
function basics() {
	updateCSS();
	if ($.browser.msie) {
		ativaHover('main_menu');
	}
}

/*
 * Atualizar CSS
 */
function updateCSS() {
	$('input[@type=button]').each(function() {
		if ((this.name == 'next') || (this.name == 'back')) {
			$(this).addClass('btn_hot');
		} else {
			$(this).addClass('btn');
		}
	});
	//$('input[@type=submit]').addClass('btn_hot');
	$('input[@type=submit]').each(function() {
		if ((this.name == 'next') || (this.name == 'back')) {
			$(this).addClass('btn_hot');
		} else {
			$(this).addClass('btn');
		}
	});


	$('form').submit(function() {
		if ($('input[@type=submit]', this).attr('disabled')) {
			return false;
		} else {
			$('input[@type=submit]', this).attr('disabled','disabled');
			return true;
		}
	});
}

/*
 * Função para o menu no IE.
 */
function DOMgetElementsByClassName($node, $className) {
	var $node, $atual, $className, $retorno = new Array(), $novos = new Array();
	$retorno = new Array();
	for (var $i = 0; $i < $node.childNodes.length; $i++) {
		$atual = $node.childNodes[$i];
		if ($atual.nodeType == 1) {
			$classeAtual = $atual.className;
			if (new RegExp("\\b" + $className + "\\b").test($classeAtual)) {
				$retorno[$retorno.length] = $atual;
			}
			if ($atual.childNodes.length > 0) {
				$novos = DOMgetElementsByClassName($atual, $className);
				if ($novos.length > 0) {
					$retorno = $retorno.concat($novos);
				}
			}
		}
	}
	return $retorno;
}

/*
 * Outra função para o menu no IE.
 */
function ativaHover(classe) {
	var pais = DOMgetElementsByClassName(document.body, classe);
	for (var j = 0; j < pais.length; j++) {
		var sfEls = pais[j].getElementsByTagName("LI");
		for (var i = 0; i < sfEls.length; i++) {
			sfEls[i].onmouseover = function() {
				this.className += ' over';
				var lstSelect = document.getElementsByTagName("SELECT");
				for (var x = 0; x < lstSelect.length; x++) {
					lstSelect[x].style.visibility = 'hidden';	
				}				
			}
			sfEls[i].onmouseout = function() {
				this.className = this.className.replace(new RegExp(" over\\b"), "");
				var lstSelect = document.getElementsByTagName("SELECT");
				for (var x = 0; x < lstSelect.length; x++) {
					lstSelect[x].style.visibility = 'visible';	
				}				
			}
		}
	}
}

/*
 * Muda o status da entrega de um documento na matrícula.
 */
function entregaDocumento(url, id, matricula) {
	var status = $('#documento_' + id).attr('checked');
	url += '?perform=editar&documento=' + 
		id + '&status=' + status + 
		'&matricula=' + matricula;
	$.ajax({
		url: url,
		type: 'post',
		complete: mostraMsg
	});
}

/*
 * Mostrar uma mensagem vinda de um XMLHttpRequest.
 */
function mostraMsg(xml) {
	if (xml.responseText != '') {
		alert(xml.responseText);
	}
}

/*
 * Buscar os filhos de uma entidade nas regras de repasse.
 */
function repasseBuscaFilhos(url, entidade) {
	var pars = {
		perform: 'resultado',
		entidade: entidade
	};
	$('#regras_repasse').load(url, pars, function() {
		updateCSS();
	});
}

/*
 * Atualizar valores de uma regra de repasse.
 */
function atualizarRegra(id, url) {
	url += '?perform=salvar';
	$('input', '#'+id).each(function() {
		if ((this.type == 'hidden') || (this.type == 'text')) {
			url += '&' + this.name + '=' + this.value;
		}
	});
	$.ajax({
		url: url,
		type: 'post',
		complete: mostraMsg
	});
}

/*
 * Associa um curso a um convenio.
 */
function associaConvenioCurso(url, curso, convenio) {
	var status = $('#curso_' + curso).attr('checked');
	url += '?perform=editar&id_curso=' + curso + 
		'&id_convenio=' + convenio + 
		'&status=' + status;
	$.ajax({
		url: url,
		type: 'post',
		complete: mostraMsg
	});
}

/*
 * Deferir/indeferir uma matricula.
 */
function deferirMatricula(url, matricula) {
	var status = $('#deferir_' + matricula).val();
	if ((status == 'true') || (status == 'false')) {
		url += '?perform=salvar&matricula=' + matricula +
			'&status=' + status;
		$.ajax({
			url: url,
			type: 'post',
			complete: mostraMsg
		});
	} else {
		alert('Por favor, selecione uma opção válida.');
	}
}

/*
 * Associa/desassocia um aluno a um grupo.
 */
function associaAlunoGrupo(url, grupoOrigem, grupoDestino, direito) {
	var origem = 'aluno_grupo_esquerdo';
	var destino = 'aluno_grupo_direito';
	if( direito ) {
		origem = 'aluno_grupo_direito';
		destino = 'aluno_grupo_esquerdo';
	}
	
	var rematricula = $('#' + origem).val();
	url += '?perform=salvar&grupo=' + grupoOrigem + 
		'&grupoTroca=' + grupoDestino +
		'&rematricula=' + rematricula;
	$.ajax({
		url: url,
		type: 'post',
		complete: function(xml) {
			if (xml.responseText == ''){
				trocaBox(origem, destino);
			}			
			mostraMsg(xml);
		}
	});
}
		
/*
 *Associa/desassocia um aluno a um grupo Projeto. 
 */
function associaAlunoGrupoProjeto(url, grupoDestino, direito) {
	var origem = 'aluno_grupo_esquerdo';
	var destino = 'aluno_grupo_direito';
	if( direito ) {
		origem = 'aluno_grupo_direito';
		destino = 'aluno_grupo_esquerdo';
	}	
	
	var rematricula = $('#' + origem).val();
	var grupoD = grupoDestino;
		
	url += '?perform=editar&grupoId=' + grupoDestino + 
		'&rematriculaId=' + rematricula;	
	$.ajax({
		url: url,
		type: 'post',
		complete: function(xml) {
			trocaBox(origem, destino);
		}
	});	
}
		
/*
 * Troca um aluno de sala.
 */
function ensalaAluno(url, classe, classeTroca, associa) {
	var origem = 'remat_troca';
	var destino = 'remat';
	if (associa) {
		origem = 'remat';
		destino = 'remat_troca';
	}
	var rematricula = $('#' + origem).val();
	url += '?perform=salvar&classe=' + classe + 
		'&classe_troca=' + classeTroca +
		'&rematricula=' + rematricula;
	$.ajax({
		url: url,
		type: 'post',
		complete: function(xml) {
			trocaBox(origem, destino);
			mostraMsg(xml);
		}
	});
}

/*
 * Troca um objeto de box.
 */
function trocaBox(origem, destino) {
	$('#' + destino).append($('option[@selected]', '#' + origem));
	$('option', '#' + origem).remove('[@selected]');
}

/*
 * Altera o valor de um campo para 'true' ou 'false' 
 * de acordo com o boolean passado.
 */
function setBooleanVal(id, bool) {
	if(bool) {
		$('#' + id).val('true');
	} else {
		$('#' + id).val('false');
	}
}

/*
 * Esconde o campo com id igual ao valor passado no 1o parâmetro e mostra o 2o.
 */
function escondeMostra(campo1, campo2) {
	$('#' + campo1).hide();
	$('#' + campo2).show();
}

/*
 * Atualiza uma observação no histórico do aluno
 */
function atualizaObservacao(url, matricula){
	var observacao = $('#observacao').val();	
	var tipo = $('#tipo').val();	
	
	observacao = observacao.replace(/^\s*/, "").replace(/\s*$/, "");

	if(observacao == "") {	
		if(!confirm('Nenhuma observação foi digitada. Deseja continuar?'))
			return;
	}
	url += '?perform=salvar&matricula=' + matricula + 
		'&observacoes=' + observacao +
		'&tipo=' + tipo;
	$.ajax({
		url: url,
		type: 'post',
		complete: function(xml) {
			if (xml.responseText != '') {
				$('#historico').val("\n" + xml.responseText + "\n" + $('#historico').val());
				$('#observacao').val('');					
			}
		}
	});	
}


function addError(erro)
{
	$("#erros").append("<li>" + erro + ".</li>");
}

function apertou(elemento, evento, nomedodiv)
{
    e = getEvent(evento);
    kc = e.keyCode;
    if (kc == 38 && selecionado>0) selecionado--;
    if (kc == 40 && selecionado<=quantidadeexibida-3) selecionado++;
    if (kc == 13) selecionaexibido(elemento, nomedodiv);
    if (kc == 27) document.getElementById(nomedodiv).display='none';
    if (kc!=46 && kc!=8) movecursorprofinal(elemento);
}


function getEvent(event)
{
    return (event ? event : window.event);
}

function noenter()
{
    return !(window.event && window.event.keyCode == 13);
}

function clear(elementId)
{
	$('div#' + elementId).empty();
	$('div#' + elementId).hide();
}

function salvaDocumento(url, tipoDocumento, entidade, check, solicitacao)
{
	$.ajax({
		type: 'post',
		url: url + '?perform=salvar&documento='+ tipoDocumento + '&entidade='+ entidade + '&status=' + $("input[@name="+ check +"]").is(":checked") + '&solicitacao=' + solicitacao,
		complete: function(xml){
			if(xml.responseText != '')
			{
				addError(xml.responseText);
			}
		}
	});
}


function disponibilizaGrupoRepasse(url, grupoRepasseId)
{
	//alert($("input[@name=disponivel" + grupoRepasseId + "]").is(":checked"));
	var sel = $("input[@name=disponivel" + grupoRepasseId + "]").is(":checked");
	$.ajax({
		type: 'post',
		url: url + '?perform=salvar&grupoRepasse=' + grupoRepasseId + '&disponivel=' + sel,
		complete: function(xml){
			if(xml.responseText != '')
			{
				addError(xml.responseText);
			}
		}
	});
}

function formataLink(link, inscricao, matricula){
	var valor = 0;
	var parametro = "";
	if (inscricao > 0) {
		valor = inscricao;
		parametro = "inscricao";
	} else if (matricula>0) {
		valor = matricula;
		parametro = "matricula";
	}
	link += '?'+parametro+"="+valor;
}

function mostraEsconde(campo){
	var disp = $('#' + campo).css('display');

	if(disp == 'block') {	
	    $('#' + campo).hide();
	} else {
	    $('#' + campo).show();
	}
	
}

function fnDesmarca(name) {
	strCheck = new String( name );
	
	var status = $('#' + name).attr('checked');
	var posUnderline = strCheck.indexOf("_");
	var numero = strCheck.slice(posUnderline+1, strCheck.length);
	var ident = strCheck.slice(3,posUnderline);
	
	$('input[@type=checkbox]').each(function() {
		posUnderline = $(this).attr('id').indexOf("_");
		var itIdent = $(this).attr('id').slice(3, posUnderline)
		var itNumero = $(this).attr('id').slice(posUnderline+1, $(this).attr('id').length);
		
		if( status && itIdent == ident ) {
			if( numero == 1 && itNumero != numero) {
				$(this).attr('checked','');
			} else if( itNumero != numero ) {
				$('#atd' + ident + '_1').attr('checked','');
			}
		}
	});
}

function trataTodos()
{
	if( $("#todos").is(":checked") )
	{
		$("#itens").attr("readonly", "readonly");
		$("#itens_excluidos").attr("readonly", "readonly");
		$("#left").attr("disabled", "disabled");
		$("#right").attr("disabled", "disabled");
		
		$("#itens").append($("#itens_excluidos").html());
		$("#itens_excluidos").empty();
	}
	else
	{
		$("#itens").removeAttr("readonly");
		$("#itens_excluidos").removeAttr("readonly");
		$("#left").removeAttr("disabled");
		$("#right").removeAttr("disabled");
		
		$("#itens_excluidos").append($("#itens").html());
		$("#itens").empty();
	}
}

function obterCidadeUF(url, uf, endereco)
{
	var comboname = "cidade";
	$("select[@name=" + comboname + "]").html("<option value=0 />Carregando...");
	$.ajax(
	{
		type: 'post',
		url: url + '?uf=' + uf + '&endereco='+ endereco,
		complete: function(xml){
			if(xml.responseText != '')
			{
				//Procurar e colocar o response dentro do combobox
				$("select[@name=" + comboname + "]").html(xml.responseText);
				//Redundancia de selected para firefox, e outros
				if(navigator.appName != "Microsoft Internet Explorer"){
					var selected = $("select[@name=" + comboname + "]")[0].boxObject.element;
					for(var index=0; index<selected.options.length; index++) {
						if(selected.options.item(index).defaultSelected){
							selected.options.item(index).selected=true;
						}
					}
					//$("select[@name=" + comboname + "]")[0].boxObject.element=selected;
				}
			}
		}
	}
	)
}


function exibeDetalhesConvenio(url,convenio,pessoa,vc)
{
	var divname = "detalhes_convenio";
	var vl = $('#show_button'+vc).attr('value')
	
	//Esconde ou mostra
	if(vl == '+') {	
	    $('#' + divname+vc).hide;
	} else {
	    $('#' + divname+vc).show;
	}
	
	$('#' + divname+vc).html("<strong>Carregando...</strong>");
	if ('+' == vl) {
		$.ajax({
			type: 'post',
			url: url + '?convenio=' + convenio + '&pessoa=' + pessoa + '&perform=resultado',
			complete: function(xml){
				//colocar html de retorno dentro da div
				$('#' + divname + vc).html(xml.responseText);
				//Alterar estado do botão
				$('#show_button' + vc).attr('value', '-');
			}
		})
	}else {
		//Alterar estado do botão
		$('#show_button' + vc).attr('value', '+');
		//Limpando
		$('#' + divname + vc).html("");
	}
}

function atualizaDescontos(url, semestreId, solId) {
	var divname = 'descontos';
	
	url += '?perform=pesquisa&semestre='+semestreId+'&solicitacao='+solId; 
	$.ajax({
		url: url,
		type: 'GET',
		complete: function(xml) {
			$('#'+divname).html(xml.responseText);
		}
	});
}

function exibeDetalheParcelamento(
	url,
	saldo,
	desconto,
	entrada,
	minima,
	parcelas,
	maxparcelas,
	vencimento,
	listaDias,
	valormatricula,
	matricula,
	incluimatricula,
	parcelaMinima)
{
	var divname = "detalhes_parcelamento";
	$.ajax({
			type: 'post',
			url: url + '?perform=detalhes&saldo=' + saldo +
				'&desconto=' + desconto + 
				'&entrada=' + entrada + 
				'&entradaMinima=' + minima +
				'&parcela=' + parcelas + 
				'&maxParcelas=' + maxparcelas +
				'&diaVencimento=' + vencimento + 
				'&listaDias=' + listaDias + 
				'&valorMatricula=' + valormatricula + 
				'&matricula=' + matricula + 
				'&incluiMatricula=' + incluimatricula +
				'&parcelaMinima=' + parcelaMinima,
			complete: function(xml){
				//colocar html de retorno dentro da div
				$('#' + divname).html(xml.responseText);
				}
		});
	}

function atualizaPeriodos(url,ano){
	var comboname="periodos";
	$.ajax(
	{
		type: 'post',
		url: url + '?ano='+ ano,
		complete: function(xml){
			if(xml.responseText != '')
			{
				//Procurar e colocar o response dentro do combobox
				$("#" + comboname).html(xml.responseText);
				//Redundancia de selected para firefox, e outros
				if(navigator.appName != "Microsoft Internet Explorer"){
					var selected = $("#" + comboname)[0].boxObject.element;
					for(var index=0; index<selected.options.length; index++) {
						if(selected.options.item(index).defaultSelected){
							selected.options.item(index).selected=true;
						}
					}
					//$("select[@name=" + comboname + "]")[0].boxObject.element=selected;
				}
				exibeDisciplinasWebTutoria(url,$("#" + comboname).val(),$('#anos').val());
			}
		}
	})
	
}
	
function exibeDisciplinasWebTutoria(url,periodo,ano){
	var divname = "disciplinas";
	$.ajax({
		type: 'post',
		url: url + '?perform=resultado&periodo=' + periodo+'&ano='+ano,
		beforeSend:function(){
			$('#' + divname).html("<strong>Carregando...</strong>");
		}, complete:function(xml){
			//colocar html de retorno dentro da div
			$('#' + divname).html(xml.responseText);
			updateCSS();
		}
	});
}


function verificaLinhasPesquisa(url, linhaPesquisaId) {
	var divname = "erro";
	
	url += '?perform=pesquisa&linha_pesquisa_id=' + linhaPesquisaId;
	$.ajax({
		url: url,
		type: 'get',
		complete:function(xml){
			//colocar html de retorno dentro da div
			$('#' + divname).html(xml.responseText);
		}
	});
}
function carregaAvaliadores(url, linhaPesquisaId, btn) {
	var divname = 'aval_'+linhaPesquisaId;
	if (btn.value=='+') {
		$('#'+divname).show();
		btn.value='-';
		url += '?perform=pesquisa&linha_pesquisa_id='+linhaPesquisaId; 
		$.ajax({
			url: url,
			type: 'GET',
			complete: function(xml) {
				$('#'+divname).html(xml.responseText);
			}
		});
	} else {
		 $('#'+divname).hide();
		 btn.value='+';
	}
}

function gravaAvaliador(projetoId, linhaPesquisaId, avalId) {
	var url = '../../../academico/coordenacao/projeto/avaliador.do?&perform=confirmar&projetoId='+ projetoId+'&linhaPesquisaId='+linhaPesquisaId+'&avaliadorId='+avalId;
	var divname = 'avaliadores';

	$.ajax({
		type: 'post',
		url: url,
		complete:function(xml){
			var sTipo = xml.getResponseHeader("Content-Type");
			if (sTipo.substring(0, 8)!='text/xml') {
				var pnlErro = document.getElementById('pnlErro');
				pnlErro.innerHTML = '';
				$('#' + divname).html(xml.responseText);
				updateCSS();
			} else {
				var pnlErro = document.getElementById('pnlErro');
				pnlErro.innerHTML = parseErroXML(xml.responseXML);
			}
		}
	});
}

function parseErroXML(oXmlDom) {
	var oRoot = oXmlDom.documentElement;
	var oFragment = document.createDocumentFragment();
	var sResult = '';

	if (null!=oRoot) {
		var cErros = oRoot.getElementsByTagName("field");
		
		for (var i=0, iLen=cErros.length;  i<iLen; i++) {
			var curChild = cErros[i].firstChild;
			do {
				if (curChild.tagName=="msg") {
					sResult+='<p class=\'aviso\'>'+curChild.firstChild.textContent+'</p>';
				}
			} while (curChild = curChild.nextSibling);
		}
	}
	return sResult;
}

function carregaHtml(url, parametros, divresult) {
	$.ajax({
		type: 'post',
		url: url + '?' + parametros,
		beforeSend:function(){
			$('#' + divresult).html("<strong>Carregando...</strong>");
		}, complete:function(xml){
			$('#' + divresult).html(xml.responseText);
		}
	});
}