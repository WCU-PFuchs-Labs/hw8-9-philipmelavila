import java.util.ArrayList;
import java.util.Scanner;

public class TestGeneration {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the name of the data file: ");
        String fileName = scanner.nextLine();
        
        System.out.println("Creating generation of 500 GPTrees...");
        Generation generation = new Generation(500, 3, fileName);
        
        System.out.println("\nBest Tree:");
        generation.printBestTree();
        
        System.out.println("\nBest Fitness:");
        generation.printBestFitness();
        
        ArrayList<GPTree> topTen = generation.getTopTen();
        System.out.print("Top Ten Fitness Values: ");
        for (int i = 0; i < topTen.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.printf("%.2f", topTen.get(i).getFitness());
        }
        System.out.println();
        
        scanner.close();
    }
}
