package com.qtu404.util.poi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;


public class PPTReader {
    private String ppt_file_path;
    private String output_path;
    private int scale = 1;

    public PPTReader() {
    }

    public String getPpt_file_path() {
        return this.ppt_file_path;
    }

    public void setPPT_file_path(String ppt_file_path) {
        this.ppt_file_path = ppt_file_path;
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getOutput_path() {
        return this.output_path;
    }

    public void setOutput_path(String output_path) {
        this.output_path = output_path;
    }

    public List<String> ppt2png(String slideId) {
        ArrayList images = new ArrayList();

        try {
            FileInputStream is = new FileInputStream(this.ppt_file_path);
            SlideShow ppt = new XMLSlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
            int width = pgsize.width * this.scale;
            int height = pgsize.height * this.scale;
            List<XSLFSlide> slide = ppt.getSlides();

            label50:
            for(int i = 0; i < slide.size(); ++i) {
                List<XSLFShape> shapes = ((XSLFSlide)slide.get(i)).getShapes();
                Iterator var12 = shapes.iterator();

                while(true) {
                    XSLFShape shape;
                    do {
                        if (!var12.hasNext()) {
                            String title = ((XSLFSlide)slide.get(i)).getTitle();
                            BufferedImage img = new BufferedImage(width, height, 1);
                            Graphics2D graphics = img.createGraphics();
                            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                            graphics.setPaint(Color.white);
                            graphics.fill(new Float(0.0F, 0.0F, (float)width, (float)height));
                            graphics.scale((double)width / (double)pgsize.width, (double)height / (double)pgsize.height);
                            ((XSLFSlide)slide.get(i)).draw(graphics);
                            String fname = this.output_path + "ppt_" + slideId + "_" + (i + 1) + ".png";
                            images.add("ppt_" + slideId + "_" + (i + 1) + ".png");
                            File file = new File(this.output_path);
                            if (!file.exists()) {
                                file.mkdirs();
                            }

                            FileOutputStream out = new FileOutputStream(fname);
                            ImageIO.write(img, "png", out);
                            out.close();
                            continue label50;
                        }

                        shape = (XSLFShape)var12.next();
                    } while(!(shape instanceof XSLFTextShape));

                    XSLFTextShape sh = (XSLFTextShape)shape;
                    List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                    Iterator var16 = textParagraphs.iterator();

                    while(var16.hasNext()) {
                        XSLFTextParagraph xslfTextParagraph = (XSLFTextParagraph)var16.next();
                        List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                        Iterator var19 = textRuns.iterator();

                        while(var19.hasNext()) {
                            XSLFTextRun xslfTextRun = (XSLFTextRun)var19.next();
                            xslfTextRun.setFontFamily("宋体");
                        }
                    }
                }
            }

            return images;
        } catch (FileNotFoundException var20) {
            var20.printStackTrace();
        } catch (IOException var21) {
            var21.printStackTrace();
        }

        return null;
    }
}