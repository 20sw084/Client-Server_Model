import java.net.*;  
import java.io.*;  
class MyServer{  
public static void main(String args[])throws Exception{  
ServerSocket ss=new ServerSocket(3333);  
Socket s=ss.accept();
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  



ServerSocket ss1=new ServerSocket(3334);  
Socket s1=ss1.accept();  
DataInputStream din1=new DataInputStream(s.getInputStream());  
DataOutputStream dout1=new DataOutputStream(s.getOutputStream());  
BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));  
  
String str="",str2="";  
while(!str.equalsIgnoreCase("Stop")){  
str=din.readUTF();  
System.out.println("Client Says: "+str);  
str2=br.readLine();  
dout.writeUTF(str2);  
dout.flush();  

}

String str1="",str3="";
while(!str.equalsIgnoreCase("Stop")){  
    str=din1.readUTF();  
    System.out.println("Client Says: "+str);  
    str2=br1.readLine();  
    dout1.writeUTF(str2);  
    dout1.flush();  
} 
din.close();  
s.close();  
ss.close();  


din1.close();  
s1.close();  
ss1.close();  
}
}  
