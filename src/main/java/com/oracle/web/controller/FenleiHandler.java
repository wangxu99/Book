package com.oracle.web.controller;

 

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.service.FenleiService;
 
 
@Controller
@Scope(value = "prototype")
public class FenleiHandler {
	 
	@Autowired
	private FenleiService fenleiService;
	
	@RequestMapping(value="/fenlei1",method=RequestMethod.GET)
	public String fenleis(HttpServletRequest request) {


	
		
			List<Fenlei> list = fenleiService.list();


			System.out.println(list);

			
			request.setAttribute("fList", list);


			return "showFenlei";
		}
		
	 
//	@RequestMapping(value="/addUI",method=RequestMethod.GET)
//	public String addUI(HttpServletRequest request){
//		
//		System.out.println("add");
//		
//		return "addFenlei";
//		
//	}
//	
	@RequestMapping(value="/fenlei",method=RequestMethod.POST)
	public String add(Fenlei fenlei){
		
		fenleiService.save(fenlei);
		
		return "redirect:/fenleis/1";
		
	}
	
	// 添加图书校验
		@RequestMapping(value = "/yanzhengAddFenlei", method = RequestMethod.GET)
		public void yanzhengAddFenlei(@RequestParam(value = "fname") String fname, 
				HttpServletResponse response) throws IOException {
			// 调用service进行查询
			 
			response.setContentType("text/html;charset=UTF-8");
			Fenlei f = this.fenleiService.yanzhengAddFenlei(fname);

			// System.out.println(existUser);
			// 获取response对象，向页面输出信息

			// 判断是否为空
			if (f == null) {
				// 分类下图书不经存在，可以添加
				// 有异常则向上抛出
				response.getWriter().write("{\"valid\":\"true\"}");
			} else {
				// 分类下图书已存在，不能添加

				response.getWriter().write("{\"valid\":\"false\"}");

			}
			// AJAX操作，不需要页面跳转

		}

	
	@RequestMapping(value = "/fenlei_delete/{fid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("fid") String fid1,HttpSession session) {

		Integer fid=Integer.parseInt(fid1);
		
		int a= this.fenleiService.yanzhengAddFenlei2(fid);
		if(a==0){
			Fenlei f = new Fenlei();


			f.setFid(fid);


			fenleiService.delete(f);

			session.setAttribute("mag", "删除成功");
			return "redirect:/fenleis/1";

		}else{
			
			session.setAttribute("mag", "该分类下有图书禁止删除");
			 
			return "redirect:/fenleis/1";

		}

	 

	}
	
	
	@RequestMapping(value = "/fenlei/{fid}", method = RequestMethod.GET)
	public String updateUI(@PathVariable("fid") String fid1, HttpSession session) {

          Integer fid=Integer.parseInt(fid1);
		Fenlei fenlei = fenleiService.selectByPrimaryKey(fid);

		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		session.setAttribute("flist", flist);
	 
		session.setAttribute("f", fenlei);
 
		return "updatefl";


	}
	
	@RequestMapping(value = "/fenleiupdate", method = RequestMethod.PUT)
	public String update(Fenlei fenlei) {


		fenleiService.update(fenlei);


		return "redirect:/fenleis/1";
		
	}
	
	
	@RequestMapping(value = "/fenleis/{pageNow}", method = RequestMethod.GET)
	public String list(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {




		if (pageNow == null || pageNow < 1) {


			pageNow = 1;


		}


		PageBean<Fenlei> pb = this.fenleiService.selectAllByPageHelper(pageNow);


		request.setAttribute("pb", pb);
		
		System.out.println(pb);


		return "showFenlei";
	}
	
	// 验证修改分类
		@RequestMapping(value = "/yzfenleiupdate", method = RequestMethod.GET)
		public void yzfenleiupdate( @RequestParam(value = "fname") String fname, @RequestParam(value = "fid") Integer fid ,HttpServletResponse response) throws IOException {
			// 调用service进行查询
			// System.out.println(userName);
			response.setContentType("text/html;charset=UTF-8");
			Fenlei f = this.fenleiService.yanzhengAddFenlei(fname);

			// System.out.println(b.toString());
			// 获取response对象，向页面输出信息

			// 判断是否为空
			if (f == null) {
				// bid 一样没改变是一本书 ，可修改
				// b没空 该分类下没有该图书可添加
				
				int a= this.fenleiService.yanzhengAddFenlei2(fid);
				if(a==0){
				response.getWriter().write("{\"valid\":\"true\"}");
				}else{
					response.getWriter().write("{\"valid\":\"false\"}");
				}
			  
			} else {
				// 分类下图书已存在，不能添加

				response.getWriter().write("{\"valid\":\"false\"}");

			}
			// AJAX操作，不需要页面跳转

		}
	
	
		// 导出分类
		@RequestMapping(value = "/outPutFenLei/{ids}", method = RequestMethod.GET)
		public void
		outPutFenLei(@PathVariable(value = "ids") String ids1,HttpServletResponse response) throws IOException {
			 
			List<Fenlei> list = null;
			String key = "";
			if (ids1.equals("a")) {//传入a 表示导出全部
				
				list = this.fenleiService.outPutFenLeiAll();
				key = "全部";

			}else{ 
				//System.out.println(ids1);
				list = this.fenleiService.outPutFenleiIds(ids1);
				key = "勾选";

			}
			//创件一个工作蒲
			HSSFWorkbook Workbook = new HSSFWorkbook();
			//创建一个工作表
			HSSFSheet sheet = Workbook.createSheet(key + "分类信息表");
	          
			sheet.setColumnWidth(7, 15 * 256); //设定列宽度
			//设置样式
			HSSFCellStyle style = Workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = Workbook.createFont();
			font.setBold(true);
			font.setColor(HSSFColor.DARK_RED.index);
			style.setFont(font);
			String[] title = { "分类编号", "分类名"};
			HSSFRow row = sheet.createRow(0);//从0开始
			for (int i = 0; i < title.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(title[i]);
			}
			HSSFCellStyle style1 = Workbook.createCellStyle();
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			// 设置字体样式
			for (int i = 0; i < list.size(); i++) { 

				HSSFRow row1 = sheet.createRow(i + 1);
				Fenlei fenlei = list.get(i);

				HSSFCell cell1 = row1.createCell(0);
				cell1.setCellValue(fenlei.getFid());

				HSSFCell cell2 = row1.createCell(1);
				cell2.setCellValue(fenlei.getFname());
 
  
 
				cell1.setCellStyle(style1);
				cell2.setCellStyle(style1);
				 
			 
 
			}
			 
			 String fname = key +"分类信息表.xls"; 
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename="+new String(fname.getBytes("UTF-8"), "iso-8859-1"));
			response.flushBuffer();
			 Workbook.write(response.getOutputStream());
			 
		}

	}
