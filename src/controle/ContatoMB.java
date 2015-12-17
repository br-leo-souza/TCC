package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@ManagedBean(name="ContatoMB")
@RequestScoped
public class ContatoMB {
	private String mail;
	private String nome;
	private String assunto;
	private String mensagem;
	
	public ContatoMB() {
	}
	
	@SuppressWarnings("deprecation")
	public String enviar() throws EmailException{
	   SimpleEmail email = new SimpleEmail();
	   //Utilize o hostname do seu provedor de email
	   System.out.println("alterando hostname...");
	   email.setHostName("br236.hostgator.com.br");
	   //Quando a porta utilizada não é a padrão (gmail = 465)
	   email.setSmtpPort(465);
	   //Adicione os destinatários
	   email.addTo("orbitacrista@gmail.com", "Órbita Cristã");
	   //Configure o seu email do qual enviará
	   //email.setFrom("seuemail@seuprovedor.com", "Seu nome");
	   email.setFrom(mail, nome);
	   //Adicione um assunto
	   email.setSubject(assunto);
	   //Adicione a mensagem do email
	   email.setMsg(mensagem);
	   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
	   System.out.println("autenticando...");
	   email.setSSL(true);
	   email.setAuthentication("orbitacrista@orbitacrista.com.br", "^!s@s~G[&T)7");
	   System.out.println("enviando...");
	   
	   try {
		   email.send();
		   System.out.println("Email enviado!");
	   } catch (Exception e) {
		   System.out.println(e.getMessage());
	   }
	   return null;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
