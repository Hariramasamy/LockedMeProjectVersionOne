package com.lockedme.lockedmefilemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lockedme.lockedmeexceptionhandler.LockedMeException;



public class FileManager {
	
	/**
	 * getFileNameList method is used to get all the file names from the provided folderPath.
	 * 
	 * @param folderPath
	 * @return fileNameList
	 */
	public static List<String> getFileNameList(String folderPathString) {
		
		File folderPathFile = new File(folderPathString);
		File[] listOfFileArray = folderPathFile.listFiles();
		List<String> fileNameList = new ArrayList<>();
		
		for (File eachFile : listOfFileArray) {
			fileNameList.add(eachFile.getName());
		}
		
		return fileNameList;
	}

	/**
	 * createAndWriteFileContent method is used to create new file object and write the file content with the specified file name.
	 * 
	 * @param folderPathString
	 * @param fileNameString
	 * @param contentStringList
	 * @return boolean
	 */
	public static boolean createAndWriteFileContent(String folderPathString, String fileNameString, List<String> contentStringList) {
		try {
			File newFileObject = new File(folderPathString, fileNameString);
			FileWriter fileWriterObject = new FileWriter(newFileObject);

			for (String eachLineString : contentStringList) {
				fileWriterObject.write(eachLineString + "\n");
			}

			fileWriterObject.close();
			return true;

		} catch (IOException e) {
			try {
				throw new LockedMeException("Error Occured. Please Contact admin@lockeme.com");
			} catch (LockedMeException e1) {
				e1.printStackTrace();
			}
			return false;
		}

	}


	/**
	 * deleteFile method is used to delete the file based on folder path and file name.
	 * 
	 * @param folderPathString
	 * @param fileNameString
	 * @return boolean
	 */
	public static boolean deleteFile(String folderPathString, String fileNameString) {
		File newFileObject = new File(folderPathString, fileNameString);
		try {
			return newFileObject.delete();
		} catch (Exception e) {
			try {
				throw new LockedMeException("Either File Not Present or Some Access Issue with File.");
			} catch (LockedMeException e1) {
				e1.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * searchFile method is used to check if the given file exists on the given path.
	 * 
	 * @param folderPathString
	 * @param fileNameString
	 * @return boolean
	 */
	public static boolean searchFile(String folderPathString, String fileNameString) {
		File newFileObject = new File(folderPathString, fileNameString);
		
		return newFileObject.exists();
	}
}
