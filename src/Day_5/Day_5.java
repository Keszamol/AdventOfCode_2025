void main() throws IOException {
    BufferedReader inputData = new BufferedReader(new FileReader("src/Day_5/input.txt"));
    List<String> idRanges = new ArrayList<>();
    List<String> foodIds = new ArrayList<>();

    List<String> current = idRanges;
    String line;

    while ((line = inputData.readLine()) != null) {
        if (line.trim().isEmpty()) {
            current = foodIds;
            continue;
        }
        current.add(line);
    }
    inputData.close();

    List<long[]> ranges = parseRanges(idRanges);
    long result = countFreshFood(ranges,foodIds);

    System.out.println("The amount of fresh food is: " + result + ".");
}

public static List<long[]> parseRanges (List<String> idRanges) {
    List<long[]> ranges = new ArrayList<>();

    for (String range : idRanges) {
        String[] parts = range.split("-");
        long start = Long.parseLong(parts[0]);
        long end = Long.parseLong(parts[1]);

        ranges.add(new long[] {start, end});
    }
    return ranges;
}

public static long countFreshFood (List<long[]> ranges, List<String> foodIds) {
    long freshFoodCount = 0;

    for (String id : foodIds) {
        long currentId = Long.parseLong(id);

        for (long[] range : ranges) {
            long start =  range[0];
            long end = range[1];

            if (currentId >= start && currentId <= end) {
                freshFoodCount++;
                break;
            }
        }
    }
    return freshFoodCount;
}