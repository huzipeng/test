package map;

import java.util.ArrayList;
import java.util.List;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class MapTest {

    public static void main(String[] args) {
        int[] n = {9, 9, 6, 0, 6, 6, 9};
        System.out.println(init().longestWPI(n));
    }

//    //找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//    public List<List<Integer>> combinationSum3(int k, int n) {
//
//    }

    public int longestWPI(int[] hours) {
        int max = 0;
        int i = 0;
        int j = 0;
        for (int hour : hours) {
            if (hour > 8) {
                i++;
            } else {
                j++;
            }
            if (j >= i) {
                if (max < i) {
                    max = i;
                }
            }
        }
        return max;
    }

    public int singleNumber(int[] nums) {
        int flag = 0;
        for (int num : nums) {
            flag ^= num;
        }
        return flag;
    }
//    //翻转链表
//    public ListNode reverseList(ListNode head) {
//
//        return reverseListDg(head);
//    }
//
//    ListNode reverseListDg(ListNode head) {
//        while (head != null) {
//
//        }
//    }

    //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        System.out.println();
    }

    //合并二叉树 思路:其中有一个节点为null 返回另一个节点 否则相加 递归继续该操作
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    //翻转二叉树 思路:递归 每次进入递归交换位置
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode right = root.right;
        root.right = root.left;
        root.left = right;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    //二叉树最大深度
    public int maxDepth(TreeNode root) {
        return maxDepthDg(root, 0);
    }

    //思路 递归每次进入节点非null 递增最大长度 递归结果取最大值
    public int maxDepthDg(TreeNode root, int sd) {
        if (root == null) {
            return sd;
        }
        sd++;
        int i = maxDepthDg(root.left, sd);
        int i1 = maxDepthDg(root.right, sd);
        if (i > i1) {
            return i;
        } else {
            return i1;
        }
    }

    /**
     * 求汉明距离
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dg(result, root, "");
        return result;
    }

    private void dg(List<String> result, TreeNode root, String path) {
        if (root == null) {
            return;
        }
        //叶子节点
        if (root.left == null && root.right == null) {
            result.add(path + root.val);
            return;
        }
        if (root.left != null) {
            dg(result, root.left, root.val + "->");
        }
        if (root.right != null) {
            dg(result, root.right, root.val + "->");
        }
    }

    //给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
//换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
//以数组形式返回答案。
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] > nums[j]) {
                    result[i]++;
                }
            }
        }
        return result;
    }

    public int numberOfSteps(int num) {
        int result = 0;
        int item = num;
        while (item > 0) {
            if (item % 2 > 0) {
                item -= 1;
            } else {
                item = item / 2;
            }
            result++;
        }
        return result;
    }

    //给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
    //    目标数组 target 最初为空。
    //    按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
    //    重复上一步，直到在 nums 和 index 中都没有要读取的元素。
    //请你返回目标数组。
    //题目保证数字插入位置总是存在。
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    //给你一个以行程长度编码压缩的整数列表 nums 。
    //考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
    //请你返回解压后的列表。
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(nums[i + 1]);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int subtractProductAndSum(int n) {
        int i1 = n / 1000;

        String s = String.valueOf(n);
        int x = 1;
        int sum = 0;
        String[] split = s.split("");
        for (String i : split) {
            int aChar = Integer.parseInt(i);
            x *= aChar;
            sum += aChar;
        }
        return x - sum;
    }

    //桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
    public int minCount(int[] coins) {
        int count = 0;
        for (int coin : coins) {
            count += coin / 2;
            if (coin % 2 > 0) {
                count++;
            }
        }
        return count;
    }


    //小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？
    //输入的guess数组为 小A 每次的猜测，answer数组为 小B 每次的选择。guess和answer的长度都等于3。
    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 重新排列数组
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * 示例 1：
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     * 示例 2：
     * 输入：nums = [1,2,3,4,4,3,2,1], n = 4
     * 输出：[1,4,2,3,3,2,4,1]
     * 示例 3：
     * 输入：nums = [1,1,2,2], n = 2
     * 输出：[1,2,1,2]
     * 提示：
     * 1 <= n <= 500
     * nums.length == 2n
     * 1 <= nums[i] <= 10^3
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shuffle-the-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        for (int i = 0; i < n; i++) {
            result[i * 2] = nums[i];
            result[i * 2 + 1] = nums[i + n];
        }
        return result;
    }


    /**
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
     * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
     * 示例 1：
     * 输入：candies = [2,3,5,1,3], extraCandies = 3
     * 输出：[true,true,true,false,true]
     * 解释：
     * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 示例 2：
     * 输入：candies = [4,2,1,1,2], extraCandies = 1
     * 输出：[true,false,false,false,false]
     * 解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
     * 示例 3：
     * 输入：candies = [12,1,12], extraCandies = 10
     * 输出：[true,false,true]
     * 提示：
     * 2 <= candies.length <= 100
     * 1 <= candies[i] <= 100
     * 1 <= extraCandies <= 50
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        //分析出最大值
        List<Boolean> result = new ArrayList<>(candies.length);
        int max = 0;
        for (int candy : candies) {
            if (candy > max) {
                max = candy;
            }
        }

        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    /**
     * 给你一个整数数组 nums 。
     * <p>
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     * <p>
     * 返回好数对的数目。
     * <p>
     * 提示：
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-good-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numIdenticalPairs(int[] nums) {
//        //暴力破解
//        //时间复杂度：O(n^2)
//        //空间复杂度：O(1)
//        int count = 0;
////        for (int i = 0; i < nums.length; i++) {
////            for (int j = i + 1; j < nums.length; j++) {
////                if (nums[i] == nums[j]) {
////                    count++;
////                }
////            }
////        }
////        System.out.println(count);
////        count = 0;
//        //利用算法
//        //时间复杂度：O(n)
//        //空间复杂度：O(n)
//        Map<Integer, Integer> size = new HashMap<>();
//        for (int num : nums) {
//            size.putIfAbsent(num, 0);
//            size.put(num, size.get(num) + 1);
//        }
//        for (Integer i : size.values()) {
//            count += (i - 1) * i / 2;
//        }
////        System.out.println(count);
//        return count;

        int ans = 0;
        //因为 1<= nums[i] <= 100  所以申请大小为100的数组
        //temp用来记录num的个数
        int[] temp = new int[100];
        /*
        从前面开始遍历nums
        假设nums = [1,1,1,1]
        第一遍
        temp是[0,0,0,0]
        ans+=0;
        temp[0]++;
        第二遍
        temp是[1,0,0,0]
        ans+=1;
        temp[0]++;
        第三遍
        temp=[2,0,0,0]
        ans+=2;
        temp[0]++;
        第四遍
        temp=[3,0,0,0]
        ans+=3;
        temp[0]++;
        */
        for (int num : nums) {
            /*
            这行代码可以写成
            ans+=temp[num - 1];
            temp[num - 1]++;
            */
            ans += temp[num - 1]++;
        }
        return ans;


    }

    /**
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * <p>
     * 请返回 nums 的动态和。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];//初始化一个数组
        for (int n = 0; n < nums.length; n++) {
            if (n == 0) {
                result[n] = nums[n];
            } else {
                result[n] = result[n - 1] + nums[n];
            }
        }
//        for (int n = 1; n < nums.length; n++) {
//            nums[n] = nums[n - 1] + nums[n];
//        }

        return result;
    }

    /**
     * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * <p>
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jewels-and-stones
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStones(String J, String S) {
        int result = 0;
        char[] chars = S.toCharArray();
        for (char c : J.toCharArray()) {
            for (char aChar : chars) {
                if (c == aChar) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * ip地址无效化
     *
     * @param address
     * @return
     */
    public static String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    public static MapTest init() {
        return new MapTest();
    }

}
