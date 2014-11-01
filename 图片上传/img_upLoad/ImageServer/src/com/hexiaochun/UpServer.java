package com.hexiaochun;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexiaochun.utils.Base64Coder;


public class UpServer extends HttpServlet {

	private String file;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		file=req.getParameter("file");
		if(file!=null){
			byte[] b= Base64Coder.decodeLines(file);
			String filepath=req.getSession().getServletContext().getRealPath("/files");
			File file=new File(filepath);
			if(!file.exists())
				file.mkdirs();
			FileOutputStream fos=new FileOutputStream(file.getPath()+"/person_head"+(int)(Math.random()*100)+".png");
			System.out.println(file.getPath());
			fos.write(b);
			fos.flush();
			fos.close();
		}
	}

	

}
