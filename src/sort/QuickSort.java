package sort;


//快速排序
//找一个基准数，将大于基准数的放到右侧，将小于基准数的放到左侧，基准数归位
//递归小于基准数的队列和大于基准数的队列
public class QuickSort {

    public static int[] nums=new int[]{3,1,7,4,2,9,10,2,6};

    public static void sort(int left,int right){
        int i=left;
        int j=right;
        int base=nums[i];
        if(i >= j) return;
        //当哨兵i和j没有相遇的时候
        while (i!=j) {
            //将大于基准数的放到右侧
            while (i < j && nums[j] >= base) j--;
            //将小于基准数的放到左侧
            while (i < j && nums[i] <= base) i++;
            if(i<j) {
                //交换位置
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        //将基准数归位
        nums[left]=nums[i];
        nums[i]=base;
        //左侧队列排序
        sort(left,i-1);
        //右侧队列排序
        sort(j+1,right);
    }

    public static void main(String[] args){
        sort(0,nums.length-1);
        for (int a:nums)
            System.out.println(a);
    }

}
