  //给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4429 👎 0


  package com.dong.leetcode.editor.cn;

  /**
   * Manacher 算法
   *
   */
  public class LongestPalindromicSubstring {
      public static void main(String[] args) {
          Solution solution = new LongestPalindromicSubstring().new Solution();

          System.out.println(solution.longestPalindrome("ab"));
          System.out.println(solution.longestPalindrome2("ab"));
      }

      //leetcode submit region begin(Prohibit modification and deletion)
      class Solution {

          /**
           * Manacher 算法 优化
           * @param s
           * @return
           */
          public String longestPalindrome(String s){
              int len = s.length();
              if (s == null || len < 1)
                  return s;
              // 添加辅助字符
              String str = addBoundaries(s,'#');

              int sLen = len * 2 + 1;
              int max = 1;
              int start = 0;
              int maxRight = 0 ,center = 0;

              int[] p = new int[sLen];
              for (int i = 0; i < sLen ; i++) {

                if (i < maxRight){
                    int mirror = center * 2 - i;
                    p[i] = Math.min(maxRight - 1 , p[mirror]);
                }

                  // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
                  int left = i - (1 + p[i]);
                  int right = i + (1 + p[i]);

                  while (left >= 0  && right < sLen && str.charAt(left) == str.charAt(right)){
                      p[i]++;
                      left --;
                      right ++;
                  }

                  // 判断是否需要更新 maxRight
                  if (i + p[i] > maxRight){
                      center = i;
                      maxRight = i + p[i];
                  }

                  if (p[i] > max){
                      // 记录最长回文子串的长度和相应它在原始字符串中的起点
                      max = p[i];
                      start = (i - max) / 2;
                  }

              }


              return s.substring(start, start + max);
          }

          public String longestPalindrome2(String s) {

              int len = s.length();
              if (len < 2){
                  return s;
              }
              // 添加辅助字符
              String str = addBoundaries(s,'#');

              int sLen = len * 2 + 1;
              int max = 1;

              int start = 0;

              for (int i = 0; i < sLen; i++) {
                  int curLen = centerSpread(str,i);
                  if (curLen > max){
                      max = curLen;
                      start = (i - max)/2;
                  }
              }

              return s.substring(start,start + max);
          }

          /**
           * 中芯扩展法
           *
           * @param s
           * @param center
           * @return
           */
          private int centerSpread(String s, int center) {
              // left = right 的时候，此时回文中心是一个空隙，回文串的长度是奇数
              // right = left + 1 的时候，此时回文中心是任意一个字符，回文串的长度是偶数
              int len = s.length();
              int i = center - 1;
              int j = center + 1;
              int step = 0;
              while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
                  i--;
                  j++;
                  step++;
              }
              return step;
          }
      private String addBoundaries(String s, char divide){
          int len = s.length();
          if (len == 0) {
              return "";
          }
          if (s.indexOf(divide) != -1) {
              throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
          }
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = 0; i < len; i++) {
              stringBuilder.append(divide);
              stringBuilder.append(s.charAt(i));
          }
          stringBuilder.append(divide);
          return stringBuilder.toString();
      }
      }
//leetcode submit region end(Prohibit modification and deletion)

  }