package Messenger;

import Exceptions.NoConnectionException;

import java.util.*;

public class MainWorkClass {
    public static void main(String[] args) {

        Users testContact = new Contact("test contact", "test", StatusEnum.ONLINE, "+380556546846");

        Users testContact2 = new Contact("second contact", "secondTest", StatusEnum.INVISIBLE, "+385224565125");
        Users activeUser = new User("Test Active User", "testuser", "passw123us", "test@test.com");
        checkSignUp((User) activeUser);
        ((User) activeUser).signIn();
        List<Users>  contacts= new ArrayList<>();
        contacts.add(testContact);
        contacts.add(testContact2);
        PrivateChat newChat = new PrivateChat(activeUser, contacts.get(0));
        PrivateChat myNewChat = new PrivateChat(activeUser, contacts.get(1));
        GroupChat myGroupChat = new GroupChat(activeUser, contacts);

        Map<Chat,List<Users>> chats= new HashMap<>();
        chats.put(newChat, Collections.singletonList(contacts.get(0)));
        chats.put(myNewChat, Collections.singletonList(contacts.get(1)));
        chats.put(myGroupChat, contacts);


        System.out.println("Select chat or contact to connect with \n" + "1." + chats.get(newChat).get(0).fullName + "\n" + "2." +
                chats.get(myNewChat).get(0).fullName + "\n" + "3.Group chat with " + chats.get(myGroupChat).get(0).fullName + " and " +
                chats.get(myGroupChat).get(1).fullName);
        Scanner in = new Scanner(System.in);
        String chat = in.nextLine();
        switch (chat) {
            case "1":
                System.out.println("Enter your message");
                String message = in.nextLine();
                Message newMessage = new Message(message);
                newChat.sendMessage(activeUser, newMessage);
                //there is test answer:
                Message messageFromContact = new Message("test message");
                newChat.receiveMessage(testContact, messageFromContact);
                break;
            case "2":
                System.out.println("Enter your message");
                String otherMessage = in.nextLine();
                Message myNewMessage = new Message(otherMessage);
                //suppose that connection was been lost
                try {
                    checkConnection(false);
                } catch (NoConnectionException e) {
                    System.err.println(e.toString());
                } finally {
                    myNewChat.sendMessage(activeUser, myNewMessage);
                }
                break;
            case "3":
                System.out.println("Enter your message");
                String messageToGroup = in.nextLine();
                Message mesToGroup = new Message(messageToGroup);
                myGroupChat.sendMessage(activeUser, mesToGroup);
                //and we can delete message from a chat:
                myGroupChat.removeMessage(activeUser, mesToGroup);
                checkContactInChat((Contact) contacts.get(0), myGroupChat);
                myGroupChat.removeParticipant(contacts.get(0));
                break;
            default:
                System.out.println("You have 3 chats");

        }
    }

    private static void checkSignUp(User test) {
        if (test.email == null) {
            test.signUp();
        } else {
            System.out.println(test.fullName+ " is already signed up");
        }

    }

    private static void checkContactInChat(Contact testcontact, GroupChat chat)  {

        if (chat.contact.get(0).equals(testcontact)) {
            System.out.println(testcontact.fullName+" is already in chat");
        } else {
            chat.addParticipant(testcontact);
        }}


    private static void checkConnection(boolean connection) throws NoConnectionException {
        if (connection == true) {
            System.out.println("Connection is ok");
        } else {
            throw new NoConnectionException("Connection is lost");
        }
    }
}

