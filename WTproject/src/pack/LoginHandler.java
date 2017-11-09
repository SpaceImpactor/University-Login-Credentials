package pack;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginHandler() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String u=request.getParameter("usr");
        String p=request.getParameter("pwd");
        
        int x=Integer.parseInt(u);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
            PreparedStatement ps=con.prepareStatement("select * from logged where usr=? and pwd=? ");
            ps.setInt(1,x);
            ps.setString(2,p);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            { 
            	   String r=rs.getString(3);
            	   if(r.equals("student"))
            	   {
                RequestDispatcher res=request.getRequestDispatcher("StudentHome.html");
                res.forward(request, response);
            	   }
            	   else if(r.equals("staff"))
            	   {
                RequestDispatcher res=request.getRequestDispatcher("StaffHome.html");
                res.forward(request, response);
            	   }
            	   else if(r.equals("admin"))
            	   {
                RequestDispatcher res=request.getRequestDispatcher("AdminHome.html");
                res.forward(request, response);
            	   }
            }
           
            else
            {
                RequestDispatcher res=request.getRequestDispatcher("Error.html");
                res.forward(request, response);
            }
                    }
        catch (Exception e)
        {
            out.println(e);
        }
        out.close();
        }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
