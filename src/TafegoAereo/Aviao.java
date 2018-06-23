/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TafegoAereo;

/**
 *
 * @author maicon
 */
public class Aviao extends Thread {

    public int id;

    final int Patio = 0;
    final int decolando = 1;
    final int voa = 2;
    final int pousa = 3;

    public Aviao() {
    }

    public Aviao(String nome, int id) {
        super(nome);
        this.id = id;
    }

    public void Decolar() {

        Trafego.estado[this.id] = 1;
        System.out.println("O Avião " + getName() + " quer decolar!");
    }

    public void Voa() {

        Trafego.estado[this.id] = 2;
        System.out.println("O Avião " + getName() + " está Voando!");

        try {

            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void Pousa() {

        Trafego.estado[this.id] = 3;
        System.out.println("O Avião " + getName() + " está Pousando!");
       
    }

    public void Patio() {

        Trafego.estado[this.id] = 0;
        System.out.println("O Avião " + getName() + " está Estacionado no patio!");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void NaoDecola() {

        Trafego.mutex.decrementar();

        Patio();

        Trafego.mutex.incrementar();
    }

    public void SimDecola() {

        Trafego.mutex.decrementar();

        Decolar();

        TentarVoar();

        Trafego.mutex.incrementar();

        Trafego.semaforos[this.id].decrementar();

        TentarPousar();

        Trafego.mutex.incrementar();

        Trafego.semaforos[this.id].decrementar();
    }

    public void TentarVoar() {

        if (Trafego.estado[this.id] == 1
                && Trafego.estado[this.id] != 2)
 {

            Voa();

            Trafego.semaforos[this.id].incrementar();
        }

    }

    public void TentarPousar() {

        if (Trafego.estado[this.id] == 2) {

            Pousa();

            Trafego.semaforos[this.id].incrementar();
        }
    }

    @Override
    public void run() {

        try {

            Patio();

            do {
                SimDecola();
                Thread.sleep(1000L);
                NaoDecola();
            } while (true);
        } catch (InterruptedException ex) {

            System.out.println("ERROR>" + ex.getMessage());

            return;
        }

    }

 
}
