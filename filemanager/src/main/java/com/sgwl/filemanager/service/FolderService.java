package com.sgwl.filemanager.service;


import com.sgwl.filemanager.mapper.FolderMapper;
import com.sgwl.filemanager.po.FilePo;
import com.sgwl.filemanager.po.FolderPo;
import com.sgwl.filemanager.vo.FileVo;
import com.sgwl.filemanager.vo.FolderVo;
import com.sgwl.filemanager.vo.RespResult;
import com.sgwl.filemanager.vo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FolderService {

    @Autowired
    private FolderMapper folderMapper;

    @Autowired
    private FileService fileService;


    /**
     * 创建文件夹
     *
     * @param pid
     * @param folderName
     */
    public void createFoleder(Long pid, String folderName) {
        FolderPo folderPo = new FolderPo();
        folderPo.setPid(pid);
        folderPo.setName(folderName);
        folderPo.setCreateTime(new Date());
        folderMapper.insertSelective(folderPo);
    }

    /**
     * 判断文件夹是否存在
     *
     * @param pid
     * @param fileName
     * @return
     */
    public RespResult isExist(Long pid, String fileName) {

        //创建查询条件
        Example example = new Example(FolderPo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);
        criteria.andEqualTo("name", fileName);

        //查询
        List list = folderMapper.selectByExample(example);

        //根据查询结果判断文件是否存在
        if (list.size() > 0) {
            return new RespResult(200, "文件夹已存在!", true);
        }
        return new RespResult(200, "文件夹不存在!", false);
    }


    /**
     * 获取指定文件夹的子目录
     *
     * @param pid
     * @return
     */
    public List<FolderPo> getFolderByPid(Long pid) {
        //查询条件
        Example example = new Example(FolderPo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);

        //查询 获取子文件夹集合
        List<FolderPo> folderPos = folderMapper.selectByExample(example);
        return folderPos;
    }


    /**
     * 获取所有文件夹
     *
     * @return
     */
    public List<FolderPo> getAllFolders() {
        return folderMapper.selectAll();
    }


    /**
     * 调用递归查询
     * 获取zTreeNodes数据
     */
    public LinkedList getZtreeNodes1() {
        long cur = System.currentTimeMillis();
        //创建链表保存zTreeNodes数据
        LinkedList zTreeNodes = new LinkedList<>();
        //调用查询递归方法 封装数据
        getDataByDiGui(0L, zTreeNodes);
        System.out.println("递归方法耗时:" + (cur - System.currentTimeMillis()));
        return zTreeNodes;
    }


    /**
     * 通过递归 查询数据库 将所有文件夹及文件数据封装到 zTreeNodes 中
     * 频繁查询数据库,消耗资源,适用于小数据量
     *
     * @param pid
     * @param zTreeNodes
     */
    public void getDataByDiGui(Long pid, LinkedList zTreeNodes) {
        //获取指定文件夹下的目录和文件
        List<FolderPo> folderPos = getFolderByPid(pid);
        List<FilePo> filePos = fileService.getFileByPid(pid);
        //根据返回结果判断当前文件夹是否还有子目录或文件
        if (folderPos.size() > 0 || filePos.size() > 0) {//有则递归
            for (FolderPo folderPo : folderPos) {
                //封装数据
                //将folderPo装成Vo对象后添加到zTreeNodes中
                FolderVo folderVo = new FolderVo(folderPo.getId(), folderPo.getPid(), folderPo.getName());
                zTreeNodes.add(folderVo);
                getDataByDiGui(folderPo.getId(), zTreeNodes);
            }
            //将filePo封装成Vo对象后添加到zTreeNodes中
            List<FileVo> fileList = filePos.stream().map(file -> new FileVo(null, file.getPid(), file.getName(), file.getId())).collect(Collectors.toList());
            zTreeNodes.addAll(fileList);
        }
    }


    /**
     * 通过递归方法
     * 获取zTreeNodes数据
     */
    public LinkedList getZtreeNodes2() {
        long cur = System.currentTimeMillis();
        //创建链表保存zTreeNodes数据
        LinkedList zTreeNodes = new LinkedList<>();
        //list 转map
        Map<Long, List<FolderPo>> folderMap = listToMap(getAllFolders());
        Map<Long, List<FilePo>> fileMap = listToMap(fileService.getAllFiles());
        //调用递归方法封装数据
        getByDiGui(zTreeNodes, folderMap, fileMap, 0L);
        System.out.println("递归方法耗时:" + (cur - System.currentTimeMillis()));
        return zTreeNodes;
    }


    /**
     * 通过程序递归 将所有文件夹及文件数据封装到 zTreeNodes 中
     * 降低对数据库连接数的消耗,适用于大量数据查询
     *
     * @param zTreeNodes
     * @param folderMap
     * @param fileMap
     * @param pid
     */
    public void getByDiGui(LinkedList zTreeNodes, Map<Long, List<FolderPo>> folderMap, Map<Long, List<FilePo>> fileMap, Long pid) {
        //分别获取指定文件夹下的所有文件夹及文件
        List<FolderPo> folderPos = folderMap.get(pid);
        List<FilePo> filePos = fileMap.get(pid);

        //判断当前pid下是否有目录或文件
        if (folderPos != null || filePos != null) {//有则递归
            if (folderPos != null) {
                for (FolderPo folderPo : folderPos) {
                    //封装数据
                    //将folderPo封装成Vo对象后添加到zTreeNodes中
                    FolderVo folderVo = new FolderVo(folderPo.getId(), folderPo.getPid(), folderPo.getName());
                    zTreeNodes.add(folderVo);

                    //获取当前文件夹的id作为pid传递
                    getByDiGui(zTreeNodes, folderMap, fileMap, folderPo.getId());
                }
            }

            //将filePo封装成Vo对象后添加到zTreeNodes中
            if (filePos != null) { //这里会抛异常,需要判空
                List<FileVo> fileVos = filePos.stream().map(file -> new FileVo(null, file.getPid(), file.getName(), file.getId())).collect(Collectors.toList());
                zTreeNodes.addAll(fileVos);
            }
        }
    }


    /**
     * list - > map
     * 自定义泛型方法,对象的pid作为key,具有相同pid的对象作放入list中并作为value ,存入map中
     *
     * @param list
     * @return
     */
    public <T> Map<Long, List<T>> listToMap(List<T> list) {
        //创建map容器
        Map<Long, List<T>> map = new HashMap<>();
        //定义map的key保存对象的pid
        Long key = null;

        if (CollectionUtils.isEmpty(list)) {
            return map ;
        }

        //获取泛型真实对象的Class对象
        Class<?> clazz = list.get(0).getClass();

        try {
            //获取真实类所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (T t : list) {
                for (Field field : fields) {
                    //因为字段是私有的，所以得暴力反射
                    field.setAccessible(true);
                    //获取字段名
                    String fieldName = field.getName();
                    //获取指定字段的值
                    if (fieldName.equals("parentid")) {
                        //获取真实对象的属性值
                        Object fieldValue = field.get(t);
                        key = (Long) fieldValue;
                    }
                }

                //定义map的value保存具有相同pid的对象集合
                List<T> value = new LinkedList<>();

                //最终添加到map中,完成 list -> map
                if (!map.containsKey(key)) {//key不存在,添加新数据
                    value.add(t);
                    map.put(key, value);
                } else {//key已存在,取出value,追加新数据
                    List<T> oldValue = map.get(key);
                    oldValue.add(t);
                    value = oldValue;
                    map.put(key, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取elementUI treeNodes
     *
     * @return
     */
    public List getTreeNodes() {
        TreeNode treeNode = new TreeNode(0L, null, null, true, null);
        //list 转map
        Map<Long, List<FolderPo>> folderMap = listToMap(getAllFolders());
        Map<Long, List<FilePo>> fileMap = listToMap(fileService.getAllFiles());

        //调用递归方法 封装 treeNodes数据
        diGui(treeNode, folderMap, fileMap);
        List<TreeNode> treeNodes = treeNode.getChildren();
        return treeNodes;
    }


    /**
     * 递归封装 elementui treenodes
     *
     * @param parentNode
     * @param folderMap
     * @param fileMap
     */
    public void diGui(TreeNode parentNode, Map<Long, List<FolderPo>> folderMap, Map<Long, List<FilePo>> fileMap) {
        //获取父节点ID
        Long id = parentNode.getId();
        //创建集合保存子节点
        LinkedList children = new LinkedList();
        //创建节点对象
        TreeNode treeNode = null;

        //从map中分别获取指定文件夹下的所有文件夹及文件
        List<FolderPo> folderPos = folderMap.get(id);
        List<FilePo> filePos = fileMap.get(id);

        //判断当前目录下是否还有子目录
        if (folderPos != null) {
            for (FolderPo folderPo : folderPos) {
                //FolderPo对象 转成TreeNode对象
                treeNode = new TreeNode(folderPo.getId(), folderPo.getPid(), folderPo.getName(), true, null);
                //添加子目录节点数据
                children.add(treeNode);
                //递归封装节点数据
                diGui(treeNode, folderMap, fileMap);
            }
        }

        //判断当前目录下是否还有子目录
        if (filePos != null) {
            //获取子文件对象集合
            List<TreeNode> children1 = filePos.stream().map(filePo -> new TreeNode(filePo.getId(), filePo.getPid(), filePo.getName(), false, null)).collect(Collectors.toList());
            //追加子文件节点数据
            children.addAll(children1);
        }
        //子节点赋值
        parentNode.setChildren(children);
//        System.out.println(parentNode);
    }
}


