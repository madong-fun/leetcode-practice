//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 6517 👎 0


package com.dong.leetcode.editor.cn;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        String s = "au";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0 || s.length() == 1)
                return s.length();

            char[] chars = s.toCharArray();
            int i = 0, j = 0;
            int max = 0;
            for (i = 1; i < chars.length; i++) {

//                if (chars[i] == chars[i - 1]) {
//                    j = i;
//                }

                for (int k = j; k < i ; k++) {

                    if (chars[k] != chars[i]){
                        continue;
                    }

                    max = Math.max(max,i - j);
                    j = k + 1;
                }

//                if (chars[i] == chars[i - 1]) {
//                    j = i - 1 ;
//                }
//
//                if (j < i && chars[j] == chars[i]) {
//                    max = Math.max(max,i - j);
//                    j++;
//                }

            }

            max = Math.max(max, i - j);

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}