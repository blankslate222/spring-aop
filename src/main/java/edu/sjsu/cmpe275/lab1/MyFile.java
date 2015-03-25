package edu.sjsu.cmpe275.lab1;

import java.util.*;

/**
 * @author Nikhil
 * 
 * @category Custom File object
 * 
 * @memeber-variable: ownerId - owner of the file
 * @member-variable: filePath - path to the file
 * @member-variable: sharedWith - set of users the file is shared with
 *
 */
public class MyFile {

	private String ownerId;
	private String filePath;
	private HashSet<String> sharedWith = new HashSet<String>();

	//owner and path must be specified to create an object of type MyFile
	public MyFile(String owner, String path) {
		this.ownerId = owner;
		this.setFilePath(path);
	}

	public void setOwnerId(String s) {
		this.ownerId = s;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setShareWith(String s) {
		sharedWith.add(s);
	}

	public String getSharedWith() {

		StringBuilder sb = new StringBuilder();
		for (String s : sharedWith) {
			sb.append(s);
			sb.append(',');
		}
		return sb.toString();
	}

	public void unshareWith(String s) {
		sharedWith.remove(s);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isSharedWith(String s) {
		boolean isShared = false;
		if (this.sharedWith.contains(s)) {
			isShared = true;
		}
		//System.out.println(" is shared with "+s+" returned ="+isShared);
		return isShared;
	}

	public boolean isOwner(String owner) {
		// TODO Auto-generated method stub
		boolean isOwner = false;
		if (owner.equals(this.getOwnerId())){
			isOwner = true;
		}
		//System.out.println(" is owner "+owner+" returned ="+isOwner);
		return isOwner;
	}

}
