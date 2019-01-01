/// <reference path="core.js"/>
/// <reference path="panda.js"/>
/// <reference path="panda_Controls.js"/>

org.netsharp.panda.QueryController = System.Object.Extends({
	
    ctor: function () {
        this.qpc = null;
        this.iscollected = false;
        this.context = null;
        this.controls = [];
    },
	reset:function(){
		
        this.collectControl();
        this.qpc = [];
        for (var i = 0; i < this.controls.length; i++) {

        	this.controls[i].clear();
        }
	},
    getQueryParameters: function () {
 
        var isValidate = $("#queryFrom").form('validate');
        if(!isValidate){
        	
        	return false;
        }

        this.collectControl();
        this.qpc = [];
        for (var i = 0; i < this.controls.length; i++) {
            var control = this.controls[i];
            var qp = control.get();
            if (qp != null) {

                this.qpc.push(qp);
            }
        }

        return this.qpc;
    },
    
    getFilterParameters:function(){
    	
        var isValidate = $("#queryFrom").form('validate');
        if(!isValidate){
        	
        	return false;
        }

        this.collectControl();
        this.qpc = [];
        for (var i = 0; i < this.controls.length; i++) {
            var control = this.controls[i];
            var qp = control.getFilter();
            if (qp != null) {

                this.qpc.push(qp);
            }
        }

        return this.qpc;
    },
    
    collectControl: function () {

        if (this.iscollected) {
            return;
        }

        this.iscollected = true;

        var viewmodel = this;

        $("#queryFrom [query='1']").each(function (index, item) {

            var controlType = $(item).attr("controlType");
            var control = null;
            var expression = '';
            if(controlType == 'Custom'){

            	var customControlType = $(item).attr("customControlType");
                expression = 'control=new '+customControlType;
            }else{

                expression = 'control=new org.netsharp.controls.'+controlType;
            }
            eval(expression);
            control.uiElement = item;
            control.propertyName = item.id;
            viewmodel.controls.push(control);
        });
    }
});


//Query Controls
org.netsharp.controls.TextBoxQueryItem=org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        var propertyValue = this.uiElement.value;
        if (System.isnull(propertyValue)){
            return null;
        }
        //去掉两端空格
        propertyValue = propertyValue.replace(/(^\s*)|(\s*$)/g,'');
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue;
        
        var operationAttribute = this.uiElement.attributes['operation'];
        if(operationAttribute){

            var operation = operationAttribute.value;
            if(operation == "Equal"){

                qp.Filter = this.propertyName + "= '" + qp.Value + "'";
            }
        }else{

            qp.Filter = this.propertyName + " LIKE '%" + qp.Value + "%'";
        }

        //qp.Filter = this.propertyName + "=@" + this.propertyName ;
        return qp;
    },
    getFilter:function(){
    	
        var propertyValue = this.uiElement.value;
        if (System.isnull(propertyValue)){
            return null;
        }
        propertyValue = propertyValue.replace(/(^\s*)|(\s*$)/g,'');
        var intelligentMode1 = this.uiElement.attributes['intelligentMode1'];
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        qp.value1 = propertyValue;
        qp.intelligentMode1 = intelligentMode1 || org.netsharp.core.intelligentMode.LIKE;
        return qp;
    },
	clear: function() {
		
		this.uiElement.value = "";
	}
});

org.netsharp.controls.EncryptionBoxQueryItem=org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        var propertyValue = this.uiElement.value;
        if (System.isnull(propertyValue))
        {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue;
        qp.Filter = this.propertyName + "= '{cryp" + qp.Value + "!cryp}'";
        return qp;
    },
    getFilter:function(){
    	
        var propertyValue = this.uiElement.value;
        if (System.isnull(propertyValue)){
            return null;
        }
        propertyValue = '{cryp' + qp.Value + '!cryp}';
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        qp.value1 = propertyValue;
        qp.intelligentMode1 = org.netsharp.core.intelligentMode.EQUALS;
        return qp;
    },
	clear: function() {
		this.uiElement.value = "";
	}
});

