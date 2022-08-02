package com.example.demo03.controller;

import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.comm.utils.AppResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/2 17:56
 * @describe 其他服务
 * @since 1.8
 */
@RestController
@RequestMapping("/other")
@Api(value = "其他服务", tags = "其他服务")
public class OtherController {

    @PostMapping("/image/upload")
    @ApiOperation("图片上传")
    public AppResult imageUpload(@RequestPart("file") MultipartFile file, HttpServletRequest req) throws Exception {
        // 自定义上传位置和返回内容
        Map<String, Object> map = new HashMap<>();
        String getImgPath = req.getRequestURL().substring(0, req.getRequestURL().indexOf("/upload"));
        // 创建文件在服务器端的存放路径
        String dir = "/Users/eternalcoder/Desktop/upload/images/";
        // String dir = "/data/upload/images/";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 如果 suffix 不是 jpg jpeg png gif bmp 抛出异常
        if (!".jpg".equals(suffix) && !".jpeg".equals(suffix) && !".png".equals(suffix) && !".gif".equals(suffix) && !".bmp".equals(suffix)) {
            throw new Exception("图片格式不正确");
        }
        try {
            //生成文件在服务器端存放的名字
            String fileName = UUID.randomUUID().toString();
            File files = new File(dir + fileName + suffix);
            //上传
            file.transferTo(files);
            map.put("fileName", fileName + suffix);
            // 设置 file 的访问路径
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/v1/zhouyuanjie/images/resources/" + fileName + suffix;
            map.put("filepath", filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(500, "文件上传失败");
        }
        return AppResult.success(200, "上传成功", map);
    }

    @PostMapping("/file/upload")
    @ApiOperation(value = "pdf文件上传", notes = "pdf文件上传")
    public AppResult fileUpload(@RequestPart("file") MultipartFile file, HttpServletRequest req) throws BaseException {
        // 定义上传位置和返回内容
        Map<String, Object> map = new HashMap<>();
        String getImgPath = req.getRequestURL().substring(0, req.getRequestURL().indexOf("/upload"));
        //创建文件在服务器端的存放路径
        String dir = "/Users/eternalcoder/Desktop/upload/files/";
        // String dir = "/data/upload/files/";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 如果 suffix 不是 pdf 抛出异常
        if (!suffix.equals(".pdf")) {
            throw new BaseException(500, "文件格式不正确");
        }
        try {
            //生成文件在服务器端存放的名字
            String fileName = UUID.randomUUID().toString();
            File files = new File(dir + fileName + suffix);
            //上传
            file.transferTo(files);
            map.put("fileName", fileName + suffix);
            // 设置 file 的访问路径
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/v1/zhouyuanjie/file/resources/" + fileName + suffix;
            map.put("filepath", filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(500, "文件上传失败");
        }

        return AppResult.success(200, "文件上传成功", map);
    }
}
