package com.FileStreamViaIP;

import java.util.Scanner;

import implementations.FactoryClient;
import implementations.TCP_Client_Inter;
import tcpClient.TCP_Client;
import tcpServer.TCP_Server;

/**
 * Hello world!
 * @author NijatSalmanov
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Please type your name");
        String name=sc.nextLine();
        System.out.println("Please type your surname");
        String surname=sc.nextLine();
        
        System.out.println("Please type file path which you want to sent."
        		+ "You must enter format like this 'C:/Users/User/Desktop/qeydlerim.txt' ");
        String filePath=sc.nextLine();
        
        System.out.println("Please type Client's IP and Port"
        		+ "You must enter format like this 'localhost:5678' ");
        String ipAndPortPath=sc.nextLine();
        
        String[] arr = ipAndPortPath.split(":");
        String ip = arr[0];
        int portNumber = Integer.parseInt(arr[1]);
        
//        TCP_Client_Inter tcpDemo=new TCP_Client(portNumber,ip,filePath);
//        FactoryClient fClientObject=new FactoryClient();
//        
        		
        Thread thrdServer=new Thread(new TCP_Server(portNumber,filePath));
        Thread thrdClient=new Thread( new TCP_Client(portNumber,ip,filePath));
      thrdServer.start();
      thrdClient.start();
        
    }
}
