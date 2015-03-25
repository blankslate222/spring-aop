package edu.sjsu.cmpe275.lab1;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Nikhil
 * 
 * Unit test for FileService
 * 
 * All test cases specified in the handout have been implemented
 *
 */
public class FileServiceTest {

	// Three bean objects representing 2 of Alice's files
	// and Carl's file
	IFileService fs, fs1, fs2;

	@SuppressWarnings("resource")
	
	//sets up the file objects for the test cases
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		fs = (IFileService) context.getBean("fileService");
		fs1 = (IFileService) context.getBean("fileService1");
		fs2 = (IFileService) context.getBean("fileService2");
	}

	@Test(expected = UnauthorizedException.class)
	public void testA() {
		System.out.println("testA");
		fs.readFile("Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
	}

	@Test
	public void testB() {
		System.out.println("testB");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		byte[] readFile = fs.readFile("Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
	}

	@Test
	public void testC() {
		System.out.println("testC");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		byte[] readFile = fs.readFile("Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
		System.out.println();
		fs.shareFile("Bob", "Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		readFile = fs.readFile("Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
		System.out.println();
	}

	@Test(expected = UnauthorizedException.class)
	public void testD() {
		System.out.println("testD");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs1.shareFile("Bob", "Carl",
				".//src//main//resources//home//Carl//shared//carltext1.txt");
	}

	@Test(expected = UnauthorizedException.class)
	public void testE() {
		System.out.println("testE");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.shareFile("Bob", "Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Alice", "Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		byte[] readFile = fs.readFile("Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
		System.out.println();
	}

	@Test(expected = UnauthorizedException.class)
	public void testF() {
		System.out.println("testF");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.shareFile("Alice", "Carl",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.shareFile("Carl", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		fs.unShareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		byte[] readFile = fs.readFile("Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
		System.out.println();
	}

	@Test(expected = UnauthorizedException.class)
	public void testG() {

		try {
			System.out.println("testG");
			fs.shareFile("Alice", "Bob",
					".//src//main//resources//home//Alice//shared//alicetext1.txt");
			fs.shareFile("Bob", "Carl",
					".//src//main//resources//home//Alice//shared//alicetext1.txt");
			fs.unShareFile("Alice", "Bob",
					".//src//main//resources//home//Alice//shared//alicetext1.txt");
			fs.shareFile("Bob", "Carl",
					".//src//main//resources//home//Alice//shared//alicetext1.txt");
		} finally {
			byte[] readFile = fs
					.readFile("Carl",
							".//src//main//resources//home//Alice//shared//alicetext1.txt");
			for (byte b : readFile) {
				System.out.print((char) b);
			}
			System.out.println();
		}
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testH(){
		System.out.println("testH");
		fs.shareFile("Alice", "Bob",
				".//src//main//resources//home//Alice//shared//alicetext1.txt");
		byte[] readFile = fs2
				.readFile("Bob",
						".//src//main//resources//home//Alice//shared//file2.txt");
		for (byte b : readFile) {
			System.out.print((char) b);
		}
		System.out.println();
	}

}
