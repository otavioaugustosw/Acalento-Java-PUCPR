package com.littlecats.acalentoong.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class EventFileHandler {
    private static final String FILE_PATH = "events.dat";

    public static void saveList(ArrayList<Event> events) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            // escreve no file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(events);
            oos.close();
            System.out.println("Os eventos foram salvos com sucesso <3");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar a lista de eventos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de eventos: " + e.getMessage());
        }
    }

    public static ArrayList<Event> readList() {
        ArrayList<Event> list = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                // lê o arquivo
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                list = (ArrayList<Event>)ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Erro ao ler a lista de eventos: " + e.getMessage());
        }

        return list;
    }

    public static void createEvent(Event newEvent) {
        ArrayList<Event> events = readList();
        events.add(newEvent);
        saveList(events);
    }


    public static void updateEvent(Event updateEvent) {
        ArrayList<Event> events = readList();

        // verifica se existe no Array, se existir retorna true e se não existir retorna false
        Optional<Event> eventToUpdate = events.stream()
                .filter(event -> event.equals(updateEvent)).findFirst();

        eventToUpdate.ifPresent(event -> {
                event.setName(updateEvent.getName());
                event.setDate(updateEvent.getDate());
                event.setTime(updateEvent.getTime());
                event.setDescription(updateEvent.getDescription());
                event.setSettlement(updateEvent.getSettlement());
                event.setMax_capacity(updateEvent.getMax_capacity());
                saveList(events);
                System.out.println("Evento " + event.getName() + " atualizados com sucesso <3");
        });

        if (!eventToUpdate.isPresent()) {
            System.err.println("Não foi possível editar o evento " + updateEvent.getName());
        }
    }

    public static void deleteEvent(UUID eventId) {
        ArrayList<Event> events = readList();

        boolean removed = events.removeIf(event -> event.getId().equals(eventId));
        if (removed) {
            saveList(events);
            System.out.println("Evento deletado com sucesso <3");
        } else {
            System.err.println("Não foi possível deletar o evento " + eventId);
        }
    }

}
