package banque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import succursales.Succursale;
import succursales.SuccursaleInfo;

public class BanqueThread extends Thread{
	private int port;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Banque banque;
	private int succursaleUniqueID = 0;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	public BanqueThread(Socket clientSocket, Banque banque){
		this.clientSocket = clientSocket;
		this.banque = banque;
	}
	
	public void run() {			
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		try {
			
			//outputStream.writeObject(banque.getSuccursaleId());
			SuccursaleInfo infoSucc;

			while((infoSucc = (SuccursaleInfo)inputStream.readObject()) != null){
			
				//String[] Infos = infoSucc.split("-");
				banque.addSurccusale(infoSucc);
	//			int montantSuccursale = Integer.parseInt(Infos[2]);
	//			banque.addTotal(montantSuccursale);
	//			System.out.println ("Succursale: " + montantSuccursale);
	//			System.out.println ("Banque: " + banque.getTotal());
				System.out.println( "AJoute avec succes");
				
			}
			
		} catch (Exception e) {
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
