package com.fwp.demo.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lambdademo {
    public static void main(String[] args) {


        List<Sku> skuList = Lists.newArrayList(
                Sku.builder().skuCode("001").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build()
                )).build(),
                Sku.builder().skuCode("002").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build()
                )).build(),
                Sku.builder().skuCode("003").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build()
                )).build(),
                Sku.builder().skuCode("004").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build()
                )).build()
        );

        //skuList.stream().sorted(Comparator.comparing(Sku::getSkuCode).thenComparing(Comparator.comparing(a -> a.getAttributeList().get("颜色"))));
    }

    ///**
    // * 排序
    // * @param skuList 待排序的数据
    // * @param sortKeys 排序的条件即：customizeKey
    // * @return
    // */
    //public List<Sku> skuSort(List<Sku> skuList, List<String> sortKeys){
    //
    //    return null;
    //}

    @Test
    public void sort() {
        List<AttributeMapping> attributeList = Lists.newArrayList(
                AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build(),
                AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build(),
                AttributeMapping.builder().customizeKey("运营商").customizeValue("移动").build(),
                AttributeMapping.builder().customizeKey("运营商").customizeValue("联通").build()
        );

        List<Sku> skuList = Lists.newArrayList(

                Sku.builder().skuCode("005").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("联通").build()
                )).build(),
                Sku.builder().skuCode("006").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("联通").build()
                )).build(),
                Sku.builder().skuCode("001").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("移动").build()
                )).build(),
                Sku.builder().skuCode("002").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("黑色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("移动").build()
                )).build(),
                Sku.builder().skuCode("003").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("移动").build()
                )).build(),
                Sku.builder().skuCode("004").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("移动").build()
                )).build(),
                Sku.builder().skuCode("007").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("128G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("联通").build()
                )).build(),
                Sku.builder().skuCode("008").attributeList(Lists.newArrayList(
                        AttributeMapping.builder().customizeKey("颜色").customizeValue("白色").build(),
                        AttributeMapping.builder().customizeKey("内存").customizeValue("256G").build(),
                        AttributeMapping.builder().customizeKey("运营商").customizeValue("联通").build()
                )).build()
        );

        List<String> customizeKeys = attributeList.stream().map(AttributeMapping::getCustomizeKey).distinct().collect(Collectors.toList());

        skuList = skuSort(skuList, customizeKeys);

        for (Sku sku : skuList) {
            System.out.println(JSON.toJSONString(sku));
        }
    }


    /**
     * 排序
     *
     * @param skuList  待排序的数据
     * @param sortKeys 排序的条件即：customizeKey
     * @return
     */
    public List<Sku> skuSort(List<Sku> skuList, List<String> sortKeys) {
        skuList.sort((o1, o2) -> {
            int compare = 0;
            for (String key : sortKeys) {
                Optional<AttributeMapping> optional1 = o1.getAttributeList().stream().filter(i -> StringUtils.equals(i.getCustomizeKey(), key)).findFirst();
                Optional<AttributeMapping> optional2 = o2.getAttributeList().stream().filter(i -> StringUtils.equals(i.getCustomizeKey(), key)).findFirst();
                if (optional1.isPresent() && optional2.isPresent()) {
                    compare = Objects.compare(optional1.get().getCustomizeValue(), optional2.get().getCustomizeValue(), String::compareTo);
                    if (compare != 0) {
                        break;
                    }
                }
            }
            return compare;
        });
        return skuList;
    }
}
