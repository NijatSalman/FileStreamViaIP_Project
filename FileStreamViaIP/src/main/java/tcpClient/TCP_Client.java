package tcpClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import implementations.TCP_Client_Inter;
import util.FileUtility;

public class TCP_Client implements TCP_Client_Inter {
	
	private static int portNumber;
	private static String ip;
	private static String filePath;
	
	
	
	public TCP_Client() {
		// TODO Auto-generated constructor stub
	}
	
	public TCP_Client(Integer portNumber, String ip,String filePath){
		TCP_Client.portNumber=portNumber;
		TCP_Client.ip=ip;
		TCP_Client.filePath=filePath;
	}
	
	public static void sendAsString() throws  IOException{
		Socket socket = new Socket("localhost", 6788);

		OutputStream outPutStream = socket.getOutputStream();
		DataOutputStream dataOutPutStream = new DataOutputStream(outPutStream);
		dataOutPutStream.write("Hi Server,I am coming".getBytes());
		socket.close();	
	}
	
	public static void sendAsBytes() throws Exception{
		Socket socket = new Socket(ip, portNumber);
		OutputStream outPutStream = socket.getOutputStream();
		DataOutputStream dataOutPutStream = new DataOutputStream(outPutStream);
		
	//	byte[] bytes=FileUtility.readBytes("C:/Users/User/Desktop/NicatSalmanov.jpg");
		byte[] bytes=FileUtility.readBytes(filePath);
		dataOutPutStream.writeInt(bytes.length);
		dataOutPutStream.write(bytes);
		outPutStream.write("Hi Server,I am coming".getBytes());
		socket.close();	
	}

	@Override
	public void run() {
		try {
			 Thread.sleep(5000);
			sendAsBytes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
