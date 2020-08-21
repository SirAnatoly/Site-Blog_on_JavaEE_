package blog.controller.page;

import blog.Constants;
import blog.controller.AbstractController;
import blog.entity.Article;
import blog.entity.Comment;
import blog.exception.RedirectToValidUrlException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/article/*")
public class ArticleController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUrl = req.getRequestURI().substring(24);


        try {
            String id = StringUtils.split(requestUrl, "/")[1];
            Article article = getBusinessService().viewArticle(Integer.parseInt(id), requestUrl);


            if (article == null) {
                resp.sendRedirect("http://localhost:8080/Site_Blog_on_JavaEE_war/error");
            }
            else{
                req.setAttribute("article", article);
                List<Comment> comments = getBusinessService().listComments(article.getId(), 0, Constants.LIMIT_COMMENTS_PER_PAGE);

                req.setAttribute("comments", comments);
                forward_to_page("article.jsp", req, resp);
            }
        }  catch (RedirectToValidUrlException e) {

            resp.sendRedirect(e.getUrl());

        }  catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            resp.sendRedirect("http://localhost:8080/Site_Blog_on_JavaEE_war/error");
        }
    }
}