package edu.sjsu.cmpe275.lab1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileReadTest {

	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FileService fs = new FileService("Alice",".//src//main//resources//home//Alice//shared//alicetext1.txt");
		try{
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");
		
		IFileService fs = (IFileService) context.getBean("fileService");
	//	FileService fs1 = (FileService) fs;
	//	System.out.println(fs1.getClass());
		
		byte[] readFile = fs.readFile("Alice", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
	for(byte b : readFile){
			System.out.print((char)b);
	}
	//System.out.println(" Printing bytes "+readFile.toString());
	//readFile = fs.readFile("Bob", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		
		fs.shareFile("Alice", "Alice", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.shareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		readFile = fs.readFile("Bob", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		System.out.println(" Printing bytes "+readFile.toString());
		fs.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
	fs.unShareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Alice", "Carl", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Bob", "Alice", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Alice", "Bob", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Bob", "Alice", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.shareFile("Bob", "Carl", ".//src//main//resources//home//Alice//shared//alicetext1.txt");
	
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("Exception thrown - message: "+e.getMessage());
		}
	}

}
