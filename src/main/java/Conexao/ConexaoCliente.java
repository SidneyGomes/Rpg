/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import com.homosapiensdeveloper.rpgproject.model.Jogador;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.ADMIN;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.ADMIN_CLIENTE;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.CHAT;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.DISCONNECT;
import com.homosapiensdeveloper.rpgproject.servidor.ThreadServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telas.TelaJogador;

/**
 *
 * @author sidne
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConexaoCliente {

    private String nomeConectado;
    private Socket socket;
    private String ip;
    ThreadConexaoCliente thread;

    public ConexaoCliente(String nomeConectado, String ip) {
        this.nomeConectado = nomeConectado;
        this.ip = ip;

    }

    public void conectar(Jogador jogador, TelaJogador telaJogador) {

        try {
            //Conectando ao Servidor do Chat
            //"26.45.172.213" ip do servidor radmin vpn
            this.socket = new Socket(this.ip, 54321);
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

            //Instanciando uma ThreadCliente para ficar recebendo mensagens do Servidor
            this.thread = new ThreadConexaoCliente(socket, telaJogador);
            this.thread.start();

            //Saída de Dados do Cliente
            saida.writeObject(jogador); //Enviando mensagem para Servidor

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage(), null);
        }

    }
}
