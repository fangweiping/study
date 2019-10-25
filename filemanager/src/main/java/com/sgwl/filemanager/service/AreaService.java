package com.sgwl.filemanager.service;

import com.sgwl.filemanager.mapper.AreaMapper;
import com.sgwl.filemanager.po.AreaPo;
import com.sgwl.filemanager.vo.AreaNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private FolderService folderService;


    /**
     * 获取所有区域对象
     *
     * @return
     */
    public List<AreaPo> getAll() {
        List<AreaPo> areaPos = areaMapper.selectAll();
        return areaPos;
    }


    /**
     * 获取插件节点对象
     *
     * @return
     */
    public List getTreeNode() {
        long cur = System.currentTimeMillis();
        //创建根节点
        AreaNode areaNode = new AreaNode(0L, null, null, null);
        //list 转map
        Map<Long, List<AreaPo>> map = folderService.listToMap(getAll());
        //调用递归方法 封装 treeNodes数据
        diGui(areaNode, map);
        //获取根节点下的子节点即可
        List<AreaNode> areaNodes = areaNode.getChildren();
        System.out.println(System.currentTimeMillis()-cur);
        return areaNodes;
    }

    /**
     * 递归封装数据
     *
     * @param parentNode
     * @param map
     */
    private void diGui(AreaNode parentNode, Map<Long, List<AreaPo>> map) {
        //获取节点ID
        Long id = parentNode.getCodeid();
        //创建集合保存子节点
        LinkedList children = new LinkedList();
        //创建节点对象
        AreaNode areaNode = null;

        //从map中获取当前区域的子集
        List<AreaPo> areaPos = map.get(id);

        //判断当前pid下是否有区域
        if (areaPos != null) {//有则递归
            for (AreaPo areaPo : areaPos) {
                //封装子节点数据
                areaNode = new AreaNode(areaPo.getCodeid(), areaPo.getParentid(), areaPo.getCityName(), null);
                //添加子节点数据
                children.add(areaNode);
                //递归封装节点数据
                diGui(areaNode, map);
            }
        //子节点赋值
        parentNode.setChildren(children);
        }
    }
}
