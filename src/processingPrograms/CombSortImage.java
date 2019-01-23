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
public class CombSortImage extends PApplet implements IprocessingImageSort{
    
    private String ImagePath;
    private int imgHeight;
    private int imgWidth;
    private int frameRate;
    private PImage img;
    private PImage sortedImg;
    private int gap;
    private boolean swapped = true;
    private boolean firstExec = true;
    
    
    
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
            "processingPrograms.CombSortImage", 
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
        noLoop();
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
        combSort();
    }
    
    private int getNextGap(int gap) {
        gap = (gap*10)/13;
        if (gap < 1) 
            return 1;
        return gap;
    }
    
    private void combSort() {
        int n = img.pixels.length;
        int gap = n;
        
        boolean swapped = true;
        
        while (gap != 1 || swapped == true) {
            gap = getNextGap(gap);
            swapped = false;
            
            for (int i = 0; i < n-gap; i++) {
                if (sortedImg.pixels[i] > sortedImg.pixels[i+gap]) {
                    int temp = sortedImg.pixels[i];
                    sortedImg.pixels[i] = sortedImg.pixels[i+gap];
                    sortedImg.pixels[i+gap] = temp;
                    
                    swapped = true;
                }
                
            }
            
        }
        
        
        

    }
    
    @Override
    public void draw() {
        sortedImg.updatePixels();
        background(0);
        image(img, 0, 0);
        image(sortedImg, img.width, 0);
        delay(50);
    }
    
    
}
