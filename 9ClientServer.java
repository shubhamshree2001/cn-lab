import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter file name:");
		String fname=s.next();
		Socket socket=new Socket("127.0.0.1",4000);
	
		OutputStream osStream=socket.getOutputStream();
		PrintWriter pwWriter=new PrintWriter(osStream,true);
		pwWriter.println(fname);
		
		InputStream inStream=socket.getInputStream();
		Scanner sin=new Scanner(inStream);
		while(sin.hasNext())
			System.out.println(sin.next());
	}
}

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Server ready for communication");
		ServerSocket serverSocket=new ServerSocket(4000);
		Socket socket=serverSocket.accept();
	
		InputStream iStream=socket.getInputStream();
		Scanner sin=new Scanner(iStream);
		String fname=sin.next();
		
		OutputStream oStream=socket.getOutputStream();
		PrintWriter pWriter=new PrintWriter(oStream,true);
		File file=new File(fname);
		Scanner fin=new Scanner(file);
		while(fin.hasNext())
			pWriter.println(fin.next());
		System.out.println("Connection is successful and file contents are displayed in the client window");
	}
}
