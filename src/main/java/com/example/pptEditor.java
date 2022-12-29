package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class pptEditor{
    XMLSlideShow ppt;

    String fileLocation = "files/template1.pptx";
    public pptEditor(){

    }

    public  void createPpt(){
        ppt = new XMLSlideShow();
        ppt.createSlide();
    }

    public void run(){
        openPpt();

        printTitle();


        String[] result = util.readInput("files/input.txt").toArray(new String[0]);
        
        createTitleSlide(result[0]);
        for(int i = 1;i<result.length;i++){
            createContentSlide(result[0],result[i]);
        }
        save();
    }

    public void openPpt(){
        try{
            File file = new File(fileLocation);
            //create presentation
            ppt = new XMLSlideShow(new FileInputStream(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printTitle(){
        if(ppt!=null){
            for(XSLFSlide s: ppt.getSlides()){
                util.print(s.getTitle());
            }
        }
    }

    public void createTitleSlide(String title){
        XSLFSlideLayout layout = ppt.getSlideMasters().get(0).getLayout(SlideLayout.TITLE);
        XSLFSlide slide = ppt.createSlide(layout);
        XSLFTextShape shapeTitle = slide.getPlaceholder(0);
        shapeTitle.clearText();
        XSLFTextParagraph titleParagraph = shapeTitle.addNewTextParagraph();
        XSLFTextRun run = titleParagraph.addNewTextRun();
        run.setFontSize(36.0);
        run.setText(title);
    }
    public void createContentSlide(String title,String content){
        XSLFSlideLayout layout = ppt.getSlideMasters().get(0).getLayout(SlideLayout.TITLE_AND_CONTENT);
        XSLFSlide slide = ppt.createSlide(layout);
        XSLFTextShape shapeTitle = slide.getPlaceholder(0);
        shapeTitle.clearText();
        XSLFTextParagraph titleParagraph = shapeTitle.addNewTextParagraph();
        XSLFTextRun run = titleParagraph.addNewTextRun();
        run.setFontSize(36.0);
        run.setText(title);

        XSLFTextShape shapeBody = slide.getPlaceholder(1);
        shapeBody.clearText();
        XSLFTextParagraph bodyParagraph = shapeBody.addNewTextParagraph();
        bodyParagraph.setBullet(false);
        bodyParagraph.setIndent(0.0);
        bodyParagraph.setLineSpacing(100.0);

        run = bodyParagraph.addNewTextRun();
        run.setFontSize(28.0);
        run.setText(content);
        // util.print(""+bodyParagraph.getLineSpacing());

    }



    public void save(){
        try{
            FileOutputStream out = new FileOutputStream(fileLocation);
            ppt.write(out);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}