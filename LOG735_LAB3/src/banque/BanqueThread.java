package banque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import succursales.Succursale;

public class BanqueThread extends Thread{
	private int port;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Banque banque;
	private int succursaleUniqueID = 0;
	private PrintWriter out;
	private BufferedReader in;
	
	public BanqueThread(Socket clientSocket, Banque banque){
		this.clientSocket = clientSocket;
		this.banque = banque;
	}
	
	public void run() {			
		try {
			out = new PrintWriter(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
		Succursale succursale;
		
		try {
			out.write(banque.getSuccursaleId());
			String infoSucc = in.readLine();
			String[] Infos = infoSucc.split("-");
			banque.addSurccusale(infoSucc);
			int montantSuccursale = Integer.parseInt(Infos[2]);
			banque.addTotal(montantSuccursale);
			System.out.println ("Succursale: " + montantSuccursale);
			System.out.println ("Banque: " + banque.getTotal());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		 
		/*try {
			objOutStream.close();
			objInStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
    }
}
