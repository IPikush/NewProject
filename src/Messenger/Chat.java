package Messenger;

public interface Chat {
    void sendMessage(Users sendler, Message text);
    void removeMessage(Users sendler, Message text);
    void receiveMessage(Users sendler,Message text);
}
