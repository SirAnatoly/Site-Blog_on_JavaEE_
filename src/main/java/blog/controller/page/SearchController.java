package blog.controller.page;

import blog.Constants;
import blog.controller.AbstractController;
import blog.entity.Article;
import blog.model.Items;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/search"})
public class SearchController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        if (StringUtils.isNotBlank(query)) {
            Items<Article> items = getBusinessService().listArticlesBySearchQuery(query, 0, Constants.LIMIT_ARTICLES_PER_PAGE);
            req.setAttribute("list", items.getItems());
            req.setAttribute("count", items.getCount());
            req.setAttribute("searchQuery", query);
            forward_to_page("search.jsp", req, resp);
        } else {
            resp.sendRedirect("/Site_Blog_on_JavaEE_war/news");
        }
    }
}
