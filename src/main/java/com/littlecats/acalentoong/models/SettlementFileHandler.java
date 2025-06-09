package com.littlecats.acalentoong.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class SettlementFileHandler {

    private static final String FILE_PATH = "settlement.dat";

    public static void saveArray(ArrayList<Settlement> settlements) {
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

    public static void updateSettlement(Settlement updatedSettlement) {
        ArrayList<Settlement> settlements = fetchSettlements();

        // PROCURA O MESMO OBJETO NO ARRAY
        Optional<Settlement> settlementToUpdate = settlements.stream()
                .filter(s -> s.equals(updatedSettlement))
                .findFirst();

        // SE O OBJETO FOR ENCONTRADO, ELE ATUALIZA A ARMAZENADA COM OS NOVOS VALORES
        settlementToUpdate.ifPresent(settlement -> {
            settlement.setNome(updatedSettlement.getNome());
            settlement.setNumeroDeFamilias(updatedSettlement.getNumeroDeFamilias());
            settlement.setCep(updatedSettlement.getCep());
            settlement.setRua(updatedSettlement.getRua());
            settlement.setNumero(updatedSettlement.getNumero());
            settlement.setBairro(updatedSettlement.getBairro());
            settlement.setCidade(updatedSettlement.getCidade());
            settlement.setEstado(updatedSettlement.getEstado());
            saveArray(settlements);
            System.out.println("Assentamento atualizado: " + settlement);
        });

        if (!settlementToUpdate.isPresent()) {
            System.err.println("Não foi possível atualizar: Assentamento com ID " + updatedSettlement.getId() + " não encontrado");
        }
    }

    public static void deleteSettlement(UUID idToDelete) {
        ArrayList<Settlement> settlements = fetchSettlements();

        // REMOVE O OBJETO SE O ID FOR O DO OBJETO DESEJADO E RETORNA TRUE SE DELETADO
        boolean removed = settlements.removeIf(settlement -> settlement.getId().equals(idToDelete));

        if (removed) {
            saveArray(settlements);
            System.out.println("Assentamento com ID " + idToDelete + " foi removido");
        } else {
            System.err.println("Não foi possível deletar: Assentamento com ID " + idToDelete + " não encontrado");
        }
    }

}
