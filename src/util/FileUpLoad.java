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
 * �ļ��ϴ���װ��
 * 
 */
public class FileUpLoad {
	/**
	 * �ļ��ϴ� �����ռ����б����
	 * @param request
	 * @param response
	 * @param uploadpath �ϴ�·��
	 * @param temppath �ϴ���ʱ·��
	 * @return ��������ͨ�ֶ����磺{ name=123,age=456};
	 * @param sizeMax �����ļ���С�ֽ���
	 * @param tempSzie ����������ݴ�С�ֽ�
	 * @throws Exception
	 */

	public static Map<String, String> upLoad(HttpServletRequest request,
			HttpServletResponse response, String uploadpath, String temppath,long sizeMax,int tempSzie)
			throws Exception {
		String name=null; 
		Map<String, String> params = new HashMap<String, String>();

		String temp = request.getSession().getServletContext().getRealPath("/")
				+ temppath; // ��ʱĿ¼
		
		System.out.println("temp=" + temp);
		
		String loadpath = request.getSession().getServletContext().getRealPath(
				"/")
				+ uploadpath; // �ϴ��ļ����Ŀ¼
		
		System.out.println("loadpath=" + loadpath);
		
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(sizeMax); // ���������û��ϴ��ļ���С,��λ:�ֽ�
		fu.setSizeThreshold(tempSzie); // �������ֻ�������ڴ��д洢������,��λ:�ֽ�
		fu.setRepositoryPath(temp); // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼

		// ��ʼ��ȡ�ϴ���Ϣ
		
		List fileItems = null;

		try {
			fileItems = fu.parseRequest(request);
			System.out.println("fileItems=" + fileItems);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterator iter = fileItems.iterator(); // ���δ���ÿ���ϴ����ļ�
		
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();// �������������ļ�������б���Ϣ
			
			if (!item.isFormField()) {
			   String string=item.getFieldName();
               name= item.getName();// ��ȡ�ϴ��ļ���,����·��
               
               System.out.println("��ʼ��name:"+name);
               
				name = name.substring(name.lastIndexOf("\\") + 1);// ��ȫ·������ȡ�ļ���
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0)
					continue;
				int point = name.indexOf(".");
				name = (new Date()).getTime()
						+ name.substring(point, name.length());
				
				System.out.println("��װ���name"+name);
				
				File fNew = new File(loadpath, name);
				try {
					item.write(fNew);
				} catch (Exception e) {
					e.printStackTrace();

				}
				params.put(string, name);
			} else// ȡ�������ļ�������б���Ϣ
			{
				// String fieldvalue = item.getString();
				// �����������ӦдΪ��(תΪUTF-8����)
				String fieldvalue = new String(item.getString().getBytes(
						"iso8859-1"), "utf-8");
				String name1 = item.getFieldName();
				params.put(name1, fieldvalue);
			}
		}
		
		return params;

	}

}
