package br.com.siqueiradev.doc_validator.util;

public class CnpjValidator {

    public static boolean isValid(String cnpj) {
        // Remove tudo que não é número
        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) return false;
        if (cnpj.chars().distinct().count() == 1) return false; // Rejeita sequências tipo 111111...

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int sum = 0;
            for (int i = 0; i < 12; i++) sum += (cnpj.charAt(i) - '0') * weights1[i];
            int mod = sum % 11;
            char dv1 = (mod < 2) ? '0' : (char) ((11 - mod) + '0');

            sum = 0;
            for (int i = 0; i < 13; i++) sum += (cnpj.charAt(i) - '0') * weights2[i];
            mod = sum % 11;
            char dv2 = (mod < 2) ? '0' : (char) ((11 - mod) + '0');

            return dv1 == cnpj.charAt(12) && dv2 == cnpj.charAt(13);
        } catch (Exception e) {
            return false;
        }
    }
}
