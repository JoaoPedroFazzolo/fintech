package br.com.moneyiteasy.model;
import java.util.Scanner;
import java.util.UUID;


public class User {
    private String id;
    private String name;
    private String email;
    private String cpf;
    private String userName;
    private String password;;

    public User(String name, String email, String cpf, String userName, String hashed) {
        this.id = UUID.randomUUID().toString();
    }

    public User(String id, String name, String email, String cpf, String userName, String password) {
        this(name, email, cpf, userName, password);
        this.name = name;
        setEmail(email);
        setCpf(cpf);
        setPassword(password);
        setUserName(userName);
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    // Validação de CPF com Exp Regulares; Requisitos: email@email.com
    public void setEmail(String email) {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    // Validação de CPF com Exp Regulares; Requisitos: 11 dígitos.
    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.replaceAll("\\D", "").length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        } this.cpf = cpf.replaceAll("\\D", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8 || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).{8,}")) {
            throw new IllegalArgumentException("Senha inválida! A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma letra minúscula e um caractere especial.");
        }
        this.password = password;
    }

    public void createUser(Scanner scanner) {
        try {
            System.out.println("Nome: ");
            setName(scanner.nextLine());
            System.out.println("Email: ");
            setEmail(scanner.nextLine());
            System.out.println("CPF (apenas números): ");
            setCpf(scanner.nextLine());
            System.out.println("Senha (Contendo 8 caracteres, sendo no minimo uma letra maiúscula, um caractere especial e um número): ");
            setPassword(scanner.nextLine());
            System.out.printf("ID do usuário gerado: %s\n", id);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void displayUser() {
        System.out.println("Detalhes do Usuário: ");
        System.out.printf("Nome: %s | E-mail: %s | CPF: %s%n", name, email, cpf);
    }
    public String getUsername() {
        return userName;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
