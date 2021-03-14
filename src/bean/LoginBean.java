package bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



@SessionScoped
@ManagedBean(name="LoginBean")
public class LoginBean extends BaseBean{

	private String login, senha;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String logar() {
		
		if(login.equals("userAdmin") && senha.equals("userAdmin")) {
			
			HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute("usuarioLogado", login);

			return "/index?faces-redirect=true";
			
		}else {
			addMessage("Usuário ou senha inválidos.", "", "error");
			return "";
		}
	}

	public String logout() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		sessao.invalidate();
		return "/login?faces-redirect=true"; //Tela inicial
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
