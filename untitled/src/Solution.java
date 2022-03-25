import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static final Integer MIN_NUMBER = 1;

    /*
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

    public static List<Integer> getMax(List<String> operations) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> maxNumbers = new ArrayList<>();

        Integer max = MIN_NUMBER;

        // Write your code here
        for (String op: operations){
            String[] splitedOp = splitOperation(op);
            Integer type = getType(splitedOp);

            if (shouldPush(type)){

                Integer value = getValue(splitedOp);
                pushElement(numbers, value);

                max = getMaxNumberOf(max, value);
            }
            else if (shouldDelete(type)){

                max = deleteElement(numbers, max);
            }
            else if (shouldGetMax(type)){

                maxNumbers.add(max);
            }
        }

        return maxNumbers;
    }

    private static String[] splitOperation(String operation){
        String[] splited = operation.split(" ");
        return splited;
    }

    private static Integer getValue(String[] splitedOperation){
        return Integer.parseInt(splitedOperation[1]);
    }

    private static Integer getType(String[] splitedOperation){
        return Integer.parseInt(splitedOperation[0]);
    }

    private static boolean shouldPush(Integer type){
        return type.equals(1);
    }

    private static boolean shouldDelete(Integer type){
        return type.equals(2);
    }

    private static boolean shouldGetMax(Integer type){
        return type.equals(3);
    }

    private static void pushElement(List<Integer> numbers, Integer number){
        numbers.add(number);
    }

    private static Integer deleteElement(List<Integer> numbers, Integer max){
        Integer removed = -1;

        if (!numbers.isEmpty()){
            removed = numbers.remove(numbers.size()-1);
        }

        if (max.equals(removed)){
            return findMaxNumber(numbers);
        }

        return max;
    }

    private static Integer findMaxNumber(List<Integer> numbers){
        Integer max = MIN_NUMBER;

        for (Integer n: numbers){
            if (n > max) {
                max = n;
            }
        }

        return max;
    }

    private static Integer getMaxNumberOf(Integer previous, Integer current){
        if (previous > current){
            return previous;
        }
        else{
            return current;
        }
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ops = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> res = Result.getMax(ops);

        bufferedWriter.write(
                res.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
