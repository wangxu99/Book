package com.oracle.web.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.oracle.web.service.BookService;
import com.oracle.web.service.FenleiService;

@Controller
@Scope(value = "prototype")
public class BookHandler {

	@Autowired
	private BookService bookService;

	@Autowired
	private FenleiService fenleiService;

	// 全查分页
	@RequestMapping(value = "/showBookHandler/{pageNow}", method = RequestMethod.GET)
	public String showBookHandler(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {
		// System.out.println("访问页数" + pageNow);
		/*
		 * if (pageNow == null || pageNow < 1) { pageNow = 1; }
		 */
		PageBean<BookAndFenlei> pb = this.bookService.showBookPesgeAll(pageNow);
		/*
		 * List<BookAndFenlei> list = pb.getBeanList();
		 * 
		 * for (BookAndFenlei bookAndFenlei : list) {
		 * System.out.println(bookAndFenlei.toString()); }
		 */
		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		request.setAttribute("flist", flist);
		request.setAttribute("pb", pb);
		request.setAttribute("showPesge", "showBook");// 控制页面跳转

		return "showBook";

	}

	// 图书高级搜索
	@RequestMapping(value = "/GaoJiSs", method = RequestMethod.GET)
	public String GaoJiSs(Book book, @RequestParam(value = "pageNow") Integer pageNow, HttpServletRequest request) {
		if (pageNow == null || pageNow < 1) {
			pageNow = 1;
		}
		PageBean<BookAndFenlei> pb = this.bookService.showBookPesgeGaoJi(pageNow, book);

		//List<BookAndFenlei> list = pb.getBeanList();
		String url = this.getUrl2(request);
		pb.setUrl(url);
		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		request.setAttribute("flist", flist);
		request.setAttribute("pb", pb);
		request.setAttribute("showPesge", "gao");// 控制页面跳转

		return "showBook";
	}

	private String getUrl2(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String url = this.getUrl(req);
		int index = url.lastIndexOf("&pageNow=");
		if (index == -1) {
			return url;
		}
		url = url.substring(0, index);
		// System.out.println(url);
		return url;
	}

	private String getUrl(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String path = req.getContextPath();
		  //System.out.println(path);     /Book
		String servlet = req.getServletPath(); 
		 // System.out.println(servlet);  /GaoJiSs
		String param = req.getQueryString();
		 // System.out.println(param);  flid=13&bname=&press=&author=&stock=&pageNow=1
		  //System.out.println(path + servlet + "?" + param);
		// /Book/GaoJiSs?flid=13&bname=&press=&author=&stock=&pageNow=1
		
		return path + servlet + "?" + param;
	}

	// 添加图书校验
	@RequestMapping(value = "/yanzhengAddBook", method = RequestMethod.GET)
	public void yanzhengAddBook(@RequestParam(value = "bname") String bname, @RequestParam(value = "flid") String flid,
			HttpServletResponse response) throws IOException {
		// 调用service进行查询
		// System.out.println(userName);
		response.setContentType("text/html;charset=UTF-8");
		Book b = this.bookService.yanzhengAddBook(bname, flid);

		// System.out.println(existUser);
		// 获取response对象，向页面输出信息

		// 判断是否为空
		if (b == null) {
			// 分类下图书不经存在，可以添加
			// 有异常则向上抛出
			response.getWriter().write("{\"valid\":\"true\"}");
		} else {
			// 分类下图书已存在，不能添加

			response.getWriter().write("{\"valid\":\"false\"}");

		}
		// AJAX操作，不需要页面跳转

	}

	// 添加图书界面,分类遍历
	@RequestMapping(value = "/addBookUl", method = RequestMethod.GET)
	public String addBookUl(HttpServletRequest request) {
		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		request.setAttribute("flist", flist);
		return "addbook";

	}

	// 添加图书
	// 转发forward:/.... 重定向:redirect:/...
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(Book book, HttpServletRequest request) {
		// System.out.println(monster.toString());
		int i = this.bookService.save(book);
		if (i == 1) {
			request.setAttribute("mag", "添加图书成功");
			return "redirect:/showBookHandler/1";
		} else {
			return "forward:/addBookUl";
		}

	}

	// 删除图书
	@RequestMapping(value = "/deleteBook/{ids}/{pageNow}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "ids") String ids, @PathVariable(value = "pageNow") Integer pageNow) {
		// System.out.println(ids);
		this.bookService.deleteBook(ids);
		return "redirect:/showBookHandler/" + pageNow;

	}

	// 修改图书界面
	@RequestMapping(value = "/updateBookUl/{id}/{pageNow}", method = RequestMethod.GET)
	public String updateUl(@PathVariable(value = "id") Integer id, @PathVariable(value = "pageNow") Integer pageNow,
			HttpServletRequest request) {// 妖怪

		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		request.setAttribute("flist", flist);
		Book b = this.bookService.updateBookUl(id);
		// System.out.println(b.toString());
		request.setAttribute("b", b);
		return "changeBook";

	}

	// 实现修改图书
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT)
	public String update(Book book, @RequestParam(value = "pageNow") Integer pageNow) {

		// System.out.println(monster.toString());
		this.bookService.updateBook(book);

		return "redirect:/showBookHandler/" + pageNow;
	}

