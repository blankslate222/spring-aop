package edu.sjsu.cmpe275.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Nikhil
 * 
 * Type: Implementation of IFileService interface
 * 
 * Extends MyFile - Represents the object of "file" 
 *
 */
public class FileService extends MyFile implements IFileService {
	
	public FileService(String owner, String path) {
		super(owner, path);
		// TODO Auto-generated constructor stub
	}

	//method to share file - adds the target user id with whom the file is shared to a set
	public void shareFile(String userId, String targetUserID, String filePath) {
		// TODO Auto-generated method stub
		this.setShareWith(targetUserID);
		//System.out.println("after share access list = "+this.getSharedWith());
	}

	//method to unshare a file - removes the target user id from the set which maintains the 
	//list of users the file is shared with
	public void unShareFile(String userId, String targetUserID, String filePath) {
		// TODO Auto-generated method stub
		this.unshareWith(targetUserID);
		//System.out.println("after unshare access list = "+this.getSharedWith());
	}

	//returns the file contents to the user who invokes this method
	public byte[] readFile(String userId, String filePath) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		byte[] fileContents = null;
		try {
			FileInputStream in = new FileInputStream(file);
			fileContents = new byte[in.available()];
			int i = 0, b = 0;
			while ((i = in.read()) != -1) {
				fileContents[b++] = (byte) i;
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		return fileContents;
	}

}
