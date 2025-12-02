void main() throws IOException {
    String inputData = Files.readString(Path.of("src/Day_2/input.txt"));
    String[] splitID = inputData.split("[,-]");
    ArrayList<Long> startNumber = new ArrayList<>();
    ArrayList<Long> endNumber = new ArrayList<>();
    ArrayList<String> invalidIDs = new ArrayList<>();

    separateIDs(splitID, startNumber, endNumber);
    compareIDs(startNumber, endNumber, invalidIDs);
    calculateSum(invalidIDs);
}

public static void separateIDs(String[] splitID,
                               ArrayList<Long> startNumber,
                               ArrayList<Long> endNumber) {

    for (int i = 0; i < splitID.length-1;) {
        startNumber.add(Long.valueOf(splitID[i]));
        i++;
        endNumber.add(Long.valueOf(splitID[i]));
        i++;
    }
}

public static void compareIDs(ArrayList<Long> startNumber,
                              ArrayList<Long> endNumber,
                              ArrayList<String> invalidIDs) {

        for (int i = 0; i < startNumber.size(); i++) {
        long numberOne = startNumber.get(i);
        long numberTwo = endNumber.get(i);

        while (numberOne <= numberTwo) {
            String fullNumberOne = String.valueOf(numberOne);
            int halfLength = fullNumberOne.length() / 2;
            String firstHalf = fullNumberOne.substring(0, halfLength);
            String secondHalf = fullNumberOne.substring(halfLength);
            if (firstHalf.equals(secondHalf)) {
                invalidIDs.add(fullNumberOne);
            }
            numberOne++;
        }
    }
}

public static void calculateSum(ArrayList<String> invalidIDs){
    long sum = 0;
    for (String id : invalidIDs) {
        sum += Long.parseLong(id);
    }
    System.out.println("The sum of all invalid IDs is: " + sum + ".");
}