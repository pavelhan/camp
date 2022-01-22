package providers.users;

import models.User;

public class Users {
    public User getUser(UsersProvider usersProvider, String userName){
        return usersProvider.getUserByName(userName);
    }
    public User getDefault(UsersProvider usersProvider){
        return usersProvider.getDefaultUser();
    }
}
