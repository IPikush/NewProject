package Messenger;

public class GroupChat implements Chat{
    Users user;
    Contact[] contact;

    public GroupChat(Users user, Contact[] contact) {
        this.user = user;
        this.contact = contact;
    }
    @Override
    public void sendMessage(Users sendler, Message text) {
        System.out.println(sendler.fullName  + " sent a message " + text.textMessage+" to group chat");
    }
    @Override
    public void removeMessage(Users sendler, Message text) {
        System.out.println(sendler.fullName  + " deleted a message " + text.textMessage+" from group chat");

    }

    @Override
    public void receiveMessage(Users sendler, Message text) {
        System.out.println("You received a message  " + text.textMessage+" from " + sendler.fullName);
    }

    public  void addParticipant(Users newContact){
        System.out.println("New participant was added: "+ newContact.fullName);
    }
    public  void removeParticipant(Users contact){
        System.out.println(contact.fullName+ " was removed from chat");
    }
}
