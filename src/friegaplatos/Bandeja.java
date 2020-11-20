package friegaplatos;

import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Bandeja {

    private ArrayBlockingQueue<Plato> platosEnBandeja = new ArrayBlockingQueue<>(10);

    Lock reLock = new ReentrantLock();
    Condition noVacio = reLock.newCondition();

    public Bandeja(int nPlatos) {
        for (int i=0;i<nPlatos;i++){
            platosEnBandeja.add(new Plato(i));
        }
    }

    public void meterPlato(Plato plato, String role) throws InterruptedException {
        platosEnBandeja.put(plato);
        System.out.println(LocalTime.now() + " -- " + role + " put the dish numbered with the serial: " + plato.getSerial());
    }

    public Plato sacarPlato(String role) throws InterruptedException {
        Plato plato = platosEnBandeja.take();
        System.out.println(LocalTime.now() + " -- " +role + " took the dish numbered with the serial: " + plato.getSerial());
        return plato;
    }

}