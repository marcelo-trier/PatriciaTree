package br.patricia.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.patricia.ElementoArvore;

public class TreePanel extends JPanel {

	static public int CANVAS_WIDTH = 800;
	static public int CANVAS_HEIGHT = 600;

	private int rootY = 10;
	private int NODE_SIZE = 40;
	private int ROW_HEIGHT = 30;
	mxGraph graph = new mxGraph();
	Object parent = graph.getDefaultParent();

	public Object drawTree(ElementoArvore root, int depth, int index) {
		if (root == null) {
			return null;
		}

		// draw root
		int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));

		Object rootVertex = graph.insertVertex(parent, null, root.toString(),
				myX, depth * ROW_HEIGHT + rootY, NODE_SIZE, NODE_SIZE / 2);

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

	public void update(ElementoArvore root) {

		graph.getModel().beginUpdate();

		try {

			Object[] cells = graph.getChildCells(parent, true, false);
			graph.removeCells(cells, true);
			drawTree(root, 1, 1);

		} finally {
			graph.getModel().endUpdate();
		}
	}

	public TreePanel() {
		super();
	}

	public TreePanel(ElementoArvore root) {
		super();
		init(root);
	}

	public void init(ElementoArvore raiz) {
		setBorder(new EmptyBorder(5, 5, 5, 5));

		update(raiz);
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		add(graphComponent);
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		//setPreferredSize( new Dimension( CANVAS_WIDTH, CANVAS_HEIGHT ) );
	}
}
