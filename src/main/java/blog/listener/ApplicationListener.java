package blog.listener;

import blog.Constants;
import blog.entity.Category;
import blog.service.impl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceManager serviceManager = ServiceManager.getInstance(sce.getServletContext());
        Map<Integer, Category> map = serviceManager.getBusinessService().mapCategories();
        sce.getServletContext().setAttribute(Constants.CATEGORY_MAP, map);
        LOGGER.info("Application started");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext()).destroy();
        LOGGER.info("Application destroyed");
    }
}