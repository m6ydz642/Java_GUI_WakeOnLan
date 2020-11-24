import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/*
class MenuTest  // 창한개 더 만들어서 쓸까 생각중
{  
	JMenu menu, submenu;
	JMenuItem i1, i2, i3, i4, i5;

	MenuTest() {
		JFrame f = new JFrame("메뉴, 메뉴 별도로 띄우기");
		JMenuBar mb = new JMenuBar();
		menu = new JMenu("Menu");
		submenu = new JMenu("Sub Menu");
		i1 = new JMenuItem("Item 1");
		i2 = new JMenuItem("Item 2");
		i3 = new JMenuItem("Item 3");
		i4 = new JMenuItem("Item 4");
		i5 = new JMenuItem("Item 5");
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		submenu.add(i4);
		submenu.add(i5);
		menu.add(submenu);
		mb.add(menu);
		f.setJMenuBar(mb);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
}  
}*/
class Ip_Mac_InPut{
	private static String ip;
	private static String mac;
	
	public static String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public static String getMac() {
		return mac;
	}
	public  void setMac(String mac) {
		this.mac = mac;
	}
	
	
	
}
class InformationAction implements ActionListener {
	
	public InformationAction() { // 기본 생성자로 호출
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 그냥 main에서 익명 함수로 쓰기로 함, 이거 사실 필요없음

	System.out.println("getIp로 받아온 아이피 : " + Ip_Mac_InPut.getIp());
	System.out.println("getMac로 받아온 아이피 : " + Ip_Mac_InPut.getMac());
	System.out.println("정보추가 버튼 이벤트 호출");
	TextLoading FileWrite = new TextLoading();
	// FileWrite.FileLoading(Ip_Mac_InPut.getIp(), Ip_Mac_InPut.getMac());
	FileWrite.FileWrite(); // 버튼 클릭시 파일 쓰기 함수 호출
	
	}
	
}
class MyMenu extends JMenu{

	JMenu menu = new JMenu("메뉴");
	JMenuItem m1 = new JMenuItem("기타");
	JMenuBar mb = new JMenuBar();

	public MyMenu() { // 기본생성자로 실행시 초기값 넣어놈
		System.out.println("메뉴 호출 성공");
		menu.add(m1);
		mb.add(menu);
		
		m1.addActionListener(new ActionListener() { // 익명함수로 호출함
			
			@Override
			public void actionPerformed(ActionEvent e) {

			System.out.println("메뉴 호출 내용 : " + e.getActionCommand());
				
			}
		});
	}

	
}
/*****************************************************************/
class SelectBox extends JComboBox implements ActionListener{  // 클래스 밖에서 JComboBox 기능 다 쓰려면 상속 받아야 함
	// 상속안받으면 객체 만들고 JComboBox에 대한 제공 함수를 사용 못함
	
// 클래스에서 빼서 public 함수형 액션 리스너로 하니까 안되서 밑에서 ComboBox 만들때 
// 같이 익명함수? 호출해서 사용하는 리스너로 바꿈 
// https://stackoverflow.com/questions/1346978/java-using-an-actionlistener-to-call-a-function-in-another-class-on-an-object-f
	
	public String Content (String ip) {
		System.out.println("ip 선택 넘어온 값 : " + ip.toString());
		return ip; // 외부에 호출해서 어디쓰던가 할듯, 일단은 장식임
	}


