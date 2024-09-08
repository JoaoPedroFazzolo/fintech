package br.com.moneyiteasy.service.user;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.com.moneyiteasy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public void addUser(Scanner scanner) {
        System.out.println("Cadastro de Usuário.");

        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();
            validateEmail(email);

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            validateCpf(cpf);

            System.out.print("Username: ");
            String userName = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();
            validatePassword(password);
            String hashed = hashPassword(password);

            if (!userExists(email, cpf, userName)) {
                // Criação do usuário com senha criptografada
                User user = new User(name, email, cpf, userName, hashed);
                users.add(user);
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Usuário com o mesmo email, username ou CPF já cadastrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private boolean userExists(String email, String cpf, String username) {
        return users.stream()
                .anyMatch(u -> u.getEmail().equals(email) ||
                        u.getCpf().equals(cpf) ||
                        u.getUsername().equals(username));
    }

    private void validateEmail(String email) {
        // Regex simples para validação de email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private void validateCpf(String cpf) {
        // Validação básica de CPF (formato 11 dígitos)
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    private void validatePassword(String password) {
        // Validação de senha com requisitos mínimos
        if (password == null || password.length() < 8 || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).{8,}$")) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }
    }

    private String hashPassword(String password) {
        // Exemplo usando hashing com SHA-256 (substitua por bcrypt ou outro algoritmo seguro em produção)
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao hash a senha", e);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void displayUsers() {
        if (!users.isEmpty()) {
            for (User user : users) {
                user.displayUser();
            }
        } else {
            System.out.println("Nenhum usuário cadastrado!");
        }
    }
}
