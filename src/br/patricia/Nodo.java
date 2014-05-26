package br.patricia;

import br.patricia.ui.ElementoUI;
import br.patricia.util.StringUtil;

enum TIPO {
	FIM, MEIO;
}

enum FILHOS {
	DIR, ESQ;
}

class Nodo implements ElementoUI {
	Nodo filhos[] = { null, null }; // filhos da direita ou esquerda
	Nodo pai = null;
	int index = 0; // claro, aqui eh o indice da diferenca...
	char c = 0; // o caracter do nodo
	String chave = null; // a chave que o nodo terá, caso seja terminal
	TIPO tipo = TIPO.FIM; // aqui uma informação dizendo se o nodo é terminal

	public Nodo(TIPO t) {
		tipo = t;
	}

	public void adicionaFilho( Nodo umNoh ) {
		
	}
	
	
	// soh adiciona filhos quem eh do meio... =)
	public void adicionaFilhos( Nodo umNoh, Nodo outroNoh ) throws Exception {
		tipo = TIPO.MEIO;
		chave = null;

		index = StringUtil.indiceDaDiferenca( umNoh.chave, outroNoh.chave );

		if( index < 0 ) { // isso quer dizer que sao iguais...
			throw new Exception( "tentativa de adicionar filhos com mesma string... verificar!" );
		}
		
		if( umNoh.chave.charAt( index ) == outroNoh.chave.charAt( index ) )
			throw new Exception( "O indice da diferenca dos dois nodos eh iguall... verificar!" );
		
		if( umNoh.chave.charAt( index ) < outroNoh.chave.charAt( index ) ) {
			c = umNoh.getChar( index );
			filhos[ FILHOS.ESQ.ordinal() ] = umNoh;
			filhos[ FILHOS.DIR.ordinal() ] = outroNoh;
		} else {
			c = outroNoh.getChar( index );
			filhos[ FILHOS.ESQ.ordinal() ] = outroNoh;
			filhos[ FILHOS.DIR.ordinal() ] = umNoh;
		}

		for( int i=0; i<filhos.length; i++ ) {
			filhos[i].pai = this;
		}
	}
	
	public void trocaFilho( Nodo oFilho, Nodo outroNoh ) throws Exception {

		for( int i=0; i<filhos.length; i++ ) {
			if( filhos[ i ] != oFilho )
				continue;
			filhos[ i ].pai = null; // apaga a relacao q o filho tem com o pai
			outroNoh.pai = this; // o novo filho aponta para o pai..
			filhos[ i ] = outroNoh; // o pai reconhece o novo filho
			return;
		}
		
		throw new Exception( "Nao encontrou nenhum filho... estranhooo" );
	}
	
	public void trocaFilho__OLD( Nodo oFilho, Nodo outroNoh ) throws Exception {

		for( int i=0; i<filhos.length; i++ ) {
			if( filhos[ i ] != oFilho )
				continue;
			filhos[ i ].pai = null; // apaga a relacao q o filho tem com o pai
			outroNoh.pai = this; // o novo filho aponta para o pai..
			filhos[ i ] = outroNoh; // o pai reconhece o novo filho
			return;
		}
		
		throw new Exception( "Nao encontrou nenhum filho... estranhooo" );
	}
	
	public static char getChar( String s, int i ) {
		if( s.length() <= i )
			return 0;
		return s.charAt( i );
	}
	
	public char getChar( int i ) {
		return getChar( chave, i );
	}
	
	public boolean ehIgual( String s ) {
		if( chave == null )
			return false;
		if( s == null )
			return false;
		
		return chave.equalsIgnoreCase( s );
	}
	
	public boolean ehFim() throws Exception {
		if( tipo == TIPO.FIM && chave == null )
			throw new Exception( "ERROOO! Existe um noh fim sem chaveee!!" );
		return ( tipo == TIPO.FIM );
	}
	
	public boolean ehMenor( int dif, String s ) throws Exception {
		if( chave.charAt( dif ) == s.charAt( dif ) )
			throw new Exception( "Erro! Os caracteres não deveriam ser iguais... :(" );

		return ( getChar( dif ) < getChar( s, dif ) );		
	}

	@Override
	public ElementoUI getDireita() {
		return filhos[ FILHOS.DIR.ordinal() ];
	}

	@Override
	public ElementoUI getEsquerda() {
		return filhos[ FILHOS.ESQ.ordinal() ];
	}
	
	@Override
	public String toString() {
		String str;
		if( tipo == TIPO.FIM )
			str = chave;
		else 
			str = ""+index+"/"+this.c;
		return str;
	}
}
