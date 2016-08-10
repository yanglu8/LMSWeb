package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({"/BorrowerServlet", "/varifyCard","/borrowBook","/returnLoans"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "checkoutbook.jsp";
		BorrowerService service = new BorrowerService();
		switch (reqUrl) {
		case "/varifyCard":
			System.out.println("varifyCard");
			{
			List<Author> authors = new ArrayList<Author>();
			if (request.getParameter("cardNo")!=""){
			
			try {
				Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
				if(service.varifyCard(cardNo)){
					System.out.println("varified!!!!!!!!!!");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			request.setAttribute("authors", authors);
			}
			break;
		case "/borrowBook":
			Integer bookID = Integer.parseInt(request.getParameter("bookId"));
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			service = new BorrowerService();
			try {
				service.checkoutBook(cardNo,bookID, branchId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage+= "?cardNo="+cardNo;
			String mainPage = "borrowermain.jsp";
			int i =1;
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("action", i);
			RequestDispatcher rd = request.getRequestDispatcher(mainPage);
			
			rd.forward(request, response);
			break;
		case "/returnLoans":
			bookID = Integer.parseInt(request.getParameter("bookId"));
			cardNo = Integer.parseInt(request.getParameter("cardNo"));
			branchId = Integer.parseInt(request.getParameter("branchId"));
			service = new BorrowerService();
			try {
				service.returnBook(cardNo,bookID, branchId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//forwardPage= "returnbook.jsp"+"?cardNo="+cardNo;
			mainPage = "borrowermain.jsp";
			i =0;
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("action", i);
			rd = request.getRequestDispatcher(mainPage);
			
			rd.forward(request, response);
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		BorrowerService service = new BorrowerService();
		switch (reqUrl) {
		case "/varifyCard":
			System.out.println("varifyCard");
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			System.out.println(cardNo);
			String forwardPage = "borrower.jsp";
			String mainPage = "borrowermain.jsp";
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("action", 2);
			try {
				if(service.varifyCard(cardNo)){
					RequestDispatcher rd = request.getRequestDispatcher(mainPage);
					rd.forward(request, response);
				}else {
					RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
					rd.forward(request, response);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		}
	}

}
