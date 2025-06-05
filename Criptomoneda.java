package org.example;
// Clase Criptomoneda

    public class Criptomoneda {
        private String nombre;
        private double cotizacion;

        public Criptomoneda(String nombre, double cotizacion) {
            this.nombre = nombre;
            this.cotizacion = cotizacion;
        }

        public String getNombre() {
            return nombre;
        }

        public double getCotizacion() {
            return cotizacion;
        }

        public void setCotizacion(double nuevaCotizacion) {
            this.cotizacion = nuevaCotizacion;
        }
    }


