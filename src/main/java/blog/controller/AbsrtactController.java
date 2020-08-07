package blog.controller;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class AbsrtactController extends HttpServlet {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public final void forward_to_page (String JSPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentPage","page/" + JSPage);
        request.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(request,response);
    }
    public final void forward_to_fragment (String JSPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + JSPage).forward(request,response);
    }
}
