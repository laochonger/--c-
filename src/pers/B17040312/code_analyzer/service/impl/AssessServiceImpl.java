package pers.B17040312.code_analyzer.service.impl;

import pers.B17040312.code_analyzer.service.AssessService;

import java.util.ArrayList;
import java.util.List;


/**
 * @title: AssessServiceImpl
 * @package pers.B17040312.code_analyzer.service.impl
 * @description: AssessService接口的实现类
 * @author: laochonger
 * @date: 2019-10-29 16:20
 * @version: V1.0
 */

public class AssessServiceImpl implements AssessService {
    /**
     * 储存对代码的评级结果
     */
    private List<String>RANKS = new ArrayList<>();

    /**
     *
     *@title: assessFunction
     *@description: 用于代码评级
     *@author: laochonger
     *@date: 2019-10-29 20:05
     *@param: [sumFunction, cntFunction]
     *@return: java.lang.String
     *@throws:
     */
    private String assessFunction(int sumFunction,int cntFunction){
        double averagrFunction = (double)sumFunction/(double)cntFunction;
        String rank = "A";
        if(averagrFunction>10.0&&averagrFunction<=16.0){
            rank = "A";
        }else if((averagrFunction>8.0&&averagrFunction<=10.0)
                ||(averagrFunction>15.0&&averagrFunction<=21.0)){
            rank = "B";
        }else if((averagrFunction>5.0&&averagrFunction<8.0)
                ||(averagrFunction>21.0&&averagrFunction<=24.0)){
            rank = "C";
        }else{
            rank = "D";
        }
        return rank;
    }

    /**
     *
     *@title: assessAnnotation
     *@description: 用于注释评级
     *@author: laochonger
     *@date: 2019-10-29 20:06
     *@param: [sum, sumAnnotation]
     *@return: java.lang.String
     *@throws:
     */
    private String assessAnnotation(int sum,int sumAnnotation){
        return getRank(sum, sumAnnotation);
    }

    /**
     *
     *@title: assessNullString
     *@description: 用于空行评级
     *@author: laochonger
     *@date: 2019-10-29 20:06
     *@param: [sum, sumNullString]
     *@return: java.lang.String
     *@throws:
     */
    private String assessNullString(int sum,int sumNullString){
        return getRank(sum, sumNullString);
    }

    /**
     *
     *@title: getRank
     *@description: 空行与注释的共同评级函数
     *@author: laochonger
     *@date: 2019-10-29 21:14
     *@param: [sum, sumNullString]
     *@return: java.lang.String
     *@throws:
     */
    private String getRank(double sum, double sumNullString) {
        double averagrFunction = sumNullString / sum *100.0;

        String rank = "A";
        if(averagrFunction>15.0&&averagrFunction<=26.0){
            rank = "A";
        }else if((averagrFunction>10.0&&averagrFunction<=15.0)
                ||(averagrFunction>26.0&&averagrFunction<=31.0)){
            rank = "B";
        }else if((averagrFunction>5.0&&averagrFunction<10.0)
                ||(averagrFunction>31.0&&averagrFunction<=35.0)){
            rank = "C";
        }else{
            rank = "D";
        }
        return rank;
    }

    /**
     *
     *@title: assess
     *@description: 实现接口声明的函数，得到代码、注释以及空行的评级
     *@author: laochonger
     *@date: 2019-10-29 20:06
     *@param: [sum, sumFunction, cntFunction, sumAnnotation, sumNullString]
     *@return: java.util.List<java.lang.String>
     *@throws:
     */

    @Override
    public List<String> assess(int sum, int sumFunction, int cntFunction, int sumAnnotation, int sumNullString) {
        RANKS.add("\r\n");
        RANKS.add("                      代码评级：    "+assessFunction(sumFunction,cntFunction));
        RANKS.add("                      注释评级：    "+assessAnnotation(sum,sumAnnotation));
        RANKS.add("                      空行评级：    "+assessNullString(sum,sumNullString));
        return RANKS;
    }
}
