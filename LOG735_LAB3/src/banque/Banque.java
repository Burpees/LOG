package banque;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import succursales.Succursale;

public class Banque extends Thread {

	private int montantTotal = 0;
	private int port;
	private List<Succursale> listSuccursale;
	private ServerSocket serverSocket;
	
	public static void main(String[] args) throws IOException {
		System.out.println("EL BANKO");
		Banque banque = new Banque(11657);
		banque.start();
	}
	
	public Banque(int port) throws IOException {		
		this.port = port;
		listSuccursale = new ArrayList<Succursale>();
	}
	
	public void run() {
		
		try { 
			serverSocket = new ServerSocket(port); 
        } 
		catch (IOException e) { 
			System.err.println("On ne peut pas ecouter au  port: " + Integer.toString(port) + "."); 
			System.exit(1); 
        }
		
		while(true) {
			Socket clientSocket = null; 
			System.out.println ("Le serveur " + port + " est en marche, Attente de la connexion.....");

			
			try { 
				clientSocket = serverSocket.accept();
			} 
			catch (IOException e) 
		    { 
				System.err.println("Accept de " + port + " a echouer."); 
				System.exit(1); 
		    } 
			
			
//			//event but for client id
//			// EXIGENCE BANQUE - 01 et BANQUE - 06 (unique ID)
//			CommunicatorBanque communicator = new CommunicatorBanque(clientSocket, banque, getSuccursaleUniqueID());
//			communicator.start();
//			addSurccusale(communicator);
		}
		
	}
	
	public void addSurccusale(Succursale succ) {
		listSuccursale.add(succ);
    }
		
	public void addTotal(int montant){
		setTotal(getTotal() + montant);
	}
	
	public void setTotal(int total) {
		this.montantTotal = total;
	}
	
	public int getTotal() {
		return montantTotal;
	}
	
}
