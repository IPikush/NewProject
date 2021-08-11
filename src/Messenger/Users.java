package Messenger;

public abstract class Users {
    String fullName;
    String login;

    public Users(String fullName,String login) {
        this.fullName = fullName;
        this.login=login;
    }
    public abstract void display();

}
