//package pers.B17040312.code_analyzer.service.impl;
//
//import pers.B17040312.code_analyzer.model.*;
//import pers.B17040312.code_analyzer.service.AnalyzeService;
//import pers.B17040312.code_analyzer.service.AssessService;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @title: AnalyzeServiceImpl
// * @package pers.B17040312.code_analyzer.service.impl
// * @description: AnalyzeService接口的实现类
// * @author: laochonger
// * @date: 2019-10-29 16:19
// * @version: V1.0
// */
//
//public class AnalyzeServiceImpl implements AnalyzeService {
//
//    /**
//     * 创建statistics、annotation、function、nullString类分别为总行数、注释、函数、空行计数
//     */
//    Statistics statistics = new Statistics();
//    Annotation annotation = new Annotation();
//    Function function = new Function();
//    NullString nullString = new NullString();
//    /**
//     * 创建struct、define类作为计数的辅助类
//     */
//    Struct struct = new Struct();
//    Define define = new Define();
//
//    /**
//     * 储存分析统计以及评级结果的容器
//     */
//    private List<String>RESULTS = new ArrayList<>();
//
//    /**
//     * 创建assessService接口
//     */
//    AssessService assessService = new AssessServiceImpl();
//
//
//    /**
//     *
//     *@title: analyze
//     *@description: 实现设计的有限状态自动机来分析统计c语言源代码
//     *@author: laochonger
//     *@date: 2019-10-29 20:13
//     *@param: [fileName]
//     *@return: java.util.List<java.lang.String>
//     *@throws:
//     */
//    @Override
//    public List<String> analyze(String fileName) throws IOException {
//
//
//        FileReader fileReader = null;
//        /**
//         * 检测是否打开的是文件夹
//         */
//        try {
//            fileReader = new FileReader(fileName);
//        } catch (FileNotFoundException e) {
//            RESULTS.add("\r\n\r\n\r\n\r\n                       大家就当无事发生");
//            return RESULTS;
//            //e.printStackTrace();
//        }
//
//        /**
//         * 按传入的文件名用BufferedReader逐字读入.c文件
//         */
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        int nowChar;              //当前读入字符
//        int state = 0;            //初始状态
//        int simulationStack  = 0; //用int变量模拟"{"、"}"的配对
//        int flagForDefine = 0;    //用于状态0中判断是否还在#define状态中
//        int flagFirst = 1;        //用于判断是否是首次读入
//        /**
//         * 循环逐字读入
//         */
//        while ((nowChar = bufferedReader.read()) != -1){
//            if(flagFirst==1&&nowChar=='\r')nullString.setSum(1);         //首次读入且是回车符，代表是空行
//            if(flagFirst==1)flagFirst=0;                                 //关闭首次读入开关
//            if(nowChar=='\n')statistics.setSum(statistics.getSum()+1);   //如果读入的为换行符，则总行数++
//            if(nowChar=='\n'&&simulationStack >0){
//                function.setSum(function.getSum()+1);
//            }
//            if(nullString.getPreChar()=='\n'&&nowChar=='\n'){            //如果连续读入'\n'，则空行++
//                nullString.setSum(nullString.getSum()+1);
//            }
//            if(nowChar!=' '&&nowChar!='\t'&&nowChar!='\r'){              //为空行前一个字符除去'\t','\r',' '的影响
//                nullString.setPreChar(nowChar);
//            }
//            /**
//             * 开始处理状态
//             */
//            switch (state) {
//                case 0:
//                    if(nowChar=='#'){
//                        flagForDefine=1;
//                    }
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if(flagForDefine>0){
//                        if(nowChar=='\n'&&define.getPreChar()!='\\')
//                            flagForDefine=0;
//                        define.setPreChar(nowChar);
//                        break;
//                    }
//                    else if (nowChar == '/') {
//                        state = 1;
//                    } else if (nowChar == '\'' || nowChar == '"') {
//                        annotation.setPreChar(nowChar);
//                        state = 6;
//                    } else if(nowChar=='{'&&struct.getPreChar()==')') {
//                        simulationStack ++;
//                    } else if(nowChar=='}'&&simulationStack >0) {
//                        simulationStack --;
//                        if(simulationStack ==0){
//                            function.setCnt(function.getCnt()+1);
//                        }
//                    }
//                    if(nowChar!=' '&&nowChar!='\n'&&nowChar!='\t') {
//                        struct.setPreChar((char)nowChar);
//                    }
//
//                    break;
//                case 1:
//                    if(nowChar=='\n')annotation.setSum(annotation.getSum()+1);
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if (nowChar == '/') {
//                        state = 2;
//                    } else if (nowChar == '*') {
//                        state = 4;
//                    } else {
//                        state = 0;
//                    }
//                    break;
//                case 2:
//                    if(nowChar=='\n')annotation.setSum(annotation.getSum()+1);
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if (nowChar == '\\') {
//                        state = 3;
//                    } else if (nowChar == '\n') {
//                        state = 0;
//                    }
//                    break;
//                case 3:
//                    if(nowChar=='\n')annotation.setSum(annotation.getSum()+1);
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if (nowChar != '\\') {
//                        state = 2;
//                    }
//                    break;
//                case 4:
//                    if(nowChar=='\n')annotation.setSum(annotation.getSum()+1);
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if (nowChar == '*') {
//                        state = 5;
//                    }
//                    break;
//                /**
//                 * 状态五
//                 *
//                 * 多行注释可能结束
//                 * 若ch=='/'，则多行注释结束，转状态0；
//                 * 若ch=='*'，属于情况{/*xyz**}，状态不变；
//                 * 其它情况，属于情况{/*xyz*a}，转状态4。
//                 */
//                case 5:
//                    if(nowChar=='\n')annotation.setSum(annotation.getSum()+1);
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                    if (nowChar == '/') {
//                        state = 0;
//                        annotation.setSum(annotation.getSum()+1);
//                    } else if (nowChar != '*') {
//                        state = 4;
//                    }
//                    break;
//                /**
//                 * 状态6
//                 * 字符或字符串状态
//                 * 若当nowChar为换行符，且处于函数状态，函数总行数++
//                 * 若nowChar=='\\',为转义字符，属于状态{'\}或{"\}，转状态7；
//                 * 否则，若nowChar==annotation.getPreChar(),说明字符或字符串状态结束，转状态0；
//                 * 其它情况，状态不变。
//                 */
//                case 6:
//
//                    if (nowChar == '\\') {
//                        state = 6;
//                    } else if (nowChar == annotation.getPreChar()) {
//                        state = 0;
//                    }
//                    break;
//                /**
//                 * 状态7
//                 * 转义字符后面的字符
//                 * 转状态6。
//                 */
//                //    case 7:
////                    if(nowChar=='\n'&&simulationStack >0){
////                        function.setSum(function.getSum()+1);
////                    }
//                //   state = 6;
//                //   break;
//                //case :8
//                //不将define单独列为状态是由于define后可以跟注释
//                /**
//                 * 其他状态
//                 * 用于debug
//                 */
//                default:
//                    //System.out.println(nowChar);
//                    if(nowChar=='\n'&&simulationStack >0){
//                        function.setSum(function.getSum()+1);
//                    }
//                    break;
//            }
//        }
//        /**
//         * 将统计结果放入容器
//         */
//        RESULTS.add("                    代码总行数:     "+statistics.getSum());
//        RESULTS.add("                    函数总个数:     "+function.getCnt());
//        RESULTS.add("                    函数总行数:     "+function.getSum());
//        RESULTS.add("                    空行总行数:     "+nullString.getSum());
//        RESULTS.add("                    注释总行数：    "+annotation.getSum());
//
//        /**
//         * 调用assessService评分接口，将返回的评价结果放入容器中
//         */
//        RESULTS.addAll(assessService.assess(statistics.getSum(),function.getSum(),function.getCnt(),annotation.getSum(),nullString.getSum()));
//        /**
//         * 关闭流与文件
//         */
//        bufferedReader.close();
//        fileReader.close();
//        return RESULTS;
//    }
//}
