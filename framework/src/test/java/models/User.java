package models;

import lombok.Data;
import providers.users.UsersProvider;

@Data
public class User {
    private String email;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
