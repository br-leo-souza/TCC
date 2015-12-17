package modelo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="visita")
public class Visita {
	@Column(name = "ip")
	private String ip;
	@Column(name = "data")
	private String data;
	@Id
	@GeneratedValue
	private int id;

	public Visita() {
		this.setData();
		this.setIP();
	}

	public Visita(String ip, String data) {
		this.ip = ip;
		this.data = data;
	}

	public void setIP() {
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void setData() {
        this.data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public String getIp() {
		return ip;
	}

	public String getData() {
		return data;
	}

}
