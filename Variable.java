public class Variable extends Unop {
    private int index;

    public Variable(int d) {
        index = d;
    }

    public double eval(double[] values) {
        return values[index];
    }

    public String toString(){
        return "X"+(index);
    }
    
}
