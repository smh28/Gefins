package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.Comment;


/**
 * Created by oloff on 27.2.2018.
 */

public class CommentRepository {

    private ArrayList<Comment> comments = new ArrayList<>();

    /**
     * Nær í öll comment í gagnagrunni
     * @return comments, skilar lista af commentum
     */
    public List<Comment> getAll(){
        return comments;
    }

    public void addComment(Comment c ){
        comments.add(c);
    }

}
