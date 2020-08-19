package blog.controller.page;

import blog.Constants;
import blog.controller.AbstractController;
import blog.entity.Article;
import blog.model.Items;
import blog.model.Pagination;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet({"/search"})
public class SearchController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        int offset = getOffset(req, Constants.LIMIT_ARTICLES_PER_PAGE);
        if (StringUtils.isNotBlank(query)) {
            Items<Article> items = getBusinessService().listArticlesBySearchQuery(query, offset, Constants.LIMIT_ARTICLES_PER_PAGE);
            req.setAttribute("list", items.getItems());
            req.setAttribute("count", items.getCount());
            req.setAttribute("searchQuery", query);
            Pagination pagination = new Pagination.Builder("/Site_Blog_on_JavaEE_war/search?query=" + URLEncoder.encode(query, "utf8") + "&",
                    offset, items.getCount()).withLimit(Constants.LIMIT_ARTICLES_PER_PAGE).build();
            req.setAttribute("pagination", pagination);
            forward_to_page("search.jsp", req, resp);
        } else {
            resp.sendRedirect("/Site_Blog_on_JavaEE_war/news");
        }
    }
}
