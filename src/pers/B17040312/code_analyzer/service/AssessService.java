package pers.B17040312.code_analyzer.service;


import java.util.List;

/**
 * @title: AssessService
 * @package pers.B17040312.code_analyzer.service
 * @description: 用于评价代码等级的接口
 * @author: laochonger
 * @date: 2019-10-29 16:17
 * @version: V1.0
 */

public interface AssessService {

    /**
     *
     *@title: assess
     *@description: 传入统计代码的信息，返回对该代码的评级
     *@author: laochonger
     *@date: 2019-10-29 16:18
     *@param: [sum, sumFunction, cntFunction, sumAnnotation, sumNullString]
     *@return: java.util.List<java.lang.String>
     *@throws:
     */
    public List<String> assess(int sum,int sumFunction,int cntFunction,
                               int sumAnnotation, int sumNullString);
}
