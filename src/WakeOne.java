import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSplitPaneUI;
import javax.tools.JavaCompiler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/*****************************************************************/
class SelectBox extends JComboBox implements ActionListener{  // 클래스 밖에서 JComboBox 기능 다 쓰려면 상속 받아야 함
	// 상속안받으면 객체 만들고 JComboBox에 대한 제공 함수를 사용 못함
	
// 클래스에서 빼서 publci 함수형 액션 리스너로 하니까 안되서 밑에서 ComboBox 만들때 
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
			
			int j =0, count = 0;
			j = i+1; // j에다 i값 플러스
			plusIP_mac = ip_text[i] + mac_text[j];
			ip_mac.add(plusIP_mac); // 텍스트에서 가져온 아이피들 리스트에 추가함
		} 
	
		JComboBox cb = new JComboBox(ip_mac.toArray()); // 배열타입으로만 받아서 toArray로 넣음
	
		cb.setBounds(217,93,200,30); // 텍스트 필드 
		System.out.println("Jcombobox호출");
		System.out.println("JComboBox 리턴값 : " + cb);
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
		JButton Start = new JButton("부분 부팅 준비"); // 요놈을 어떻게 할까.......
		Start.setBounds(626,117,200,30); // 텍스트 필드 
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
		
		JFrame mainframe = new JFrame("WakeOnLan JAVA");
		JButton TurnonAll = new JButton("전체 부팅");
		JTextField ip_mac_view; // ip, mac주소 보여주는 텍스트 창
		TurnOnLan IP = new TurnOnLan();
		
		 TextLoading fileread = new TextLoading(); // 텍스트 가져오는 파일 별도로 만듦
		 List<String> contentlist = new ArrayList<String>();
		 contentlist = fileread.FileLoading();
		 
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		mainframe.setVisible(true); // 창을 화면에 나타낼 것인지
		mainframe.setSize(1000, 500); // 프레임 사이즈 
		mainframe.setLayout(null);
		TurnonAll.setBounds(47,57,95,30); // x, y, height,width 
		mainframe.add(TurnonAll); // 메인 프레임에 버튼 추가해서 넣기

		System.out.println("프로그램 실행 콘솔");
		
		String alltext = contentlist.toString().replaceAll("^\\[", "")
				.replaceAll("\\]$", "").replace(", ", "");
		String[] ip_text = alltext.split(";");
	//	System.out.println("ip address : " + ip_text[0]); // 0번째는 아이피
	//	공백 등 toString 자체 양식 제거 (오버라이딩으로 재정의 해서 쓰려고 했는데 안됨 ㅡ.ㅡ;;)
	
		String[] macaddress_text = alltext.split(";");
	//	System.out.println("mac address : " + macaddress_text[1]); // 배열의 첫번째는 맥주소
		ip_mac_view = new JTextField(ip_text[0]); // 객체 생성 하면서 뷰 보여줌
	
		 // text field 내용

		
		ip_mac_view.setBounds(157,152,200,30); // 텍스트 필드 
		mainframe.add(ip_mac_view);
		
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
	 
	}
	








}
