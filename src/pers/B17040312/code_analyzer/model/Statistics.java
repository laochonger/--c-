package pers.B17040312.code_analyzer.model;

/**
 * @title: Statistics
 * @package pers.B17040312.code_analyzer.model
 * @description: 用于统计代码长度、个数以及在统计时用到的前字符标记
 * @author: laochonger
 * @date: 2019-10-29 15:59
 * @version: V1.0
 */
public class Statistics {

    private int cnt;
    private int sum;
    private int preChar;


    public int getCnt() {


        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPreChar() {
        return preChar;
    }

    public void setPreChar(int preChar) {
        this.preChar = preChar;
    }

    /**
     *
     * @description: 构造函数
     * @param: []
     *
     */
    public Statistics(){
        this.cnt=0;
        this.sum=0;
        this.preChar= 0;
    }
}
