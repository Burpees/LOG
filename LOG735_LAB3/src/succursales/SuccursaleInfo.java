package succursales;

import java.io.Serializable;
import java.net.Socket;

public class SuccursaleInfo implements Serializable {

	private int port;
	private int montant;
	private int id;
	private Socket socket;	

	public SuccursaleInfo (int port, int montant, int id) {
		this.port = port;
		this.montant = montant;
		this.id = id;
		
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
