import java.util.Arrays;

public class One {

        public static int[] twoSum(int[] nums, int target) {
            int front = 0, up = front +1;
            int[] num = new int[2];
            while(front <= nums.length) {
                if (up < nums.length) {
                    if (target == nums[front] + nums[up]) {
                        A:
                        for (int i = 0; i <= num.length - 1; i++) {
                            if (i == 0) num[i] = front;
                            else
                                num[i] = up;
                        }
                        return num;
                    }
                    up++;
                }
                else {
                    front++;
                    up = front + 1;
                }
            }
            return null;
        }

    public static void main(String[] args) {
        int[] nums= {3, 2, 3};
        System.out.println(Arrays.toString(twoSum(nums, 6)));//twoSum(nums, 9));
    }

}
