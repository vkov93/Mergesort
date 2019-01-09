import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class StartFile {
    private static final int NUMBER_IN_FILE = 8192;

    private static int randInt(Random random){
        int result = random.nextInt();
        return result;
    }

    public static void create(String path, int qt){
        System.out.print("Start creating file... ");
        if(qt < 0) return;
        Random random = new Random(System.currentTimeMillis());
        try(FileWriter writer = new FileWriter(new File(path))){
            for(int i = 0; i < qt; i++){
                writer.write(randInt(random) + "\r\n");
            }
            System.out.println("Create success!");
        }catch(Exception e){
            System.out.println("Cant create file!!!");
            e.printStackTrace();
        }
    }

    public static void divideAndSort(String path){
        File dir = new File("C:\\test\\temp");
        dir.mkdir();
        boolean last = false;
        int[] array = new int[NUMBER_IN_FILE];
        int[] lastarray = {};
        int file_count = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            while(reader.ready()){
                for(int i = 0; i < NUMBER_IN_FILE; i++){
                    if(reader.ready()) array[i] = Integer.parseInt(reader.readLine());
                    else {
                       lastarray = Arrays.copyOf(array, i);
                       last = true;
                       break;
                    }
                }
                if(last){
                    Arrays.sort(lastarray);
                    writeArray(dir, lastarray, file_count);
                    break;
                }
                else {
                    Arrays.sort(array);
                    writeArray(dir, array, file_count++);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeArray(File dir, int[] array, int x){
        if(dir.isDirectory()) {
            try (FileWriter writer = new FileWriter(new File(dir, x + ".tmp"))) {
                for (int i : array) {
                    writer.write(i + "\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
