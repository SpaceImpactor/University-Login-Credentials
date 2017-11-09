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
 * Servlet implementation class StaffHandler
 */
@WebServlet("/StaffHandler")
public class StaffHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String c=request.getParameter("class");
        int y=Integer.parseInt(request.getParameter("yr"));
        int p=Integer.parseInt(request.getParameter("period"));
        String[] att=request.getParameterValues("attendence");
        String x="";
        for(int i=0;i<att.length;i++){
           x=x+att[i]+" ";
       }        
		try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
            PreparedStatement ps=con.prepareStatement("insert into staff values(?,?,?,?)");
            ps.setString(1,c);
            ps.setInt(2,y);
            ps.setInt(3,p);
            
            ps.setString(4,x);
            int rs=ps.executeUpdate();
            
            if(rs==1)
            {   out.println("User Added Successfully xD");
                RequestDispatcher res=request.getRequestDispatcher("StaffHome.html");
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
