private static final int START_POSITION = 50;
private static final int MAX = 100;
private static final int MIN = 0;

void main() throws IOException {

    ArrayList<String> inputData = (ArrayList<String>) Files.readAllLines(Paths.get("src/Day_1/input.txt"));
    ArrayList<Character> rotations = new ArrayList<>();
    ArrayList<Integer> oldPositions = new ArrayList<>();
    ArrayList<Integer> newPositions = new ArrayList<>();
    ArrayList<Integer> zeroCounts = new ArrayList<>();

    // splitting the input in two array lists (direction of rotation and the position)
    for (String rotation : inputData) {
        rotations.add(rotation.charAt(0));
        oldPositions.add(Integer.valueOf(rotation.substring(1)));
    }

    calculateStartPosition(newPositions, oldPositions, rotations, zeroCounts);
    calculateCurrentPosition(newPositions, oldPositions, rotations, zeroCounts);
    countPointZeros(newPositions);
    countAllZeros(zeroCounts);
}

public static void calculateStartPosition
        (ArrayList<Integer> newPositions,
         ArrayList<Integer> oldPositions,
         ArrayList<Character> rotations,
         ArrayList<Integer> zeroCounts) {

    int prevPos = START_POSITION;
    int oldPos = oldPositions.getFirst();
    int newPos = prevPos + oldPos;
    int zeros;

    if (rotations.getFirst() == 'R') {
        int distToZero = 100 - prevPos;
        if (oldPos < distToZero) {
            zeros = 0;
        } else {
            zeros = 1 + (oldPos - distToZero) / 100;
        }
        zeroCounts.add(zeros);

        if (newPos >= MAX) {
            newPos = newPos % 100;
        }
        newPositions.add(newPos);
    } else {
        if (oldPos < prevPos) {
            zeros = 0;
        } else {
            zeros = 1 + (oldPos - prevPos) / 100;
        }
        zeroCounts.add(zeros);

        if (newPos < MIN) {
            int zero = (-newPos + 99) / 100;
            newPos += 100 * zero;
        }
        newPositions.add(newPos);
    }
}

public static void calculateCurrentPosition
        (ArrayList<Integer> newPositions,
         ArrayList<Integer> oldPositions,
         ArrayList<Character> rotations,
         ArrayList<Integer> zeroCounts) {

    int oldPos;
    int newPos;
    int zeros;

    for (int i = 1; i < rotations.size(); i++) {
        int prevPos = newPositions.get(i - 1);

        if (rotations.get(i) == 'R') {
            oldPos = oldPositions.get(i);
            newPos = prevPos + oldPos;

            if (prevPos == 0) {
                zeros = oldPos / 100;
            } else {
                int distToZero = 100 - prevPos;
                if (oldPos < distToZero) {
                    zeros = 0;
                } else {
                    zeros = 1 + (oldPos - distToZero) / 100;
                }
            }
            zeroCounts.add(zeros);

            if (newPos >= MAX) {
                newPos = newPos % 100;
            }
            newPositions.add(newPos);

        } else {
            oldPos = oldPositions.get(i);
            newPos = prevPos - oldPos;

            if (prevPos == 0) {
                zeros = oldPos / 100;
            } else if (oldPos < prevPos) {
                zeros = 0;
            } else {
                zeros = 1 + (oldPos - prevPos) / 100;
            }
            zeroCounts.add(zeros);

            if (newPos < MIN) {
                int zero = (-newPos + 99) / 100;
                newPos += 100 * zero;
            }
            newPositions.add(newPos);
        }
    }
}

public static void countPointZeros(ArrayList<Integer> newPositions) {
    int count = 0;

    for (Integer zero : newPositions) {
        if (zero == 0) {
            count++;
        }
    }
    IO.println("The dial is pointing at zero for " + count + " times.");
}

public static void countAllZeros(ArrayList<Integer> zeroCounts) {
    int sum = 0;

    for (Integer zero : zeroCounts){
        sum+= zero;
    }
    IO.println("The dial is rotated over zero for " + sum + " times.");
}