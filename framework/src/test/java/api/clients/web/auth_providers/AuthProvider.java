package api.clients.web.auth_providers;

import http_session.HttpSession;
import models.User;

public interface AuthProvider {
    HttpSession login(User user, String expires) throws Exception;
}
