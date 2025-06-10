package com.littlecats.acalentoong.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class UserFileHandler {

    private static final String FILE_PATH = "user.dat";

    public static void saveArray(ArrayList<User> users) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(users);
            oos.close();
            System.out.println("Lista de Usuários salva com sucesso.");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static void createUser(User newUser) {
        ArrayList<User> users = fetchUsers();
        users.add(newUser);
        saveArray(users);
    }

    public static ArrayList<User> fetchUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            File arq = new File(FILE_PATH);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return users;
    }

    public static void updateUser(User updatedUser) {
        ArrayList<User> users = fetchUsers();

        // PROCURA O MESMO OBJETO NO ARRAY
        Optional<User> userToUpdate = users.stream()
                .filter(u -> u.equals(updatedUser))
                .findFirst();

        // SE O OBJETO FOR ENCONTRADO, ELE ATUALIZA A ARMAZENADA COM OS NOVOS VALORES
        userToUpdate.ifPresent(user -> {
            user.setName(updatedUser.getName());
            user.setCpf(updatedUser.getCpf());
            user.setBirthDay(updatedUser.getBirthDay());
            user.setCellphone(updatedUser.getCellphone());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setCep(updatedUser.getCep());
            user.setStreet(updatedUser.getStreet());
            user.setNeighborhood(updatedUser.getNeighborhood());
            user.setNumber(updatedUser.getNumber());
            user.setComplement(updatedUser.getComplement());
            user.setCity(updatedUser.getCity());
            user.setState(updatedUser.getState());
            saveArray(users);
            System.out.println("Usuário atualizado: " + user);
        });

        if (!userToUpdate.isPresent()) {
            System.err.println("Não foi possível atualizar: Usuário com ID " + updatedUser.getId() + " não encontrado");
        }
    }

    public static void deleteUser(UUID idToDelete) {
        ArrayList<User> users = fetchUsers();

        // REMOVE O OBJETO SE O ID FOR O DO OBJETO DESEJADO E RETORNA TRUE SE DELETADO
        boolean removed = users.removeIf(user -> user.getId().equals(idToDelete));

        if (removed) {
            saveArray(users);
            System.out.println("Usuário com ID " + idToDelete + " foi removido");
        } else {
            System.err.println("Não foi possível deletar: Usuário com ID " + idToDelete + " não encontrado");
        }
    }

}