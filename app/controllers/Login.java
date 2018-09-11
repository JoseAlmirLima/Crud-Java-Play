package controllers;

import models.*;

import play.Logger;
import play.db.jpa.JPA;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import controllers.UsuarioController;
import controllers.Secure.Security;

public class Login extends Controller  {
	
	
	//metodo para tentar logar
	
	public static void tentaLogin(Usuario u) throws Throwable {
		/** Tenta logar:
		 * @param user
		 * @param password
		 */
		
        List<BigInteger> rs =JPA.em().createNativeQuery("select id from usuario").getResultList();
		
		List<Usuario> usuarios = new ArrayList<>();
		for(BigInteger id : rs){
			Usuario user = Usuario.findById(id.longValue());
			
			usuarios.add(user);
		}

		
				
		//verifica se o usuario tem permissao
		Usuario usu = Usuario.find("byEmailAndSenha", u.email, u.senha)
				.first();
		
		
		
			if (usu != null && usu.status ==true && usu.ativo ==true){
				session.put("usuario", usu.id.toString());
				flash.success("Olá %s ", usu.nome);
				render("Application/tela.html", usuarios, usu);
				
				
				
			}
			
			     //verifica se tem permissão
			
			if (usu != null && usu.status == false && usu.ativo==false){
				
				flash.error("Você não tem permissão para acessar!");
				form();
		
			}
			
			//verifica se o usuario é ativo ou não
			
            if (usu != null && usu.status == true && usu.ativo==false){
				
				flash.error("Você não tem permissão para acessar!");
				form();
		
			}
            
            //verifica se o usuario é ativo
            
            if (usu != null && usu.status == false && usu.ativo==true){
				
            	session.put("usuario", usu.id.toString());
				flash.success("Olá %s ", usu.nome);
				render("Application/tela.html", usuarios);
		
			}
		
   	else {
			
			flash.error("Login/senha Incorretos");
			form();
		}

		
        
		
	}
	
	

	//Exibe o formulario de login
	
	public static void form() {
		
		 
		render("Application/login.html");
		
	}

	//remove os dados do login
	
	public static void logout() throws Throwable {
		
		
        UsuarioController.logout();
      
				
        
	}

}