	public JComboBox ListBox (String[] ip_text, String mac_text[]) {
		
		String plusIP_mac = null;
		List <String> ip_mac = new ArrayList<String>(); 
		// 받아온걸 다시 리스트로 넣음 ㅡ.ㅡ 저 JComboBox가스트링 인자 2개가 지원안함
		
		
		for (int i = 0; i<ip_text.length-1; i+=2) { 		 // 출력 테스트용
			/*
			 * ip_text[0]는 아이피 
			 * macaddress_text[1]에는 맥주소 들어있음
			 * 
			 * 어쩌다 보니 0 2 4 6 8등 짝수 형태는 아이피
			 * 홀수 형태는 무조건 mac주소가 됨
			 * */
			
			int j =0;
			j = i+1; // j에다 i값 플러스
			plusIP_mac = ip_text[i] + mac_text[j];
			ip_mac.add(plusIP_mac); // 텍스트에서 가져온 아이피들 리스트에 추가함
			
		} 

		JComboBox cb = new JComboBox(ip_mac.toArray()); // 배열타입으로만 받아서 toArray로 넣음
	
		cb.setBounds(223,63,200,30); // 텍스트 필드 
		System.out.println("Jcombobox호출");
		System.out.println("JComboBox 리턴값 : " + cb);
		

		cb.setSelectedIndex(-1); // 초기 선택값
		
		cb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 System.out.println("select Listner 호출 : " + e.getActionCommand());
				 
				 Object o = e.getActionCommand();
				 if (o == "comboBoxChanged"){
					 System.out.println("콤보박스 오브젝트 실행");
					 System.out.println("getsource : " + e.getSource().toString());
				//	 Content(e.getSource().toString());
				 }
//				 System.out.println("얻어온 아이피 내용 : " + Content(cb.getSelectedItem().toString()));
				GetSet_IP_Mac ip = new GetSet_IP_Mac();
				ip.setIp((cb.getSelectedItem().toString()));	 // 리스트 박스 호출 될때마다 요청
				// Select 박스 선택시 setIp함수에 아이피를 등록 해놈
				ip.setMac((cb.getSelectedItem().toString())); // 사실 setIp에 이미 맥주소도 선택한것도 같이 들어가있어서
															// 맥주소에 대한 setter, getter는 필요없음
	
			}
		});
		
	
		
	
		return cb; // 외부에서 객체 호출해서 쓸때 사용하려고 호출
}


}
/*****************************************************************/
class IpListValue extends JButton{ 
	public Component IpList() {
		JButton Start = new JButton("전체부팅 하기"); 
		Start.setBounds(469,64,200,30); // 텍스트 필드 
		return Start;
	}
}


/*****************************************************************/
class TurnonButton extends JButton implements ActionListener { // 버튼 액션 정의

	@Override
	public void actionPerformed(ActionEvent e) {
		addActionListener(this);
	 System.out.println("TurnOnButton 클릭됨 ->  " + e.getActionCommand());
		GetSet_IP_Mac cb = new GetSet_IP_Mac() ;
		System.out.println("부팅 버튼 호출 getter ip값 : " + cb.getIp());
		
	 TurnOnLan LanAction = new TurnOnLan(); // TurnOnLan객체 생성
		LanAction.TurnOnLan(); // WakeOnLan함수 호출
		
	
	 
	}
	
	public void IP(String ip) { // 객체 타입으로 받기 위해 바꿈
		System.out.println("로딩한 아이피 : " + ip);
	} // 리턴 나갈꺼 없음 콘솔 내부적으로 표기만 할꺼임
	
	
	
	
}


public class WakeOne extends JFrame{


