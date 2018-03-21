package is.hi.teymi9.gefins.service;


import java.io.Serializable;
import java.util.ArrayList;
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


    private List<Comment> commentList = new ArrayList<Comment>();

    public List<Comment> getAllComments() {
        commentList = commentRep.getAll();
        return commentList;
    }

    public List<Comment> getCommentsByAdId(int id) {

        Comment c1 = new Comment("user1","vei þetta virkar :)", 1);
        Comment c2 = new Comment("user2", "Vúbbvúbb! hér er ég", 1);
        commentList.add(c1);
        commentList.add(c2);
        System.out.print(commentList);
        return commentList;
    }

    public void addAd(Comment c) {
        commentRep.addComment(c);
    }

}
