package blog.controller.ajax;

import blog.controller.AbstractController;
import blog.entity.Comment;
import blog.form.CommentForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/ajax/comment")
public class NewCommentController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentForm form = createForm(req,CommentForm.class);
        Comment comment = getBusinessService().createComment(form);
        req.setAttribute("comments", Collections.singleton(comment));
        forwardToFragment("comments.jsp", req, resp);
    }
}