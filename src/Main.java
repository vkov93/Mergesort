import java.io.File;

public class Main {
    public static void main(String[] args){

        StartFile.create("C:\\test\\start.txt", 9000000);
        StartFile.divideAndSort("C:\\test\\start.txt");

        File dir = new File("C:\\test\\temp");
        Merge.mergeAll(dir);

    }

}
