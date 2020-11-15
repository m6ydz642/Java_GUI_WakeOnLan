
class GetSet_IP_Mac {

	private static String ip; // 아이피 값 전달용
	
	public GetSet_IP_Mac() {
		// TODO Auto-generated constructor stub
	}
public GetSet_IP_Mac(String ip) {
	this.ip = ip;
}
	public static String getIp() { // 공통적으로 사용해야 하는 코드라 static 붙임 
		System.out.println("get ip 넘어온 값 " + ip);
	return ip;
}


public void setIp(String ip) {
	System.out.println("set ip 넘어온 값 : " + ip.toString());
	this.ip = ip;
}
}
