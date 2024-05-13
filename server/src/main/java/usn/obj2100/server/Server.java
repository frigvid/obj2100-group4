package usn.obj2100.server;

import usn.obj2100.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Server
{
	private static final Set<PrintWriter> clients = new HashSet<>();
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Server is running...");
		
		try
			(
				ServerSocket listener = new ServerSocket(Constants.PORT, 50, InetAddress.getLocalHost())
			)
		{
			while (true)
			{
				new ClientHandler(listener.accept()).start();
			}
		}
	}
	
	private static class ClientHandler extends Thread
	{
		private final Socket socket;
		private PrintWriter out;
		
		public ClientHandler(Socket socket)
		{
			this.socket = socket;
		}
		
		public void run()
		{
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				clients.add(out);
				
				Scanner in = new Scanner(socket.getInputStream());
				while (in.hasNextLine())
				{
					String message = in.nextLine();
					
					for (PrintWriter client : clients)
					{
						client.println(message);
					}
				}
			}
			catch (IOException error)
			{
				throw new RuntimeException(error);
			}
			finally
			{
				if (out != null)
				{
					clients.remove(out);
				}
				
				try
				{
					socket.close();
				}
				catch (IOException error)
				{
					System.out.println("Something unexpected happened!");
					error.printStackTrace(System.err);
				}
			}
		}
	}
}
