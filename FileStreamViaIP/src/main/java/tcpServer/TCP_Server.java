package tcpServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import util.FileUtility;

public class TCP_Server implements Runnable{

	private static int portNumber;
	private static String filePath;
	
	public TCP_Server(){
		
	}
	
	public TCP_Server(Integer portNumber,String filePath){
		TCP_Server.portNumber=portNumber;
		TCP_Server.filePath=filePath;
	}
	
	public static void receivedAsByte() throws Exception {
		ServerSocket ourFirstServerSocket = new ServerSocket(portNumber);
		System.out.println("Server is waiting new Clients1");
		while (true) {
			System.out.println("Server is waiting new Clients1");
			Socket connection = ourFirstServerSocket.accept();
			InputStream iStream = connection.getInputStream();
			DataInputStream dataStream = new DataInputStream(iStream);

			byte[] arr = readMessage(dataStream);
		//	FileUtility.writeBytes(arr, "C:/Users/User/Desktop/NicatSalmanovNew.jpg");
			String[] arrFilePath=filePath.split("\\.");
			String filePathNew=arrFilePath[0]+"new."+arrFilePath[1];
			
			FileUtility.writeBytes(arr, filePathNew);
		}
	}

	public static byte[] readMessage(DataInputStream din) throws IOException {
		int msgLength = din.readInt();
		System.out.println("message length1=" + msgLength);
		byte[] msg = new byte[msgLength];
		din.readFully(msg);
		return msg;
	}

	public static void receivedAsString() throws IOException {
		ServerSocket ourFirstServerSocket = new ServerSocket(6788);
		System.out.println("server1");
		while (true) {
			System.out.println("server1");
			Socket connection = ourFirstServerSocket.accept();
			System.out.println("server1");
			InputStream iStream = connection.getInputStream();
			InputStreamReader iStreamReader = new InputStreamReader(iStream);
			BufferedReader bReader = new BufferedReader(iStreamReader);
			System.out.println("server1");
			String messageFromClient = bReader.readLine();
			System.out.println("message from client " + messageFromClient);
		}
	}

	@Override
	public void run() {
		try {
			receivedAsByte();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
