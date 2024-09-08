package br.com.moneyiteasy.service.user;

import br.com.moneyiteasy.model.User;
import java.util.List;
import java.util.Optional;

public class Login {
    private List<User> users;

    public Login(List<User> users) {
        this.users = users;
    }

    // Busca um usuário por email ou username
    public Optional<User> findUserByEmailOrUsername(String identifier) {
        return users.stream()
                .filter(u -> u.getEmail().equals(identifier) || u.getUsername().equals(identifier))
                .findFirst();
    }

    // Valida o login do usuário
    public boolean validateLogin(String identifier, String password) {
        Optional<User> userOpt = findUserByEmailOrUsername(identifier);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.checkPassword(password);
        }
        return false;
    }
}
