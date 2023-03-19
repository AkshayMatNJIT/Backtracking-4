// Approach: Backtracking

import java.util.*;

class BraceExpansion {
    private List<String> result;
    public String[] expand(String s) {
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if (c == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    char ch = s.charAt(i);
                    if (ch != ',') block.add(ch);
                    i++;
                }
            } else {
                block.add(c);
            }
            i++;
            blocks.add(block);
        }
        backtrack(blocks, 0, new StringBuilder());
        String[] arr = new String[result.size()];
        for (int k = 0; k<arr.length; k++) {
            arr[k] = result.get(k);
        }
        Arrays.sort(arr);
        return arr;
    }
    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb) {
        // base case
        if (idx == blocks.size()) {
            result.add(sb.toString());
            return;
        }
        // logic
        List<Character> block = blocks.get(idx);
        for (int i = 0; i<block.size(); i++) {
            char ch = block.get(i);
            // action
            sb.append(ch);
            // recurse
            backtrack(blocks, idx+1, sb);
            // backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}