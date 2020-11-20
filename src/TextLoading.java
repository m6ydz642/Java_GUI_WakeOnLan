import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

class TextLoading{
	private final static Logger log = Logger.getGlobal();
	
public void FileWrite(){ // 파일등록
	System.out.println("파일 쓰기 함수 호출");
	File file = new File("ip.txt"); // 파일 객체 생성
	String path = file.getAbsolutePath(); // 파일경로 읽어 오기
	try {
		
		
		FileWriter fw = new FileWriter(path, true); // boolean타입 true로 하면 파일이 
		// 기존파일 + append된다고 명시 되어있음
		BufferedWriter bw = new BufferedWriter(fw); 
		
		bw.newLine();
		bw.write(Ip_Mac_InPut.getIp() + ";"); // 세미콜론으로 구분지어줘야 해서 추가
		bw.newLine(); // 버퍼에 띄어쓰기 삽입
		bw.write(Ip_Mac_InPut.getMac() + ";");
		// bw.newLine(); // 버퍼에 삽입
		bw.flush(); // 버퍼 내용  파일에 쓰기 
		System.out.println("파일 읽기 함수 호출");
		
	} catch (Exception e) {
		System.out.println("파일 쓰기 예외 발생 : " + e);
	}

	
	
}
/**********************************************************************/
	public List<String> FileLoading() { // 리스트로 반환
		log.info("파일 읽기 함수 호출");
		 List<String> content = new ArrayList<String>(); // 리스트 형식으로 추가
		 String contentList = "";
		try {
	
			File file = new File("ip.txt"); // 파일 객체 생성
			String path = file.getAbsolutePath(); // 파일경로 읽어 오기
			System.out.println("파일 읽기 path 경로 : " + path);
			FileReader filereader = new FileReader(path); // 입력 스트림 생성하면서 파일 경로를 가져옴
			
			int fileread = 0;
			System.out.println("fileread값 : " + fileread);
			
			while ((fileread = filereader.read()) != -1) { // filereader.read() 다 읽으면 반환값이 -1이라 -1 까지 
				// filereader.read()결과값이 int타입이라 int변수 만들어서 대입
				// fileread값에 넣으면서 while문 실행
			//	System.out.print((char)fileread); // 강제 형변환

				 contentList = Character.toString((char)fileread); // char 타입을 String으로 형변환
				
			//	System.out.println("contentlist 내용 : " + contentList);
				content.add(contentList); // 리스트에 추가

			}
			
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("파일 읽기 예외 발생 : " + e);
		}
		return content; 
	}
	/**********************************************************************/
}
