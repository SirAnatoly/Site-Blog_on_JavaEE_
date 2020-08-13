package blog.service.impl;

import blog.dao.SQLDAO;
import blog.entity.Category;
import blog.exception.ApplicationException;
import blog.service.BusinessService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class BusinessServiceImpl implements BusinessService{
    private final DataSource dataSource;
    private final SQLDAO sql;

    BusinessServiceImpl(ServiceManager serviceManager) {
        super();
        this.dataSource = serviceManager.dataSource;
        this.sql = new SQLDAO();
    }

    @Override
    public Map<Integer, Category> mapCategories() {
        try (Connection c = dataSource.getConnection()) {
            return sql.mapCategories(c);
        } catch (SQLException e) {
            throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }
}
