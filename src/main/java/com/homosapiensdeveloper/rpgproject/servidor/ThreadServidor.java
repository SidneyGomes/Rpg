/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homosapiensdeveloper.rpgproject.servidor;

import com.homosapiensdeveloper.rpgproject.model.Jogador;
import com.homosapiensdeveloper.rpgproject.model.Jogador.Action;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.ADMIN;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.CONNECT;
import static com.homosapiensdeveloper.rpgproject.model.Jogador.Action.ENVIA;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author sidne
 */
public class ThreadServidor extends Thread {

    private static Map<String, Socket> jogadoresMap = new HashMap<>();
    private Socket socket;
    private JTextArea jtext;

    public ThreadServidor(Socket s, JTextArea jtext) {
        this.socket = s;
        this.jtext = jtext;
    }

    @Override
    public void run() {

        boolean sair = false;
        try {
            while (!sair) {

                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                Jogador jogador = (Jogador) entrada.readObject();
                Action action = jogador.getAction();

                this.jtext.append(action + "\n");

                switch (action) {
                    case CONNECT:
                        conectar(jogador);
                        enviarUsuarios(jogador.getAction());
                        break;
                    case ENVIA:
                        break;
                    case ADMIN:
                        selectedRequisicaoCliente(jogador);
                        break;
                    case SEND_INFORM:
                        sendAdmin(jogador);
                        break;
                    case CHAT:
                        enviarChat(jogador);
                        break;
                    case DISCONNECT:
                        disconnect(jogador);
                        sair = true;
                        break;
                    case ADMIN_CLIENTE:
                        sendAdminCliente(jogador);
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void conectar(Jogador jogador) {
        jogadoresMap.put(jogador.getNome(), socket);

    }

    //Enviar usuarios online para o admin
    private void enviarUsuarios(Action action) throws IOException {
        ArrayList<String> usuariosOnline = new ArrayList();

        for (Map.Entry<String, Socket> cliente : jogadoresMap.entrySet()) {
            if (cliente.getKey().equals("Admin") == false) {
                usuariosOnline.add(cliente.getKey());
            }
        }

        Jogador jogador = new Jogador();
        jogador.setAction(action);
        jogador.setUsuariosOnline(usuariosOnline);

        for (Map.Entry<String, Socket> cliente : jogadoresMap.entrySet()) {
            if (cliente.getKey().equals("Admin")) {
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getValue().getOutputStream());
                saida.writeObject(jogador);
            }
        }
    }

    private void selectedRequisicaoCliente(Jogador jogador) throws IOException {

        Socket socketRequisitado = jogadoresMap.get(jogador.getRequisicaoAdmin());
        jogador.setAction(Jogador.Action.ADMIN);
        ObjectOutputStream saida = new ObjectOutputStream(socketRequisitado.getOutputStream());
        saida.writeObject(jogador);

    }

    private void enviarChat(Jogador jogador) throws IOException {

        for (Map.Entry<String, Socket> cliente : jogadoresMap.entrySet()) {
            if (!cliente.getKey().equals(jogador.getNome())) {
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getValue().getOutputStream());
                saida.writeObject(jogador);
            }
        }

    }

    private void sendAdmin(Jogador jogador) throws IOException {
        Socket socketRequisitado = jogadoresMap.get("Admin");
        jogador.setAction(Jogador.Action.SEND_INFORM);
        ObjectOutputStream saida = new ObjectOutputStream(socketRequisitado.getOutputStream());
        saida.writeObject(jogador);

    }

    private void disconnect(Jogador jogador) throws IOException {
        Socket socketRequisitado = jogadoresMap.get(jogador.getNome());
        jogadoresMap.remove(jogador.getNome());
        enviarUsuarios(jogador.getAction());

        ObjectOutputStream saida = new ObjectOutputStream(socketRequisitado.getOutputStream());
        saida.writeObject(jogador);

    }

    private void sendAdminCliente(Jogador jogador) throws IOException {

        Socket socketRequisitado = jogadoresMap.get(jogador.getNome());
        ObjectOutputStream saida = new ObjectOutputStream(socketRequisitado.getOutputStream());
        saida.writeObject(jogador);

    }
}
