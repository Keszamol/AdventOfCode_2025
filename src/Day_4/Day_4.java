public static final char PAPER = '@';
public static final int MAX = 4;

void main() throws IOException {
    List<String> inputData = Files.readAllLines(Paths.get("src/Day_4/input.txt"));

    char[][] inputGrid = new char[inputData.size()][];

    for (int i = 0; i < inputData.size(); i++) {
        inputGrid[i] = inputData.get(i).toCharArray();
    }

    calculateTotalPaper(inputGrid);
}

public static int calculateGridCorners(char[][] inputGrid) {
    int lastRowIndex = inputGrid.length - 1;
    int lastColIndex = inputGrid[0].length - 1;

    List<Character> gridCorners = List.of(
            inputGrid[0][0],
            inputGrid[0][lastColIndex],
            inputGrid[lastRowIndex][0],
            inputGrid[lastRowIndex][lastColIndex]
    );

    int paperCount = 0;
    for (char corner : gridCorners) {
        if (corner == PAPER) {
            paperCount++;
        }
    }
    return paperCount;
}

public static int calculateFirstRow(char[][] inputGrid) {
    int lastColIndex = inputGrid[0].length - 1;
    int paperCount = 0;

    List<int[]> firstRowComparison = List.of(
            new int[]{0, -1},
            new int[]{0, +1},
            new int[]{1, -1},
            new int[]{1, 0},
            new int[]{1, +1}
    );

    for (int colIndex = 1; colIndex < lastColIndex; colIndex++) {
        if (inputGrid[0][colIndex] != PAPER) {
            continue;
        }

        int adjacentCount = 0;

        for (int[] row : firstRowComparison) {
            int comparedRow = row[0];
            int comparedCol = colIndex + (row[1]);

            if (inputGrid[comparedRow][comparedCol] == PAPER) {
                adjacentCount++;
            }
        }
        if (adjacentCount < MAX) {
            paperCount++;
        }
    }
    return paperCount;
}

public static int calculateLastRow(char[][] inputGrid) {
    int lastColIndex = inputGrid[0].length - 1;
    int paperCount = 0;

    List<int[]> lastRowComparison = List.of(
            new int[]{-1, -1},
            new int[]{-1, 0},
            new int[]{-1, +1},
            new int[]{0, -1},
            new int[]{0, +1}
    );

    for (int colIndex = 1; colIndex < lastColIndex; colIndex++) {
        int rowIndex = inputGrid.length - 1;

        if (inputGrid[rowIndex][colIndex] != PAPER) {
            continue;
        }

        int adjacentCount = 0;

        for (int[] row : lastRowComparison) {
            int comparedRow = rowIndex + (row[0]);
            int comparedCol = colIndex + (row[1]);

            if (inputGrid[comparedRow][comparedCol] == PAPER) {
                adjacentCount++;
            }
        }
        if (adjacentCount < MAX) {
            paperCount++;
        }
    }
    return paperCount;
}

public static int calculateLeftEdges(char[][] inputGrid) {
    int lastRowIndex = inputGrid.length - 1;
    int paperCount = 0;

    List<int[]> rowComparison = List.of(
            new int[]{-1, 0},
            new int[]{-1, +1},
            new int[]{0, +1},
            new int[]{1, 0},
            new int[]{1, +1}
    );

    for (int rowIndex = 1; rowIndex < lastRowIndex; rowIndex++) {
        if (inputGrid[rowIndex][0] != PAPER) {
            continue;
        }

        int adjacentCount = 0;

        for (int[] row : rowComparison) {
            int comparedRow = rowIndex + (row[0]);
            int comparedCol = (row[1]);

            if (inputGrid[comparedRow][comparedCol] == PAPER) {
                adjacentCount++;
            }
        }
        if (adjacentCount < MAX) {
            paperCount++;
        }

    }
    return paperCount;
}

public static int calculateRightEdges(char[][] inputGrid) {
    int lastColIndex = inputGrid[0].length - 1;
    int lastRowIndex = inputGrid.length - 1;
    int paperCount = 0;

    List<int[]> rowComparison = List.of(
            new int[]{-1, 0},
            new int[]{-1, -1},
            new int[]{0, -1},
            new int[]{1, 0},
            new int[]{1, -1}
    );

    for (int rowIndex = 1; rowIndex < lastRowIndex; rowIndex++) {
        if (inputGrid[rowIndex][lastColIndex] != PAPER) {
            continue;
        }

        int adjacentCount = 0;

        for (int[] row : rowComparison) {
            int comparedRow = rowIndex + (row[0]);
            int comparedCol = lastColIndex + (row[1]);

            if (inputGrid[comparedRow][comparedCol] == PAPER) {
                adjacentCount++;
            }
        }
        if (adjacentCount < MAX) {
            paperCount++;
        }

    }
    return paperCount;
}


public static int calculateBetweenRows(char[][] inputGrid) {
    int lastColIndex = inputGrid[0].length - 1;
    int lastRowIndex = inputGrid.length - 1;
    int paperCount = 0;

    List<int[]> rowComparison = List.of(
            new int[]{-1, -1},
            new int[]{-1, 0},
            new int[]{-1, +1},
            new int[]{0, -1},
            new int[]{0, +1},
            new int[]{1, -1},
            new int[]{1, 0},
            new int[]{1, +1}
    );

    for (int rowIndex = 1; rowIndex < lastRowIndex; rowIndex++) {
        for (int colIndex = 1; colIndex < lastColIndex; colIndex++) {
            if (inputGrid[rowIndex][colIndex] != PAPER) {
                continue;
            }

            int adjacentCount = 0;

            for (int[] row : rowComparison) {
                int comparedRow = rowIndex + (row[0]);
                int comparedCol = colIndex + (row[1]);

                if (inputGrid[comparedRow][comparedCol] == PAPER) {
                    adjacentCount++;
                }
            }
            if (adjacentCount < MAX) {
                paperCount++;
            }
        }
    }
    return paperCount;
}

public static void calculateTotalPaper(char[][] inputGrid) {
    int totalPaper = calculateGridCorners(inputGrid) + calculateFirstRow(inputGrid) + calculateLastRow(inputGrid)
            + calculateBetweenRows(inputGrid) + calculateLeftEdges(inputGrid) + calculateRightEdges(inputGrid);

    System.out.println("The accessible amount of paper is a total of: " + totalPaper + ".");
}