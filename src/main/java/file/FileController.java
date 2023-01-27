package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileController {

    private static File outputFile = new File("src/resources/output.txt");
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;



    public static void writeToFile(String detail, String price) throws IOException {
        fileWriter = new FileWriter(outputFile);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Product detail: " + detail);
        bufferedWriter.newLine();
        bufferedWriter.write("Product price: " + price);
        bufferedWriter.close();
    }

}
