package com.trix.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BroadCastTCPServer {

    private static ArrayList<Socket> sockets = new ArrayList<Socket>();

    @Autowired
    EchoService echo;
	
    public void startServer(String portStr) throws IOException {
    	
        int port = Integer.parseInt(portStr);
        ServerSocket  serverSocket = new ServerSocket(port);

         System.out.println("Listening on IP:" +  serverSocket.getInetAddress().getHostAddress() + " Port:"+port);

         while(true){
            Socket connectionSocket = serverSocket.accept();
            sockets.add(connectionSocket);
            System.out.println("New client connection:" + connectionSocket.getRemoteSocketAddress());
            ClientHandler cl = new ClientHandler(connectionSocket);
            Thread clientThread = new Thread(cl);
            clientThread.start();
         }

    }

    public void sendAll() throws Exception{
        System.out.println("No of connections:" + sockets.size());
        for(Socket current: sockets){
            System.out.println(current.getRemoteSocketAddress());
           ObjectOutputStream out=new ObjectOutputStream (current.getOutputStream());
           out.flush();
           out.writeObject("Tata\n");
           out.flush();
           //out.close();
         }
    }

    class ClientHandler implements Runnable{

        Socket socket;
        
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            BufferedReader inFromClient = null;
            ObjectOutputStream out = null;
            BufferedWriter br = null;
            try {
                inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out=new ObjectOutputStream (socket.getOutputStream());
                OutputStreamWriter or= new OutputStreamWriter(out);
                br = new BufferedWriter(or);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            int i = 0;
			while (true) {
				String clientSentence = null;
				try {
					clientSentence = inFromClient.readLine();
					String str = ""+socket.getRemoteSocketAddress();
					String check = str.substring(str.lastIndexOf(":") + 1);
					if(i==0) {
//						AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//				    	echo = context.getBean(EchoService.class);
				    	System.out.println("Echo broad "+echo.test("hello"));
						System.out.println(socket.getRemoteSocketAddress());
						if(clientSentence.equals(check)) {
							out.writeObject("Autorised your client id is "+check);
						}
						else {
							out.writeObject("Not Autorised...");
							out.writeObject("Tata\n");
							out.flush();
							out.close();
						}
					}
					
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("" + echo.test(clientSentence));
            if (clientSentence.equalsIgnoreCase("fire")) {
                try {
                    sendAll();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                try {
                    br.write(clientSentence + "\n" );
                    br.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            i++;
            }
        }

    }

}