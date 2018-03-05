package is.hi.teymi9.gefins.service;


import java.io.Serializable;
import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;
import is.hi.teymi9.gefins.repository.CommentRepository;

/**
 * Service klasi fyrir Comment.
 * Sér um þjónustu við comment: finna, búa til, breyta og staðfestingar.
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class CommentService implements Serializable {

    CommentRepository commentRep = new CommentRepository();


    private List<Comment> commentList;

    public List<Comment> getAllComments() {
        commentList = commentRep.getAll();
        return commentList;
    }

    public void addAd(Comment c) {
        commentRep.addComment(c);
    }

}
