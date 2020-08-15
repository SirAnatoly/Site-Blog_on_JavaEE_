package blog.dao.mapper;

import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ListMapper<T> extends AbstractListHandler<T> {
    private final AbstractMapper<T> handler;

    public ListMapper(AbstractMapper<T> handler) {
        super();
        this.handler = handler;
        this.handler.shouldBeIterateThroughResultSet = false;
    }

    @Override
    protected T handleRow(ResultSet rs) throws SQLException {
        return handler.handle(rs);
    }
}