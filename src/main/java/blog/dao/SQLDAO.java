package blog.dao;

import blog.dao.mapper.MapCategoryMapper;
import blog.entity.Category;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class SQLDAO {
    private final QueryRunner sql = new QueryRunner();

    public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
        return sql.query(c, "select * from category;", new MapCategoryMapper());
    }
}
