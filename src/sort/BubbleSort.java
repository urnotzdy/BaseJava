package sort;


//冒泡排序
//依次比较相邻的两个元素，将较大的放到右侧
//直到遍历数组，没有需要交换元素的时候，就结束
public class BubbleSort {

    public static void main(String[] args){
        int[] nums={3,2,8,9,5,7,1,2};
        for(int i=0;i<nums.length;i++){
            boolean flag=false;
            for(int j=0;j<nums.length-i-1;j++){
                if(nums[j]>nums[j+1]){
                    int temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
        }
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }

}
