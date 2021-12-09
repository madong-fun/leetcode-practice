package com.dong.leetcode.editor.cn;

/**
 * @Description TODO
 * @Author Ma Dong
 * @Date 2021/12/7 15:39
 **/
public class Solutions {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        System.out.println(solutions.longestPalindrome("aba"));
        System.out.println(solutions.longestPalindrome2("aba"));

        solutions.lastRemaining(5,3);
    }

    /**
     * 约瑟夫环
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {

        int x = 0;
        for (int i = 2; i <= n ; i++) {
            x = (x + m) % i;
        }
        return x;
    }


    public String preProcess(char[] chars, char divide){
        StringBuilder builder = new StringBuilder();
        builder.append(divide);
        for (int i = 0; i < chars.length ; i++) {
            builder.append(chars[i]).append(divide);
        }
        return builder.toString();
    }

    public String longestPalindrome2(String s){
        if (s == null || s.length() < 1)
            return s;

        String str = preProcess(s.toCharArray(),'#');
        int len = str.length();
        int[] p = new int[len];
        int center = 0 , max = 0;

        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;

        for (int i = 0; i < len ; i++) {
            if (i < max){
                int mirror = center * 2 - i;
                p[i] = Math.min(max-i,p[mirror]); // 放在超出右边界
            }

            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            while (left >= 0  && right < len && str.charAt(left) == str.charAt(right)){
                p[i]++;
                left --;
                right ++;
            }

            // 判断是否需要更新 max
            if (i + p[i] > max){
                center = i;
                max = i + p[i];
            }

            if (p[i] > maxLen){
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }

        }


        return s.substring(start, start + maxLen);
    }

    public String longestPalindrome(String s){

        if (s == null || s.length() < 1)
            return s;

        int start =0 ,end = 0;

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            // 由于存在奇数或者偶数回文字符串，所以需要从一个字符或者两个字符开始扩展
            int l1 = expendAroundCenter(chars,i,i);
            int l2 = expendAroundCenter(chars,i,i+1);
            int len = Math.max(l1,l2);
            if (len > end - start){
                start = i - (len - 1)/2; //
                end = i + len/2;
            }
        }

        return s.substring(start,end + 1);
    }


    public int expendAroundCenter(char[] chars, int left , int right ){
        int l = left;
        int r = right;
        while (l >=0 && r < chars.length && chars[l] == chars[r]){
            l --;
            r ++;
        }
        return r - l - 1;
    }
}
