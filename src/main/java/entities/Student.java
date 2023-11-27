package entities;

public class Student {
    private String id;
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;

    public Student(String id, String name, String password, String homeAddress,
                   String uoftEmail) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.homeAddress = homeAddress;
        this.uoftEmail = uoftEmail;
    }
    public Student(String name, String password, String homeAddress,
                   String uoftEmail) {
        this.name = name;
        this.password = password;
        this.homeAddress = homeAddress;
        this.uoftEmail = uoftEmail;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

    public void setUoftEmail(String uoftEmail) { this.uoftEmail = uoftEmail; }

    public String getUoftEmail() { return uoftEmail; }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() { return homeAddress; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
