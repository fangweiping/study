package com.sgwl.filemanager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sgwl.filemanager.enums.ExceptionEnum;
import com.sgwl.filemanager.exception.FileManagerException;
import com.sgwl.filemanager.mapper.FileMapper;
import com.sgwl.filemanager.po.FilePo;
import com.sgwl.filemanager.vo.PageResult;
import com.sgwl.filemanager.vo.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    /**
     * 文件上传
     *
     * @param pid
     * @param multipartFile
     */
    public void uploadFile(Long pid, MultipartFile multipartFile) {
        int length;
        String fileName = multipartFile.getOriginalFilename();
        try {
            //获取文件大小
            length = multipartFile.getBytes().length;
            //父路径
            File parentPath = new File("d:/file");
            //判断父路径是否存在
            if (!parentPath.exists()) {
                parentPath.mkdir();
            }

            //文件路径
            File path = new File(parentPath + "/" + fileName);

            //判断文件是否存在
            if (!path.exists()) {
                //保存文件
                multipartFile.transferTo(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileManagerException(ExceptionEnum.FILE_UPLOAD_FAIL);
        }

        //插入数据库
        FilePo filePo = new FilePo();
        filePo.setName(fileName);
        filePo.setPid(pid);
        filePo.setSize(Long.valueOf(length));
        filePo.setUpdateTime(new Date());
        fileMapper.insertSelective(filePo);
    }


    /**
     * 多文件上传
     *
     * @param pid
     * @param files
     */
    public void uploadFiles(Long pid, MultipartFile[] files) {
        //创建list保存文件对象
        List<FilePo> list = new ArrayList<>();

        //父路径
        File parentPath = new File("d:/upload");
        if (!parentPath.exists()) {
            parentPath.mkdir();
        }

        //获取每个文件对象
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            long size = file.getSize();
            File path = new File(parentPath, fileName);

            //文件不存在的情况下才保存
            if (!path.exists()) {
                try {
                    file.transferTo(path);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new FileManagerException(ExceptionEnum.FILE_UPLOAD_FAIL);
                }
            }
            FilePo filePo = new FilePo();
            filePo.setName(fileName);
            filePo.setPid(pid);
            filePo.setSize(Long.valueOf(size));
            filePo.setUpdateTime(new Date());
            list.add(filePo);
        }
        //批量插入
        fileMapper.insertList(list);

    }


    /**
     * 根据传入的文件ID集合删除文件
     *
     * @param ids
     */
    public void deleteByIds(List<Long> ids) {
        fileMapper.deleteByIdList(ids);
    }


    /**
     * 判断文件是否存在
     *
     * @param pid
     * @param fileName
     * @return
     */
    @SuppressWarnings("all")
    public RespResult isExist(Long pid, String fileName) {
        //创建查询条件
        Example example = new Example(FilePo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);
        criteria.andEqualTo("name", fileName);

        //查询
        List list = fileMapper.selectByExample(example);

        //根据查询结果判断文件是否存在
        if (list.size() > 0) {
            return new RespResult(200, "文件已存在!", true);
        }
        return new RespResult(200, "文件不存在!", false);
    }


    /**
     * 获取指定文件夹的子文件
     *
     * @param pid
     */
    public List<FilePo> getFileByPid(Long pid) {
        //查询条件
        Example example = new Example(FilePo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);

        //查询   获取子文件集合
        List<FilePo> filePos = fileMapper.selectByExample(example);
        return filePos;
    }


    /**
     * 获取所有文件
     *
     * @return
     */
    public List<FilePo> getAllFiles() {
        return fileMapper.selectAll();
    }


    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param pid
     * @return
     */
    public PageResult<FilePo> queryFileByPage(Integer page, Integer rows, Long pid) {
        //1.开启分页   分页助手下第一个方法进行分页
        PageHelper.startPage(page, rows);

        //2.条件过滤
        Example example = new Example(FilePo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);

        //3.查询
        List<FilePo> files = fileMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(files)) {
            throw new FileManagerException(ExceptionEnum.FILE_NOT_EXIST);
        }

        //4. PageInfo 帮助计算分页信息
        PageInfo<FilePo> info = new PageInfo<>(files);
        long total = info.getTotal();//总数

        //5.按分页规则计算总页数
        int totalPage;

        if (total <= 10) {
            totalPage = 1;
        } else {
            totalPage = (int) (total / rows + 1);
        }
        return new PageResult(total, totalPage, files);
    }
}
