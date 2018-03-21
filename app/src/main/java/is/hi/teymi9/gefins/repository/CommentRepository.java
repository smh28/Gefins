package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;


/**
 * Geymsla fyrir athugasemdir (Comment)
 *
 * @author Ólöf
 * @version 1.0
 */

public class CommentRepository {

    // Listi af athugasemdum
    private ArrayList<Comment> comments = new ArrayList<>();

    /**
     * Nær í öll comment í gagnagrunni
     * @return comments, skilar lista af commentum
     */
    public List<Comment> getAll(){
        Ad ad = new Ad("gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");

        Comment c1 = new Comment("sandra","comment 1",ad);
        Comment c2 = new Comment("sandra", "comment 2", ad);
        comments.add(c1);
        comments.add(c2);
        return comments;
    }

    /**
     * Bætir commenti í repository
     * @param c Comment sem bæta skal við
     */
    public void addComment(Comment c ){
        comments.add(c);
    }

}
