package srilanka.sliit.apps.trubeauty;

/**
 * Created by Rajitha Shavinda on 2018-05-30.
 */

public class User {

    private String Number;
    private String Name;
    private String Email;
    private String Gender;
//asd
    public User(String number, String name, String email, String gender) {
        this.Number = number;
        this.Name = name;
        this.Email = email;
        this.Gender = gender;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }
}
