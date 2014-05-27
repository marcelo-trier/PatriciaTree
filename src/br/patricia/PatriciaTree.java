package br.patricia;

import br.patricia.util.IndexUtils;
import br.patricia.util.StringUtil;


public class PatriciaTree {

	public Nodo raiz = null;

	public void treina( String[] umaLista ) throws Exception {
		for (String vlr : umaLista ) {
			add(vlr);
		}
	}
	
	public Nodo buscaSemelhante( Nodo umNodo, String compara ) {

		// quais situacoes aqui irah estar nulo?? Acho q somente a raiz... =)
		if( umNodo == null )
			return null;

		// se for folha... jah retorna...
		if( umNodo.tipo == TIPO.FIM )
			return umNodo;

		char umChar = IndexUtils.getChar( compara, umNodo.index );
		if( umChar <= umNodo.c )
			return buscaSemelhante( umNodo.filhos[ FILHOS.ESQ.ordinal() ], compara );
		else
			return buscaSemelhante( umNodo.filhos[ FILHOS.DIR.ordinal() ], compara );
		
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
		//int len = s.length();
		//char umChar = ( umNoh.index < len ) ? s.charAt( umNoh.index ) : 0;
		
		char umChar = IndexUtils.getChar( s, umNoh.index );
		
		if( umChar <= umNoh.c )
			novoNoh = adiciona( umNoh.filhos[ FILHOS.ESQ.ordinal() ], s );
		else
			novoNoh = adiciona( umNoh.filhos[ FILHOS.DIR.ordinal() ], s );
		
		return novoNoh;
	}

	public void adicionaMeio( Nodo umNodo, Nodo novoMeio ) throws Exception {

		// ufa, encontrou uma folha... agora simm...
		if( umNodo.tipo == TIPO.FIM ) {
			
			//if( umNodo == raiz )
			//	raiz = novoMeio;
			for( int i=0; i<novoMeio.filhos.length; i++ ) {
				if( novoMeio.filhos[i] == null ) {
					novoMeio.filhos[i] = umNodo;
					Nodo pai = umNodo.pai;
					if( pai == null && umNodo == raiz ) {
						umNodo.pai = novoMeio;
						novoMeio.pai = null;
						raiz = novoMeio;
						//novoMeio.adicionaFilho( umNodo );
					} else if( pai != null && umNodo != raiz )
						pai.trocaFilho(umNodo, novoMeio);
					else
						throw new Exception( "problemas ao adicionar ao meio" );
				}
			}
		} else {
			if( novoMeio.index < umNodo.index  ){
				Nodo pai = umNodo.pai;
				if( pai == null && umNodo == raiz ) {
					umNodo.pai = novoMeio;
					novoMeio.pai = null;
					raiz = novoMeio;
					novoMeio.adicionaFilho( umNodo );
				} else if( pai != null && umNodo != raiz )
					pai.trocaFilho( umNodo, novoMeio );
				else
					throw new Exception( "aaaaaaaaaa1" );
			} else if( novoMeio.index > umNodo.index ) {
				adicionaMeio( umNodo.filhos[ FILHOS.DIR.ordinal() ], novoMeio );
			} else {
				if( novoMeio.c <= umNodo.index ) {
					Nodo pai = umNodo.pai;
					if( pai == null && umNodo == raiz ) {
						umNodo.pai = novoMeio;
						novoMeio.pai = pai;
						raiz = novoMeio;
						novoMeio.adicionaFilho( umNodo );
					} else if( pai != null && umNodo != raiz )
						pai.trocaFilho( umNodo, novoMeio );
					else
						throw new Exception( "bbbbbbbb2" );
				} else {
					adicionaMeio( umNodo.filhos[ FILHOS.ESQ.ordinal() ], novoMeio );
				}
			}
			
		}
	}

	
	public void add( String s ) throws Exception {
		if( raiz == null ) {
			raiz = new Nodo( TIPO.FIM );
			raiz.chave = s;
			return;
		}
		
		Nodo novaFolha = new Nodo( TIPO.FIM );
		novaFolha.chave = s;

		Nodo umNodo = buscaSemelhante( raiz, s );
		Nodo novoMeio = new Nodo( TIPO.MEIO );
		novoMeio.adicionaFilho( novaFolha, umNodo.chave );
		
		adicionaMeio( raiz, novoMeio );
	}
	
	public void add__OLD(String s) throws Exception {
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
