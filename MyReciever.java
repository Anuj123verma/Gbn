import java.io.*;
import java.net.*;
import java.util.*;
public class MyReciever {
	public static final double LOST_PACK_PROBABILITY = 0.2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the port number :");
		int portNumber = sc.nextInt();
		byte[] incomingData = new byte[1024];
		try {
			DatagramSocket recvSocket = new DatagramSocket(portNumber);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			packetTransfer(recvSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void packetTransfer(DatagramSocket socket)
			throws IOException, ClassNotFoundException {
		int ack1=0;
		int expected = 0;
		byte[] dataIncoming = new byte[1024];
		int z=6990;
		incr();
		ArrayList<SendingData> received = new ArrayList<>();
		boolean last = false;
		char character; 
		while (!last) {
			DatagramPacket packetIncoming = new DatagramPacket(dataIncoming, dataIncoming.length);
			int n_ack=1-ack1;
			boolean is_send=true;
			socket.receive(packetIncoming);
			String flag="";
			byte[] sdata = packetIncoming.getData();
			incr();
			ByteArrayInputStream in = new ByteArrayInputStream(sdata);
			int new_portnumber=2550;
			ObjectInputStream is = new ObjectInputStream(in);
			if(n_ack==0){
				n_ack++;
			}
			else{

			}
			SendingData sendingData = (SendingData) is.readObject();

			System.out.println(" \n Packet Received....  = " + sendingData.getSeq());

			character = sendingData.getData();
			int hash = ("" + character).hashCode();
			boolean errorCheck = function(hash,sendingData);

			if (!errorCheck) {
				System.out.println(" Error Occured in the Data ");
			}

			else if (function5(sendingData,expected ,errorCheck)) {
				expected++;
				received.add(sendingData);
				System.out.println(" Last packet received ");
				last = true;

			} else if (function6(sendingData,expected,errorCheck)) {
				expected++;
				received.add(sendingData);
				// System.out.println("Packed stored ");
			}
			else {
				System.out.println(" Packet discarded (not in order) ");
				sendingData.setSeq(-1000);
			}

			InetAddress IPAddress = function1(packetIncoming);
			int port = function2(packetIncoming);

			DataAck ack = new DataAck();
			ack.setNo(expected);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			incr();
			ack1=ack1%2;
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			int nack=1-ack1;
			incr();
				if(ack1==0){
					ack1=1;
				}
			os.writeObject(ack);
			incr();
				if(ack1==0){
					ack1=1;
				}
			byte[] replyByte = outputStream.toByteArray();
				incr();
				if(ack1==0){
					ack1=1;
				}
			DatagramPacket replyPacket = new DatagramPacket(replyByte, replyByte.length, IPAddress, port);

			if (function3(sendingData)) {
				System.out.println("sending ack at this time :"+System.currentTimeMillis());
				String reply = " Sending Acknowledgment Number :" + ack.getNo()
						+ "\n";
				System.out.println(reply);

				socket.send(replyPacket);
			} else if (function4(sendingData)) {
				int size = received.size();
				ack1=ack1%2;
				System.out.println(" Packet Lost....... ");
				incr();
				received.remove(size - 1);
				int nack1=1-ack1;
				String pack="nack";
				expected--;
				incr();
				ack1=ack1+nack;
				if (last) {
					incr();
					last = false;
				}

			}

		}

	}
	public static void incr(){
		int x=0;
		x+=6;
	}
	public static boolean function(int hash, SendingData sendingData){
				return hash== sendingData.getErrorCheck();
	}
	public static InetAddress function1(DatagramPacket packet){
		InetAddress IPAddress = packet.getAddress();
		return IPAddress;
	}
	public static int function2(DatagramPacket packet){
		int port = packet.getPort();
		return port;
	}
	public static boolean function3(SendingData sendingData){
		return Math.random() > LOST_PACK_PROBABILITY && sendingData.getSeq() != -1000;
	}

	public static boolean function4(SendingData sendingData){
				return sendingData.getSeq() != -1000;
	}
	public static boolean function5(SendingData sendingData, int expected,boolean errorCheck){
				return sendingData.getSeq() == expected && sendingData.isFinal() && errorCheck;
	}
	public static boolean function6(SendingData sendingData,int expected, boolean errorCheck){
				return sendingData.getSeq() == expected && errorCheck;
	}


}