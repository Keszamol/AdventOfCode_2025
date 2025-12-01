private static final int START_POSITION = 50;
private static final int MAX = 100;
private static final int MIN = 0;

void main() throws IOException {

    ArrayList<String> inputData = (ArrayList<String>) Files.readAllLines(Paths.get("src/input.txt"));
    ArrayList<Character> rotations = new ArrayList<>();
    ArrayList<Integer> oldPositions = new ArrayList<>();
    ArrayList<Integer> newPositions = new ArrayList<>();

    // splitting the input in two array lists (direction of rotation and the position)
    for (String rotation : inputData) {
        rotations.add(rotation.charAt(0));
        oldPositions.add(Integer.valueOf(rotation.substring(1)));
    }

    calculateStartPosition(newPositions, oldPositions, rotations);
    calculateCurrentPosition(newPositions, oldPositions, rotations);
    countPointZeros(newPositions);
}

public static void calculateStartPosition
        (ArrayList<Integer> newPositions,
         ArrayList<Integer> oldPositions,
         ArrayList<Character> rotations) {

    int oldPos;
    int newPos;

    if (rotations.getFirst() == 'R') {
        oldPos = START_POSITION;
        newPos = oldPos + oldPositions.getFirst();

        while (newPos >= MAX) {
            newPos = newPos - MAX;
        }
        newPositions.add(newPos);
    } else {
        oldPos = START_POSITION;
        newPos = oldPos - oldPositions.getFirst();

        while (newPos < MIN) {
            newPos = newPos + MAX;
        }
        newPositions.add(newPos);
    }
}

public static void calculateCurrentPosition
        (ArrayList<Integer> newPositions,
         ArrayList<Integer> oldPositions,
         ArrayList<Character> rotations) {

    int oldPos;
    int newPos;

    for (int i = 1; i < rotations.size(); i++) {
        if (rotations.get(i) == 'R') {
            oldPos = oldPositions.get(i);
            newPos = newPositions.get(i - 1) + oldPos;
            while (newPos >= MAX) {
                newPos = newPos - MAX;
            }
            newPositions.add(newPos);
        } else {
            oldPos = oldPositions.get(i);
            newPos = newPositions.get(i - 1) - oldPos;
            while (newPos < MIN) {
                newPos = newPos + MAX;
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