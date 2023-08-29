/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homosapiensdeveloper.rpgproject.model;

import java.util.Random;

/**
 *
 * @author sidne
 */
public class Main {
    public static void main(String args[]){
        
        RolagemDados dados = new RolagemDados();
        
        String rolagem = "/6d20+8";
        
        Random random = new Random();
        
      
  
        dados.montarDados(rolagem);
    }
    
}
