import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class TurnOnLan {
	public static final int PORT = 9;    
	

	public void TurnOnLan() {
		GetSet_IP_Mac iplist = new GetSet_IP_Mac();
		
	      String ipStr = iplist.getIp(); 
	
	     String macStr = "D8:D3:85:00:5A:A9";
	        
		 try {
	            byte[] macBytes = getMacBytes(macStr);
	            byte[] bytes = new byte[6 + 16 * macBytes.length];
	            for (int i = 0; i < 6; i++) {
	                bytes[i] = (byte) 0xff;
	            }
	            for (int i = 6; i < bytes.length; i += macBytes.length) {
	                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	            }
	            
	            InetAddress address = InetAddress.getByName(ipStr);
	            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
	            DatagramSocket socket = new DatagramSocket();
	            socket.send(packet);
	            socket.close();
	            
	            System.out.println("Wake-on-LAN 패킷 전송 ");
	            System.out.println("전송 아이피 : " + ipStr);
	            // 프로그램 실행하면서 로딩한 아이피 보여주기
	    	 	// 나중에 어떤 txt 파일을 읽어서 텍스트 필드로 보여주는 걸로 바꿀 예정 
	        }
	        catch (Exception e) {
	            System.out.println("패킷 전송 실패 맥주소나 아이피 주소 확인 바람 : " + e);
	            System.exit(1);
	        }
	}
	
	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		if (hex.length != 6) {
			throw new IllegalArgumentException("Invalid MAC address.");
		}
		try {
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("맥 주소가 이상하다 휴먼");
		}
		return bytes;
	}
}