org.netsharp.controls.BoolComboBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        var propertyValue = $('#' + this.propertyName).combobox('getValues');

        if (propertyValue == null || propertyValue == undefined || propertyValue == ""||propertyValue=="-1") {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        qp.Filter = this.propertyName + " ='" + propertyValue[0] + "'";

        return qp;
    },
    getFilter:function(){
    	
        var propertyValue = $('#' + this.propertyName).combobox('getValues');
        if (propertyValue == null || propertyValue == undefined || propertyValue == ""||propertyValue=="-1") {
            return null;
        }
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        qp.value1 = propertyValue[0];
        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).combobox('setValue','');
	}
});

org.netsharp.controls.CheckBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        //使用jquery时，打勾的值是checked,未打勾的值是undefined
        //var propertyValue = $('#' + this.uiElement.id).attr('checked');

        var propertyValue = document.getElementById(this.uiElement.id).checked;

        if(System.isnull(propertyValue)){
        	return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "Bool";
        qp.Value = propertyValue;

        qp.Filter = this.propertyName + "='" + qp.Value + "'";

        return qp;
    },
    getFilter:function(){
    	
    	var propertyValue = document.getElementById(this.uiElement.id).checked;
    	 if(System.isnull(propertyValue)){
            return null;
        }
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        qp.value1 = propertyValue;
        return qp;
    },
	clear: function() {
		
	}
});

org.netsharp.controls.DateBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {
    	
        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');
        var interval = $('#' + this.uiElement.id).attr('interval');
        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');
        
        if(System.isnull(propertyValue)){
            return null;
        }

        if (!System.isDateTime(propertyValue)) {

            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {
//            qp.Filter = this.propertyName + ">=@" + this.propertyName;
//            qp.Filter = this.propertyName + ">='" + qp.Value+"'";
        	
        	if(interval === 'true'){
        		
        		 qp.Filter = this.propertyName + ">='" + qp.Value + " 00:00:00' ";
        		
        	}else{

                qp.Filter = this.propertyName + ">='" + qp.Value + " 00:00:00' and "+this.propertyName + "<='" + qp.Value + " 23:59:59' ";
        	}
        }
        else {
//            qp.Filter = this.propertyName + "<@" + this.propertyName;

            qp.Filter = this.propertyName + "<='" + qp.Value + " 23:59:59' ";
        }
        return qp;
    },
    getFilter:function(){
    	
        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');
        var interval = $('#' + this.uiElement.id).attr('interval');
        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');
        
        if(System.isnull(propertyValue)){
            return null;
        }

        if (!System.isDateTime(propertyValue)) {
            return null;
        }

        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        
        if (this.uiElement.id.indexOf("Start_") == 0) {

	      	if(interval === 'true'){

		       qp.value1 = propertyValue+ ' 00:00:00';
	      	   qp.intelligentMode1 = org.netsharp.core.intelligentMode.GTE;
	      	   
	      	   var propertyName = this.propertyName.replace('.','_');
	      	   var value2 =  $('#End_' + propertyName).datebox('getValue');
	      	   if(!System.isnull(value2)){
	      		   
	      		  qp.value2 = value2+ ' 23:59:59';
	      		  qp.intelligentMode2 = org.netsharp.core.intelligentMode.LE;
	      	   }
	      		   
	      	}else{
	
		       qp.value1 = propertyValue+ ' 00:00:00';
		       qp.value2 = propertyValue+ ' 23:59:59';
	      	}
      }else{
    	  
    	  return null;
      }
      return qp;
    },
	clear: function() {
		
		$('#' + this.uiElement.id).datebox('setValue','');
		$('#' + this.uiElement.id).datebox('setValue','');
	}
});

org.netsharp.controls.MonthBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');

        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');

        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }


        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {

        	qp.Filter = this.propertyName + ">='" + qp.Value+"-01'";
        }else {

        	var value = propertyValue+"-01";
        	var day = new Date(value);
        	var daycount = day.getDays();
        	value = propertyValue + "-" + daycount + " 23:59:59";
            qp.Filter = this.propertyName + "<'" + value+"'";
        }
        return qp;
    },
    getFilter:function(){
    	
    	var propertyValue = $('#' + this.uiElement.id).datebox('getValue');
    	 if(System.isnull(propertyValue)){
            return null;
        }
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        if (this.uiElement.id.indexOf("Start_") == 0) {
	       qp.value1 = propertyValue+ '-01 00:00:00';
      	   qp.intelligentMode1 = org.netsharp.core.intelligentMode.GTE;
        }else {
 	       qp.value1 = propertyValue+ '-'+daycount+' 23:59:59';
      	   qp.intelligentMode1 = org.netsharp.core.intelligentMode.LESS;
        }
        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).datebox('setValue','');
	}
});


