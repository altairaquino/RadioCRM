// JavaScript Document
// Use 
// OnKeyUp="criaMascara(this, '##.###.###-#');"
// em seu INPUT
  function criaMascara(_RefObjeto, _Modelo){
  
    var valorAtual = _RefObjeto.value;        
    var valorNumerico = '';
    var nIndexModelo = 0;
    var nIndexString = 0;
    var valorFinal = '';
    var adicionarValor = true;
    
     
      // limpa a string valor atual para verificar 
      // se todos os caracteres são números
      for (i=0;i<_Modelo.length;i++){
        if (_Modelo.substr(i,1) != '#'){
          valorAtual = valorAtual.replace(_Modelo.substr(i,1),'');
      }}
      
      // verifica se todos os caracteres são números
      for (i=0;i<valorAtual.length;i++){
        if (!isNaN(parseFloat(valorAtual.substr(i,1)))){
          valorNumerico = valorNumerico + valorAtual.substr(i,1);
      }}
      
      // aplica a máscara ao campo informado usando
      // o modelo de máscara informado no script
      for (i=0;i<_Modelo.length;i++){
        
        if (_Modelo.substr(i,1) == '#'){
          if (valorNumerico.substr(nIndexModelo,1) != ''){
            valorFinal = valorFinal + valorNumerico.substr(nIndexModelo,1);
            nIndexModelo++;nIndexString++;
          } 
            else {
              adicionarValor = false;
        }}
          
          else {
            if (adicionarValor && valorNumerico.substr(nIndexModelo,1) != ''){
            valorFinal = valorFinal + _Modelo.substr(nIndexString,1)
            nIndexString++;
          }}
      }
    
      //alert(valorFinal)
      _RefObjeto.value = valorFinal 

  }
  
  function mostrarCampos(source, args)
  {
        var valor = document.getElementById('CheckBoxTermo');
        if(valor.checked)
            args.IsValid = true;
        else
            args.IsValid = false; 
   }
   function mudarfoco()
   {
    document.getElementById('CheckBoxTermo').focus();
   }
    function validarCPF(sourse, args)
    {
        args.IsValid = IsCPF(args.Value);
    }
    
    // Função para validação do CPF
function IsCPF(valor) {
    
    valor = valor.replace( "-", "" );
	valor = valor.replace( ".", "" );
	valor = valor.replace( ".", "" );
	primeiro=valor.substr(1,1);
	falso=true;
	size=valor.length;
	if (size!=11){
		return false;
	}
	size--;
	for (i=2; i<size-1; ++i){
		proximo=(valor.substr(i,1));
		if (primeiro!=proximo) {
			falso=false
		}
	}
	if (falso){
		return false;
	}
   	if(modulo(valor.substring(0,valor.length - 2)) + "" + modulo(valor.substring(0,valor.length - 1)) != valor.substring(valor.length - 2,valor.length)) {
   		return false;
   	}
   	return true
}

// Função utilizada para validação de CPF e CNPJ
function modulo(str) {

   	soma=0;
   	ind=2;
   	for(pos=str.length-1;pos>-1;pos=pos-1) {
   		soma = soma + (parseInt(str.charAt(pos)) * ind);
   		ind++;
   		if(str.length>11) {
   			if(ind>9) ind=2;
   		}
	}
   	resto = soma - (Math.floor(soma / 11) * 11);
   	if(resto < 2) {
    	return 0
   	}
   	else {
   		return 11 - resto
   	}
}

function validarExistente(source, args)
{
    alert(args);
    args.IsValide =  Login.validarExistente(args.Value).value;
}

function Limpar(valor, validos) {
	// retira caracteres invalidos da string
	var result = "";
	var aux;
	for (var i=0; i < valor.length; i++) {
		aux = validos.indexOf(valor.substring(i, i+1));
		if (aux>=0) {
			result += aux;
		}
	}
	return result;
}
	
//Formata número tipo moeda usando o evento onKeyDown
//Exemplo: onkeydown="Formata(this,10,event,2)"
function Formata(campo,tammax,teclapres,decimal) {
	var tecla = teclapres.keyCode;
	vr = Limpar(campo.value,"0123456789");
	tam = vr.length;
	dec=decimal
	
	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }
	
	if (tecla == 8 )
		{ tam = tam - 1 ; }
	
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
	
		if ( tam <= dec )
			{ campo.value = vr ; }
	
		if ( (tam > dec) && (tam <= 5) ){
			campo.value = vr.substr( 0, tam - 2 ) + "," + vr.substr( tam - dec, tam ) ; }
			if ( (tam >= 6) && (tam <= 8) ){
				campo.value = vr.substr( 0, tam - 5 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ;
			}
			if ( (tam >= 9) && (tam <= 11) ){
				campo.value = vr.substr( 0, tam - 8 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ; }
			if ( (tam >= 12) && (tam <= 14) ){
				campo.value = vr.substr( 0, tam - 11 ) + "." + vr.substr( tam - 11, 3 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ; }
			if ( (tam >= 15) && (tam <= 17) ){
				campo.value = vr.substr( 0, tam - 14 ) + "." + vr.substr( tam - 14, 3 ) + "." + vr.substr( tam - 11, 3 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - 2, tam ) ;}
			}
	
}
