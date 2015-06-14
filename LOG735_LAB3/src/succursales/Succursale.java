package succursales;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Succursale extends Thread{

	private int montant;
	private int port;
	private int id;
	private static Succursale succursale;
	public String name;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	public ArrayList<SuccursaleInfo> listSuccursale;
	
	public static void main(String[] args) throws IOException {
		System.out.println("EL SUCCURSALO");
		succursale = new Succursale((int)(Math.random() * 8000) + 2000);		
		succursale.name = "name1";
		succursale.start();
	}
	
	public Succursale(int montantInitial) throws IOException {
		
		System.out.println( "solde : " + montantInitial );
		
		this.setMontant(montantInitial);

		this.port = (int) (5000 + (Math.random() * 5000));
	}	
	
	public void run() {
		Socket echoSocket = null;
		
		try {
            echoSocket = new Socket("127.0.0.1", 2002);
            
            outputStream = new ObjectOutputStream(echoSocket.getOutputStream());
            inputStream = new ObjectInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Hôte inconnu: " + "127.0.0.1");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Ne pas se connecter au serveur: " + "127.0.0.1");
            System.exit(1);
        }
		 
        try {
        	
//        	int id = (Integer)inputStream.readObject();        	
//        	succursale.id = id;
        	
//        	String infoSucc = port + "-" + id + "-" + montant + "-" + "127.0.0.1";
        	id = (Integer)inputStream.readObject();
        	SuccursaleInfo succInfo = new SuccursaleInfo(port, montant, id);
        	outputStream.writeObject(succInfo);
        	//listSuccursale = (ArrayList<SuccursaleInfo>)inputStream.readObject();
         	
        	Object o;
        	while ((o = inputStream.readObject()) != null)
        	{
        		if (o instanceof List<?>)
        		{
        			listSuccursale = (ArrayList<SuccursaleInfo>)o;
        			
        			for (int i = 0; i < listSuccursale.size(); i ++ ) {
        				System.out.println(listSuccursale.get(i).getId());
        			}
        		}
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}   
        
        try {
        	//outputStream.close();
        	//inputStream.close();
        	//echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getMontant() {
		return montant;
	}
	
	public void ajouterArgent(int montant, int succid) {
		this.montant += montant;
	}
}
