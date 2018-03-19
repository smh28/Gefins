package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;
import is.hi.teymi9.gefins.model.Message;


/**
 * Geymsla fyrir skilaboð (message)
 *
 * @author Einar
 * @version 1.0
 */

public class MessageRepository {

    private ArrayList<Message> messageList = new ArrayList();

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMesssage(Message m) {
        messageList.add(m);
    }

}
