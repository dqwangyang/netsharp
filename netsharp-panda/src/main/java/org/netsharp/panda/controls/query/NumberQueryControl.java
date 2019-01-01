package org.netsharp.panda.controls.query;

import org.netsharp.panda.controls.input.NumberBox;
import org.netsharp.panda.controls.input.Select;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;


public class NumberQueryControl extends Table
{
    protected String jsControlType = "NumberBoxQueryItem";

    private Boolean interval = false;
    
    private Boolean shortcut = false;
    
    private String operation;

    private PQueryItem queryItem;
    
    protected Class<?> controlType;

    private boolean required;
    
    public String getJsControlType() {
		return jsControlType;
	}

	public void setJsControlType(String jsControlType) {
		this.jsControlType = jsControlType;
	}

	public Boolean getInterval() {
		return interval;
	}

	public void setInterval(Boolean interval) {
		this.interval = interval;
	}

	public Boolean getShortcut() {
		return shortcut;
	}

	public void setShortcut(Boolean shortcut) {
		this.shortcut = shortcut;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public PQueryItem getQueryItem() {
		return queryItem;
	}

	public void setQueryItem(PQueryItem queryItem) {
		this.queryItem = queryItem;
	}

	public Class<?> getControlType() {
		return controlType;
	}

	public void setControlType(Class<?> controlType) {
		this.controlType = controlType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
    public void initialize()
    {
		this.setId(queryItem.getPropertyName());
		this.required = queryItem.isRequired();
		this.interval = queryItem.isInterzone();
		this.operation = queryItem.getOperation();
		this.shortcut = queryItem.isShortcut();
		this.setStyle("width:"+queryItem.getWidth()+"px;");
		
		if(this.interval){

			this.initializeIntervalControls();
		}else{
			
			this.initializeControls();
		}
		
        super.initialize();
    }
    
	private void initializeControls(){
		TR tr = new TR();
		this.getControls().add(tr);
		TD td = new TD();{

			td.setStyle("width:80px;");
			tr.getControls().add(td);
			if(this.shortcut){

				Select shortcut = this.GetOperatorControls();
				td.getControls().add(shortcut);
			}
		}

		td = new TD();{
			td.setStyle ("width:150px;");
	    	NumberBox numberBox = (NumberBox)ReflectManager.newInstance(controlType);
	        {
	        	numberBox.setClassName("nsInput");
	        	numberBox.collected = false;
	        	numberBox.required = this.required;
	        	numberBox.getInnerValues().put("query", "1");
	        	numberBox.width = 150;
	        	numberBox.controlType = jsControlType;
	        	numberBox.setId("Start_" + queryItem.getPropertyName().replaceAll("\\.","_"));
	        	numberBox.getInnerValues().put("propertyName", queryItem.getPropertyName());
				if(!this.interval){

					numberBox.getInnerValues().put("interval","false");
				}
	        }
			td.getControls().add(numberBox);
			tr.getControls().add(td);
		}
	}
    
    private Select GetOperatorControls(){
    	
		Select shortcut = new Select();{
			shortcut.setStyle("width:80px");
			shortcut.editable = false;
			shortcut.disabled = this.shortcut;
			if(!StringManager.isNullOrEmpty(operation)){
				
				shortcut.value = operation;
			}
			shortcut.setId("shortcut_"+ queryItem.getPropertyName().replaceAll("\\.", "_"));
		}
		SelectOption option = null;
		option = new SelectOption();
		{
			option.optionValue = ">=";
			option.value = "大于等于";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = ">";
			option.value = "大于";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "=";
			option.value = "等于";
		}
		shortcut.getControls().add(option);
		
		option = new SelectOption();
		{
			option.optionValue = "<>";
			option.value = "不等于";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "<=";
			option.value = "小于等于";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "<";
			option.value = "小于";
		}
		shortcut.getControls().add(option);

		return shortcut;
    }

	private void initializeIntervalControls(){
		
    	NumberBox start = (NumberBox)ReflectManager.newInstance(controlType);
        {
        	start.setClassName("nsInput");
        	start.collected = false;
        	start.required = this.required;
        	start.width = 68;
        	start.controlType = jsControlType;
        	start.getInnerValues().put("query", "1");
        	start.setId("Start_" + queryItem.getPropertyName().replaceAll("\\.","_"));
            start.getInnerValues().put("propertyName", queryItem.getPropertyName());
        }

        NumberBox end = (NumberBox)ReflectManager.newInstance(controlType);
        {
        	end.setClassName("nsInput");
        	end.collected = false;
        	end.required = this.required;
        	end.width = 68;
        	end.controlType = jsControlType;
        	end.getInnerValues().put("query", "1");
        	end.setId("End_" + queryItem.getPropertyName().replaceAll("\\.","_"));
            end.getInnerValues().put("propertyName", queryItem.getPropertyName());
        }

        TR tr = new TR();
        this.getControls().add(tr);

        TD td = new TD();
        {
            td.getControls().add(start);
            tr.getControls().add(td);
        }

        td = new TD();{
        	td.setStyle("padding: 0 4px;");
        	td.value = "-";
            tr.getControls().add(td);
        }

        td = new TD(); 
        { 
            tr.getControls().add(td);
            td.getControls().add(end);
        }
	}
}