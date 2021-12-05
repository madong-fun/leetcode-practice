  //给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
// Related Topics 数组 二分查找 分治 👍 4727 👎 0


  package com.dong.leetcode.editor.cn;

  public class MedianOfTwoSortedArrays {
      public static void main(String[] args) {
          Solution solution = new MedianOfTwoSortedArrays().new Solution();
          int[] nums1 = new int[]{1,3};
          int[] nums2 = new int[]{2};

          System.out.println(solution.findMedianSortedArrays(nums1,nums2));

      }

      //leetcode submit region begin(Prohibit modification and deletion)
      class Solution {
          public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int len = nums1.length > nums2.length ? nums2.length : nums1.length ;

              int[] temp = new int[nums1.length + nums2.length];

              int x = 0, y = 0 , i = 0;

              for (; i < temp.length && x < nums1.length && y < nums2.length ; i++) {

                  if (nums1[x] <= nums2[y]){
                      temp[i] = nums1[x];
                      x ++;
                  }else {
                      temp[i] = nums2[y];
                      y ++ ;
                  }

              }

              if (y < nums2.length){
                  for (int j = y; j < nums2.length ; j++,i++) {
                    temp[i] = nums2[j];
                  }
              }

              if (x < nums1.length){
                  for (int j = x; j < nums1.length ; j++,i++) {
                      temp[i] = nums1[j];
                  }
              }
              int mid = temp.length / 2 ;
              if (temp.length % 2 == 0){
                  return (temp[mid] + temp[mid - 1])/2.0;
              }else {
                  return temp[mid];
              }
          }
      }
//leetcode submit region end(Prohibit modification and deletion)

  }