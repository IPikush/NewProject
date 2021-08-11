package Messenger;

public class Contact extends Users {
    String phone;

    public StatusEnum getStatus() {
        return status;
    }

    StatusEnum status;

    public Contact(String fullName,String login,StatusEnum status, String phone) {
        super(fullName,login);
        this.phone = phone;
        this.status=status;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void display() {
        System.out.println("This contact is " + this.fullName + " and his login is " + this.login);
    }
}
