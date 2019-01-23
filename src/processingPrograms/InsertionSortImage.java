/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processingPrograms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import processing.core.PApplet;
import processing.core.PImage;
import processingPrograms.IprocessingImageSort;

/**
 *
 * @author erik
 */
public class InsertionSortImage extends PApplet implements IprocessingImageSort{
    
    private String ImagePath;
    private int imgHeight;
    private int imgWidth;
    private int frameRate;
    private PImage img;
    private PImage sortedImg;
    
    private int i = 1;

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
        try {
            BufferedImage bufImg = ImageIO.read(new File(this.ImagePath));
            this.imgHeight = bufImg.getHeight();
            this.imgWidth = bufImg.getWidth();
            
        } catch (IOException e) {
            System.out.println("putzs");
        }
    }
    
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    
    @Override
    public void startSort() {
        PApplet.main(new String[] {
            "processingPrograms.InsertionSortImage", 
            ImagePath, 
            String.valueOf(this.imgWidth),
            String.valueOf(this.imgHeight),
            String.valueOf(this.frameRate)
        });
    }
    
    @Override
    public void settings() {
        size(parseInt(args[1]) * 2, parseInt(args[2]));
    }
    
    @Override
    public void setup() {
        frameRate(parseInt(args[3]));
        this.ImagePath = args[0];
        img = loadImage(ImagePath);
        img.loadPixels();
        sortedImg = createImage(img.width, img.height, RGB);
        sortedImg.loadPixels();
        
        for (int i = 0; i < img.pixels.length; i++) {
            sortedImg.pixels[i] = img.pixels[i];
        }
        
        sortedImg.updatePixels();
    }
    
    @Override
    public void draw() {
        int n = img.pixels.length;
        
        if (i < n) {
            
            int key = sortedImg.pixels[i];
            int j = i-1;
            
            while (j >= 0 && sortedImg.pixels[j] > key) {
                sortedImg.pixels[j+1] = sortedImg.pixels[j];
                j--;
            }
            
            sortedImg.pixels[j+1] = key; 
            
            i++;
        }
        
        sortedImg.updatePixels();
        background(0);
        image(img, 0, 0);
        image(sortedImg, img.width, 0);
    }
    
    
}
