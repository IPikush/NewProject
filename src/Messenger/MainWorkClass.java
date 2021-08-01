package Messenger;

import Exceptions.AlreadySignedUpException;
import Exceptions.ContactAlreadyInChatException;
import Exceptions.NoConnectionException;

import java.util.Scanner;

public class MainWorkClass {
    public static void main(String[] args) {
        Users[] test = new Contact[2];
        Users testContact = new Contact("test contact", "test", StatusEnum.ONLINE, "+380556546846");
        test[0] = testContact;
        Users testContact2 = new Contact("second contact", "secondTest", StatusEnum.INVISIBLE, "+385224565125");
        test[1] = testContact2;
        Users activeUser = new User("Test Active User", "testuser", "passw123us", "test@test.com");

        try {
            checkSignUp((User) activeUser);
        } catch (AlreadySignedUpException e) {
            System.err.println(e.toString());
        } finally {
            ((User) activeUser).signIn();
        }

        System.out.println("Select chat or contact to connect with \n" + "1." + testContact.fullName + "\n" + "2." +
                testContact2.fullName + "\n" + "3.Group chat with " + testContact.fullName + " and " + testContact2.fullName);
        Scanner in = new Scanner(System.in);
        String chat = in.nextLine();
        switch (chat) {
            case "1":
                PrivateChat newChat = new PrivateChat(activeUser, testContact);
                System.out.println("Enter your message");
                String message = in.nextLine();
                Message newMessage = new Message(message);
                newChat.sendMessage(activeUser, newMessage);
                //there is test answer:
                Message messageFromContact = new Message("test message");
                newChat.receiveMessage(testContact, messageFromContact);
                break;
            case "2":
                PrivateChat myNewChat = new PrivateChat(activeUser, testContact2);
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
                GroupChat myGroupChat = new GroupChat(activeUser, (Contact[]) test);

                System.out.println("Enter your message");
                String messageToGroup = in.nextLine();

                Message mesToGroup = new Message(messageToGroup);
                myGroupChat.sendMessage(activeUser, mesToGroup);
                //and we can delete message from a chat:
                myGroupChat.removeMessage(activeUser, mesToGroup);
                try {
                    checkContactInChat((Contact) test[0], myGroupChat);
                } catch (ContactAlreadyInChatException e) {
                    System.out.println(e.toString());//I used "out" here, because "err" outputs text later than "finally"
                } finally {
                    myGroupChat.removeParticipant(test[0]);
                }
                break;
            default:
                System.out.println("You have 3 chats");

        }
    }

    private static void checkSignUp(User test) throws AlreadySignedUpException {
        if (test.email == null) {
            test.signUp();
        } else {
            throw new AlreadySignedUpException("This user is already signed up");
        }

    }

    private static void checkContactInChat(Contact testcontact, GroupChat chat) throws ContactAlreadyInChatException {
        if (chat.contact[0].equals(testcontact)) {
            throw new ContactAlreadyInChatException("This contact is already in chat");
        } else {
            chat.addParticipant(testcontact);
        }

    }

    private static void checkConnection(boolean connection) throws NoConnectionException {
        if (connection == true) {
            System.out.println("Connection is ok");
        } else {
            throw new NoConnectionException("Connection is lost");
        }
    }
}

