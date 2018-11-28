package util;



import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

/**
 * 文件上传封装类
 * 
 */
public class FileUpLoad {
	/**
	 * 文件上传 返回收集的列表参数
	 * @param request
	 * @param response
	 * @param uploadpath 上传路径
	 * @param temppath 上传临时路径
	 * @return 表单域中普通字段形如：{ name=123,age=456};
	 * @param sizeMax 限制文件大小字节数
	 * @param tempSzie 限制类存数据大小字节
	 * @throws Exception
	 */

	public static Map<String, String> upLoad(HttpServletRequest request,
			HttpServletResponse response, String uploadpath, String temppath,long sizeMax,int tempSzie)
			throws Exception {
		String name=null; 
		Map<String, String> params = new HashMap<String, String>();

		String temp = request.getSession().getServletContext().getRealPath("/")
				+ temppath; // 临时目录
		
		System.out.println("temp=" + temp);
		
		String loadpath = request.getSession().getServletContext().getRealPath(
				"/")
				+ uploadpath; // 上传文件存放目录
		
		System.out.println("loadpath=" + loadpath);
		
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(sizeMax); // 设置允许用户上传文件大小,单位:字节
		fu.setSizeThreshold(tempSzie); // 设置最多只允许在内存中存储的数据,单位:字节
		fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录

		// 开始读取上传信息
		
		List fileItems = null;

		try {
			fileItems = fu.parseRequest(request);
			System.out.println("fileItems=" + fileItems);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterator iter = fileItems.iterator(); // 依次处理每个上传的文件
		
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();// 忽略其他不是文件域的所有表单信息
			
			if (!item.isFormField()) {
			   String string=item.getFieldName();
               name= item.getName();// 获取上传文件名,包括路径
               
               System.out.println("开始的name:"+name);
               
				name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0)
					continue;
				int point = name.indexOf(".");
				name = (new Date()).getTime()
						+ name.substring(point, name.length());
				
				System.out.println("封装后的name"+name);
				
				File fNew = new File(loadpath, name);
				try {
					item.write(fNew);
				} catch (Exception e) {
					e.printStackTrace();

				}
				params.put(string, name);
			} else// 取出不是文件域的所有表单信息
			{
				// String fieldvalue = item.getString();
				// 如果包含中文应写为：(转为UTF-8编码)
				String fieldvalue = new String(item.getString().getBytes(
						"iso8859-1"), "utf-8");
				String name1 = item.getFieldName();
				params.put(name1, fieldvalue);
			}
		}
		
		return params;

	}

}
