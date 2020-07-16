package com.muqing.controller;

import java.io.IOException;
import java.util.List;

import com.muqing.dao.FileInfoDao;
import com.muqing.dto.WangEditorFile;
import com.muqing.model.FileInfo;
import com.muqing.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileInfoDao fileInfoDao;

    @PostMapping
    public FileInfo uploadFile(MultipartFile file) throws IOException {
        return fileService.save(file);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:file:del')")
    public void delete(@PathVariable String id) {
        fileService.delete(id);
    }

    /**
     * wangEditor富文本文件自定义上传
     */
    @PostMapping("/wangEditor")
    public WangEditorFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
        FileInfo fileInfo = fileService.save(file);

        WangEditorFile editorFile = new WangEditorFile();
        editorFile.setErrno(0);
        editorFile.setData(new String[]{domain + "/statics" + fileInfo.getUrl()});

        return editorFile;
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('sys:file:query')")
//    public PageTableResponse listFiles(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//            @Override
//            public int count(PageTableRequest request) {
//                return fileInfoDao.count(request.getParams());
//            }
//        }, new ListHandler() {
//            @Override
//            public List<FileInfo> list(PageTableRequest request) {
//                List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
//                return list;
//            }
//        }).handle(request);
//    }

}
