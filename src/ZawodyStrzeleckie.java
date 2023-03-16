import java.io.*;
import java.util.*;


public class ZawodyStrzeleckie {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./resources/SHO.IN"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./resources/SHO.OUT"));

        int numberOfBlocks = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numberOfBlocks; ++i) {
            HashMap<Integer, List<String>> columnAndPossibleRowsMap = new HashMap<>();
            List<String> currentSteps = new ArrayList<>();
            String columnsAndRowsData = bufferedReader.readLine();

            int rows = Integer.parseInt(columnsAndRowsData.split(" ")[0]);
            int columns = Integer.parseInt(columnsAndRowsData.split(" ")[1]);

            for (int j = 0; j < columns; ++j) {
                columnAndPossibleRowsMap.put(j, Arrays.asList(bufferedReader.readLine().split(" ")));
            }

            stepToColumn(columnAndPossibleRowsMap, 0, 0, currentSteps, columns);

            LinkedHashSet<String> removedDuplicates = new LinkedHashSet<>(currentSteps);

            if (removedDuplicates.size() == rows) {
                bufferedWriter.write(String.join(" ", removedDuplicates) + "\n");
            } else {
                bufferedWriter.write("NO" + "\n");
            }

        }
        bufferedWriter.close();
    }

    public static void stepToColumn(Map<Integer, List<String>> columnAndPossibleRowsMap, Integer columnDepth, Integer nRowTry, List<String> currentSteps, Integer columns ) {
        if (columnDepth > -1 && columnDepth < columns && nRowTry <= 1) {
            if (nRowTry == 1) {
                currentSteps = currentSteps.subList(0, columnDepth);
            }
                currentSteps.add(columnAndPossibleRowsMap.get(columnDepth).get(nRowTry));

            if (new HashSet<>(currentSteps).size() > columnDepth) {
                stepToColumn(columnAndPossibleRowsMap, columnDepth + 1, 0, currentSteps, columns);
            } else if (nRowTry == 0){
                    stepToColumn(columnAndPossibleRowsMap, columnDepth, nRowTry + 1, currentSteps, columns);
            } else {
                stepToColumn(columnAndPossibleRowsMap, columnDepth - 1, nRowTry + 1, currentSteps, columns);
            }
        }
    }
}
