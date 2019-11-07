package pers.B17040312.code_analyzer.service;

import java.io.IOException;
import java.util.List;


/**
 * @title: AnalyzeService
 * @package pers.B17040312.code_analyzer.service
 * @description: 用于打开并统计分析C语言源代码的接口
 * @author: laochonger
 * @date: 2019-10-29 16:13
 * @version: V1.0
 */

public interface AnalyzeService {
    /**
     *
     *@title: analyze
     *@description: 传入c语言源代码文件名，打开并统计分析，返回统计分析结果
     *@author: laochonger
     *@date: 2019-10-29 16:16
     *@param: [fileName]
     *@return: java.util.List<java.lang.String>
     *@throws:
     */
    public List<String> analyze(String fileName) throws IOException;
}
