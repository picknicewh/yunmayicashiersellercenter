package com.yun.ma.yi.app.module.common.view.listener;

import com.yun.ma.yi.app.module.common.view.ItemEditText;
import com.yun.ma.yi.app.module.common.view.ItemMidEditText;

/**
 * 文本框变化，监听.
 */
public interface IItemTextChangeListener {

	/**
	 * 编辑表单类型输入变动事件.
	 * 
	 * @param obj
	 */
	void onItemEditTextChange(ItemEditText obj);

	/**
	 * 编辑表单类型输入变动事件.
	 *
	 * @param obj
	 */
	void onItemMidEditTextChange(ItemMidEditText obj);
	
}
