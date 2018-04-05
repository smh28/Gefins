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
 * @date March 2018
 */

public class MessageRepository {

    // Listi af skilaboðum
    private ArrayList<Message> messageList = new ArrayList();
    // Listi af outbox skilaboðum
    private ArrayList<Message> outboxMessageList = new ArrayList();

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    public ArrayList<Message> getOutboxMessageList() {
        return outboxMessageList;
    }

    /**
     * stillir outbox skilaboða listann
     * @param outboxMessageList
     */
    public void setOutboxMessageList(ArrayList<Message> outboxMessageList) {
        this.outboxMessageList = outboxMessageList;
    }

    /**
     * Bætir skilaboði við listann af skilaboðum
     * @param m
     */
    public void addMesssage(Message m) {
        messageList.add(m);
    }

}
