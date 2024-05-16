package usn.obj2100.client;

import usn.obj2100.shared.Constants;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Search;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
/**
 * Denne klassen styrer nettverkskommunikasjonen mellom klienten og serveren i applikasjonen.
 * Den håndterer sending av forespørsler og mottak av svar ved å serialisere objekter over nettverket.
 * Kommunikasjonen involverer ulike typer kommandoer og objekter, avhengig av brukerens handlinger og behov.
 */
public class Client
{
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	/**
	 * Konstruktør som forsøker å opprette en forbindelse med serveren ved å bruke en forhåndsdefinert port.
	 * Denne forbindelsen vil prøve å koble til serveren kontinuerlig til den lykkes.
	 */
	public Client()
	{
		int port = Constants.PORT;
		
		while (true)
		{
			try
			{
				System.out.println("Prøver å koble på " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
				socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				break;
			}
			catch (IOException error)
			{
				System.out.println("Server slo seg av!");
				error.printStackTrace(System.err);
			}
		}
	}
	
	/**
	 * Request serveren for å søke etter ett eller flere objekter.
	 * <p/>
	 * Eksempel bruk:
	 * {@snippet id = "requestObject" lang = "java" group = "clientRequest":
	 * 	import usn.obj2100.shared.model.Inventar;
	 *
	 * 	Client client = new Client();
	 * 	// Denne er tom, og ville ikke faktisk funket, men ellers ok.
	 * 	Inventar inventar = new Inventar();
	 * 	client.request(Command.CREATE, inventar);
	 *}
	 *
	 * @param command The enum C(R)UD command.
	 * @param object The object being sent.
	 */
	public Object request(Command command, Object object)
	{
		try
		{
			objectOutputStream.writeObject(command);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	/**
	 * Request serveren for å få tak i alle objekter i databasetabellen som
	 * tilsvarer enum Type.
	 * <p/>
	 * Eksempel bruk:
	 * {@snippet id="requestType" lang="java" group="clientRequest":
	 * 	Client client = new Client();
	 * 	client.request(Type.INVENTAR);
	 * }
	 *
	 * @param type Hvilken type objekt du vil hente.
	 * @return Returnerer en liste av objekter av gitt type.
	 */
	public Object request(Type type)
	{
		try
		{
			objectOutputStream.writeObject(Command.READALL);
			objectOutputStream.writeObject(type);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	/**
	 * Request serveren for å søke etter ett eller flere objekter.
	 * <p/>
	 * Eksempel bruk:
	 * {@snippet id="requestSearch" lang="java" group="clientRequest":
	 * 	Client client = new Client();
	 * 	Search searchQuery = new Search("Basisk søk");
	 * 	client.request(searchQuery);
	 * }
	 *
	 * @param query Search objektet som sendes til serveren.
	 * @return Returnerer et søkerespons objekt fra serveren.
	 */
	public Object request(Search query)
	{
		try
		{
			objectOutputStream.writeObject(Command.SEARCH);
			objectOutputStream.writeObject(query);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}
	/**
	 * Sjekker om klienten fortsatt er tilkoblet serveren.
	 *
	 * @return {@code true} hvis forbindelsen er aktiv, ellers {@code false}.
	 */
	public boolean isConnected()
	{
		return !socket.isClosed();
	}
	/**
	 * Lukker forbindelsen til serveren. Dette skal kalle når klienten skal avslutte.
	 */
	public void disconnect()
	{
		try
		{
			socket.close();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
	}
}
