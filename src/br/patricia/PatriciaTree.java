package br.patricia;


public class PatriciaTree {

	public Nodo raiz = null;

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
		if( s.charAt( umNoh.index ) <= umNoh.c )
			novoNoh = adiciona( umNoh.filhos[ FILHOS.ESQ.ordinal() ], s );
		else
			novoNoh = adiciona( umNoh.filhos[ FILHOS.DIR.ordinal() ], s );
		
		return novoNoh;
	}

	public void add(String s) throws Exception {
		if (raiz == null) {
			raiz = new Nodo(TIPO.FIM);
			raiz.chave = s;
			raiz.pai = raiz;
			//raiz.filhos[0] = raiz.filhos[1] = raiz;
			return;
		}
		Nodo n = adiciona(raiz, s); // ao retornar a raiz terah q apontar para
									// algum lugar, não??
		//raiz = n;
	}
}
