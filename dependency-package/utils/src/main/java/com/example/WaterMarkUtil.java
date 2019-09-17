package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class WaterMarkUtil {

    private static final String IMAGE_TYPE = "jpg";
    private static final String FONT_FAMILY = "微软雅黑";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 16;
    private static final Color COLOR = Color.BLUE;
    private static final float IMAGE_ALPHA = 0.4F;

    public WaterMarkUtil() {
        super();
    }


    /**
     * 在源图片上设置水印文字
     */
    public static void wordsToImage(String srcImagePath,String inputWords,String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            // 读取图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            // 创建java2D对象
            Graphics2D g2d = image.createGraphics();
            // 开启文字抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // 用源图像填充背景
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);

            // 为 Graphics2D 上下文设置 Composite。 Composite 用于所有绘制方法中，如 drawImage、
            // drawString、draw 和 fill。 它指定新的像素如何在呈现过程中与图形设备上的现有像素组合。
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, IMAGE_ALPHA);

            g2d.setComposite(ac);

            // 设置文字字体名称、样式、大小
            g2d.setFont(new Font(FONT_FAMILY, FONT_STYLE, FONT_SIZE));
            // 设置字体颜色
            g2d.setColor(COLOR);
            // 输入水印文字及其起始x、y坐标
            g2d.drawString(inputWords, image.getWidth()/2, image.getHeight()/2);
            g2d.dispose();
            // 将水印后的图片写入toPath路径中
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, IMAGE_TYPE, fos);
        }
        // 文件操作错误抛出
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.flush();
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 在源图像上设置图片水印
     */
    public static void imageToImage(String srcImagePath, String appendImagePath, String toPath) throws IOException {
        FileOutputStream fos = null;
        try {
            // 读图
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            // 创建java2D对象
            Graphics2D g2d = image.createGraphics();
            // 用源图像填充背景
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);

            // 关键地方
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, IMAGE_ALPHA);
            g2d.setComposite(ac);

            BufferedImage appendImage = ImageIO.read(new File(appendImagePath));

            int x = image.getWidth()/2-image.getWidth()/6;
            int y = image.getHeight()/2-image.getHeight()/6;
            g2d.drawImage(appendImage, x, y, image.getWidth()/3,image.getHeight()/3, null, null);

            g2d.dispose();
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, IMAGE_TYPE, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.flush();
            if (fos != null) {
                fos.close();
            }
        }
    }


    public static void imageAndwordsToImage(String srcImagePath, String appendImagePath,String inputWords, String toPath)throws IOException {
        FileOutputStream fos = null;
        try {
            // 读图
            BufferedImage image = ImageIO.read(new File(srcImagePath));

            // 创建java2D对象
            Graphics2D g2d = image.createGraphics();
            // 用源图像填充背景
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);

            // 为 Graphics2D 上下文设置 Composite。 Composite 用于所有绘制方法中，如 drawImage、
            // drawString、draw 和 fill。 它指定新的像素如何在呈现过程中与图形设备上的现有像素组合。
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, IMAGE_ALPHA);
            g2d.setComposite(ac);

            BufferedImage appendImage = ImageIO.read(new File(appendImagePath));

            int x = image.getWidth()/2-image.getWidth()/6;
            int y = image.getHeight()/2-image.getHeight()/6;

            g2d.drawImage(appendImage, x, y, image.getWidth()/3,image.getHeight()/3, null, null);

            Font font = new Font(FONT_FAMILY, FONT_STYLE, FONT_SIZE);
            // 字体倾斜展示
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(Math.toRadians(45), 0, 0);
            Font rotatedFont = font.deriveFont(affineTransform);

            // 设置文字字体名称、样式、大小
            g2d.setFont(rotatedFont);
            // 设置字体颜色
            g2d.setColor(COLOR);
            // 输入水印文字及其起始x、y坐标
            g2d.drawString(inputWords, 0, 0);
            //g2d.drawString(inputWords, 100, 100);
            g2d.dispose();

            fos = new FileOutputStream(toPath);
            ImageIO.write(image, IMAGE_TYPE, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.flush();
            if (fos != null) {
                fos.close();
            }
        }
    }




    public static void main(String[] args) throws Exception {

        // 源图片路径
        String srcImagePath = "//home//xiemin//图片//测试单票.jpg";
        // 水印图片路径
        String appendImagePath = "//home//xiemin//图片//电网logo.jpeg";

        String inputWords = "图片上设置水印文字";

        // 水印文字后的存储路径
        String wToPath = "//opt//test.jpeg";

        String imgName = UUID.randomUUID().toString();
        System.out.println(imgName+"===>"+new Date());
        // 水印图片后的存储路径
        String IToPath = "//opt//"+ imgName +".jpg";

        //WaterMarkUtil.wordsToImage(srcImagePath,inputWords, IToPath);

        WaterMarkUtil.imageToImage(srcImagePath, appendImagePath, IToPath);
        //WaterMarkUtil.imageAndwordsToImage(srcImagePath,appendImagePath,inputWords,IToPath);

    }



}
