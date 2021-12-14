import java.net.*;  
import java.io.*;  

class MyServer{  
    boolean isRead;
    String sender;
    String reciever;
    String body;
    int portNumber;
public MyServer(int portNumber) throws IOException{   
    this.portNumber=portNumber;
}
public static void main(String args[])throws Exception{
    ServerSocket ss=new ServerSocket(3333);  
    Socket s=ss.accept();
    DataInputStream din=new DataInputStream(s.getInputStream());  
    DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

    String str="",str2="";
    while(!str.equalsIgnoreCase("Stop")){  
    str=din.readUTF();  
    System.out.println("Client Says: "+str);  
    str2=br.readLine();  
    dout.writeUTF(str2);  
    dout.flush();  
    }
    din.close();  
    s.close();  
    ss.close();   
    }
}  