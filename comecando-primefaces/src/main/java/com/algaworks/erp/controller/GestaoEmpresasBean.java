package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.service.CadastroEmpresaService;
import com.algaworks.erp.util.FacesMessages;
@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;
	
	@Inject
	private CadastroEmpresaService service;
	
	@Inject
	private FacesMessages messages;
	
	private Empresa empresaEdicao = new Empresa();
	
	private List<Empresa> todasEmpresas;
	
	private Empresa empresaSelecionada;
	
	public void prepararNovoCadastro(){
		empresaEdicao = new Empresa();
	}
	
	public void consultar() {
		todasEmpresas = empresas.todas();
	}
	
	public void salvar(){
		service.salvar(empresaEdicao);
		consultar();
		messages.info("Empresa salva com sucesso");
		
		messages.update("frm:msgs","frm:table-empresa");
	}
	
	public void excluir(){
		service.excluir(empresaSelecionada);
		todasEmpresas.remove(empresaSelecionada);
		empresaSelecionada = null;
		messages.info("Empresa excluida com sucesso!");
	}

	public List<Empresa> getTodasEmpresas() {
		return todasEmpresas;
	}
	
	public TipoEmpresa [] getTiposEmpresas(){
		return TipoEmpresa.values();
	}
	
	public Empresa getEmpresaEdicao() {
		return empresaEdicao;
	}
	
	public void setEmpresaEdicao(Empresa empresaEdicao) {
		this.empresaEdicao = empresaEdicao;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	
	
}
