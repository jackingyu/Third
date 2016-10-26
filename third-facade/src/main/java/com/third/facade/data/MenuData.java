package com.third.facade.data;

import java.util.ArrayList;
import java.util.List;


public class MenuData
{

	private String menuid;
	private String icon;
	private String url;
	private String menuname;
	private List<MenuData> menus = new ArrayList<MenuData>();

	public void setMenuid(String menuid)
	{
		this.menuid = menuid;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setMenuname(String menuname)
	{
		this.menuname = menuname;
	}

	public void setMenus(List<MenuData> menus)
	{
		this.menus = menus;
	}

	public String getMenuid()
	{
		return menuid;
	}

	public String getIcon()
	{
		return icon;
	}

	public String getUrl()
	{
		return url;
	}

	public String getMenuname()
	{
		return menuname;
	}

	public List<MenuData> getMenus()
	{
		return menus;
	}

	public void addSubMenu(MenuData subMenuData)
	{
		this.getMenus().add(subMenuData);
	}
}