	// 验证修改图书
	@RequestMapping(value = "/yzupdateBook", method = RequestMethod.GET)
	public void yzUpdateBook(@RequestParam(value = "bname") String bname, @RequestParam(value = "flid") String flid,
			@RequestParam(value = "bid") Integer bid, HttpServletResponse response) throws IOException {
		// 调用service进行查询
		// System.out.println(userName);
		response.setContentType("text/html;charset=UTF-8");
		Book b = this.bookService.yanzhengAddBook(bname, flid);

		// System.out.println(b.toString());
		// 获取response对象，向页面输出信息

		// 判断是否为空
		if (b == null) {
			// bid 一样没改变是一本书 ，可修改
			// b没空 该分类下没有该图书可添加

			response.getWriter().write("{\"valid\":\"true\"}");
		} else if (b.getBid().equals(bid)) {
			response.getWriter().write("{\"valid\":\"true\"}");
		} else {
			// 分类下图书已存在，不能添加

			response.getWriter().write("{\"valid\":\"false\"}");

		}
		// AJAX操作，不需要页面跳转

	}

	// 导出图书
	@RequestMapping(value = "/outPutBook/{ids}", method = RequestMethod.GET)
	public void outPutBook(@PathVariable(value = "ids") String ids1, HttpServletResponse response) throws IOException {

		List<BookAndFenlei> list = null;
		String key = "";
		if (ids1.equals("a")) {// 传入a 表示导出全部

			list = this.bookService.outPutBookAll();
			key = "全部";

		} else {
			// System.out.println(ids1);
			list = this.bookService.outPutBookIds(ids1);
			key = "勾选";

		}
		// 创件一个工作蒲
		HSSFWorkbook Workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = Workbook.createSheet(key + "图书信息表");

		sheet.setColumnWidth(7, 15 * 256); // 设定列宽度
		// 设置样式
		HSSFCellStyle style = Workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = Workbook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.DARK_RED.index);
		style.setFont(font);
		String[] title = { "编号", "分类编号", "图书名", "图书价格", "出版社", "作者", "库存" };
		HSSFRow row = sheet.createRow(0);// 从0开始
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
			BookAndFenlei book = list.get(i);

			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue(book.getBid());

			HSSFCell cell2 = row1.createCell(1);
			cell2.setCellValue(book.getFenlei().getFname());

			HSSFCell cell3 = row1.createCell(2);
			cell3.setCellValue(book.getBname());

			HSSFCell cell4 = row1.createCell(3);
			cell4.setCellValue(book.getMoney());

			HSSFCell cell5 = row1.createCell(4);
			cell5.setCellValue(book.getPress());

			HSSFCell cell6 = row1.createCell(5);
			cell6.setCellValue(book.getAuthor());

			HSSFCell cell7 = row1.createCell(6);
			cell7.setCellValue(book.getStock());

			cell1.setCellStyle(style1);
			cell2.setCellStyle(style1);
			cell3.setCellStyle(style1);
			cell4.setCellStyle(style1);
			cell5.setCellStyle(style1);
			cell6.setCellStyle(style1);
			cell7.setCellStyle(style1);

		}

		String fname = key + "图书信息表.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fname.getBytes("UTF-8"), "iso-8859-1"));
		response.flushBuffer();
		Workbook.write(response.getOutputStream());

	}

}
