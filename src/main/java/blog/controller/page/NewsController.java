package blog.controller.page;

import blog.Constants;
import blog.controller.AbstractController;
import blog.entity.Article;
import blog.entity.Category;
import blog.model.Items;
import blog.model.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/news", "/news/*"})
public class NewsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = getOffset(req, Constants.LIMIT_ARTICLES_PER_PAGE);
        String requestUrl = req.getRequestURI();
        Items<Article> items = null;
        if(requestUrl.endsWith("/news") || requestUrl.endsWith("/news/")){
            items = getBusinessService().listArticles(offset, Constants.LIMIT_ARTICLES_PER_PAGE);
            req.setAttribute("isNewsPage",Boolean.TRUE);
        }
        else{
            String categoryUrl = requestUrl.replace("/news", "");
            Category category = getBusinessService().findCategoryByUrl(categoryUrl);
            if (category==null){
                resp.sendRedirect("http://localhost:8080/Site_Blog_on_JavaEE_war/error");
                return;
            }

            items = getBusinessService().listArticlesByCategory(categoryUrl, offset, Constants.LIMIT_ARTICLES_PER_PAGE);

            req.setAttribute("selectedCategory", category);
        }
        req.setAttribute("list", items.getItems());
        Pagination pagination = new Pagination.Builder(requestUrl+"?", offset, items.getCount()).withLimit(Constants.LIMIT_ARTICLES_PER_PAGE).build();
        req.setAttribute("pagination", pagination);
        forward_to_page("news.jsp", req, resp);
    }
}
