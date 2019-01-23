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
public class SelectionSortImage extends PApplet implements IprocessingImageSort{
    
    private String ImagePath;
    private int imgHeight;
    private int imgWidth;
    private int frameRate;
    private PImage img;
    private PImage sortedImg;
    
    private int i = 0;

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
            "processingPrograms.SelectionSortImage", 
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
        for (int frame = 0; frame < 1; frame++) {
            
            if (i < n-1) {
                int min_idx = i;
                for (int j = i+1; j < n; j++) {
                    if ((sortedImg.pixels[j]) < (sortedImg.pixels[min_idx])) {
                        min_idx = j;
                    }

                    int temp = sortedImg.pixels[min_idx];
                    sortedImg.pixels[min_idx] = sortedImg.pixels[i];
                    sortedImg.pixels[i] = temp;
                }

                i++;
            }
        }
        
        
        sortedImg.updatePixels();
        background(0);
        image(img, 0, 0);
        image(sortedImg, img.width, 0);
    }
    
    
}
