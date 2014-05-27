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
		int minLen = (s1.length() < s2.length()) ? s1.length() : s2.length();
		
		int maxLen = (s1.length() >= s2.length()) ? s1.length() : s2.length();
		for (i = 0; i < minLen; ++i) {
			if ( s1.charAt(i) != s2.charAt(i) )
				break;
		}

		if( i >= maxLen ) {
			throw new Exception( "A diferenca aqui tah com algum problemaaaa!" );
		}

		return i;
	}

	public static char getChar( String str, int idx ) {
		int len = str.length();
		char umChar = ( len > idx ) ? str.charAt( idx ) : 0;
		return umChar;
	}
	
	
}
