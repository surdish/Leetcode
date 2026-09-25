import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        return new ArrayList<>(new TreeSet<>(result));
    }

    // Handles unions separated by commas
    private Set<String> parseExpression() {
        Set<String> result = parseConcatenation();

        while (index < s.length() && s.charAt(index) == ',') {
            index++;

            Set<String> next = parseConcatenation();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation
    private Set<String> parseConcatenation() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> current;

            if (s.charAt(index) == '{') {
                index++;
                current = parseExpression();
                index++; // skip '}'
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(index)));
                index++;
            }

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }
}