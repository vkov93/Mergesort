import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class Merge {

    public static void start(File fileOne, File fileTwo, File file_result){

        try(BufferedReader readerOne = new BufferedReader(new FileReader(fileOne));
                BufferedReader readerTwo = new BufferedReader(new FileReader(fileTwo));
                    FileWriter writer = new FileWriter(file_result)){

                System.out.println("Starting merge files " + fileOne.getName()
                        + " and " + fileTwo.getName()
                        + " in " + file_result.getName());

                int a = Integer.parseInt(readerOne.readLine());
                int b = Integer.parseInt(readerTwo.readLine());

               while(true){
                    if (a <= b) {
                            writer.write(a + "\r\n");
                            //
                            if (readerOne.ready()) {
                                a = Integer.parseInt(readerOne.readLine());
                                if(!readerOne.ready() && !readerTwo.ready()) {
                                    writer.write(a + "\r\n");
                                    break;
                                }
                            } else a = Integer.MAX_VALUE;
                            //
                    }else {
                            writer.write(b + "\r\n");
                            //
                            if (readerTwo.ready()) {
                                b = Integer.parseInt(readerTwo.readLine());
                                if(!readerOne.ready() && !readerTwo.ready()) {
                                    writer.write(b + "\r\n");
                                    break;
                                }
                            } else b = Integer.MAX_VALUE;
                            //
                    }
                }


                System.out.println("Merge files " + fileOne.getName()
                        + " and " + fileTwo.getName()
                        + " success - " + file_result.getName());


        }catch(Exception e){
            System.out.println("Merge files " + fileOne.getName() + " and " + fileTwo.getName() + " Failed!");
            e.printStackTrace();
        }
        fileTwo.delete();
        fileOne.delete();

    }


    public static void mergeAll(File dir){
            if(dir.isDirectory() && dir.listFiles() != null){
                while(dir.listFiles().length >= 2){
                    Merge.start(dir.listFiles()[0], dir.listFiles()[1], Merge.createFile(dir, dir.listFiles()[0]));
                }
            }

    }


    public static File createFile(File dir, File file){
        String fullname = file.getName();
        String name = fullname.substring(0, fullname.indexOf('.'));
        return new File(dir, "a" + name + ".tmp");
    }


}
