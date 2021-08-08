package Messenger;

public class PrivateChat implements Chat{
    Users user;
    Users contact;

    public PrivateChat(Users user, Users contact) {
        this.user = user;
        this.contact = contact;
    }

    public PrivateChat(Users user) {
        this.user=user;
    }

    @Override
    public void sendMessage(Users sendler, Message text) {
        System.out.println(sendler.fullName  + " sent a message " + text.textMessage+" to "+contact.fullName);

    }

    @Override
    public void removeMessage(Users sendler, Message text) {
        System.out.println(sendler.fullName  + " deleted a message " + text.textMessage+" from chat with "+contact.fullName);

    }

    @Override
    public void receiveMessage(Users sendler, Message text) {
        System.out.println(sendler.fullName  + " sent a message to you:  " + text.textMessage);
    }
}
