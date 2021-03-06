package com.wondersgroup.util.controller;

import com.wondersgroup.util.util.Page;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 定义标准的rest方法以对应实体对象的操作,以达到统一rest的方法名称, 还可以避免子类需要重复编写@RequestMapping
 * annotation.
 * 
 * 子类要实现某功能只需覆盖下面的方法即可. 注意: 覆盖时请使用@Override,以确保不会发生错误
 * 
 * <pre>
 * /userinfo                => index()  
 * /userinfo/page           => page()  
 * /userinfo/new            => _new()  
 * /userinfo/{id}           => show()  
 * /userinfo/{id}/edit      => edit()  
 * /userinfo        POST    => create()  
 * /userinfo/{id}   PUT     => update()  
 * /userinfo/{id}   DELETE  => delete()  
 * /userinfo        DELETE  => batchDelete()
 * </pre>
 * 
 * @author badqiu
 */
@RestController
public class BaseRestSpringController<Entity, PK> {
	protected static final String SUCCESS="success";
	protected static final String ERROR="error";

	// 特别说明: 由于spring的方法参数映射太过于灵活,如果以下参数不适应你,请自己修改参数并修改代码生成器模板
	// 如果你不喜欢 HttpServletRequest request,HttpServletResponse response作为方法参数，也请删除

	@RequestMapping
	public Object index(HttpServletRequest request,
			HttpServletResponse response, Entity model) {
		throw new UnsupportedOperationException("not yet implement");
	}

	@RequestMapping(value = "/page")
	@ResponseBody
	public Object page(HttpServletRequest request,
			HttpServletResponse response,Page page, Entity model) {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 进入新增 */
	@RequestMapping(value = "/new")
	public ModelAndView _new(HttpServletRequest request,
			HttpServletResponse response, Entity model) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 显示 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Object show(@PathVariable PK id) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 编辑 */
	@RequestMapping(value = "/{id}/edit")
	public Object edit(@PathVariable PK id) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 保存新增 */
	@RequestMapping(method = RequestMethod.POST)
	public Object create(HttpServletRequest request,
			HttpServletResponse response, Entity model) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 保存更新 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable PK id, HttpServletRequest request,
			HttpServletResponse response, Entity model) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 删除 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable PK id) {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object batchDelete(@RequestParam("items") PK[] items) {
		throw new UnsupportedOperationException("not yet implement");
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}
