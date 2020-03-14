package domain.Models;

public class Angajat implements Entity<Integer> {
    private Integer id;
    private String username;
    private String password;

    public Angajat(Integer id,String user,String pass){
        this.id=id;
        this.username=user;
        this.password=pass;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setID(Integer integer) {
        this.id=integer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