org.netsharp.controls.EnumBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {
    	
        var propertyValue = $('#' + this.propertyName).combobox('getValues');
        if (propertyValue == null || propertyValue == undefined || propertyValue == "" || propertyValue == "-1") {
        	
            return null;
        }
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        var filter = "";
        $(propertyValue).each(function(i,item){
        	
        	if(item != "-1"){

            	filter+="'"+item+"',";
        	}
        });

        if(!System.isnull(filter)){

            qp.Filter = this.propertyName.replace("_",".") + " in (" + filter.substring(0,filter.length-1) + ")";
        }else{
        	
        	return null;
        }
        return qp;
    },
    getFilter:function(){
    	
    	var propertyValue = $('#' + this.uiElement.id).combobox('getValues');
    	 if(System.isnull(propertyValue) || propertyValue == "-1"){
            return null;
        }
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName.replace("_",".");
        
        var filter = "";
        $(propertyValue).each(function(i,item){
        	
        	if(item != "-1"){

            	filter+="'"+item+"',";
        	}
        });

        if(!System.isnull(filter)){

            qp.value1 =  filter.substring(0,filter.length-1);
        }else{
        	
        	return null;
        }
  	    qp.intelligentMode1 = org.netsharp.core.intelligentMode.IN;
        return qp;
    },
	clear: function() {
		$('#' + this.propertyName).combobox('setValue','');
	}
});

org.netsharp.controls.JavaEnumBoxQueryItem=org.netsharp.controls.EnumBoxQueryItem.Extends({

	clear: function() {
		$('#' + this.propertyName).combobox('setValue','-1');
	}
});



org.netsharp.controls.ReferenceBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        var propertyValue = $('#' + this.propertyName).combogrid('getValue');

        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }

        var foreignkey = $(this.uiElement).attr("foreignkey");
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + foreignkey;
        qp.DbType = "Guid";
        qp.Value = propertyValue;
        var multiple =  $('#' + this.propertyName).combogrid('options').multiple;
        if(multiple == true){
        	//多选
        	var values = $('#' + this.propertyName).combogrid('getValues');
        	var ids = "";
        	$(values).each(function(i,item){
        		
        		if(i==(values.length-1)){

        			ids += item;
        		}else{
        			ids += item+",";
        		}
        	});
        	qp.Value = ids
            qp.Filter = foreignkey + " in (" + ids + ")";
        }else{
        	//单选

            qp.Filter = foreignkey + "='" + qp.Value + "'";
        }

        return qp;
    },
    
    getFilter:function(){
    	
    	var propertyValue = $('#' + this.uiElement.id).combogrid('getValue');
    	 if(System.isnull(propertyValue)){
            return null;
        }
    	 
    	var foreignkey = $(this.uiElement).attr("foreignkey");
        var qp = new org.netsharp.core.FilterParameter();
        qp.key = foreignkey;
        qp.value1 =  propertyValue;
        return qp;
    },
    
	clear: function() {
		$('#' + this.propertyName).combogrid('setValue','');
	}
});

