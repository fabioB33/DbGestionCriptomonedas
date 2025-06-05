package org.example;


public class TransaccionEnviar extends Transaccion {


    public TransaccionEnviar(String cripto, double monto) {
        super(cripto, monto);
    }

    @Override
        public void procesar(Billetera billetera) {
            if (billetera.enviarCripto(cripto, monto)) {
                System.out.println("Envío exitoso de " + monto + " " + cripto);
            } else {
                System.out.println("Fondos insuficientes para enviar " + monto + " " + cripto);
            }
        }
    }


