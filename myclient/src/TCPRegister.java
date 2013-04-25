import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class TCPRegister {
	
	private Socket socket;

	public void process(String username,String password){
        try {
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse("sys.xml");
            NodeList iplist =  doc.getElementsByTagName("ip");
            NodeList portlist = doc.getElementsByTagName("tcpport");
        	String msg = "@2"+username+" "+password;
        	String ip = iplist.item(0).getTextContent();
        	int port = Integer.parseInt(portlist.item(0).getTextContent());
			socket = new Socket(ip,port);
			InputStream in ;
			OutputStream out;
			out = socket.getOutputStream();
			out.write(msg.getBytes());
			in = socket.getInputStream();
			byte[] b =new byte[1024];
			int n = in.read(b);
			String result = new String(b,0,n);
			if(result.equals("101"))JOptionPane.showMessageDialog(null, "Register Success!");
			else JOptionPane.showMessageDialog(null, "The Name has been registered!");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Can't connect to the Server!");
		}
	}
	
}
