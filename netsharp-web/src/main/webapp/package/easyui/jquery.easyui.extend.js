
/***************************************************************
 * 
 * 扩展验证方法
 * 
 * */
$.extend($.fn.validatebox.defaults.rules, {
  
    maxLength: {     
        validator: function(value, param){     
            return param[0] >= value.length;     
        },     
        message: '请输入最大{0}位字符.'    
    },
    minLength: {     
        validator: function(value, param){     
            return param[0] <= value.length;     
        },     
        message: '请输入最小{0}位字符.'    
    },
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入不一致'   
    },
    lessOrEqual:{
    	validator: function(value, param){     
            return Number($(param[0]).val()) <= Number($(param[1]).val());
        },     
        message: '输入值不能大于{2}的值.' 
    },
    
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '邮政编码不正确'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
            return /^0?(13[0-9]|15[012356789]|18[0123456789]|14[57]|17[0135678])[0-9]{8}$/.test(value);
        },
        message: '手机号码不正确'
    },
    mp:{
        validator: function (value, param) {
            return /^\d{7,11}$/.test(value);
        },
        message: '电话号码需要是7-11位数字'
    	
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    safepass: {
        validator: function (value, param) {
        	
        	var pa=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$/;
            return pa.test(value);
        },
        message: '密码必须包含字母、数字,6-30字符!'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    qq:{
        validator: function (value, param) {
        	
            return /^\d{5,10}$/.test(value);
        },
        message: '请输入正确的QQ号码'
    },
    fax:{
        validator: function (value, param) {
        	
            return /^(\d{3,4}-)?\d{7,8}$/.test(value);
        },
        message: '请输入正确的传真号码'
    },
    bankNum:{
        validator: function (value, param) {
        	
            return /^([1-9]{1})(\d{14}|\d{15}|\d{16}|\d{18})$/.test(value);
        },
        message: '请输入正确的银行卡号'
    },   
    
    idcard: {
        validator: function (value, param) {
        	
        	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        	return reg.test(value);
        },
        message: '请输入正确的身份证号码'
    },
    specialchar: {
        validator: function (value, param) {
            return specialCharacters(value);
        },
        message: '不能含有 ,\'\"\{\}\[\] 特殊字符'
    },
    unnormal : {
    	// 验证是否包含空格和非法字符 
        validator : function(value) {
        	if(value==""){
        		return true;
        	}
        	var s = $.trim(value).replace(/[ ]/g,"");
        	if(s==""){
        		return false;
        	}
        	return true;
        }, 
        message : '输入值不能全为空格' 
    },
    plateNumber:{
    	
        validator : function(value) {
        	
        	if(value==""){
        		
        		return true;
        	}
        	return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
        },
        message:'车牌号格式错误'
    },
    settlementPlateNumber:{
    	
        validator : function(value) {
        	
        	if(value=="" || /^[\u4E00-\u9FA5][A-Z]?$/.test(value)){        		
        		return true;
        	}
        	return /^[\u4E00-\u9FA5][A-Z0-9]{6}$/.test(value);
        },
        message:'车牌号格式错误'
    }
});

/***************************************************************
 * 
 * Numberbox 扩展获取float数值
 * 
 * */
$.extend($.fn.numberbox.methods, {

	getFloatValue: function (jq) {

		var options = $(jq).numberbox("options");
		var value = $(jq).numberbox("getValue");
		if(value == ""){
			return options.min;
		}
		var percision  = options.precision;
		value = parseFloat(value);
		if(percision>0){
			value = parseFloat(value.toFixed(percision));
		}
		
		if(options.prefix == '-'){
			value = 0 - value;
		}
		return value;
    },
    setFloatValue:function(jq,value){

    	var options = $(jq).numberbox("options");
    	if(typeof value == "string"){
    		value = parseFloat(value);
    	}

    	var percision  = options.precision;
    	if(percision >0){
    		value = parseFloat(value.toFixed(percision));
    	}
    	
    	if(value < 0){
    		options.prefix = '-';
    		value = 0 - value;
    	}
    	
    	options.value = value;
    	$(jq).numberbox(options);
    }
});

/***************************************************************
 * 
 * 表格扩展根据索引返回行数据
 * 
 * */
$.extend($.fn.datagrid.methods, {
	
    getRowData: function (jq, rowIndex) {
        var data = null;
        $.each($(jq).datagrid('getRows'), function (index, rowData) {

            if (index == rowIndex) {
                data = rowData;
            }
        });
        return data;
    }
});
 
/***************************************************************
 * 
 * 添加清除按钮
 * 
 * */
