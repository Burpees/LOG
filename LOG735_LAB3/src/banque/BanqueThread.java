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
import java.util.Iterator;

import succursales.Succursale;
import succursales.SuccursaleInfo;

public class BanqueThread extends Thread{
	private int port;
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
			outputStream.writeObject(banque.generateSuccursaleId());
			infoSucc = (SuccursaleInfo)inputStream.readObject();
			infoSucc.setSocket(clientSocket);
			
			//for (Iterator<SuccursaleInfo> i = banque.listSuccursale.iterator(); i.hasNext();)
			//{
			//	SuccursaleInfo s = i.next();				
			//}			
				//String[] Infos = infoSucc.split("-");
				banque.addSurccusale(infoSucc);
				outputStream.writeObject(banque.listSuccursale);
				//outputStream.writeObject(banque.listSuccursale);
	//			int montantSuccursale = Integer.parseInt(Infos[2]);
	//			banque.addTotal(montantSuccursale);
	//			System.out.println ("Succursale: " + montantSuccursale);
	//			System.out.println ("Banque: " + banque.getTotal());
				System.out.println( "AJoute avec succes");
				System.out.println(infoSucc.getMontant());
				System.out.println(infoSucc.getId());
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		 
		try {
			inputStream.close();
			outputStream.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    }
	
	public void updateListSuccursale()
	{}
	
}
