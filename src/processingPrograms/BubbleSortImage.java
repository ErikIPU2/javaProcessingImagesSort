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
public class BubbleSortImage extends PApplet implements IprocessingImageSort{
    
    private String ImagePath;
    private int imgHeight;
    private int imgWidth;
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
    
    @Override
    public void startSort() {
        PApplet.main(new String[] {
            "processingPrograms.BubbleSortImage", 
            ImagePath, 
            String.valueOf(this.imgWidth),
            String.valueOf(this.imgHeight)
        });
    }
    
    @Override
    public void settings() {
        size(parseInt(args[1]) * 2, parseInt(args[2]));
    }
    
    @Override
    public void setup() {
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
                for (int j = 0; j < n-i-1; j++) {
                    if (brightness(sortedImg.pixels[j]) > brightness(sortedImg.pixels[j+1])) {
                        int temp = sortedImg.pixels[j];
                        sortedImg.pixels[j] = sortedImg.pixels[j+1];
                        sortedImg.pixels[j+1] = temp;
                    }
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
