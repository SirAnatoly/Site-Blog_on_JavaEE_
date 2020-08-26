package blog.dao.mapper;

import blog.entity.Account;
import blog.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper extends AbstractMapper<Comment> {
    private final boolean hasAccountData;
    private final boolean hasNoErrors;

    public CommentMapper(boolean hasAccountData) {
        super();
        this.hasAccountData = hasAccountData;
        this.hasNoErrors = true;
    }
    public CommentMapper(boolean hasAccountData,boolean hasErrors) {
        super();
        this.hasAccountData = hasAccountData;
        this.hasNoErrors = hasErrors;
    }

    @Override
    public Comment handleItem(ResultSet rs) throws SQLException {
        Comment comment = convert.toBean(rs, Comment.class);
        if(hasNoErrors)
            comment.setIdArticle(rs.getInt("id_article"));
        if (hasAccountData) {
            Account account = convert.toBean(rs, Account.class);
            account.setId(rs.getInt("id_account"));
            account.setCreated(rs.getTimestamp("accountCreated"));
            comment.setAccount(account);
        }
        return comment;
    }
}