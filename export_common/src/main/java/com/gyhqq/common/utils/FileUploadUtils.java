package com.gyhqq.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.*;

public class FileUploadUtils {
    public static final String accessKey = "fPs2EoVFTc6Scf_bzZWXnOAgOS3clCVhtdor83Sk";
    public static final String secretKey = "l4nN-RpVCJhdB5e5zVa7EhKo76RrgTY3iCzXrWy4";
    public static final String bucket = "saas_export";
    public static final String url ="http://putasbtc2.bkt.clouddn.com";

    public static String fileUpload(byte[] bytes){

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        try {

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                key = putRet.key;

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return url+"/"+key;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\图片\\ASC2 table.jpg");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String s = fileUpload(bytes);
        System.out.println(s);
    }

}
