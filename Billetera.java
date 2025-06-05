package org.example;

import java.util.HashMap;
import java.util.Map;

public class Billetera {
    private String alias;
    private Map<String, Double> criptomonedas;

    public Billetera(String alias) {
        this.alias = alias;
        this.criptomonedas = new HashMap<>();
    }

    public void agregarCripto(String cripto, double cantidad) {
        criptomonedas.put(cripto, criptomonedas.getOrDefault(cripto, 0.0) + cantidad);
    }

    public boolean enviarCripto(String cripto, double cantidad) {
        if (criptomonedas.containsKey(cripto) && criptomonedas.get(cripto) >= cantidad) {
            criptomonedas.put(cripto, criptomonedas.get(cripto) - cantidad);
            return true;
        }
        return false;
    }

    public void mostrarSaldo() {
        System.out.println("Alias: " + alias);
        for (String cripto : criptomonedas.keySet()) {
            System.out.println(cripto + ": " + criptomonedas.get(cripto));
        }
    }

    public String getAlias() {
        return alias;
    }
}




