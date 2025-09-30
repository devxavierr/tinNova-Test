package com.tinnova;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== QUESTÃO 2 - VOTOS EM ELEIÇÃO ===");
        VotosEleicao eleicao = new VotosEleicao();
        eleicao.calcularPercentuais(300, 150, 50, 20);
        
        System.out.println();
        
        System.out.println("=== QUESTÃO 3 - ORDENAÇÃO DE VETOR ===");
        OrdenacaoVetor ordenacao = new OrdenacaoVetor();
        int[] vetor = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("Vetor original: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + (i < vetor.length - 1 ? ", " : ""));
        }
        System.out.println();
        
        int[] vetorOrdenado = ordenacao.bubbleSort(vetor.clone());
        ordenacao.exibirVetor(vetorOrdenado);
        
        System.out.println();
        
        System.out.println("=== QUESTÃO 4 - FATORIAL ===");
        Fatorial fatorial = new Fatorial();
        int numero = 5;
        System.out.println(numero + "! (iterativo) = " + fatorial.calcularIterativo(numero));
        System.out.println(numero + "! (recursivo) = " + fatorial.calcularRecursivo(numero));
        System.out.println("0! = " + fatorial.calcularIterativo(0));
    }
}