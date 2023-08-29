/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homosapiensdeveloper.rpgproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Jogador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Icon icon;
    private String nome;
    private String requisicaoAdmin;
    private String nomeDoPersonagem;
    private String classeNivel;
    private String raca;
    private String antecedentes;
    private String tendencia;
    private String experiencia;
    private String forca;
    private String bonusForca;
    private String constituicao;
    private String bonusConstituicao;
    private String sabedoria;
    private String bonusSabedoria;
    private String carisma;
    private String bonusCarisma;
    private String inteligencia;
    private String bonusInteligencia;
    private String destreza;
    private String bonusDestreza;
    private String bonusDeProficiencia;
    private String inspiracao;
    private String armadura;
    private String iniciativa;
    private String deslocamento;
    private String pontosDeVidaMax;
    private String pontosDeVidaAtuais;
    private String pontosDeVidaTemporarios;
    private boolean testeResistenciaForca;
    private boolean testeAtletismoForca;
    private boolean testeResistenciaConstituicao;
    private boolean testeResistenciaSabedoria;
    private boolean testeAdestrarAnimaisSabedoria;
    private boolean testePercepcaoSabedoria;
    private boolean testeIntuicaoSabedoria;
    private boolean testeMedicinaSabedoria;
    private boolean testeSobrevivenciaSabedoria;
    private boolean testeResistenciaCarisma;
    private boolean testeEnganacaoCarisma;
    private boolean testePersuasaoCarisma;
    private boolean testeIntimidacaoCarisma;
    private boolean testePerformanceCarisma;
    private boolean testeInvestigacaoInteligencia;
    private boolean testeResistenciaInteligencia;
    private boolean testeArcanismoInteligencia;
    private boolean testeHistoriaInteligencia;
    private boolean testeReligiaoInteligencia;
    private boolean testeResistenciaDestreza;
    private boolean testeAcrobaciaDestreza;
    private boolean testeFurtividadeDestreza;
    private boolean testePrestidigitacaoDestreza;
    private Action action;
    private ArrayList<String> usuariosOnline;
    private String mensagemChat;
    private String equipamentos;
    private String magia;
    private boolean sucesso1;
    private boolean sucesso2;
    private boolean sucesso3;
    private boolean falha1;
    private boolean falha2;
    private boolean falha3;

    public enum Action {
        CONNECT, ENVIA, RECEBE, ADMIN, USERS_ONLINE, SEND_INFORM, CHAT, DISCONNECT, ADMIN_CLIENTE
    }

}
