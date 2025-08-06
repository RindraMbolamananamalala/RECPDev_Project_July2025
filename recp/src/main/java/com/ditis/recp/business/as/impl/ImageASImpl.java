package com.ditis.recp.business.as.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.ditis.recp.business.as.intf.ImageASIntf;

/**
 * The Abstract Class regrouping any Image-related Application Service 
 * @author Rindra Mbolamananamalala
 */
 @Service
public class ImageASImpl implements ImageASIntf{
	
	/**
	* Creating and putting a copy of given image within a dedicated folder on the server-side
	* @param imagePath The file path related to the image yo be copied
	* @param serverSideFolderPath The path leading to the dedicated folder on the server side
	*/
	@Override
	public void copyImageToServerSide(String imagePath, String serverSideFolderPath) {
		File toBeCopied = new File(imagePath);
		File copied = new File(serverSideFolderPath);
		try {
			FileUtils.copyFile(toBeCopied, copied);
			System.out.println("Successfully copied from " + imagePath + " to " + serverSideFolderPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 

