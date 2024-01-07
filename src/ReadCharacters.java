import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCharacters {

    private static ReadCharacters instance;
    private final int[][] matrix = new int[14][14];

    private ReadCharacters() {
        this.readMatrixFromFile();
    }


    public static synchronized ReadCharacters getInstance() {
        //synchronized: thread control
        if (instance == null) {
            instance = new ReadCharacters();
        }
        return instance;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    private void readMatrixFromFile() {

        try {
            File file = new File("game_characters.txt");
            Scanner scanner = new Scanner(file);

            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(" ");
                for (int col = 0; col < values.length; col++) {
                    this.matrix[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());
        }
    }

}
