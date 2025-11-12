import java.util.Random;

public class NodeFactory {
    private int numIndepVars;
    private Node[] currentOps;

    NodeFactory(Binop[] b, int numVars) {
        numIndepVars = numVars;
        currentOps = new Node[4];
        for (int i = 0; i<4; i++) {
            currentOps[i] = new Node(b[i]);
        }

    }

    public Node getOperator(Random rand) {
        return (Node) currentOps[rand.nextInt(getNumOps())].clone();
    }

    public int getNumOps() {
        return currentOps.length;
    }

    public Node getTerminal(Random rand) {
        int num = rand.nextInt(numIndepVars + 1);
        if (num < numIndepVars) {
            return new Node(new Variable(num));
        }
        return new Node(new Const(rand.nextDouble()));
    }

    public int getNumIndepVars() {
        return numIndepVars;
    }
}