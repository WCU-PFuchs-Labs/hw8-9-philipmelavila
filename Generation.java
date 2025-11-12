import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generation {

    private DataSet data;
    private GPTree[] trees;

    public Generation(int size, int maxDepth, String fileName) {
        data = new DataSet(fileName);
        trees = new GPTree[size];
        for (int i = 0; i < size; i++) {
            Binop[] binops = {new Plus(), new Minus(), new Mult(), new Divide()};
            NodeFactory factory = new NodeFactory(binops, data.getNumIndependentVariables());
            Random rand = new Random();
            GPTree tree = new GPTree(factory, maxDepth, rand);
            trees[i] = tree; 
        }
        
    }

    public void evalAll() {
        for (GPTree tree : trees) {
            tree.evalFitness(data);
        }
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen() {
        evalAll();
        ArrayList<GPTree> returnList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            returnList.add(trees[i]);
        }
        return returnList;
    }

    public void printBestFitness() {
        evalAll();
        System.out.println(trees[0].getFitness());
    }

    public void printBestTree() {
        evalAll();
        System.out.println(trees[0]);
    }


    public void evolve() {
        evalAll();
        int repeatAmount = trees.length/2;
        Random rand = new Random();
        GPTree[] newGeneration = new GPTree[trees.length];


        for (int i = 0; i < repeatAmount; i++) {

            int parent1Index = rand.nextInt(trees.length / 2);
            int parent2Index = rand.nextInt(trees.length / 2);

            GPTree child1 = (GPTree) trees[parent1Index].clone();
            GPTree child2 = (GPTree) trees[parent2Index].clone();

            child1.crossover(child2, rand);

            newGeneration[2*i] = child1;
            newGeneration[(2*i) + 1] = child2;
            
        }

        trees = newGeneration;
    }


    
}
