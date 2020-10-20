import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


import java.util.*;

public class BulletinBoardClient{
    
    public static void main(String [] arg) {
        
        PrintWriter out = null;
        BufferedReader br = null;
        Socket socket = null;
        String ip;
        int port = 16000;
        int size = 0;
        Scanner sr = new Scanner(System.in);
        char charArray[] = new char[1024];
        String input = "";
        String postInput="";
        String cType;
        boolean quit = false;
        System.out.println("Input the IP address:"); 
        ip = sr.nextLine();

        try{ //open socket and connect 
            System.out.println("IP address: "+ip+"      Port Number:"+port);
            socket = new Socket(ip,port);
            out = new PrintWriter(socket.getOutputStream(),true); 
            br = new BufferedReader (new InputStreamReader(socket.getInputStream()));
            System.out.println(socket.isConnected());

            if(socket.isConnected()){
                System.out.println("Connect status: success");
            }else{
                System.out.println("Connect status: fail");
            }
            while(quit == false){
                cType = sr.nextLine();
                switch(cType){
                    case "POST":
                        System.out.print("client: ");
                        postInput="";
                        postInput+=cType+"\n";
                        do{
                            input = sr.nextLine();
                            postInput+=input+"\n";
                            if(input.length()==1&&input.contains(".")){
                                break;
                            }
                        }
                        while(input.length()>=1);
                        out.print(postInput+"\n");
                        out.flush();
                        receiveServer(size,charArray,input,br);
                        break;
                    case "READ":
                        out.print(cType+"\n");
                        out.flush();
                        receiveServer(size,charArray,input,br);
                        break;
                    case "QUIT":
                        out.print(cType+"\n");
                        out.flush();
                        receiveServer(size,charArray,input,br);
                        quit = true;
                        break;
                    default:
                        out.print(cType+"\n");
                        out.flush();
                        receiveServer(size,charArray,input,br);
                        break;
                }
            }
           
        }catch (ConnectException ce){
            System.out.println("Connect status: fail");
        }catch (IOException e) {
		    System.out.println(e);
		}finally{
            try{
                out.close();
                br.close();
                socket.close();
            }catch (IOException e) {
                System.out.println(e);
            }
        } 
    }

    public static void receiveServer(int size, char[] charArray, String input, BufferedReader br){
        try{
            while((size=br.read(charArray)) >=0)  //receive server output
            {
                input = String.valueOf(charArray, 0, size);
                System.out.println("Server: "+input);
                if(input.contains("\0")&& input.contains(".")|| input.contains("ERROR") ||input.contains("\0")&& input.contains("OK")){ //check the message is contain ok and null and error
                    break;
                }
            }
        }catch (IOException e) {
		    System.out.println(e);
		}   
    }
}
