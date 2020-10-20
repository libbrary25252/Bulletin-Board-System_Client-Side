import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


import java.util.*;

public class BulletinBoardClient{
    
    public static void main(String [] arg) {
        
        PrintWriter out;
        BufferedReader br;
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
            Socket socket = new Socket(ip,port);
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
                        }
                        while(input.length()!=1&&!input.contains("."));
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
            // out.print("POST\nsome text! OK\nmore text.\n.\n"); //send to server
            // out.flush();
            // receiveServer(size,charArray,input,br);
            // out.print("POST\nsome text!\n.\n");
            // out.flush();
            // receiveServer(size,charArray,input,br);
            // out.print("READ\n");
            // out.flush();
            // receiveServer(size,charArray,input,br);
            // out.print("QUIT\n");
            // out.flush();
            // receiveServer(size,charArray,input,br);
            br.close();
            socket.close();
        }catch (ConnectException ce){
            System.out.println("Connect status: fail");
        }catch (IOException e) {
		    System.out.println(e);
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