org.netsharp.controls.NumberBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get: function () {

        var propertyValue = $("#" +  this.uiElement.id).numberbox('getValue');
        var propertyName = $("#" +  this.uiElement.id).attr("propertyName");
        var interval = $('#' +  this.uiElement.id).attr('interval');

        if (System.isnull(propertyValue)) 
        {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.DbType = "String";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {
        	
        	if(interval){
        		
        		qp.Filter = propertyName + '=' + propertyValue+" ";
        	}else{

        		qp.Filter = propertyName + ">=" + propertyValue+" ";
        	}
           
        }
        else
        {
            qp.Filter = propertyName + "<=" + propertyValue+" ";
        }

        return qp;
    },
    getFilter:function(){
    	
        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');
        var interval = $('#' + this.uiElement.id).attr('interval');
        var propertyValue = $('#' + this.uiElement.id).numberbox('getValue');
        
        if(System.isnull(propertyValue)){
            return null;
        }

        var qp = new org.netsharp.core.FilterParameter();
        qp.key = this.propertyName;
        if (this.uiElement.id.indexOf("Start_") == 0) {

	       qp.value1 = propertyValue;
      	   qp.intelligentMode1 = org.netsharp.core.intelligentMode.GTE;
      	   
      	   var endCtrlId = '#End_' + this.propertyName;
      	   if($(endCtrlId).length>0){

          	   var value2 =  $(endCtrlId).numberbox('getValue');
          	   if(!System.isnull(value2)){
          		   
          		  qp.value2 = value2;
          		  qp.intelligentMode2 = org.netsharp.core.intelligentMode.LE;
          	   }
      	   }
      }else{
    	  
    	  return null;
      }
      return qp;
    },
	clear: function() {
		$('#' + this.uiElement.id).numberbox('setValue','');
	}
});

org.netsharp.controls.CustomerControl= org.netsharp.controls.Control.Extends({
	ctor: function () {
        this.base();
    },
    init:function(){
    	
    },
    get:function(){
    	
    },
	clear: function() {
		
	}
});


org.netsharp.controls.MonthDateBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');
        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');
        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }


        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {

        	qp.Filter = this.propertyName + ">='" + qp.Value+"-01'";
        }else {

        	var value = propertyValue+"-01";
        	var day = new Date(value);
        	var daycount = day.getDays();
        	value = propertyValue + "-" + daycount + " 23:59:59";
            qp.Filter = this.propertyName + "<'" + value+"'";
        }
        return qp;
    },
    getFilter:function(){
    	
    	return null;
//    	var propertyValue = $('#' + this.uiElement.id).combogrid('getValue');
//    	 if(System.isnull(propertyValue)){
//            return null;
//        }
//    	 
//    	var foreignkey = $(this.uiElement).attr("foreignkey");
//        var qp = new org.netsharp.core.FilterParameter();
//        qp.key = foreignkey;
//        qp.value1 =  propertyValue;
//        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).datebox('setValue','');
	}
});

org.netsharp.controls.MonthBoxQueryItem = org.netsharp.controls.EnumBoxQueryItem.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {
    	
        var propertyValue = $('#' + this.propertyName).combobox('getValues');
        if (propertyValue == null || propertyValue == undefined || propertyValue == "" || propertyValue == "-1") {
        	
            return null;
        }
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        qp.Filter = this.propertyName.replace("_",".") + " =" + propertyValue;
        return qp;
    },
    getFilter:function(){
    	
    	return null;
//    	var propertyValue = $('#' + this.uiElement.id).combogrid('getValue');
//    	 if(System.isnull(propertyValue)){
//            return null;
//        }
//    	 
//    	var foreignkey = $(this.uiElement).attr("foreignkey");
//        var qp = new org.netsharp.core.FilterParameter();
//        qp.key = foreignkey;
//        qp.value1 =  propertyValue;
//        return qp;
    },
    clear: function() {
    	
    }
});

org.netsharp.controls.YearBoxQueryItem = org.netsharp.controls.MonthBoxQueryItem.Extends({
    ctor: function () {
        this.base();
    }
});

org.netsharp.core.QueryParameter = System.Object.Extends({
	
    ctor: function () {
    	
        this.type = null;
        this.name = null;
        this.value = null;
    }
});

org.netsharp.core.FilterParameter = System.Object.Extends({
	
    ctor: function () {

        this.key = null;
        this.value1 = null;
        this.value2 = null;
        this.intelligentMode1 = org.netsharp.core.intelligentMode.EQUALS;
        this.intelligentMode2 = null;
    }
});

org.netsharp.core.intelligentMode = {
		
	EQUALS:0,//严格匹配
	LEFT:1,//左匹配
	RIGHT:2,//右匹配
	LIKE:3,//模糊匹配 
	GTR:4,//大于
	GTE:5,//大于等于
	LESS:6,//小于
	LE:7,//大于等于
	IN:8//In
};
