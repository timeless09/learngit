/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Until;

import entity.Clazz;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author cleju
 */
public class Export {
    //要存储的json数据，表头，key，临时存储路径及名称
    public void save(JSONArray json,String[] data,String [] keys,String path,HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow(0);
        for (int i = 0; i < data.length; i++) {
            cell = row.createCell(i);//根据表格行创建单元格
            cell.setCellValue(String.valueOf(data[i]));//添加表头
        }
        for (int i = 0; i < json.size(); i++) {
            row = sheet.createRow(i + 1);//创建表格行
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject job = json.getJSONObject(i);
                    // 得到 每个对象中的属性值
                for (int j = 0; j < keys.length; j++) {
                    cell = row.createCell(j);//创建表格列
//                    System.out.println(job.get(keys[j]));
                    cell.setCellValue(job.get(keys[j]).toString());//添加json对象里key的对应的value
                }
        }
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.flush();
        fos.close();
        wb.close();
        download(path,response);
        File file = new File(path);
        file.delete();
    }
    private void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
