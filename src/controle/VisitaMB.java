package controle;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.VisitaDAO;
import modelo.Visita;

@ManagedBean(name="VisitaMB")
@RequestScoped
public class VisitaMB {
	
	private Visita visita;
	private VisitaDAO dao = new VisitaDAO();
	private List<Visita> visitas = new ArrayList<Visita>();
	private String ip;
	private String data;

	public VisitaMB(){
		
		this.setVisitas(dao.ListarTodas());
		this.visita = new Visita();
		this.visita.setIP();
		this.visita.setData();
	}
	
	public void cadastrar(){
		dao.inserir(visita);
	}
	
	public String listar(){
		return "lista";
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
	
	public String inicio(){
		return "index";
	}
	
	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public String getIp() {
		return ip;
	}

	public void setIp() {
		try {
			this.ip = (InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String getData() {
		return data;
	}

	public void setData() {
		this.data = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

}
