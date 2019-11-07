package pers.B17040312.code_analyzer.ui;


import pers.B17040312.code_analyzer.service.AnalyzeService;
import pers.B17040312.code_analyzer.service.impl.AnalyzeServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 * @title: OpenFile
 * @package pers.B17040312.code_analyzer.ui
 * @description: 创建打开文件的GUI
 * @author: laochonger
 * @date: 2019-10-29 16:54
 * @version: V1.0
 */

public class OpenFile extends JFrame implements ActionListener
{

   // private static final long serialVersionUID = 1L;

    private String result = "";   //储存输出的结果
    JButton btn = null;                  //按钮组件
    JTextPane resultField = null;        //文本组件

    public OpenFile()
    {
        /**
         *设置组件
         */
        this.setTitle("代码分析器");                  //窗口名
        btn = new JButton("浏览选择文件");      // 按钮
        JLabel label = new JLabel("评价结果：");// 标签
        resultField = new JTextPane();               // 文本域
        resultField.setFont(new java.awt.Font("黑体",1,20)); //设置文本框中的字体
        resultField.setEditable(false);              //不可编辑
        /**
         * 设置布局
         */
        BorderLayout layout = new BorderLayout();    // 五位布局
        this.setLayout(layout);
        this.setBounds(1600, 800, 850, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn.addActionListener(this);
        /**
         * 添加组件
         */
        this.add(btn,BorderLayout.NORTH);
        this.add(label,BorderLayout.WEST);
        this.add(resultField,BorderLayout.CENTER);
        /**
         * 使得窗口居中
         */
        int windowWidth = this.getWidth();                     //获得窗口宽
        int windowHeight = this.getHeight();                   //获得窗口高
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
    }




    /**
     *
     *@title: access
     *@description: 传入文件名，调用AnalyzeService接口，输出评价
     *@author: laochonger
     *@date: 2019-10-29 19:28
     *@param: String::[fileName]
     *@return: void
     *@throws:
     */
    private void access(String fileName){
        /**
         * 创建analyzeService接口
         */
        AnalyzeService analyzeService = new AnalyzeServiceImpl();
        /**
         * 检测是否为文件夹以及非.c后缀文件
         */
        try {
            int pos = fileName.lastIndexOf(".");
            if(pos == -1){
                resultField.setText("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n                      它只是一个文件夹啊");
                return;
            }else{
                String suffixFileName = fileName.substring(pos+1);
                if(!suffixFileName.equals("c")){
                    resultField.setText("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n                      我觉得你和"+suffixFileName+"不合适");
                    return;
                }
            }
            /**
             * 调用analyzeService接口，获得评价的List<String>并输出
             */
            List<String> TEXT = analyzeService.analyze(fileName);
            result = "\r\n\r\n\r\n";
            for(String text : TEXT){
                result+=text+"\r\n";
            }
            resultField.setText(result);
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

    /**
     *
     *@title: actionPerformed
     *@description: 重写接口ActionListener的actionPerformed方法，浏览文件按钮时触发
     *@author: laochonger
     *@date: 2019-10-29 19:30
     *@param: [e]
     *@return: void
     *@throws:
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        /**
         * 创建过滤器，只显示文件见与.c文件
         */
        FileFilter fileFilter=new FileFilter ();  //创建过滤器对象
        JFileChooser chooser = new JFileChooser();//创建文件选择器
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//打开模式：文件与文件夹
        chooser.setFileFilter(fileFilter);        //为文件选择器设置文件过滤器
        chooser.showDialog(new JLabel(), "选择");//对话框
        File file = chooser.getSelectedFile();    //获得选择的文件
        String fileName = null;                   //初始化文件名字符串
        /**
         * 获得文件名并检测文件名是否为空，不为空则调用access方法
         */
        try{
            fileName = file.getAbsoluteFile().toString();
            access(fileName);
        }catch (NullPointerException E){

        }
    }
}
//gg