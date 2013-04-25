package br.com.company.gwt.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImagensResources extends ClientBundle{

	public static final ImagensResources INSTANCE =  GWT.create(ImagensResources.class);

	@Source("br/com/company/gwt/client/resources/iconAdicionar24.png")
	ImageResource iconAdicionar24();	

	@Source("br/com/company/gwt/client/resources/iconEditar24.png")
	ImageResource iconEditar24();
	
	@Source("br/com/company/gwt/client/resources/iconDelete24.png")
	ImageResource iconDelete24();
	
	@Source("br/com/company/gwt/client/resources/iconPesquisa24.png")
	ImageResource iconPesquisa24();
	
	@Source("br/com/company/gwt/client/resources/cancelar_16.png")
	ImageResource cancelar16();
	
	@Source("br/com/company/gwt/client/resources/salvar_16.png")
	ImageResource salvar16();
	
	@Source("br/com/company/gwt/client/resources/refresh_24.png")
	ImageResource refresh24();
	
	@Source("br/com/company/gwt/client/resources/report_24.png")
	ImageResource report24();
	
	@Source("br/com/company/gwt/client/resources/chave_icone_16.png")
	ImageResource chaveIcone16();
	
	@Source("br/com/company/gwt/client/resources/icone_form_16.png")
	ImageResource iconeForm16();

	@Source("br/com/company/gwt/client/resources/icone_report_16.png")
	ImageResource iconeReport16();
	
	@Source("br/com/company/gwt/client/resources/icone_venda_16.png")
	ImageResource iconeVenda16();
	
	@Source("br/com/company/gwt/client/resources/icone_impressora_16.png")
	ImageResource iconeImpressora16();
	
	@Source("br/com/company/gwt/client/resources/icone_movimento_16.png")
	ImageResource iconeMovimento16();
	
	@Source("br/com/company/gwt/client/resources/icone_configuracao_16.png")
	ImageResource iconeConfiguracao16();
	
	@Source("br/com/company/gwt/client/resources/icone_caixa_16.png")
	ImageResource iconeCaixa16();
	
	@Source("br/com/company/gwt/client/resources/icone_confirma_16.png")
	ImageResource iconeConfirma16();
	
	@Source("br/com/company/gwt/client/resources/icone_adiciona_16.png")
	ImageResource iconeAdiciona16();
	
	@Source("br/com/company/gwt/client/resources/icone_remove_16.png")
	ImageResource iconeRemove16();
	
	@Source("br/com/company/gwt/client/resources/atalho_caixa_64.png")
	ImageResource atalhoCaixa64();
	
	@Source("br/com/company/gwt/client/resources/icone_refresh_16.png")
	ImageResource iconeRefresh16();
	
	@Source("br/com/company/gwt/client/resources/icone_pesquisa_16.png")
	ImageResource iconePesquisa16();

	@Source("br/com/company/gwt/client/resources/icone_abrir_caixa_16.png")
	ImageResource iconeAbrirCaixa16();
	
	@Source("br/com/company/gwt/client/resources/icone_fechar_caixa_16.png")
	ImageResource iconeFecharCaixa16();
	
	@Source("br/com/company/gwt/client/resources/icone_cliente_16.png")
	ImageResource iconeCliente16();
	
}