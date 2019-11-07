package pers.B17040312.code_analyzer.test;


import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import pers.B17040312.code_analyzer.ui.OpenFile;

import javax.swing.*;


public class AnalyzeTest {


    public static void main(String[] args) {

        /**
         * 导入BeautyEye.jar包，美化swing
         */
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            //TODO exception
        }
        /**
         * 程序执行
         */
        new OpenFile();

    }

}