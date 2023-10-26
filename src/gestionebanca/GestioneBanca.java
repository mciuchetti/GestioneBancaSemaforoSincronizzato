/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionebanca;

/**
 *
 * @author monica ciuchetti
 */
public class GestioneBanca{
    public static void main(String args[]){
        System.out.println(" [0] Apertura banca");
        Conto contoComune = new Conto(50, true);
        Cliente c1 = new Cliente(contoComune);
        Cliente c2 = new Cliente(contoComune);
        c1.start();
        c2.start();
        try{
            c1.join();
            c2.join();
        }
        catch(InterruptedException e){
            System.err.println("[0] Errore nel join()!");
        }
         System.out.println(" [0] Chiusura banca");
    }
}

