package fileSystem;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

public class FileTest {
	private int headNum = 0 ;
	private int recordNum = 0 ;
	FileWriter fw = null;
	@Test
	public void test() {
		
		try {
			File folder = new File("C:\\Users\\bruce.huang\\Desktop\\順發3C\\順發交易備份 - 複製 (2)");
			File newTrans = new File("trans.txt");
			newTrans.createNewFile();
			
			File [] files = folder.listFiles();
			int filesAmount = files.length;
			fw = new FileWriter(newTrans);
			for(int i = 0 ; i<filesAmount ;i++){
				readFile(files[i]);
			}
			System.out.println("headNum : "+headNum);
			System.out.println("recordNum : "+recordNum);
			
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void readFile(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		while(br.ready()){
			String line = br.readLine();
			if(line.indexOf("H")==0)headNum++;
			if(line.indexOf("H")!=0){
				recordNum++;
				fw.append(line+"\r\n");
				System.out.println(recordNum+":"+line);
			}
		}
	}
}
