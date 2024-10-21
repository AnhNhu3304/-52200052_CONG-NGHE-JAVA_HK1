import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;
public class Program{
    public Program(){
    }
    public static void main(String[] var0){
        int[] var1 = new int[]{12, 2, 24, 5, 6};
        int[] var2 = new int[]{7, 3, 11, 23};
        ArrayOutput.print(var1);
        ArrayOutput.print(var2);
        int[] var3 = ArrayHandler.merge(var1, var2);
        ArrayOutput.print(var3);
        ArrayHandler.sort(var3);
        ArrayOutput.print(var3);
        ArrayOutput.write(var3, "output.txt");
    }
}