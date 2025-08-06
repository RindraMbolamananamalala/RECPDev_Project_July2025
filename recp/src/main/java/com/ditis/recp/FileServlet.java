package com.ditis.recp;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A class dedicated to any need of File Servlet configuration
 * @author Rindra Mbolamananamalala
 */
@WebServlet("/images/*")
public class FileServlet extends HttpServlet {
	
	@Value("${pattern.diagramsimages.localrepository.path}")
	private String patternsDiagramsLocalImageRepositoryPath;
	
	/**
	 * Synchronizing the local folder (repository) of pattern's diagrams' images and that of the Server (TomCat)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file = new File(this.patternsDiagramsLocalImageRepositoryPath, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}