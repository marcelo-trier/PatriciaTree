package br.patricia.util;

public class StringUtil {
	// irah retornar [0 ... len()], ou -1 se nao encontrar nenhuma diferenca...
	public static int indiceDaDiferenca(String s1, String s2) throws Exception {

		if ( s1 == null || s2 == null || s1 == s2 || s1.equals( s2 ) ) {
			throw new Exception(
					"Vc tah tentanto comparar chaves nulas??? ou as mesmas chaves??");
		}
		
		int i;
		// pega o menor valor
		int maxI = (s1.length() < s2.length()) ? s1.length() : s2.length();

		for (i = 0; i < maxI; ++i) {
			if ( s1.charAt(i) != s2.charAt(i) )
				break;
		}

		return (i < maxI) ? i : -1;
	}

}
