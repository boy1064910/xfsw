package com.ptmind.datadeck.entity.datasource;

import com.ptmind.datadeck.entity.datasource.editor.EditorConfig;

/**
 * 数据源编辑器公共功能的结构体(提供给数据源编辑器初始化的接口使用)
 * @author lxp
 * @version 3.0.0
 */
public class EditorDataSource extends AbstractDataSource {

	private EditorConfig editorConfig;

	public EditorConfig getEditorConfig() {
		return editorConfig;
	}

	public void setEditorConfig(EditorConfig editorConfig) {
		this.editorConfig = editorConfig;
	}
}

