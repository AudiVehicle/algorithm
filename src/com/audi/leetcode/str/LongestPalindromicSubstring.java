package com.audi.leetcode.str;


/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * 参考链接：https://www.jianshu.com/p/a7741619dd58
 * https://www.cxyxiaowu.com/2869.html
 *
 * @author: WangQuanzhou
 * @date: 2020/11/1 15:39
 */
public class LongestPalindromicSubstring {

    private static final Character c = ',';

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder(c);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(s.charAt(i)).append(c);
        }
        String newStr = stringBuilder.toString();

        int newLen = newStr.length();
        int midPos = 1;
        int maxLen = 1;
        for (int i = 2; i < newLen; i++) {
            // 由中心向两边扩散
            int j = 0;
            for (j = 0; j <= i; i++) {
                int left = i - j;
                int right = i + j;
                // 如果两边字符不相等
                if (left < 0 || right >= newLen || newStr.charAt(left) != newStr.charAt(right)) {
                    break;
                }
            }
            if (j > maxLen) {
                maxLen = j;
                midPos = i;
            }
        }

        String substring = newStr.substring(midPos - maxLen, midPos + maxLen + 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == c) {
                continue;
            }
            builder.append(substring.charAt(i));
        }
        return builder.toString();

    }

    public static void main(String[] args) {
        String s = "ac";
        LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(palindromicSubstring.longestPalindrome(s));
    }
}