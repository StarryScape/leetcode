package BinaryTree;

/**
 * 平衡二叉树相关
 */
public class BalancedBinaryTree {

    /**
     * 判断二叉树是否平衡
     *
     * @param root
     * @return 是否
     */
    boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return isBalanced;
    }

    int dfs(TreeNode root){
        if(isBalanced == false || root == null){
            return 0;
        }else{
            int left = dfs(root.left);
            int right = dfs(root.right);
            if(Math.abs(left - right) > 1)isBalanced = false;
            return Math.max(left, right) + 1;
        }
    }

    public long maxKelements(int[] nums, int k) {
        TopMaxHeap topMaxHeap = new TopMaxHeap(nums.length);
        long result = 0;
        for (int num : nums) {
            topMaxHeap.insert(num);
        }
        for (int i = 0; i < k; i++) {
            result += topMaxHeap.getMax();
            topMaxHeap.heapify();
            topMaxHeap.check();
        }
        return result;
    }

    public static class TopMaxHeap {
        int[] arr = null;

        int size = 0;

        public TopMaxHeap(int len) {
            this.arr = new int[len];
        }

        public int getMax(){
            return arr[0];
        }

        public void insert(int num) {
            int index = size;
            arr[index] = num;

            while(index != 0 && arr[index] > arr[(index-1)/2]) {
                arr[index] = arr[(index-1)/2];
                arr[(index-1)/2] = num;
                index = (index-1)/2;
            }
            size++;
        }

        public void heapify() {
            arr[0] = (int) Math.ceil((double) arr[0] /3);
            int index = 0;
            while((2*index+1 < size && arr[index] < arr[2*index+1]) || (2*index+2 < size && arr[index] < arr[2*index+2])) {
                int targetIndex = (2*index+1 < size && arr[index] < arr[2*index+1] && (2*index+2 >= size || arr[2*index+2]<= arr[2*index+1])) ? 2*index+1 : 2*index+2;
                int temp = arr[index];
                arr[index] = arr[targetIndex];
                arr[targetIndex] = temp;
                index = targetIndex;
            }
        }

        public void check() {
            for (int index = 0; index < size; index++) {
                if((2*index+1 < size && arr[index] < arr[2*index+1]) || (2*index+2 < size && arr[index] < arr[2*index+2])) {
                    throw new RuntimeException("");
                }
            }
        }
    }

    public static void main(String[] args) {
        new BalancedBinaryTree().maxKelements(new int[]{881784984,829998714,730002802,56524562,120336848,548306998,801116106,828640251,519131180,819176153,476262254,15904939,540793851,53572296,259044378,491129827,561147559,205793082,967833729}, 16);
    }
}
