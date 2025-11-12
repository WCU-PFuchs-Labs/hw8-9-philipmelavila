import java.util.Scanner;

public class TestGPTree {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the name of the data file: ");
        String fileName = scanner.nextLine();
        Generation generation = new Generation(500, 3, fileName);
        generation.evalAll();
        
        for (int generationNum = 1; generationNum <= 50; generationNum++) {
            System.out.println("Generation " + generationNum + ":");
            System.out.println("Best Tree: ");
            generation.printBestTree();
            System.out.print("Best Fitness: ");
            generation.printBestFitness();
            generation.evolve();
        }
        
        scanner.close();
    }
}
