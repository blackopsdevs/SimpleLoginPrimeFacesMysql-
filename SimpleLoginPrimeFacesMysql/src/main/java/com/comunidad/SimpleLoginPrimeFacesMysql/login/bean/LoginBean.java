package com.comunidad.SimpleLoginPrimeFacesMysql.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.comunidad.SimpleLoginPrimeFacesMysql.login.dao.vo.LoginVo;
import com.comunidad.SimpleLoginPrimeFacesMysql.login.service.LoginService;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7563701052367927275L;

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	private String usuario;
	private String password;
	
	private LoginVo loginVo = new LoginVo();

	private LoginService loginService = new LoginService();

	@PostConstruct
	public void init() {
		usuario = "";
		password = "";
	}

	public void login() {
		System.out.println("login()");
		try {
			if (usuario.trim().isEmpty() && password.trim().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN", "Datos incorrectos."));
			}
			loginVo.setUsername(usuario);
			loginVo.setPassword(password);
			if (loginService.buscar(loginVo) != 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Datos correctos!!!!!."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN", "Datos incorrectos."));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error-> " + ex.getMessage()));
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
