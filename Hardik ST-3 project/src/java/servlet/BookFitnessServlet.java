import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.app.book.Book;
import com.web.app.bookdao.BookDAO;


public class BookFitnessServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		//Interpret the request		
		PrintWriter out = response.getWriter();
		String driver, url, user, pass;
                
		url = getServletContext().getInitParameter("url"); 
		driver = getServletContext().getInitParameter("driver"); 		
		user = getServletContext().getInitParameter("username"); 
		pass = getServletContext().getInitParameter("password"); 

		try
		{
                        BookDAO dao = new BookDAO(driver, url, user, pass);
			ArrayList<Book> list = (ArrayList<Book>)dao.getFitnessBooks();
			//attach the value to the request
			request.setAttribute("listBook",list);				
			RequestDispatcher dispatch = request.getRequestDispatcher("BooksFitness.jsp");
			dispatch.forward(request,response);		
		
			
		}
		catch(Exception e)
		{
		
			out.println("<html><body><span><font color=red>Session has expired.<br>Please Logout and Login Again.</font></span></body></html>");
		}
        }    
}
