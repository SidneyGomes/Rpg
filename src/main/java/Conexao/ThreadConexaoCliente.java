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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import telas.TelaJogador;

/**
 *
 * @author sidne
 */

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)

public class ThreadConexaoCliente extends Thread{
    
        Socket socket;
        boolean sair = false;
        TelaJogador telaJogador;

        public ThreadConexaoCliente(Socket socket, TelaJogador telaJogador) {

            this.socket = socket;
            this.telaJogador = telaJogador;
        }

        @Override
        public void run() {
            try {
                while (!sair) {
                    ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                    Jogador jogador = (Jogador) entrada.readObject();
                    Jogador.Action action = jogador.getAction();

                    System.out.println(jogador);

                    switch (action) {
                        case ADMIN:                           
                            telaJogador.enviaServidorInformacaoJogador(jogador);
                            break;
                        case CHAT:
                            telaJogador.mostraMensagemChat(jogador);
                            break;
                        case DISCONNECT:
                            disconnect(jogador);
                            break;
                        case ADMIN_CLIENTE:
                            telaJogador.preencherInterfaceGrafica(jogador);
                            telaJogador.calcularTodosOsBonus();
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void disconnect(Jogador jogador) throws IOException {

            this.socket.close();
            this.sair = true;
        }
    
}
