package org.example;

public abstract class Transaccion {
    protected String cripto;
    protected double monto;

    public Transaccion(String cripto, double monto) {
        this.cripto = cripto;
        this.monto = monto;
    }

    public abstract void procesar(Billetera billetera);
}
