package juice.samples.web.controller;

import juice.contracts.ResultDTO;
import juice.ftp.FtpAccessException;
import juice.ftp.FtpTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/ftp")
public class FtpController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FtpTemplate ftpTemplate;

    @GetMapping("/download")
    public ResultDTO submit(@RequestParam("path") String path) {
        try {
            File file = ftpTemplate.getFile(path, "abc.txt", new File("/Users/apple/Downloads"));
            return ResultDTO.ok(file);
        } catch (Exception e) {
            LOG.error("FTP文件管理-下载文件异常", e);
        }
        return ResultDTO.systemError();
    }
}
