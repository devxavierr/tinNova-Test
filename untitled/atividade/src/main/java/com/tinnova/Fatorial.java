package com.tinnova;

public class Fatorial {
    
    public long calcularIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fatorial não definido para números negativos");
        }
        
        long resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    
    public long calcularRecursivo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fatorial não definido para números negativos");
        }
        
        if (n == 0 || n == 1) {
            return 1;
        }
        
        return n * calcularRecursivo(n - 1);
    }
}