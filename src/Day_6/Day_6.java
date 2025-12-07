void main() throws IOException {
    List<String> input = Files.readAllLines(Paths.get("src/Day_6/input.txt"));
    String[][] grid = new String[input.size()][];
    List<Long> results = new ArrayList<>();

    for (int i = 0; i < input.size(); i++) {
        grid[i] = input.get(i).trim().split("\\s+");

    }
    calculate(grid, results);
    calculateSum(results);
}

public static void calculate(String[][] grid, List<Long> results) {
    List<Long> numbers = new ArrayList<>();
    int gridIndex = grid.length - 1;
    int colLength = grid[gridIndex].length;
    long result;

    for (int c = 0; c < colLength; c++) {
        switch (grid[gridIndex][c]) {

            case "*":
                result = 1;
                for (String[] row : grid) {
                    if (!row[c].equals("*")) {
                        numbers.add(Long.valueOf(row[c]));
                    }
                }
                for (Long number : numbers) {
                    result *= number;
                }
                results.add(result);
                numbers.clear();
                break;

            case "+":
                result = 0;
                for (String[] row : grid) {
                    if (!row[c].equals("+")) {
                        numbers.add(Long.valueOf(row[c]));
                    }
                }
                for (Long number : numbers) {
                    result += number;
                }
                results.add(result);
                numbers.clear();
                break;
        }
    }
}

public static void calculateSum(List<Long> results) {
    long sum = 0;

    for (Long result : results) {
        sum += result;
    }

    System.out.println("The sum of this homework is: " + sum + ".");
}
