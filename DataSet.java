
/**
 * Author:
 * Date:
 * Purpose:
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DataSet {

    private ArrayList<DataRow> data;
    private int numIndepVariables;

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename)
    {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            data = new ArrayList<DataRow>();

            String[] titles = reader.nextLine().split(",");
            numIndepVariables = titles.length - 1;

            while (reader.hasNextLine()) {
                String[] values = reader.nextLine().split(",");

                double y = Double.parseDouble(values[0]);
                double[] x = new double[numIndepVariables];
                for (int i = 0; i < numIndepVariables; i++) {
                    x[i] = Double.parseDouble(values[i + 1]);
                }

                data.add(new DataRow(y, x));

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(filename + " is an Invalid File");
        }

    }

    /**
     * @return the list of rows
     */
    public ArrayList<DataRow> getRows() {
        return data;
    }

    /**
     * @return the number of independent variables in each row of the data set
     */
    public int getNumIndependentVariables() {
        return numIndepVariables;
    }
}
