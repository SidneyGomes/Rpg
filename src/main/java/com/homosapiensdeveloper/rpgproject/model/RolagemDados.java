/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homosapiensdeveloper.rpgproject.model;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sidne
 */
public class RolagemDados implements Serializable {

    Random random = new Random();

    public String montarDados(String string) {

        ArrayList<String> palavraReduzida = new ArrayList<>();
        String result = "";

        if (isPalavraAceita(string)) {

            for (Character c : string.toCharArray()) {
                if (Character.isDigit(c) || Character.toString(c).equals("+")) {
                    palavraReduzida.add(Character.toString(c));
                }
            }

            int tamanho = palavraReduzida.size();
            System.out.println(tamanho);

            switch (tamanho) {
                case 2 -> {
                    int soma = 0;
                    result = palavraReduzida.get(0) + "d" + palavraReduzida.get(1) + ": [";
                    for (int i = 0; i < Integer.parseInt(palavraReduzida.get(0)); i++) {
                        int valor = random.nextInt(1, Integer.parseInt(palavraReduzida.get(1)) + 1);
                        soma += valor;
                        if (i == Integer.parseInt(palavraReduzida.get(0)) - 1) {
                            result = result + valor;
                        } else {
                            result = result + valor + ",";
                        }
                    }
                    result += "] Result: " + soma;
                }
                case 3 -> {
                    result = palavraReduzida.get(0) + "d" + palavraReduzida.get(1) + palavraReduzida.get(2) + ": [";
                    String valorConc = palavraReduzida.get(1) + palavraReduzida.get(2);
                    int soma = 0;
                    for (int i = 0; i < Integer.parseInt(palavraReduzida.get(0)); i++) {

                        int valor = random.nextInt(1, Integer.parseInt(valorConc) + 1);
                        soma += valor;
                        if (i == Integer.parseInt(palavraReduzida.get(0)) - 1) {
                            result = result + valor;
                        } else {
                            result = result + valor + ",";
                        }
                    }
                    result += "] Result: " + soma;
                }
                case 4 -> {
                    result = palavraReduzida.get(0) + "d" + palavraReduzida.get(1) + ": [";
                    String valorConc = palavraReduzida.get(1);
                    int soma = 0;
                    for (int i = 0; i < Integer.parseInt(palavraReduzida.get(0)); i++) {

                        int valor = random.nextInt(1, Integer.parseInt(valorConc) + 1);
                        soma += valor;
                        if (i == Integer.parseInt(palavraReduzida.get(0)) - 1) {
                            result = result + valor;
                        } else {
                            result = result + valor + ",";
                        }
                    }
                    soma = soma + Integer.parseInt(palavraReduzida.get(3));
                    result += "] + Bonus: " + palavraReduzida.get(3) + " Result: " + soma;
                }
                case 5 -> {
                    result = palavraReduzida.get(0) + "d" + palavraReduzida.get(1) + palavraReduzida.get(2) + ": [";
                    String valorConc = palavraReduzida.get(1) + palavraReduzida.get(2);
                    int soma = 0;
                    for (int i = 0; i < Integer.parseInt(palavraReduzida.get(0)); i++) {

                        int valor = random.nextInt(1, Integer.parseInt(valorConc) + 1);
                        soma += valor;
                        if (i == Integer.parseInt(palavraReduzida.get(0)) - 1) {
                            result = result + valor;
                        } else {
                            result = result + valor + ",";
                        }
                    }
                    soma = soma + Integer.parseInt(palavraReduzida.get(4));
                    result += "] + Bonus: " + palavraReduzida.get(4) + " Result: " + soma;

                }
                default -> {
                }
            }
        }

        return result;
    }

    public boolean isPalavraAceita(String string) {
        return string.matches("/[1-9]d[1-9]([0-9])?(\\+[0-9])?");

    }

    public enum Action {
        ROLAR
    }

}
