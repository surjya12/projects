package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entites.Note;
import com.helper.FactoryProvider;

@WebServlet("/SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Note note=new Note(title,content,new Date());
		
		Session s=FactoryProvider.getFactory().openSession();
		Transaction t=s.beginTransaction();
		s.save(note);
		t.commit();
		s.close();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Note is Added Succesfully</h1>");
		out.println("<h1><a href='all_notes.jsp'>View All</h1>");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
