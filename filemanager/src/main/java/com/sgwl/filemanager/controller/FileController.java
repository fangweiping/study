package com.sgwl.filemanager.controller;

import com.sgwl.filemanager.po.FilePo;
import com.sgwl.filemanager.service.FileService;
import com.sgwl.filemanager.vo.PageResult;
import com.sgwl.filemanager.vo.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;


    /**
     * 单文件上传
     *
     * @param pid
     * @param multipartFile
     * @return
     */
    @PostMapping
    public ResponseEntity<RespResult> uploadFile(@RequestParam("pid") Long pid, @RequestParam("file") MultipartFile multipartFile) {
        fileService.uploadFile(pid, multipartFile);
        RespResult respResult = new RespResult(200, "上传成功", null);
        return ResponseEntity.ok(respResult);
    }


    /**
     * 多文件上传
     *
     * @param pid
     * @param files
     * @return
     */
    @PostMapping("files")
    public ResponseEntity<RespResult> uploadFiles(@RequestParam("pid") Long pid, @RequestParam("files") MultipartFile[] files) {
        fileService.uploadFiles(pid, files);
        RespResult respResult = new RespResult(200, "上传成功", null);
        return ResponseEntity.ok(respResult);
    }



    /**
     *根据文件ID集合删除文件
     * @param ids
     * @return
     */
    @DeleteMapping("list")
//    @CrossOrigin("http://localhost:8088")
    public ResponseEntity isExist(@RequestParam("ids") List<Long> ids) {
        fileService.deleteByIds(ids);
        RespResult respResult = new RespResult(200, "删除成功", null);
        return  ResponseEntity.ok(respResult);
    }


    /**
     * 判断文件夹是否存在
     *
     * @return
     */
    @GetMapping("{pid}/{fileName}")
    public ResponseEntity<RespResult> isExist(@PathVariable Long pid, @PathVariable String fileName) {
        return ResponseEntity.ok(fileService.isExist(pid, fileName));
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<FilePo>> queryFileByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer rows,
            @RequestParam(value = "pId") Long pid
    ) {
        return ResponseEntity.ok(fileService.queryFileByPage(page, rows, pid));
    }

}
