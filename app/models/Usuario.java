package models;

import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.mvc.With;

import java.util.*;

import javax.persistence.*;

import controllers.Secure;


@Entity
public class Usuario extends Model {
	

	public String nome;
	
	public String email;
	
	public String telefone;

	public String senha;
	
	@Column(nullable = false, columnDefinition="boolean default true")
	public boolean status;
	
	@Column(nullable = false, columnDefinition="boolean default true")
	public boolean ativo;
	
	
	
    public Usuario () {
	   
	   	   
   }
   
   
   public Usuario(String nome, String email, String telefone, String senha, boolean status, boolean ativo) {
	   
	   this.nome = nome;
	   this.email = email;
	   this.telefone = telefone;
       this.senha = senha;
       this.status = status;
       this.ativo = ativo;
	   
	 }

   

  public String getNome() {
	return nome;
}


  public void setNome(String nome) {
	this.nome = nome;
}


  public String getEmail() {
	return email;
}


  public void setEmail(String email) {
	this.email = email;
}


  public String getTelefone() {
	return telefone;
}


  public void setTelefone(String telefone) {
	this.telefone = telefone;
}


public String getSenha() {
	return senha;
}


public void setSenha(String senha) {
	this.senha = senha;
}

public boolean getStatus() {
	return status;
}


 public void setStatus(boolean status) {
	this.status = status;
	
}

 
 public boolean getAtivo() {
		return ativo;
	}


	 public void setAtivo(boolean ativo) {
		this.ativo = ativo;
		
	 }	

public static boolean Autenticar(String ususenha, String usuemail){
	// TODO Auto-generated method stub
	return false;
}


public static void cadastro(Usuario usu) {

	
}


public static Object connect(String email, String senha) {
	// TODO Auto-generated method stub
	return null;
}


public static Usuario valueOf(String string) {
	// TODO Auto-generated method stub
	return null;
}









   
 //public Usuario(String nome, String email, String telefone, String email) {
	   
	//   this.nome = nome;
	//   this.email = email;
	 //  this.telefone = telefone;//
	 //  this.email = email;
	   
	// }
   
   
  
   
	
	
}