	public static void main(String[] args) {
		/*********************************************************************/
		// 메인 프레임에 붙는 정보들
		JFrame mainframe = new JFrame("WakeOnLan JAVA");
		JButton TurnonAll = new JButton("부팅");
		JTextField IpInPut; // 아이피 입력할 텍스트 창
		JTextField MacInPut; // 맥주소 입력할 텍스트 창
		// TurnOnLan IP = new TurnOnLan();
		JButton AddInformation = new JButton("정보추가 하기"); // 그냥 main안에서 하는걸로
		/*********************************************************************/
	
	
		
		 TextLoading fileread = new TextLoading(); // 텍스트 가져오는 파일 별도로 만듦
		 List<String> contentlist = new ArrayList<String>();
		 contentlist = fileread.FileLoading();
		 
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		mainframe.setVisible(true); // 창을 화면에 나타낼 것인지
		mainframe.setSize(800, 400); // 메인 프레임 사이즈 
		mainframe.setLayout(null); 
		TurnonAll.setBounds(47,57,95,30); // x, y, height,width 
		/*********************************************************************/
		mainframe.add(TurnonAll); // 메인 프레임에 버튼 추가해서 넣기
		mainframe.add(AddInformation); // 아이피 & 맥주소 추가 버튼 
		/*********************************************************************/
		System.out.println("프로그램 실행 콘솔");
		
		String alltext = contentlist.toString().replaceAll("^\\[", "")
				.replaceAll("\\]$", "").replace(", ", "");
		String[] ip_text = alltext.split("; "); // 아이피 부분 한칸 띄움, txt파일 저장 형식이 아이피; 맥주소 형식이라 --> ; 아이피로 나눔
	//	System.out.println("ip address : " + ip_text[0]); // 0번째는 아이피
	//	공백 등 toString 자체 양식 제거 (오버라이딩으로 재정의 해서 쓰려고 했는데 안됨 ㅡ.ㅡ;;)
	
		String[] macaddress_text = alltext.split(";"); // 맥주소 마지막 부분은 붙임 다음 아이피에서 다시 띄움 아이피->맥주소 순
		
	//	System.out.println("mac address : " + macaddress_text[1]); // 배열의 첫번째는 맥주소
		IpInPut = new JTextField("저장할 아이피를 입력하세요"); // 객체 생성 하면서 뷰 보여줌
		// text field 내용
		MacInPut = new JTextField("저장할 맥 주소를 입력하세요");
		
		AddInformation.setBounds(474,227,120,30); // 정보추가 버튼 사이즈 & 위치
		
		InformationAction InformationButtonAction = new InformationAction(); // 정보추가 클래스

		AddInformation.addActionListener(InformationButtonAction); // 리스너 추가

		AddInformation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // 익명함수 호출, 값 받아오기
				Ip_Mac_InPut value = new Ip_Mac_InPut();
				value.setIp(IpInPut.getText());
				value.setMac(MacInPut.getText());
				
				System.out.println("IpInPut 입력받은 값 : " + IpInPut.getText());
				System.out.println("MacInPut 입력받은 값 : " + MacInPut.getText());
				
			}
		});
		
		IpInPut.setBounds(221,192,200,30); // 아이피 입력창 텍스트 필드 위치 
		MacInPut.setBounds(228, 262, 200, 30); // 맥주소 입력창 텍스트 필드 위치

		
		mainframe.add(IpInPut); // 아이피 입력창 메인프레임에 추가
		mainframe.add(MacInPut); // 맥 입력창 메인 프레임에 추가
		
		
		MouseEvent mouseevent = new MouseEvent(); // 마우스 이벤트 객체 호출
		// 좌표 설정 할때 사용하려고 만듦
		mainframe.addMouseListener(mouseevent); // 프레임에 addmouseListenter를 호출하여 mousevent객체 넣음
		
		
		 TurnonButton Turnbutton = new TurnonButton();
		 TurnonAll.addActionListener(Turnbutton); // 버튼 클릭 이벤트 호출
	

		 IpListValue IpButton = new IpListValue(); 
		 
		 mainframe.add(IpButton.IpList()); // 메인 프레임에 추가
		 System.out.println("iplist 내용 : " + IpButton);

		 SelectBox cb = new SelectBox();
			
		 
		 mainframe.add(cb.ListBox(ip_text, macaddress_text)); // ComboBox 메인 프레임에 추가
		 // 리스트 박스 호출해서 사용해서 쓸대 리스너도 같이 호출해서 별도로 호출 안해도 됨
	 
		 MyMenu MainMenu = new MyMenu();
		 mainframe.add(MainMenu); // 메인 프레임에 메인 메뉴 추가
		 mainframe.setJMenuBar(MainMenu.mb); // 이시끼 없어서 계속 뻘짓함 ㅡ.ㅡ ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
		 						// MainMenu -> mb 멤버 변수에서 가져옴
		 						// 메인 프레임에 메뉴바 추가

		 
		
		 
	}
	








}
