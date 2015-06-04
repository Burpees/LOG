package succursales;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Succursale extends Thread {

	private int total;
	private int port;
	private ServerSocket serverSocket;
	
	public Succursale(int montantInitial) throws IOException {
		
		System.out.println( "solde : " + montantInitial );
		
//		listSuccursale = new ArrayList<TunnelSuccursale>();
		
		this.setTotal(montantInitial);

		this.port = (int) (5000 + (Math.random() * 5000));
		
		String banqueIP = "127.0.0.1";
		int BanquePort = 11657;
		Socket s = new Socket(banqueIP, BanquePort);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("EL SUCCURSALO");
		Succursale succursale = new Succursale((int)(Math.random() * 8000) + 2000);		
	
		succursale.start();
	}
	
	public void run() {
		try { 
			serverSocket = new ServerSocket(port); 
        }
		
		catch (IOException e) { 
			System.err.println("On ne peut pas écouter au port: " + Integer.toString(port) + "."); 
			System.exit(1); 
        }
		
		System.out.println ("Le serveur est en marche, écoute au port " + port + ", Attente de la connexion.....");
		
		while(true) {
			Socket clientSocket = null; 
			
			try { 
				clientSocket = serverSocket.accept(); 
				System.out.println("Connexion réussie, port : " + clientSocket.getPort());
			} 
			catch (IOException e) { 
				System.err.println("Connexion échouée, port : " + clientSocket.getPort()); 
				System.exit(1); 
		    } 
		}
	
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}
	
	public void ajouterArgent(int montant, int succid) {
		this.total += montant;
	}
}
