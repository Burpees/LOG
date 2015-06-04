package succursales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
		Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
		try { 
			serverSocket = new ServerSocket(port); 
        }
		
		catch (IOException e) { 
			System.err.println("On ne peut pas écouter au port: " + Integer.toString(port) + "."); 
			System.exit(1); 
        }
		
		System.out.println ("Le serveur est en marche, écoute au port " + port + ", Attente de la connexion.....");
		
		try {
            echoSocket = new Socket("127.0.0.1", 11657);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Hôte inconnu: " + "127.0.0.1");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Ne pas se connecter au serveur: " + "127.0.0.1");
            System.exit(1);
        }
		
		 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        String userInput;
	        System.out.print ("Entrée: ");
	        try {
				while ((userInput = stdIn.readLine()) != null) {
					out.println(userInput);
					System.out.println("echo: " + in.readLine());
				    System.out.print ("Entrée: ");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        out.close();
	        try {
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				stdIn.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				echoSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
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
