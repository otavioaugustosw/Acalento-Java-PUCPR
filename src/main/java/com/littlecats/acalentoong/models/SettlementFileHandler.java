package com.littlecats.acalentoong.models;

import java.io.*;
import java.util.ArrayList;

public class SettlementFileHandler {

    private static final String FILE_PATH = "settlement.dat";

    public static void saveArray(ArrayList<Settlement> settlements) {
        FileOutputStream f;
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(settlements);
            oos.close();
            System.out.println("Lista de Assentamento salva com sucesso.");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static void createSettlement(Settlement newSettlement) {
        ArrayList<Settlement> settlements = fetchSettlements();
        settlements.add(newSettlement);
        saveArray(settlements);
    }

    public static ArrayList<Settlement> fetchSettlements() {
        ArrayList<Settlement> settlements = new ArrayList<>();
        try  {
            File arq = new File(FILE_PATH);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                settlements = (ArrayList<Settlement>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return settlements;
    }

    public static void updateSettlement(Settlement oldSettlement, Settlement editedSettlement) {
        ArrayList<Settlement> settlements = fetchSettlements();
        settlements.remove(oldSettlement);
        settlements.add(editedSettlement);
        saveArray(settlements);
    }

    public static void deleteSettlement(Settlement settlementToDelete) {
        ArrayList<Settlement> settlements = fetchSettlements();
        settlements.remove(settlementToDelete);
        saveArray(settlements);
    }

}
