package controllers;

import play.db.jpa.JPA;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.mvc.results.Result;

import java.math.BigInteger;
import java.util.*;

import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

import controllers.Secure.Security;

import javax.persistence.*;


import models.Usuario;

@With(Secure.class)
public class UsuarioController extends Controller {
	
	
	//atualiza a lista na tabela
	
	public void atualizarLista(){
	    List<BigInteger> rs =JPA.em().createNativeQuery("select id from usuario").getResultList();
		
		List<Usuario> usuarios = new ArrayList<>();
		for(BigInteger id : rs){
			Usuario user = Usuario.findById(id.longValue());
			usuarios.add(user);
		}
		
		if (isConnected() ==  true) {
			String id = session.get("usuario"); 
			Usuario usu = Usuario.find("byId", Long.valueOf(id)).first(); // Busca o usuário na base de dados e coloca na varivel usu 
			render("Application/tela.html", usuarios, usu);
			
		}
		String id = session.get("usuario"); 
		Usuario usu = Usuario.find("byId", Long.valueOf(id)).first(); // Busca o usuário na base de dados e coloca na variavel usu
		render("Application/tela.html", usuarios, usu);
		
		
		 
	}
	
	//atualiza a lista na tabela
	
	public void atualizarTabela(){
	    List<BigInteger> rs =JPA.em().createNativeQuery("select id from usuario").getResultList();
		
		List<Usuario> usuarios = new ArrayList<>();
		for(BigInteger id : rs){
			Usuario user = Usuario.findById(id.longValue());
			
			usuarios.add(user);
		}

		
		
		
		String id = session.get("usuario"); 
		Usuario usu = Usuario.find("byId", Long.valueOf(id)).first(); // Busca o usuário na base de dados e coloca na variavel usu
		render("Application/tela.html", usuarios, usu);
		
		 
	}
	
	//adiciona um novo usuario
	
	public void  addUsuario(String nome, String email, String telefone, boolean status, String senha, boolean ativo) {
		if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()){
		Usuario usuario =  new Usuario();
	    usuario.setNome(nome);
	    usuario.setEmail(email);
	    usuario.setTelefone(telefone);
	    usuario.status=status;
	    usuario.senha=senha;
	    usuario.ativo=true;
	    usuario.save();
	    flash.success("Tabela de Usuários atualizada!");
	    
		}
		
		//atualiza a tabela assim que adicionar o novo usuario
		atualizarTabela();

		
	}
	
      //deleta um usuario
	public void deletarUsuario(Long id, String email, boolean ativo, boolean status ) {
		
        
		
		Usuario usuario = Usuario.findById(id);
			
			
					
		int iduser = Integer.valueOf(session.get("usuario"));
		
		
		if(usuario.email.equals("admin@admin")) {
			
		flash.error("Operação Negada!");
		
		atualizarTabela();
		}
		else if(iduser != id) {
			
			
			
			usuario.delete();
			flash.success("Tabela de Usuários atualizada!");
			atualizarTabela();
		}   
		
		else  {
			
			flash.error("Operação Negada!");
			
		}
		
		atualizarTabela();//atualiza a lista
		
		
		}
		
	 
	  
	
	//altera o usuario
	public void alterarUsuario(Long id) {
		Usuario usuario =  Usuario.findById(id);

		render("Application/alterarCliente.html", usuario);
		
		
	}

	//atualiza o usuario
	public void atualizarUsuario(Long id, String nome, String email, String telefone, boolean status, String senha, boolean ativo) {
		
		Usuario usuario = Usuario.findById(id);
		usuario.nome=nome;
		usuario.email=email;
		usuario.telefone=telefone;
		usuario.senha = senha;
		usuario.ativo = ativo;
		usuario.save();
		flash.success("Tabela de Usuários atualizada!");
		atualizarTabela();
		
		
		
		
	}
	
	 public static void logout() throws Throwable {
	        Security.invoke("onDisconnect");
	        session.clear();
	        response.removeCookie("rememberme");
	        Security.invoke("onDisconnected");
	        render ("/application/login.html");
	    }
	
	 static boolean isConnected() {
         return session.contains("usuario");
     }
      
	
}
