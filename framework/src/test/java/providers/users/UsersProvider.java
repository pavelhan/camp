package providers.users;

import models.User;

public abstract class UsersProvider {
    public abstract User getUserByName(String name);

    public abstract User getDefaultUser();
}
