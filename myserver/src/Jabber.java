import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;


public class Jabber implements Runnable {

	private DatagramSocket datagramSocket;
	private DatagramPacket receivePacket;
	private OutputStream out;
	private ArrayList<User> list;
	byte[] receiveByte;
	public Jabber(int port) throws SocketException{
		this.list = new ArrayList<User>();
		//this.serverSocket = serverSocket;
		datagramSocket =new DatagramSocket(port); 
		
	}
	public void add(User user){
		list.add(user);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!datagramSocket.isClosed()) {
				receiveByte = new byte[1024];
				receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
				datagramSocket.receive(receivePacket);
				if(receivePacket.getLength()>0){
				byte[] data = receivePacket.getData();
				String msg = new String(data,0,receivePacket.getLength());
				if(!checkQuit(msg))
				sendMsg(msg);
				}
				else{
					System.out.println("Not received");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void sendMsg(String msg){
		for(int i = 0;i<list.size();i++){
			try {
				out = list.get(i).getSocket().getOutputStream();
				out.write(msg.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void quit() throws IOException{
		String s = "SERVER QUIT";
		for(int i = 0;i<list.size();i++){
			out = list.get(i).getSocket().getOutputStream();			
			out.write(s.getBytes());
			list.get(i).stop();
		}
		datagramSocket.close();
	}
	private boolean checkQuit(String msg){
		if(msg.length()>5)
			if(msg.substring(0, 5).equals("@quit")){
				String name = msg.substring(5);
				String mymsg = name+" QUIT\n";
				closeSocket(name);
				sendMsg(mymsg);
				return true;
			}
		return false;
	}
	private void closeSocket(String username){
		for(int i =0;i<list.size();i++){
			if(list.get(i).getName().equals(username)){
				try {
					list.get(i).stop();
					list.remove(i);
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
