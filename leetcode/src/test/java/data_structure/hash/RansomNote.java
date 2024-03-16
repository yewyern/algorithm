package data_structure.hash;

/**
 * <a href="https://leetcode.cn/problems/ransom-note">383. 赎金信</a>
 * @author xuzhou
 * @since 2023/12/21 18:28
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int m = ransomNote.length();
        int n = magazine.length();
        if (m > n) {
            return false;
        }
        int[] charCount = new int[26];
        for (char c : magazine.toCharArray()) {
            charCount[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (charCount[c - 'a'] == 0) {
                return false;
            }
            charCount[c - 'a']--;
        }
        return true;
    }
}
