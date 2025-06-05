package org.example;


public class TransaccionRecibir extends Transaccion {


    public TransaccionRecibir(String cripto, double monto) {
        super(cripto, monto);
    }

    @Override
        public void procesar(Billetera billetera) {
            billetera.agregarCripto(cripto, monto);
            System.out.println("Recepción exitosa de " + monto + " " + cripto);
        }
    }


