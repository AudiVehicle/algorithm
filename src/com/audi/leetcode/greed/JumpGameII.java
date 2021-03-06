package com.audi.leetcode.greed;


/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * @author: WangQuanzhou
 * @date: 2020/5/31 19:14
 */
public class JumpGameII {


    /**
     * 53 / 92 test cases passed.
     * <p>
     * 借鉴了JumpGame的思想，也维护了一个每个位置可以到达的最远位置的数组index。然后循环遍历index数组，当最大值发生变化时，更新step
     * <p>
     * 但是这里，忽略了一个思想，当更新最大值时，step也应该同步跳至该最远位置，因此没有完全AC
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        // 如果数组为空  或者只有一步，那么不需要走（因为起点就在第一步）
        if (null == nums || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int len = nums.length;
        // index[i]表示在第i个位置最远可以跳跃到的位置
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i + nums[i];
        }

        // 代表当前所处的位置
        int jump = 0;
        // 表示当前位置可以跳跃的最远位置
        int maxIndex = index[0];

        int step = 0;

        while (jump < len && jump <= maxIndex) {

            // 维护maxIndex
            if (maxIndex < index[jump]) {
                maxIndex = index[jump];
                step++;
            }

            // 如果当前位置已经可以直接跳到末尾，就直接跳出循环，返回true
            if (index[jump] >= len - 1) {
                // 因为jump从0开始计算，但是实际走的步数需要进行+1操作
                // 下面不能写成jump++
                return ++step;
            }

            jump++;
//            System.out.println("跳到第 " + jump + " 位置");
        }
        return step;
    }


    /**
     * https://www.iteye.com/blog/huntfor-2056997
     * <p>
     * 照抄参考链接里的一句话
     * <p>
     * 由于本题并没有要求我们计算出跳跃路径，因此我们可以单纯的求跳数， 采取贪心的算法，比如2,3,4,1,0,5对于2，其覆盖范围是3,4,，
     * 因此我们只需要求3,4，的最大覆盖范围即可，既然在4的覆盖范围中求下一跳的最大覆盖范围。每一次更新覆盖范围，就说明要进行一跳。
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }

        // index 数组表示每个nums[i]位置可以跳到最远位置的index
        int[] index = new int[length];
        for (int i = 0; i < length; i++) {
            index[i] = i + nums[i];
        }

//        int step = 1;
//        int fromIndex = 0;
//        int toIndex = index[0];
//        for (int i = 0; i < length; ) {
//            for (int j = fromIndex; j <= index[i] && j < length; j++) {
//                if (toIndex < index[j]) {
//                    toIndex = index[j];
//                }
//            }
//            step++;
//            if (toIndex >= length) {
//                break;
//            }
//            fromIndex = toIndex;
//            i = toIndex;
//        }
//        return step;

        // 下面的代码对于1, 1, 1, 1这种测试用例还是不能通过
//        int step = 0;
//        int max_cur = 0;
//        for (int i = 0; i < length; ) {
//            int max = index[i];
//            for (int j = max_cur; j <= max && j < length; j++) {
//                if (max < index[j]) {
//                    max = index[j];
//                    max_cur = j;
//                }
//            }
//
//            step++;
//            if (max >= length) {
//                return step;
//            }
//
//            if (i != max_cur) {
//                i = index[i];
//            } else {
//                i = max;
//            }
//        }
//        return step;


        /*

        下面的代码很多变量是不必要的，可以省去

        int step = 0;
        for (int i = 0; i < length; ) {
            // max表示从pre_max到max_index过程中可以跳跃的最远位置
            int max = index[i];
            // 表示从pre_max位置可以跳到的最远index
            int max_index = index[i];
            // pre_max表示本次起跳的起点位置
            int pre_max = i;


            step++;

            if (max >= length - 1) {
                return step;
            }

            for (int j = pre_max; j <= max_index && j < length; j++) {
                if (max < index[j]) {
                    max = index[j];
                    pre_max = j;
                }
            }

            i = pre_max;

        }
        return step;*/


        // 这里对上面紧邻的代码进行优化，主要是省去部分没有太大实际意义的中间变量
        int step = 0;
        for (int i = 0; i < length; ) {
            // max表示从pre_max到max_index过程中可以跳跃的最远位置
            int max = index[i];
            // pre_max表示本次起跳的起点位置
            int pre_max = i;


            step++;

            // 表示本次已经可以跳跃至末尾
            if (max >= length - 1) {
                return step;
            }

            for (int j = i; j <= index[i] && j < length; j++) {
                if (max < index[j]) {
                    max = index[j];
                    pre_max = j;
                }
            }

            // 设置新的起跳点
            i = pre_max;

        }
        return step;
    }


    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {1, 2};
//        int[] nums = {1, 2, 3};
//        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
//        int[] nums = {1, 1, 1, 1};
        int[] nums = {1, 2, 1, 1, 1};
//        int[] nums = {0};
        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump2(nums));
    }
}
