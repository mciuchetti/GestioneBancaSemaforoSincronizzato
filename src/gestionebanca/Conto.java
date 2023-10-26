/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionebanca;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monica ciuchetti
 */
class Conto{
private int saldo;
private boolean semaforo;

    /**
     * apertura conto con saldo iniziale e uso semaforo
     * 
     * @param saldo
     * @param semaforo 
     */
    public Conto(int saldo, boolean semaforo)
        {
            this.semaforo = semaforo;
            this.saldo = saldo;
        }
    
    /**
     * lettura saldo conto
     * 
     * @return saldo del conto gestito attraverso un semaforo
     */
    synchronized int getSaldo()
        {
            while (!semaforo)
              try {
                    wait();
                } catch (InterruptedException ex) {
                System.err.println("Errore nel wait()!");
                }
            semaforo = true;
            notifyAll();
            return saldo;      
        }

    /**
     * deposito di una sommma data con uso del semaforo
     * 
     * @param somma 
     */
    synchronized void deposito(int somma){
        while (!semaforo) {
            try {
                 wait();
            } catch (InterruptedException ex) {
                System.err.println("Errore nel wait()!");
            }
        }
        
        semaforo = false;
        saldo+=somma;
        System.out.println(Thread.currentThread() + " Deposito:"+somma+" saldo:"+saldo);
        semaforo = true;
        notifyAll();
       }
    
    /**
     * deposito di una sommma data con uso del semaforo
     * 
     * @param somma 
     */
    synchronized void prelievo(int somma){
        while (!semaforo){
            try {
                    wait();
            } catch (InterruptedException ex) {
                System.err.println("Errore nel wait()!");
            }
        }
        semaforo = false;
        if(saldo >= somma){
            saldo-=somma;
            System.out.println(Thread.currentThread() + " Prelievo:"+somma+" saldo:"+saldo);
        }
        else {
          System.out.println(Thread.currentThread() + " Saldo non sufficiente!");  
        }
        semaforo = true;
        notifyAll();
    }
  }
