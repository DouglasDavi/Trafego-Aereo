/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TafegoAereo;

import java.awt.Toolkit;

/**
 *
 * @author maicon
 */
public class Trafego extends Thread {

    public Trafego() {
        init();
    }

    // Thread principal da aplicação
    Thread voo;

    // Criação dos semáforos da aplicação
    // O semáforo mutex que recebe o valor incial 1 para o contador
    // e é o semáforo principal da nossa aplicação
    public static Semaforo mutex = new Semaforo(1);

    // O vetor semáforos são normais e existe um semáforo para cada filósofo
    // que será criado, esses semafóros não recebem valores de inicialização
    // portanto iniciando o contador em 0
    public static Semaforo semaforos[] = new Semaforo[5];

    // Define um vetor para o estado de cada um dos avioes presentes
    // na aplicação
    public static int estado[] = new int[5];

    // Cria 5 avioes em um vetor para a aplicação
    static Aviao aviao[] = new Aviao[5];

    // Método para inicializar tudo o que é preciso dentro da classe
    public void init() {
        // Inicializa todos estados para zero
        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }

        // Verifica se o Thread de voo é vazia
        if (voo == null) {
            // Então cria um novo Thread
            voo = new Thread(this);
            // Inicia sua execução
            voo.start();
        }

        // Define a prioridade principal para este atual Thread
        Thread.currentThread().setPriority(1);

        // Inicializa todos Aviões
        aviao[0] = new Aviao("Foça Aéria 1", 0);
        aviao[1] = new Aviao("Boing 747", 1);
        aviao[2] = new Aviao("Air France", 2);
        aviao[3] = new Aviao("Jatinho", 3);
        aviao[4] = new Aviao("Santos Dumont", 4);

        // Inicializa todos semáforos
        semaforos[0] = new Semaforo(0);
        semaforos[1] = new Semaforo(0);
        semaforos[2] = new Semaforo(0);
        semaforos[3] = new Semaforo(0);
        semaforos[4] = new Semaforo(0);

        aviao[0].start();
        aviao[1].start();
        aviao[2].start();
        aviao[3].start();
        aviao[4].start();
    }

}
