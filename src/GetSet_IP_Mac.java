import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GetSet_IP_Mac {

	private static String ip; // 아이피 값 전달용
	private static String mac; // 맥주소 전달용 (ip에 맥주소 까지 같이 나와서 사실 필요없음 ㅋㅋ)

	private static Map<String, String> allip_mac;

	/*public static Map<String, String> getAllip_mac() {
		Map<String,String> ip = new HashMap<String, String>();
		
		System.out.println("getAllIp_mac 호출");
		System.out.println("get allip_mac 값 " + allip_mac);
		ip.putAll(allip_mac);
		return ip;
	}*/

	public static Map<String, String> getAllip_mac() {
		
		System.out.println("getAllIp_mac 호출");
		System.out.println("get allip_mac 값 " + allip_mac);
		return allip_mac;
	}
	public static void setAllip_mac(Map<String, String> map) {
		System.out.println("setAllIp mac 호출");
		System.out.println("setAllIp list : " + map);
		GetSet_IP_Mac.allip_mac = map;
	}
	
	/*	private static List<String> allip_mac;

	public static List<String> getAllip_mac() {
		List<String> ip = new ArrayList<String>();
		System.out.println("getAllIp_mac 호출");
		System.out.println("get allip_mac 값 " + allip_mac);
		ip.addAll(allip_mac); // 리스트 추가
		System.out.println("리스트 추가된 mac값 : " + ip);
		return ip;
	}

	public static void setAllip_mac(List<String> list) {
		System.out.println("setAllIp mac 호출");
		System.out.println("setAllIp list : " + list);
		GetSet_IP_Mac.allip_mac = list;
	}*/
	
/*	private static  String[] allip_mac;
	

	public static String[] getAllip_mac() {
		System.out.println("getAllIp_mac 호출");
		System.out.println("get allip_mac 값 " + allip_mac);
		return allip_mac;
	}

	public static void setAllip_mac(String[] allip_mac) {
		System.out.println("setAllIp mac 호출");
		System.out.println("setAllIp list : " + allip_mac);
		GetSet_IP_Mac.allip_mac = allip_mac;
	}
*/
	public static String getMac() {
		System.out.println("get mac 넘어온 값 " + mac);
		return mac;
	}

	public static void setMac(String mac) {
	
		System.out.println("set mac 넘어온 값 : " + mac.toString());
		GetSet_IP_Mac.mac =  mac;
	}

	/*****************************************/
	// 왜 만들었는지 기억이 안나는 생성자
	public GetSet_IP_Mac() {

	}

	public GetSet_IP_Mac(String ip) {
		this.ip = ip;
	}

	/*****************************************/
	public static String getIp() { // 공통적으로 사용해야 하는 코드라 static 붙임
		System.out.println("get ip 넘어온 값 " + ip);
		return ip;
	}

	public void setIp(String ip) {
		System.out.println("set ip 넘어온 값 : " + ip.toString());
		this.ip = ip;
	}
}
