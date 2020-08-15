package blog.controller.page;

import blog.Constants;
import blog.controller.AbstractController;
import blog.entity.Article;
import blog.model.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/news", "/news/*"})
public class NewsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();
        Items<Article> items = null;
        if(requestUrl.endsWith("/news") || requestUrl.endsWith("/news/")){
            items = getBusinessService().listArticles(0, Constants.LIMIT_ARTICLES_PER_PAGE); }

        req.setAttribute("list", items.getItems());
        forward_to_page("news.jsp", req, resp);
    }
}
