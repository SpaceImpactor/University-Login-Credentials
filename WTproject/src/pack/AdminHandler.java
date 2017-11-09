package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminHandler
 */
@WebServlet("/AdminHandler")
public class AdminHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHandler() {
        super();
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
        String r=request.getParameter("role");
		try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
            PreparedStatement ps=con.prepareStatement("insert into logged values(?,?,?)");
            ps.setInt(1,x);
            ps.setString(2,p);
            ps.setString(3,r);
            
            int rs=ps.executeUpdate();
            
            if(rs==1)
            {   out.println("User Added Successfully xD");
                RequestDispatcher res=request.getRequestDispatcher("AdminHome.html");
                res.forward(request, response);
            }
            else
            {    
            	out.println("User not added, Try Again!");   
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
