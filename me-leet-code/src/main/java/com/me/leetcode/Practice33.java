package com.me.leetcode;

/**
 *  假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *  ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *  搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *  假设数组中不存在重复的元素。
 *   算法时间复杂度必须是 O(log n) 级别。
 *
 * @ClassName Practice33
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/19 9:21
 * @Version 1.0
 **/
public class Practice33 {
    public static void main(String[] args) {
        int[] arr=new int[]{4,5,6,7,0,1,2};
        System.out.println(search(arr,2));
        System.out.println(search2(arr,2));
    }

    /**
     *   4,5,6,7, 0,1,2    3
     *   二分法 找到中点
     *   一边有序 一边无序
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if(nums.length<0){return -1;}
        if(nums.length==1){return nums[0]==target?0:-1;}
            int start=0;
            int midpoint=nums.length/2;
            int end=nums.length-1;
            while (start<end){
                System.out.println(start+"--"+midpoint+"--"+end);
                if(nums[start]==target){return start;}
                if (nums[midpoint]==target){return midpoint;}
                if(nums[end]==target){return end;}
                // 判断哪边是有序的
                if(nums[start]<nums[midpoint]){
                    //前面是有序的
                    if(nums[start]<target&&nums[midpoint]>=target){
                        //在前面有序
                        end=midpoint;
                        midpoint=(start+end)/2;
                    }else {
                        start=midpoint+1;
                        midpoint=(start+end)/2;
                    }
                }else {
                    //后半段是有序的
                    if(nums[midpoint]<=target&&nums[end]>target){
                        start=midpoint;
                        midpoint=(start+end)/2;
                    }else {
                        end=midpoint+1;
                        midpoint=(start+end)/2;
                    }
                }
            }

        return -1;
    }
    public static int search2(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;

        while (start <= end){
            int mid = start + (end -start)/2;
            System.out.println(start+"=="+mid+"=="+end);
            if (nums[mid] == target){
                return mid;
            }

            //后半部分有序
            if(nums[mid] < nums[end]){
                if(nums[mid] < target && target <= nums[end]){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            } else {
                if(nums[mid] > target && target >= nums[start]){
                    end = mid - 1;

                } else {
                    start = mid + 1;

                }


            }

        }
        return -1;


    }

}
