package math;

public class Solution {

    /**
     * 给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。
     * 如果满足以下条件，那么箱子是 "Bulky" 的： 箱子 至少有一个 维度大于等于 10 4 。 或者箱子的 体积 大于等于 10 9 。
     * 如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
     * 如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
     * 如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
     * 如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
     * 如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
     * 注意，箱子的体积等于箱子的长度、宽度和高度的乘积。
     * @param length
     * @param width
     * @param height
     * @param mass
     * @return
     */
    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isHeavy = mass > 100;
        boolean isBulky = length>10000 || width > 10000 || height > 10000 || mass > 10000;
        if(!isBulky) {
            isBulky = ((long)length * width * height) > 1000000000;
        }
        if(isBulky&&isHeavy){
            return "Both";
        }
        if(!isBulky && !isHeavy) {
            return "Neither";
        }
        return isBulky?"Bulky":"heavy";
    }

    /**
     * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
     * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
     * 1 <= i <= n
     * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
     * @param n
     * @return
     */
//    public int punishmentNumber(int n) {
//
//    }
}
