import java.sql.*;
import java.util.regex.*;
import java.util.*;
import java.net.*;
import java.security.*;
import java.io.*;  

class MyClient{  
    String IP;
    String portNumber;
    int FN_ID;
    static String arguments;
    String userName;
    String authToken;
    static List<String> messageBox; 
    static ArrayList<String> userList = new ArrayList<String>();
 
public MyClient(String name ,String user ,String IP,String portNumber,int FN_ID,String arguments){ 
    this.userName=user;
    this.authToken=name;
    this.IP=IP;
    this.portNumber=portNumber;
    this.FN_ID=FN_ID;
    this.arguments=arguments;
}  
public static <messageBox> void main(String args[])throws Exception{  
    Scanner sc=new Scanner(System.in); 
    messageBox=new ArrayList<String>();
    MyClient c1=new MyClient(((java.util.Scanner) sc).next(),((java.util.Scanner) sc).next(),((java.util.Scanner) sc).next(), ((java.util.Scanner) sc).next(),((java.util.Scanner) sc).nextInt(), ((java.util.Scanner) sc).next());
if(c1.FN_ID==1){
    boolean regex=Pattern.matches("[a-zA-Z0-9]{6}",arguments);
    if(regex){
    //     System.out.println("Sorry, the user already exists");
    userList.add(arguments);
final SecureRandom secureRandom = new SecureRandom(); //threadsafe
final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    c1.authToken= base64Encoder.encodeToString(randomBytes);
    System.out.println(c1.authToken);
}
else 
{
    System.out.println("Invalid Username");
}
}
else if(c1.FN_ID==2){
    userList.add("niaz57");
    userList.add("demo84");
    for(int i=0;i<userList.size();i++){
       System.out.println((i+1)+".     "+userList.get(i));  
    }  
}
else if(c1.FN_ID==3){
    c1.authToken=arguments;
    if(c1.authToken==arguments){
        Socket s=new Socket("localhost",3333);  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

        try{
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/messagesdb","root","root");   
        String query="INSERT INTO `messagesdb`.`messages` (`message`, `sender`) VALUES (?,?)";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setString(1,br.readLine());
        stmt.setString(2,arguments);
        stmt.execute();
        }
        catch(Exception e){ System.out.println(e);}

        String str="",str2="";  
        while(!str.equalsIgnoreCase("stop")){  
        str=br.readLine(); 
        dout.writeUTF(str);
        System.out.println("~~~OK~~~");
        dout.flush();  
        str2=din.readUTF();  
        System.out.println("Server says: "+str2);  
            }
        dout.close();  
        s.close();  
    }
    else{
        System.out.println("User Does not Exist.");
    }
}
else if(c1.FN_ID==4){
    //Esatblish Connection
    try{
        Random isread=new Random();
        // Class.forName("com.mysql.jdbc.driver");
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/messagesdb","root","root");  
        //here sonoo is database name, root is username and password  
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from messages");  
        while(rs.next())  {
        System.out.print(rs.getInt(1)+". from "+rs.getString(3));  
        System.out.println(isread.nextBoolean()?"*":"");
        }
        con.close();  
        }
    catch(Exception e){ System.out.println(e);}    
}
else if(c1.FN_ID==5){
try{
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/messagesdb","root","root");  
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("SELECT * FROM messages "+" WHERE sender = '"+arguments+"';");  
    int Found=0;
    while(rs.next()){
        Found++;
    System.out.println("("+rs.getString(3)+")"+rs.getString(2));
    }
    if(Found==0){
        System.out.println("Message ID does not exist");
    }
    con.close();
}  
catch(Exception e){ System.out.println(e);} 
}
else if(c1.FN_ID==6){
 //Esatblish Connection
try{
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/messagesdb","root","root");  
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select * FROM messages where sender='"+arguments+"';");  
    int Found=0;
    while(rs.next())  {
        Found++;
    }
    if(Found!=0){
        stmt.executeUpdate("DELETE FROM messages WHERE sender= '"+arguments+"';");  
        System.out.println("~~~OK~~~");
    }
    else    {
        System.out.println("Message ID does not exist");
    }
    con.close();  
    }
catch(Exception e){ System.out.println(e);} 
        }   
    }
}
