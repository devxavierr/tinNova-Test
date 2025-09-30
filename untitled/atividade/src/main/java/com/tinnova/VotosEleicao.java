package com.tinnova;

public class VotosEleicao {
    
    public void calcularPercentuais(int candidatoX, int candidatoY, int nulos, int brancos) {
        int total = candidatoX + candidatoY + nulos + brancos;
        
        double percentualX = (candidatoX * 100.0) / total;
        double percentualY = (candidatoY * 100.0) / total;
        double percentualNulos = (nulos * 100.0) / total;
        double percentualBrancos = (brancos * 100.0) / total;
        
        System.out.println("Candidato X: " + candidatoX + " votos (" + String.format("%.2f", percentualX) + "%)");
        System.out.println("Candidato Y: " + candidatoY + " votos (" + String.format("%.2f", percentualY) + "%)");
        System.out.println("Nulos: " + nulos + " votos (" + String.format("%.2f", percentualNulos) + "%)");
        System.out.println("Brancos: " + brancos + " votos (" + String.format("%.2f", percentualBrancos) + "%)");
        System.out.println("Total: " + total + " votos");
    }
}