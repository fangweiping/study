package com.dwl.rep.common;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.dwl.rep.pojo.ReportDetail;
import com.dwl.rep.pojo.ReportInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarker {

	
	/**
	 * 生成静态table
	 * @param reportInfo
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String MakeHtml(ReportInfo reportInfo){
		// 第一步：创建一个Configuration对象。
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 创建一个Writer对象。
        StringWriter out = new StringWriter();
        // 第二步：设置模板文件所在的路径。  
        try {
			configuration.setDirectoryForTemplateLoading(new File(PropertyUtils.getProperty(Constants.P_TEMPLATE)));
			// 第三步：设置模板文件使用的字符集。 
	        configuration.setDefaultEncoding("utf-8");  
	        // 第四步：加载一个模板，创建一个模板对象。  
	        Template template = configuration.getTemplate(Constants.TEMPLATE);  
	        // 第五步：创建一个模板使用的数据集。
	        Map<String,Object> dataModel = dealData(reportInfo);  
	        // 第六步：调用模板对象的process方法输出文件。  
	        template.process(dataModel, out); 
	        return out.toString();
		} catch (IOException|TemplateException e) {
			return Constants.ERROR;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
         	
	}
	
	
	/**
	 * 处理数据
	 * @param reportInfo
	 * @return
	 */
	public static Map<String, Object> dealData(ReportInfo reportInfo){
		Map<String, Object> data = new HashMap<>();
		List<ReportDetail> list = reportInfo.getDetails();
		Map<String, List<ReportDetail>> header = list.stream().collect(Collectors.groupingBy(ReportDetail::getType));
		data.put("row", header.get("0"));
		data.put("column", header.get("1"));
		data.put("hasSec1", reportInfo.getHasSecHead1());
		data.put("hasSec2", reportInfo.getHasSecHead2());
		return data;
		
	}
	
	
	/**
	 * 填充数据
	 * @param html
	 * @param data
	 * @return
	 */
	public static String setData(String html,List<Object> data){
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		Elements tds = body.getElementsByAttribute(Constants.TYPE);
		for(Element td : tds){
			String keyId = td.attr(Constants.ID);
			String[] keys = Strings.splitIgnoreBlank(keyId);
			int value = 0;
			for(int i = 0;i<data.size();i++){
				JSONObject obj = (JSONObject) data.get(i);
				boolean isValue = true;
				for(String key:keys){
					String[] kv = Strings.splitIgnoreBlank(key,Constants.SPLIT_LINE);
					if(kv[1].equals(obj.getString(kv[0]))||
							Constants.ALL.equals(kv[1])&&obj.containsKey(kv[0])){
						
					}else{
						isValue = false;
						break;
					}
				}
				if(isValue){
					value+=obj.getIntValue(Constants.VALUE);
				}
			}
			td.html(value+"");
		}
		return doc.toString();
		
	}
		


             
               
		
		
	

}
