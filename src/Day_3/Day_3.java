void main () throws IOException {
    ArrayList<String> inputData = (ArrayList<String>) Files.readAllLines(Paths.get("src/Day_3/input.txt"));
    ArrayList<Integer> highestBatteries = new ArrayList<>();

    calculateHighesBatteries(inputData, highestBatteries);
    calculateSum(highestBatteries);
}

public static void calculateHighesBatteries
        (ArrayList<String> inputData,
         ArrayList<Integer> highestBatteries) {

    for (String currentBatteriePack : inputData) {
        int highestLeftDigit = 0;
        int highestBatteriePair = 0;

        for (char batterie : currentBatteriePack.toCharArray()) {
            int batterieValue = batterie - '0';

            if (highestLeftDigit > 0) {
                int batteriePairValue = highestLeftDigit * 10 + batterieValue;

                if (batteriePairValue > highestBatteriePair) {
                    highestBatteriePair = batteriePairValue;
                }
            }
            if (batterieValue > highestLeftDigit) {
                highestLeftDigit = batterieValue;
            }
        }

        highestBatteries.add(highestBatteriePair);
        System.out.println(highestBatteriePair);
    }
}

public static void calculateSum(ArrayList<Integer> highestBatteries) {
    int sum = 0;

    for (int batterie : highestBatteries) {
        sum+= batterie;
    }
    System.out.println("The total 'joltage' value of the highest batteries is: " + sum + ".");
}