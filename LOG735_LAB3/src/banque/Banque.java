package banque;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import succursales.Succursale;
import succursales.SuccursaleInfo;

public class Banque{

	private int montantTotal = 0;
	private int port;
	public ArrayList<SuccursaleInfo> listSuccursale;
	private ArrayList<BanqueThread> listBanqueThread;
	private ServerSocket serverSocket;
	private static Banque banque;
	private int succursaleId = 1;
	
	public static void main(String[] args) throws IOException {
		System.out.println("EL BANKO");
		banque = new Banque(2002);
		banque.openConnection();		
	}
	
	public Banque(int port) throws IOException {		
		this.port = port;
		listSuccursale = new ArrayList<SuccursaleInfo>();
		listBanqueThread = new ArrayList<BanqueThread>();
	}
	
	public void openConnection() throws IOException{
		boolean isStopped = false;
		ServerSocket serverSocket = null; 

		try { 
			serverSocket = new ServerSocket(port); 
        } 
		
		catch (IOException e) 
        { 
			System.err.println("On ne peut pas écouter au  port: 10118."); 
			System.exit(1);
			isStopped = true;
        } 

		System.out.println ("Le serveur est en marche, Attente de la connexion.....");
		Socket clientSocket = null;
		
		while (!isStopped)
		{
			try { 
				clientSocket = serverSocket.accept(); 
		    } 
			catch (IOException e) 
		    { 
				System.err.println("Accept a échoué."); 
				System.exit(1); 
		    } 
			
			BanqueThread banqueThread = new BanqueThread(clientSocket, banque);
			banqueThread.start();
			listBanqueThread.add(banqueThread);
			for ( int i = 0; i < listBanqueThread.size(); i ++ ) {
				listBanqueThread.get(i).updateListSuccursale(listSuccursale);
			}
		}
	}
	
	public void addSurccusale(SuccursaleInfo succ) {
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
	
	public int generateSuccursaleId(){
		return succursaleId ++;
	}
}
