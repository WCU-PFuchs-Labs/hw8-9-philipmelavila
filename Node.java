/**
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */

import java.util.Random;

public class Node extends Op {
    protected int depth;
    private Node left;
    private Node right;
    private Op operation;

    public Node(Unop operation) {
        this.operation = operation;
        left = null;
        right = null;
        depth = 0;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public void traverse(Collector c) {
        c.collect(this);
        if (left != null) left.traverse(c);
        if (right != null) right.traverse(c);
    }

    public void swapLeft(Node trunk) {
        Node temp = this.left;
        this.left = trunk.left;
        trunk.left = temp;

    }

    public void swapRight(Node trunk) {
        Node temp = this.right;
        this.right = trunk.right;
        trunk.right = temp;
    }

    public Node(Binop operation,Node left, Node right) {
        this.left = left;
        this.right = right;
        this.operation = operation;
        depth = 0;
    }


    public Node(Binop op) {
        this.operation = op;
        left = null;
        right = null;
        depth = 0;
    }
 

    public double eval(double[] values) {
        if (operation instanceof Unop) {
              return ((Unop)operation).eval(values);
        } else if (operation instanceof Binop) {
              return ((Binop)operation).eval(left.eval(values),right.eval(values));
        } else {
              System.err.println("Error operation is not a Unop or a Binop!");
              return 0.0;
        }
    }

    public String toString() {
        double[] empty = {};

        if (operation instanceof Const) {
            return String.format("%.2f", ((Const)operation).eval(empty));
        } else if (operation instanceof Variable){
            return ((Variable)operation).toString();
        } else if (operation instanceof Binop) {   
            return ("(" + left.toString() + operation.toString() + right.toString() + ")");
        } else {
            return "INVALID";
        }
    }


    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (Exception e) {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if (left != null) {
            b.left = (Node) left.clone();
        }
        if (right != null) {
            b.right = (Node) right.clone();
        }
        if (operation != null) {
            b.operation = (Op) operation.clone();
        }
        return o;
    }



    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        
        if (operation instanceof Unop) {
            return;
        }
        
        if (depth == maxDepth) {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;
            right = nf.getTerminal(rand);
            right.depth = depth + 1;
            return;
        }

        int number = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars() + 1);
        if (number < nf.getNumOps()) {
            left = nf.getOperator(rand);
            left.depth = depth + 1;
            left.addRandomKids(nf, maxDepth, rand);
        } else {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;
        }

        number = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars() + 1);
        if (number < nf.getNumOps()) {
            right = nf.getOperator(rand);
            right.depth = depth + 1;
            right.addRandomKids(nf, maxDepth, rand);
        } else {
            right = nf.getTerminal(rand);
            right.depth = depth + 1;
        }


    }


}
