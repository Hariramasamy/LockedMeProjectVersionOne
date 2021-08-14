package com.lockedme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.lockedme.lockedmefilemanager.FileManager;

public class LockedMeMainProject {

	static final String FOLDER_PATH = "C:\\Users\\haric\\Documents\\Projects\\LockedMeProject";

	public static void main(String[] args) {
		int choice;
		Scanner getInputScanner = new Scanner(System.in);
		do {
			displayMenu();
			System.out.println("Enter your choice:");
			choice = Integer.parseInt(getInputScanner.nextLine());
			switch (choice) {
			case 1:
					getFileNamesAsc();
				break;
			case 2:
					getFileDetailsAndCreate(getInputScanner);
				break;
			case 3:
					deleteFile(getInputScanner);
				break;
			case 4:
					searchFile(getInputScanner);
				break;
			case 5:
					getInputScanner.close();
					System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (true);
	}

	/**
	 * displayMenu method is used to display the menu to the user.
	 */
	private static void displayMenu() {
		String starConstantString = "_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*";
		
		System.out.println(starConstantString);
		System.out.println("\tCompany Lockers Pvt. Ltd.");
		System.out.println(starConstantString);

		System.out.println("1. Display All Files \n2. Add New File \n3. Delete a File \n4. Search a File\n5. Exit");
		System.out.println(starConstantString);
	}

	/**
	 * getFileNamesAsc method is used to ge the file name list in Ascending order.
	 */
	public static void getFileNamesAsc() {
		List<String> fileNameStringList = FileManager.getFileNameList(FOLDER_PATH);
		Collections.sort(fileNameStringList);
		if (fileNameStringList.isEmpty()) {
			System.out.println("There is no file available");
		}
		else {
			fileNameStringList.forEach(System.out::println);
		}
	}

	/**
	 * getFileDetailsAndCreate method is used to get the file details such as File name, File content, Number of Lines and
	 * create the file.
	 * 
	 * @param getInputScanner
	 */
	public static void getFileDetailsAndCreate(Scanner getInputScanner) {
		String fileNameString;
		int linesCount;
		boolean isSavedSuccessFlag;
		List<String> contentStringList = new ArrayList<>();

		System.out.println("Enter the File Name: ");
		fileNameString = getInputScanner.nextLine();

		System.out.println("Enter the No of Lines in the File: ");
		linesCount = Integer.parseInt(getInputScanner.nextLine());

		for (int iterator = 1; iterator <= linesCount; iterator++) {
			System.out.println("Enter line " + iterator + ": ");
			contentStringList.add(getInputScanner.nextLine());
		}
		
		isSavedSuccessFlag = FileManager.createAndWriteFileContent(FOLDER_PATH, fileNameString, contentStringList);

		if (isSavedSuccessFlag) {
			System.out.println("File and Data Saved Successfully");
		}
		else {
			System.out.println("Error Occured. Please Contact admint@lockme.com");
		}
	}

	/**
	 * deleteFile method is used to delete the file based on the given file name.
	 * 
	 * @param getInputScanner
	 */
	public static void deleteFile(Scanner getInputScanner) {
		String fileName;
		boolean isFileDeletedFlag;
		
		System.out.println("Enter File Name to Delete : ");
		
		fileName = getInputScanner.nextLine();
		isFileDeletedFlag = FileManager.deleteFile(FOLDER_PATH, fileName);
		
		if (isFileDeletedFlag) {
			System.out.println("File deleted successfully");
		}
		else {
			System.out.println("Either File Not Present or Some Access Issue with File.");
		}
	}

	/**
	 * searchFile method is used to search the specified file in the folder.
	 * 
	 * @param getInputScanner
	 */
	public static void searchFile(Scanner getInputScanner) {
		String fileName;
		boolean isFoundFlag;
		System.out.println("Enter file name to be searched:");
		
		fileName = getInputScanner.nextLine();
		isFoundFlag = FileManager.searchFile(FOLDER_PATH, fileName);
		
		if (isFoundFlag) {
			System.out.println("File is Present in the Folder.");
		}
		else {
			System.out.println("File is Not Present in the Folder");
		}
	}
}
