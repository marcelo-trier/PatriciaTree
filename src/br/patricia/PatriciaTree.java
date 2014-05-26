package br.patricia;

import br.patricia.ui.TreeViewer;


public class PatriciaTree {

	public Nodo raiz = null;

	public void treina( String[] umaLista ) throws Exception {
		for (String vlr : umaLista ) {
			add(vlr);
		}
	}
	
	public Nodo adiciona(Nodo umNoh, String s) throws Exception {
		
		// verifica se o noh eh fim...
		if( umNoh.ehIgual( s ) )
			return umNoh;

		Nodo oPai = umNoh.pai;
		Nodo novoNoh = null;
		
		if( umNoh.ehFim() ) {
			novoNoh = new Nodo( TIPO.FIM );
			novoNoh.chave = s;

			Nodo novoMeio = new Nodo( TIPO.MEIO );
			
			if( umNoh != raiz )
				oPai.trocaFilho( umNoh, novoMeio );
			else {
				raiz = novoMeio;
			}
			
			novoMeio.adicionaFilhos( umNoh, novoNoh );
			return novoNoh;
		}

		if( umNoh.tipo != TIPO.MEIO )
			throw new Exception( "chegou em uma parte do codigo que nao entendi!" );

		// se nao eh fim, o NOH eh meio...
		int len = s.length();
		char umChar = ( umNoh.index < len ) ? s.charAt( umNoh.index ) : 0;
		
		if( umChar <= umNoh.c )
			novoNoh = adiciona( umNoh.filhos[ FILHOS.ESQ.ordinal() ], s );
		else
			novoNoh = adiciona( umNoh.filhos[ FILHOS.DIR.ordinal() ], s );
		
		return novoNoh;
	}

	public void add(String s) throws Exception {
		if (raiz == null) {
			raiz = new Nodo(TIPO.FIM);
			raiz.chave = s;
			raiz.pai = null;
			//raiz.filhos[0] = raiz.filhos[1] = raiz;
			return;
		}
		Nodo n = adiciona(raiz, s); // ao retornar a raiz terah q apontar para
									// algum lugar, não??
		//raiz = n;
	}
}
