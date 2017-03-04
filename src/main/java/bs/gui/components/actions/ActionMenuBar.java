package bs.gui.components.actions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import bs.commons.objects.access.MethodAccessor;
import bs.commons.objects.execution.LocationType;
import bs.commons.objects.execution.MethodId;
import bs.commons.objects.labeling.LabelReader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

public class ActionMenuBar extends MenuBar
{

	private Object menuClass;
	private HashMap<String, Menu> menus;

	public ActionMenuBar(Object menu_class)
	{
		super();
		menuClass = menu_class;
		menus = new HashMap<String, Menu>();
		initialize();
	}

	private void initialize()
	{
		HashMap<String, Method> menuMethods = MethodAccessor.getEventMethod(menuClass, MethodId.class);
		for (Method method : menuMethods.values())
		{
			try
			{
				MethodId methodId = (MethodId) method.getAnnotation(MethodId.class);
				initializeMenu(methodId.location(), methodId.locationTypes());
				initializeMenuItem(methodId.location(), methodId.locationTypes(), methodId.label(), methodId.id());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for (String menuName : menus.keySet())
		{
			//getMenus().add(menus.get(menuName));
		}

	}

	private void initializeMenu(String menu_location, String[] menu_location_types)
	{
		if (!menu_location.equals(""))
			if (Arrays.asList(menu_location_types).contains(LocationType.menuItem))
			{
				if (!menus.containsKey(menu_location))
				{
					menus.put(menu_location, new Menu(menu_location));
					getMenus().add(menus.get(menu_location));
				}
			}
	}

	private void initializeMenuItem(String menu_location, String[] menu_location_types, String menu_label,
	String method_id)
	{
		if (Arrays.asList(menu_location_types).contains(LocationType.menuItem))
		{
			menus.get(menu_location).getItems().add(new ActionMenuItem(menu_label, menuClass, method_id));

		}
	}
}