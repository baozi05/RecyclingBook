package org.example.baozi.recyclingbook.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

// 用户上传图片类
public class ImageUtils {
    
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String[] ALLOWED_TYPES = {"image/jpeg", "image/png", "image/jpg"}; // 规定只许 png、jpeg、jpg格式
    
    public static byte[] compressImage(MultipartFile file) throws IOException {
        // 验证文件类型
        String contentType = file.getContentType();
        if (!Arrays.asList(ALLOWED_TYPES).contains(contentType)) {
            throw new IllegalArgumentException("不支持的文件类型,仅支持JPG/PNG格式");
        }

        // 验证文件大小
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("文件大小不能超过5MB");
        }
        
        // 读取图片
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        
        // 计算压缩后的尺寸
        int targetWidth = originalImage.getWidth();
        int targetHeight = originalImage.getHeight();
        
        if (file.getSize() > 1024 * 1024) { // 如果大于1MB才进行压缩
            double scale = Math.sqrt(1024 * 1024.0 / file.getSize());
            targetWidth = (int) (targetWidth * scale);
            targetHeight = (int) (targetHeight * scale);
        }
        
        //压缩图片
        BufferedImage compressedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        compressedImage.getGraphics().drawImage(originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH), 0, 0, null);

        // 转换为byte数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(compressedImage, "jpg", baos);
        return baos.toByteArray();
    }
} 