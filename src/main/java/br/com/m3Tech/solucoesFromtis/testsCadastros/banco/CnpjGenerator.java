package br.com.m3Tech.solucoesFromtis.testsCadastros.banco;
import java.util.Random;

public class CnpjGenerator {
    
	public static String geraCNPJ() {
        StringBuilder cnpj = new StringBuilder();
        Random rand = new Random();
        
        // Gera os primeiros 8 dígitos do CNPJ de forma aleatória
        for (int i = 0; i < 8; i++) {
            cnpj.append(rand.nextInt(10));
        }
        cnpj.append("0001"); // Insere os quatro dígitos da inscrição estadual
        cnpj.append("00"); // Insere os dois dígitos verificadores temporários
        
        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int resto = soma % 11;
        int dv1 = (resto < 2) ? 0 : 11 - resto;
        cnpj.append(dv1);
        
        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        resto = soma % 11;
        int dv2 = (resto < 2) ? 0 : 11 - resto;
        cnpj.append(dv2);
        
        return cnpj.toString();
    }
    
    private static int calculateVerifierDigit(int[] cnpj, int start, int end) {
        int sum = 0;
        int weight = start;
        
        // Calcula o valor da soma dos dígitos multiplicados pelos pesos
        for (int i = 0; i < end; i++) {
            sum += cnpj[i] * weight;
            weight--;
            if (weight < 2) {
                weight = 9;
            }
        }
        
        // Calcula o valor do dígito verificador
        int remainder = sum % 11;
        int digit;
        if (remainder < 2) {
            digit = 0;
        } else {
            digit = 11 - remainder;
        }
        return digit;
    }
    
    // Exemplo de uso
    public static void main(String[] args) {
        String cnpj = geraCNPJ();
        System.out.println(cnpj);
    }
}


