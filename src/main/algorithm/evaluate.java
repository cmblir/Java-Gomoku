package main.algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class evaluate implements dictionary{

    public static ArrayList<ArrayList<String>> white6Patterns = new ArrayList<ArrayList<String>>() {{
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("white"); add("white"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("white"); add("white"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("white"); add("white"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("white"); add("empty"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("empty"); add("white"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("white"); add("white"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("white"); add("empty"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("empty"); add("white"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("white"); add("empty"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("empty"); add("white"); add("empty"); add("empty"); }});
    }};

    public static int[] white6Scores = {50000, 5000, 5000, 500, 500, 100, 100, 100, 10, 10};

    public static ArrayList<ArrayList<String>> white5Patterns = new ArrayList<ArrayList<String>>() {{
        add(new ArrayList<String>() {{ add("white"); add("white"); add("white"); add("white"); add("white"); }});
        add(new ArrayList<String>() {{ add("white"); add("white"); add("white"); add("white"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("white"); add("white"); add("white"); add("white"); }});
        add(new ArrayList<String>() {{ add("white"); add("white"); add("empty"); add("white"); add("white"); }});
        add(new ArrayList<String>() {{ add("white"); add("empty"); add("white"); add("white"); add("white"); }});
        add(new ArrayList<String>() {{ add("white"); add("white"); add("white"); add("empty"); add("white"); }});
    }};

    public static int[] white5Scores = {1000000, 5000, 5000, 5000, 5000, 5000};

    public static ArrayList<ArrayList<String>> black6Patterns = new ArrayList<ArrayList<String>>() {{
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("black"); add("black"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("black"); add("black"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("black"); add("black"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("black"); add("empty"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("empty"); add("black"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("black"); add("black"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("black"); add("empty"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("empty"); add("black"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("black"); add("empty"); add("empty"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("empty"); add("empty"); add("black"); add("empty"); add("empty"); }});
    }};

    public static int[] black6Scores = {50000, 5000, 5000, 500, 500, 100, 100, 100, 10, 10};

    public static ArrayList<ArrayList<String>> black5Patterns = new ArrayList<ArrayList<String>>() {{
        add(new ArrayList<String>() {{ add("black"); add("black"); add("black"); add("black"); add("black"); }});
        add(new ArrayList<String>() {{ add("black"); add("black"); add("black"); add("black"); add("empty"); }});
        add(new ArrayList<String>() {{ add("empty"); add("black"); add("black"); add("black"); add("black"); }});
        add(new ArrayList<String>() {{ add("black"); add("black"); add("empty"); add("black"); add("black"); }});
        add(new ArrayList<String>() {{ add("black"); add("empty"); add("black"); add("black"); add("black"); }});
        add(new ArrayList<String>() {{ add("black"); add("black"); add("black"); add("empty"); add("black"); }});
    }};

    public static int[] black5Scores = {1000000, 5000, 5000, 5000, 5000, 5000};

    public static boolean sublist(String[] small, String[] big) {
        for (int i = 0; i < big.length - small.length + 1; i++) {
            for (int j = 0; j < small.length; j++) {
                if (!big[i+j].equals(small[i+j])) {
                    break;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<String> enumToString(ArrayList<Integer> vector) {
        ArrayList<String> stringList = new ArrayList<>();
        for (int item : vector) {
            if (item == black) {
                stringList.add("black");
            } else if (item == white) {
                stringList.add("white");
            } else {
                stringList.add("empty");
            }
        }
        return stringList;
    }

    public static HashMap<String, Integer> evaluateVector(ArrayList<Integer> vector) {
        HashMap<String, Integer> score = new HashMap<>();
        score.put("white", 0);
        score.put("black", 0);
        ArrayList<String> stringList = enumToString(vector);
        int length = stringList.size();
        
        if (length == 5) {
            for (int i = 0; i < white5Patterns.size(); i++) {
                if (white5Patterns.get(i) == stringList) {
                    score.put("white", score.get("white") + white5Scores[i]);
                }
                if (black5Patterns.get(i) == stringList) {
                    score.put("black", score.get("black") + black5Scores[i]);
                }
            }
        }

        for (int i = 0; i < length - 5; i++) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(stringList.get(i), stringList.get(i + 1), stringList.get(i + 2)
                    , stringList.get(i + 3), stringList.get(i + 4)));
            for (int j = 0; j < white5Patterns.size(); j++) {
                if (white5Patterns.get(j) == temp) {
                    score.put("white", score.get("white") + white5Scores[j]);
                }
                if (black5Patterns.get(j) == temp) {
                    score.put("black", score.get("black") + black5Scores[j]);
                }
            }
        }

        for (int i = 0; i < length - 6; i++) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(stringList.get(i), stringList.get(i + 1), stringList.get(i + 2)
                    , stringList.get(i + 3), stringList.get(i + 4)
                    , stringList.get(i + 5), stringList.get(i + 6)));
            for (int j = 0; i < white6Patterns.size(); j++) {
                if (white6Patterns.get(j) == temp) {
                    score.put("white", score.get("white") + white6Scores[j]);
                }
                if (black6Patterns.get(j) == temp) {
                    score.put("black", score.get("black") + black6Scores[j]);
                }
            }
        }
        return score;
    } 
}
