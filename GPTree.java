import java.util.ArrayList;
import java.util.Random;

public class GPTree implements Collector, Comparable<GPTree>, Cloneable {
    private Node root;
    private ArrayList<Node> crossNodes;
    private double fitness;

    /**
     * @param - node The node to be collected.
     *          TODO: implement this method
     */
    public void collect(Node node) {
        if (!node.isLeaf()) crossNodes.add(node);
    }

    public void evalFitness(DataSet dataSet) {
        ArrayList<DataRow> data = dataSet.getRows();

        fitness = 0;
        for (int i = 0; i<data.size(); i++) {
            fitness += Math.pow(eval(data.get(i).getIndependentVariables()) - data.get(i).getDependentVariable(), 2);
        }
    }

    public double getFitness() {
        return fitness;
    }

    public int compareTo(GPTree tree) {
        if (getFitness() < tree.getFitness()) return -1;
        else if (getFitness() == tree.getFitness()) return 0;
        else return 1;
    }

    public boolean equals(GPTree tree) {
        if (tree == null) return false;
        if (compareTo(tree) == 0) return true;
        return false;
    }

    
    public Object clone() {
        try {
            GPTree cloneTree = (GPTree) super.clone();
            cloneTree.root = (Node) this.root.clone();
            cloneTree.crossNodes = new ArrayList<>();
            return cloneTree;
        } catch (CloneNotSupportedException e) {
            System.out.println("GPTree clone failed");
        }
        return null;
    }

    // DO NOT EDIT code below for Homework 8.
    // If you are doing the challenge mentioned in
    // the comments above the crossover method
    // then you should create a second crossover
    // method above this comment with a slightly
    // different name that handles all types
    // of crossover.

    /**
     * This initializes the crossNodes field and
     * calls the root Node's traverse method on this
     * so that this can collect the Binop Nodes.
     */
    public void traverse() {
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }

    /*
     * This returns a String with all of the binop Strings
     * separated by semicolons
     */
    public String getCrossNodes() {
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() - 1;
        for (int i = 0; i < lastIndex; ++i) {
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }

    /**
     * this implements left child to left child
     * and right child to right child crossover.
     * Challenge: additionally implement left to
     * right child and right to left child crossover.
     */
    public void crossover(GPTree tree, Random rand) {
        // find the points for crossover
        this.traverse();
        tree.traverse();
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get the connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);

        if (left) {
            thisTrunk.swapLeft(treeTrunk);

        } else {
            thisTrunk.swapRight(treeTrunk);
        }

    }

    GPTree() {
        root = null;
    }

    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
    }

    public String toString() {
        return root.toString();
    }

    public double eval(double[] data) {
        return root.eval(data);
    }

}