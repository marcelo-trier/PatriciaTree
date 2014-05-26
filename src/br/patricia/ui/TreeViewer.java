package br.patricia.ui;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

// from: https://github.com/jgraph/jgraphx
// from: http://stackoverflow.com/questions/22535847/bst-graphical-representation-needed-in-java


class Elemento implements ElementoUI {
	Elemento direita = null;
	Elemento esquerda = null;
	int umDado = 0;

	public Elemento( int vlr ) {
		umDado = vlr;
	}

	public Elemento getDireita() {
		return direita;
	}
	public Elemento getEsquerda() {
		return esquerda;
	}

	public String toString() {
		return "" + umDado;
	}
}

public class TreeViewer extends JFrame {

	static public int CANVAS_HEIGHT = 1000;
	static public int CANVAS_WIDTH = 1500;

	private int rootY = 10;
	private int NODE_SIZE = 40;
	private int ROW_HEIGHT = 30;
	mxGraph graph = new mxGraph();
	Object parent = graph.getDefaultParent();

	public Object drawTree(ElementoUI root, int depth, int index) {
		if (root == null) {
			return null;
		}

		// draw root

		/*
		 * leftChildIndex = parentIndex * 2 - 1 rightChildIndex = parentIndex *2
		 * 
		 * 
		 * x = index * canvasWidth / (2^depth + 1)
		 * 
		 * y = depth * canvasHeight / treeDepth
		 */

		int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));

		Object rootVertex = graph.insertVertex(parent, null, root.toString(),
				myX, depth * ROW_HEIGHT + rootY, NODE_SIZE, NODE_SIZE/2);

		//Logger.log("new x coordinate=" + myX);

		// recurse for right child

		Object rightChildVertex = drawTree(root.getDireita(), depth + 1,
				index * 2);

		if (rightChildVertex != null) {// edge
			graph.insertEdge(parent, null, null, rootVertex, rightChildVertex,
					"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=red");
		}

		Object leftChildVertex = drawTree(root.getEsquerda(), depth + 1,
				index * 2 - 1);

		// recurse for right child

		if (leftChildVertex != null) { // edge
			graph.insertEdge(parent, null, null, rootVertex, leftChildVertex,
					"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=green");
		}

		return rootVertex;

	}

	/**
	 * Redraw the whole tree
	 * 
	 * @param root
	 *            the root of tree to be drawn
	 */

	public void update(ElementoUI root) {

		graph.getModel().beginUpdate();

		try {

			Object[] cells = graph.getChildCells(parent, true, false);
			graph.removeCells(cells, true);
			drawTree(root, 1, 1);

		} finally {
			graph.getModel().endUpdate();
		}
	}

	public TreeViewer(ElementoUI root) {
		// super("Hello, World!");

		this.update(root);

		mxGraphComponent graphComponent = new mxGraphComponent(graph);

		getContentPane().add(graphComponent);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( CANVAS_WIDTH, CANVAS_HEIGHT);
	}


	public static void main(String[] args) {

		Elemento raiz;

		raiz = new Elemento( 0 );
		
		ArrayList<Elemento> lista = new ArrayList<Elemento>();
		
	    Elemento vez;
		Elemento dir;
		Elemento esq;

		lista.add( raiz );
	    int cont = 1;
	    for (int i = 0; i < 13; i++) {//try generating 20 random integers
	    	vez = lista.remove( 0 );

	    	esq = new Elemento( cont++ );
	    	lista.add( esq );
	    	vez.esquerda = esq;
	    	
	    	dir = new Elemento( cont++ );
	    	vez.direita = dir;
	    	lista.add( dir );
	    }

		
		TreeViewer myTreeViewer = new TreeViewer(	raiz );
		JFrame frame = myTreeViewer;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		frame.setVisible(true);

	}

}
