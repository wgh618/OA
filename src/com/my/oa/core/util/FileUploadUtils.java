package com.my.oa.core.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public class FileUploadUtils {

    public static File uploadFile(MultipartFile resource, HttpServletRequest request) throws IOException {
        String realPath = request.getRealPath("/template");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String format1String = format.format(new Date());

        File newFile = new File(file, File.separator + format1String);
        File uploadFile = new File(newFile, UUID.randomUUID().toString()+".doc");

        FileUtils.copyInputStreamToFile(resource.getInputStream(), uploadFile);

        return uploadFile;
    }
}
