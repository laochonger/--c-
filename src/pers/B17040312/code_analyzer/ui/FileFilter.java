package pers.B17040312.code_analyzer.ui;

/**
 * @title: FileFilter
 * @package pers.B17040312.code_analyzer.ui
 * @description: 用于打开文件的文件过滤器类
 * @author: laochonger
 * @date: 2019-10-29 16:21
 * @version: V1.0
 */

public class FileFilter extends javax.swing.filechooser.FileFilter{
    /**
     *
     *@title: accept
     *@description: 使得在该文件过滤器中只显示.c文件和文件夹
     *@author: laochonger
     *@date: 2019-10-29 16:22
     *@param: [f]
     *@return: boolean
     *@throws:
     */
    public boolean accept(java.io.File f) {
        if (f.isDirectory())return true;
        return f.getName().endsWith(".c");  //设置为选择以.class为后缀的文件
    }

    /**
     * @title: FileFilter
     * @package pers.B17040312.code_analyzer.ui
     * @description: 复写javax.swing.filechooser.FileFilter中的getDescription()方法，给文件过滤按钮命名
     * @author: laochonger
     * @date: 2019-10-29 16:28
     * @version: V1.0
     */
    @Override
    public String getDescription(){
        return ".c";
    }
}