(function($){  
    
    //初始化清除按钮  
	//未完成功能：鼠标移动控件上才显示，有值时才显示。
    function initClearBtn(target){  
    	
        var jq = $(target);  
        var opts = jq.data('combo').options;
        if(opts.disabled === true){
        	
        	return;
        }

//    	if(opts.isClear==false || opts.isClear == undefined){
//    		return;
//    	} 

        var combobox = jq.data('combo').combo;
        combobox.mouseover(function(){
        	
        	$(this).find('span.combo-clear').show();
        });
        
        combobox.mouseout(function(){
        	
        	$(this).find('span.combo-clear').hide();
        });
        var arrow = combobox.find('a.combo-arrow');
        var clear = arrow.siblings("span.combo-clear");  
        if(clear.size()==0){
        	
            //创建清除按钮。  
            clear = $('<span class="combo-clear fa fa-close"></span>');
            //清除按钮添加悬停效果。  
            clear.unbind("mouseenter.combobox mouseleave.combobox").bind("mouseenter.combobox mouseleave.combobox",  
                function(event){  
                    var isEnter = event.type=="mouseenter";  
                    clear[isEnter ? 'addClass' : 'removeClass']("combo-clear-hover");  
                }  
            );  
            //清除按钮添加点击事件，清除当前选中值及隐藏选择面板。  
            clear.unbind("click.combo").bind("click.combo",function(){
            	
            	var disabled = jq.combo("options").disabled;
            	if(disabled==true){
            		return;
            	}
                jq.combo("clear");  
                jq.combo('hidePanel');  
            });  
            arrow.before(clear);  
        };  
//        var input = combobox.find("input.textbox-text");  
//        input.outerWidth(input.outerWidth()-clear.outerWidth());
        opts.initClear = true;//已进行清除按钮初始化。  
        $('span.combo-clear').hide();
    }  
      
    //扩展easyui combo添加清除当前值。  
    var oldResize = $.fn.combobox.methods.resize;
    $.extend($.fn.combobox.methods,{  
    	initClearBtn:function(jq){

            return jq.each(function(){  
            	
            	initClearBtn(this);  
            }); 
        },  
        resize:function(jq){  
        	
        	if(!oldResize){
        		return;
        	}
            //调用默认combo resize方法。  
            var returnValue = oldResize.apply(this,arguments);  
            var opts = jq.data("combo").options;  
            if(opts.initClear){  
                jq.combobox("initClear",jq);  
            }  
            return returnValue;  
        }  
    });
    
}(jQuery));

(function($){  
    
    //初始化清除按钮  
	//未完成功能：鼠标移动控件上才显示，有值时才显示。
    function initClearBtn(target){  
    	
        var jq = $(target);  
        var opts = jq.data('filebox').options;
        var filebox = jq.data('filebox').filebox;
        filebox.mouseover(function(){
        	
        	$(this).find('span.combo-clear').show();
        });
        
        filebox.mouseout(function(){

        	$(this).find('span.combo-clear').hide();
        });
        var arrow = filebox.find('a.textbox-button');
        var clear = arrow.siblings("span.combo-clear");  
        if(clear.size()==0){
        	
            //创建清除按钮。  
            clear = $('<span class="combo-clear fa fa-close" style="position: absolute;right:30px;top: 0;"></span>');
            //清除按钮添加悬停效果。  
            clear.unbind("mouseenter.filebox mouseleave.filebox").bind("mouseenter.filebox mouseleave.filebox",  
                function(event){  
                    var isEnter = event.type=="mouseenter";  
                    clear[isEnter ? 'addClass' : 'removeClass']("combo-clear-hover");  
                }  
            );  
            //清除按钮添加点击事件，清除当前选中值及隐藏选择面板。  
            clear.unbind("click.filebox").bind("click.filebox",function(){
            	
            	var disabled = jq.filebox("options").disabled;
            	if(disabled==true){
            		return;
            	}
                jq.filebox("clear");
            });  
            arrow.before(clear);  
        };  
        var input = filebox.find("input.textbox-text");  
        input.outerWidth(input.outerWidth()-clear.outerWidth());
        opts.initClear = true;//已进行清除按钮初始化。  
        $('span.combo-clear').hide();
    }  
      
    //扩展easyui combo添加清除当前值。  
    var oldResize = $.fn.filebox.methods.resize;
    $.extend($.fn.filebox.methods,{  
    	initClearBtn:function(jq){

            return jq.each(function(){  
            	initClearBtn(this);  
            }); 
        },  
        resize:function(jq){  
        	
        	if(!oldResize){
        		return;
        	}
            //调用默认combo resize方法。  
            var returnValue = oldResize.apply(this,arguments);  
            var opts = jq.data("filebox").options;  
            if(opts.initClear){  
                jq.filebox("initClear",jq);  
            }  
            return returnValue;  
        }  
    });
    
}(jQuery));

