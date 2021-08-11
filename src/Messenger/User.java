package Messenger;

public class User extends Users{
    String password;
    StatusEnum status;
    String email;

    public User(String fullName, String login, String password,String email) {
        super(fullName, login);
        this.password=password;
        this.email=email;
    }

    @Override
    public void display() {
        System.out.println("This user is "+ this.fullName + " and his login is " + this.login);
    }
    public void signIn(){
        System.out.println(this.login + " is authorized");
    }
    public void signUp(){
        System.out.println(this.login + " is successfully registered");
    }

}
