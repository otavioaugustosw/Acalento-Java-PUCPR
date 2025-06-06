package com.littlecats.acalentoong;

import com.littlecats.acalentoong.models.Settlement;
import com.littlecats.acalentoong.models.SettlementFileHandler;

import java.util.ArrayList;

public class Programa {
    public static void main(String[] args) {
        ArrayList<Settlement> settlements = SettlementFileHandler.fetchSettlements();
        for (Settlement settlement : settlements) {
            System.out.println(settlement.getNome());
        }
    }
}
