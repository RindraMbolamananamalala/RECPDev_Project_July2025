package com.ditis.recp.business.as.intf;

import org.springframework.stereotype.Service;

/**
 * The Abstract Class regrouping any Image-related Application Service 
 * @author Rindra Mbolamananamalala
 */
 @Service
public interface ImageASIntf {
	 /**
	  * Creating and putting a copy of given image within a dedicated folder on the server-side
	  * @param imagePath The file path related to the image yo be copied
	  * @param serverSideFolderPath The path leading to the dedicated folder on the server side
	  */
	 public void copyImageToServerSide(String imagePath, String serverSideFolderPath);
}
