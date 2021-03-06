package com.be3short.jfx.buttons;

import com.be3short.jfx.connectors.ApplicationPropertiesInterface;
import com.be3short.jfx.connectors.application.DisplayActions;
import com.be3short.jfx.event.menu.FxEventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;

public class TabMenuButton
{

	Node displayContent;

	public Node getDisplayContent()
	{
		return displayContent;
	}

	public void setDisplayContent(Node displayContent)
	{
		this.displayContent = displayContent;
	}

	public SplitMenuButton button;
	private Label buttonLabel;
	private FxEventHandler eventHandler;
	DisplayActions appElements;

	public TabMenuButton(FxEventHandler handler, Label label, Node display_content, DisplayActions app_elements)
	{
		eventHandler = handler;
		buttonLabel = label;
		displayContent = display_content;
		appElements = app_elements;
		initializeButton();
	}

	private void initializeButton()
	{
		button = new SplitMenuButton();
		button.setPopupSide(Side.RIGHT);
		button.setGraphic(buttonLabel);
		button.setOnAction(new EventHandler<ActionEvent>()
		{

			public void handle(ActionEvent event)
			{
				appElements.updateContentDisplay(displayContent);
			}
		});
		try
		{
			button.getItems().addAll(eventHandler.getRootMenuItems());
		} catch (Exception noItems)
		{

		}
	}

}