//$(function(){
//	
//    $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
//    $('.easyui-filebox').filebox("initClearBtn");
//});

/***************************************************************
 * 
 * 扩展Tabs右击菜单事件
 * 
 * */
$.extend($.fn.tabs.methods,{
    allTabs:function(jq){
        var tabs = $(jq).tabs('tabs');
        var all = [];
        all = $.map(tabs,function(n,i){
             return $(n).panel('options')
        });
        return all;
    },
    closeCurrent: function(jq){ // 关闭当前
        var currentTab = $(jq).tabs('getSelected');
        var currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        $(jq).tabs('close',currentTabIndex);
    },
    closeAll:function(jq){ //关闭全部
        var tabs = $(jq).tabs('allTabs');
        $.each(tabs,function(i,n){
        	
        	if(n.closable == true){

                $(jq).tabs('close', n.title);
        	}
        })
    },
    closeOther:function(jq){ //关闭除当前标签页外的tab页
        var tabs =$(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
 
        $.each(tabs,function(i,n){
            if(currentTabIndex != i && n.closable == true) {
                $(jq).tabs('close', n.title);
            }
        })
    },
    closeLeft:function(jq){ // 关闭当前页左侧tab页
        var tabs = $(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        var i = currentTabIndex-1;
 
        while(i > -1){
        	
        	if(tabs[i].closable == true){

                $(jq).tabs('close', tabs[i].title);
        	}
            i--;
        }
    },
    closeRight: function(jq){ //// 关闭当前页右侧tab页
        var tabs = $(jq).tabs('allTabs');
        var currentTab = $(jq).tabs('getSelected'),
            currentTabIndex = $(jq).tabs('getTabIndex',currentTab);
        var i = currentTabIndex+ 1,len = tabs.length;
        while(i < len){
        	
        	if(tabs[i].closable == true){

                $(jq).tabs('close', tabs[i].title);
        	}
            i++;
        }
    }
});

/***************************************************************
 * 
 * 扩展弹出参照
 * 
 * */
(function($) {

    function setSize(target, width) {
        var opts = $.data(target, "combo").options;
        var combo = $.data(target, "combo").combo;
        if (width) {
            opts.width = width;
        }
        combo.appendTo("body");
        if (isNaN(opts.width)) {
            opts.width = combo.find("input.combo-text").outerWidth();
        }
        var arrowWidth = 0;
        if (opts.hasDownArrow) {
            arrowWidth = combo.find(".reference-magnifier").outerWidth();
        }
        combo.find("input.combo-text").width(0);
        combo._outerWidth(opts.width);
        combo.find("input.combo-text").width(combo.width() - arrowWidth);
        combo.insertAfter(target);
    };

    function init(target) {
        $(target).addClass("combo-f").hide();
        var span = $("<span class=\"combo textbox\"></span>").insertAfter(target);
        var input = $("<input type=\"text\" class=\"textbox-text combo-text validatebox-invalid\">")
            .css("padding","7px 2px")
            .appendTo(span);
        $("<span class=\"textbox-addon textbox-addon-right\" style=\"right: 0;\"><span class=\"reference-magnifier fa fa-search\" ></span></span>").appendTo(span);
        $("<input type=\"hidden\" class=\"combo-value\">").appendTo(span);
        var window = $("<div style='top: 100px;'></div>").appendTo("body");
        var name = $(target).attr("name");
        if (name) {
            span.find("input.combo-value").attr("name", name);
            $(target).removeAttr("name").attr("comboName", name);
        }
        input.attr("autocomplete", "off");

        return {
            combo : span,
            window : window
        };
    };

    function destroy(target) {
        var input = $.data(target, "combo").combo.find("input.combo-text");
        input.validatebox("destroy");
        $.data(target, "combo").window.window("destroy");
        $.data(target, "combo").combo.remove();
        $(target).remove();
    };

    function bindEvents(target) {
        var data = $.data(target, "combo");
        var opts = data.options;
        var combo = $.data(target, "combo").combo;
        var input = combo.find(".combo-text");
        var magnifier = combo.find(".reference-magnifier");
        combo.unbind(".combo");
        input.unbind(".combo");
        magnifier.unbind(".combo");
        if (!opts.disabled) {
            input.bind("mousedown.combo", function(e) {
                e.stopPropagation();
            }).bind("change.combo", function() {

                var val = $(input).val();
                if(val == ""){

                    clear(target);
                }
            });

            magnifier.bind("click.combo", function() {

                showWindow(target);
            }).bind("mouseenter.combo", function() {
                $(this).addClass("combo-arrow-hover");
            }).bind("mouseleave.combo", function() {
                $(this).removeClass("combo-arrow-hover");
            }).bind("mousedown.combo", function() {
                return false;//㈡
            });

            //绑定文本框的改事件
            input.unbind().bind("change",function(){
                var newText = $(this).val();
                opts.onTextChange.call(target,newText, newText);
            });
        }
    };

    function validate(target, doit) {
        var opts = $.data(target, "combo").options;
        var input = $.data(target, "combo").combo.find("input.combo-text");
        input.validatebox(opts);
        if (doit) {
            input.validatebox("validate");
        }
    };

    function setDisabled(target, disabled) {
        var ops = $.data(target, "combo").options;
        var combo = $.data(target, "combo").combo;
        if (disabled) {
            ops.disabled = true;
            $(target).attr("disabled", true);
            combo.find(".combo-value").attr("disabled", true);
            combo.find(".combo-text").attr("disabled", true);
        } else {
            ops.disabled = false;
            $(target).removeAttr("disabled");
            combo.find(".combo-value").removeAttr("disabled");
            combo.find(".combo-text").removeAttr("disabled");
        }
    };

    function clear(target) {
        var ops = $.data(target, "combo").options;
        var combo = $.data(target, "combo").combo;
        if (ops.multiple) {
            combo.find("input.combo-value").remove();
        } else {
            combo.find("input.combo-value").val("");
        }
        combo.find("input.combo-text").val("");
    };

    function getText(target) {
        var combo = $.data(target, "combo").combo;
        return combo.find("input.combo-text").val();
    };

    function setText(target, text) {
        var opts = $.data(target, "combo").options;
        var combo = $.data(target, "combo").combo;
        var oldText =  combo.find("input.combo-text").val();
        combo.find("input.combo-text").val(text);
        validate(target, true);
        $.data(target, "combo").previousValue = text;
        if(oldText != text){
            opts.onTextChange.call(target,text, oldText);
        }
    };

    function getValues(target) {
        var values = [];
        var combo = $.data(target, "combo").combo;
        combo.find("input.combo-value").each(function() {
            values.push($(this).val());
        });
        return values;
    };

    function setValues(target, values) {
        var opts = $.data(target, "combo").options;
        var oldValues = getValues(target);
        var combo = $.data(target, "combo").combo;
        combo.find("input.combo-value").remove();
        var comboName = $(target).attr("comboName");
        for (var i = 0; i < values.length; i++) {
            var comboValue = $("<input type=\"hidden\" class=\"combo-value\">")
                .appendTo(combo);
            if (comboName) {
                comboValue.attr("name", comboName);
            }
            comboValue.val(values[i]);
        }
        var tmp = [];
        for (var i = 0; i < oldValues.length; i++) {
            tmp[i] = oldValues[i];
        }
        var aa = [];
        for (var i = 0; i < values.length; i++) {
            for (var j = 0; j < tmp.length; j++) {
                if (values[i] == tmp[j]) {
                    aa.push(values[i]);
                    tmp.splice(j, 1);
                    break;
                }
            }
        }
        if (aa.length != values.length || values.length != oldValues.length) {
            if (opts.multiple) {
                opts.onChange.call(target, values, oldValues);
            } else {
                opts.onChange.call(target, values[0], oldValues[0]);
            }
        }
    };

    function getValue(target) {
        var values = getValues(target);
        return values[0];
    };

    function setValue(target, value) {
        setValues(target, [value]);
    };

    function setObject(target,obj){
        var ops = $.data(target, "combo").options;
        var value = eval("obj." + ops.valueField);
        var text = eval("obj." + ops.textField);
        setValue(target,value);
        setText(target,text);
    };
    function initValue(target) {
        var opts = $.data(target, "combo").options;
        var fn = opts.onChange;
        opts.onChange = function() {
        };
        if (opts.multiple) {
            if (opts.value) {
                if (typeof opts.value == "object") {
                    setValues(target, opts.value);
                } else {
                    setValue(target, opts.value);
                }
            } else {
                setValues(target, []);
            }
        } else {
            setValue(target, opts.value);
        }
        opts.onChange = fn;
    };

    function showWindow(target) {

        var opts = $.data(target, "combo").options;
        var window = $.data(target, "combo").window;
        if ($.fn.window) {
            //control

            var url = opts.url + "?controlId=" + $(target).attr("id");
            $(window).dialog({
                title: opts.windowTitle,
                width: opts.windowWidth,
                height: opts.windowHeight,
                content: '<iframe id="sonFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:99.6%;overflow-y: auto; "></iframe>',
                modal: true,
                maximized:false,
                minimizable: false,
                maximizable: false,
                shadow: true,
                cache: false,
                closed: false,
                collapsible: false,
                resizable: false,
                inline: true,
                loadingMessage: '正在加载，请稍等......'
            });

        }

    };

    $.fn.referencebox = function(options, param) {
        if (typeof options == "string") {
            return $.fn.referencebox.methods[options](this, param);
        }
        options = options || {};

        return this.each(function() {
            var state = $.data(this, "combo");
            if (state) {
                $.extend(state.options, options);
            } else {
                var r = init(this);
                state = $.data(this, "combo", {
                    options : $.extend({}, $.fn.referencebox.defaults,
                        $.fn.referencebox.parseOptions(this), options),
                    combo : r.combo,
                    window : r.window,
                    previousValue : null
                });
                $(this).removeAttr("disabled");
            }
            $("input.combo-text", state.combo).attr("readonly",
                !state.options.editable);

            setDisabled(this, state.options.disabled);
            setSize(this);
            bindEvents(this);
            validate(this);
            initValue(this);
        });
    };

    $.fn.referencebox.methods = {
        options : function(jq) {
            return $.data(jq[0], "combo").options;
        },
        textbox : function(jq) {
            return $.data(jq[0], "combo").combo.find("input.combo-text");
        },
        destroy : function(jq) {
            return jq.each(function() {
                destroy(this);
            });
        },
        resize : function(jq, width) {
            return jq.each(function() {
                setSize(this, width);
            });
        },
        disable : function(jq) {
            return jq.each(function() {
                setDisabled(this, true);
                bindEvents(this);
            });
        },
        enable : function(jq) {
            return jq.each(function() {
                setDisabled(this, false);
                bindEvents(this);
            });
        },
        validate : function(jq) {
            return jq.each(function() {
                validate(this, true);
            });
        },
        isValid : function(jq) {
            var input = $.data(jq[0], "combo").referencebox.find("input.combo-text");
            return input.validatebox("isValid");
        },
        clear : function(jq) {
            return jq.each(function() {
                clear(this);
            });
        },
        getText : function(jq) {
            return getText(jq[0]);
        },
        setText : function(jq, text) {
            return jq.each(function() {
                setText(this, text);
            });
        },
        getValue : function(jq) {
            return getValue(jq[0]);
        },
        setValue : function(jq, value) {
            return jq.each(function() {
                setValue(this, value);
            });
        },
        setObject : function(jq, obj) {
            return jq.each(function() {
                setObject(this, obj);
            });
        }
    };

    $.fn.referencebox.parseOptions = function(target) {
        var t = $(target);
        return $.extend({}, $.fn.validatebox.parseOptions(target), $.parser
            .parseOptions(target, ["valueField", "textField", "width", "separator", {
                editable : "boolean"
            }]), {
            multiple : (t.attr("multiple") ? true : undefined),
            disabled : (t.attr("disabled") ? true : undefined),
            value : (t.val() || undefined)
        });
    };

    $.fn.referencebox.defaults = $.extend({}, $.fn.validatebox.defaults, {
        width : "auto",
        valueField : "id",
        textField : "name",
        url:"",
        windowWidth : 1000,
        windowHeight : 600,
        windowTitle:'选择',
        editable : true,
        disabled : false,
        value : "",
        delay : 200,
        onChange : function(_5d, _5e) {
        },
        onTextChange:function(newText,oldText){

        }
    });
})(jQuery);
$.parser.plugins.push("referencebox");

/****
 * 覆盖表单的验证方法
 * 主要是解决新增的两个控件验证BUG,暂时没有好的办法，先这样解决
 */

$.extend($.fn.form.methods, {
	
	validate:function(target){
		
		if ($.fn.validatebox){
			var t = $(target);
			
			var ctrlItems = t.find('.validatebox-text:not(:disabled)');
			$(ctrlItems).each(function(i,item){
				
				if($(item).hasClass('checkboxgroup-f')){
					
					$(item).checkboxgroup('validate');
					
				}else if($(item).hasClass('radiogroupbox-f')){
					
					$(item).radiogroupbox('validate');
				}else{

					$(item).validatebox('validate');
				}
			});
			
			//checkboxgroup
			var invalidbox = t.find('.validatebox-invalid');
			invalidbox.filter(':not(:disabled):first').focus();
			return invalidbox.length == 0;
		}
		return true;
	}
})
