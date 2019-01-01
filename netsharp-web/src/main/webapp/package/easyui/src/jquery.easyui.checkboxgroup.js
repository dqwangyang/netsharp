
/***************************************************************
 * 
 * 扩展多选分组
 * 
 * */
(function($){


	/**
	 * 选中一项
	 */
	function select(target, value){

        selects(target, [value]);
    }

    /**
	 * 选中多项，不清空原来的
	 */
    function selects(target, values){
	
		var oldValues = getValues(target);
		var state = $.data(target,"checkboxgroup");
		var checkboxgroup = state.checkboxgroup;
		for (var i = 0; i < values.length; i++) {
			var value = values[i];
			checkboxgroup.find('.checkbox-value[value='+value+']').remove();
			$("<input type=\"hidden\" class=\"checkbox-value\">").val(value).appendTo(checkboxgroup);
			checkboxgroup.find('input[type=checkbox][value='+value+']').prop('checked',true);
		}
		var newValues = getValues(target);
		var opts = state.options;
		//触发改变事件
		opts.onChange.call(target, newValues, oldValues);
    }
    
    /**
	 * 取消选中一项，不清空原来的
	 */
    function unSelect(target, value){

		unSelects(target,[value]);
    }

    /**
	 * 取消选中多项，不清空原来的
	 */    
    function unSelects(target, values){

		var oldValues = getValues(target);
		var state = $.data(target,"checkboxgroup");
		var checkboxgroup = state.checkboxgroup;
		for (var i = 0; i < values.length; i++) {
			var value = values[i];
			checkboxgroup.find('.checkbox-value[value='+value+']').remove();
			checkboxgroup.find('input[type=checkbox][value='+value+']').prop('checked',false);
		}
		var opts = state.options;
		var newValues = getValues(target);
		opts.onChange.call(target, newValues, oldValues);
    } 

    /**
	 * 设置新值(不触发改变事件)
	 */
	function initValue(target, values){

        var checkboxgroup = $.data(target,"checkboxgroup").checkboxgroup;
        checkboxgroup.find(".checkbox-value").remove();
        checkboxgroup.find('input[type=checkbox]').prop('checked',false);
        values.forEach(function(value) {

            $("<input type=\"hidden\" class=\"checkbox-value\">").val(value).appendTo(checkboxgroup);
            checkboxgroup.find('input[type=checkbox][value='+value+']').prop('checked',true);
        }, this);
    }

    function getValues(target) {
        var values = [];
        var checkboxgroup = $.data(target,"checkboxgroup").checkboxgroup;
        checkboxgroup.find("input.checkbox-value").each(function() {
            values.push($(this).val());
        });
        return values;
    }

    function setValues(target, values) {
    
        var opts = $.data(target, "checkboxgroup").options;
        var oldValues = getValues(target);
        initValue(target, values);

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
            
            opts.onChange.call(target, values, oldValues);
        }
    }

    function clear(target){
    	
    	initValue(target,[]);
    }
    
	/**
	 * 设置新值
	 */
     function setValue(target, value) {
        setValues(target, [value]);
    };   
	
	/**
	 * 取值
	 * @param {*} target 
	 */
    function getValue(target){

        var values = getValues(target);
        return values[0];
	}
	
	/**
	 * 显示错误提示
	 * @param {*} target 
	 * @param {*} message 
	 * @param {*} type 
	 */
	function error(target,message,type){

		var state = $.data(target, "checkboxgroup");
		var checkboxgroup = state.checkboxgroup;
		if(type=='hide'){
			$(checkboxgroup).tooltip('hide');
		}if(type=='destroy'){
			$(checkboxgroup).tooltip('destroy');
		}else{
			
			$(checkboxgroup).tooltip({
				position: 'right',    
				content: message,
				onShow: function(){       
					
					$(this).tooltip('tip').css({
						backgroundColor: '#ffffcc',
						borderColor: '#cc9933',
					});
				}
			});
			$(checkboxgroup).tooltip('show');
		}
	}
	
	/**
	 * 验证
	 * @param {*} target 
	 * @param {*} doit 
	 */
    function validate(target, doit) {

		var state = $.data(target, "checkboxgroup");
		var opts = state.options;
		var values = getValues(target);
		var checkboxgroup = state.checkboxgroup;

		function setTipMessage(msg) {   
            $.data(target, "checkboxgroup").message = msg;   
        };   
		if(opts.required){

			if(values.length == 0){

				checkboxgroup.addClass('validatebox-invalid');
				error(target,'最少选择1项');
				return false;
			}else{
				checkboxgroup.removeClass('validatebox-invalid');
			}
		}
        if(opts.minLenth || opts.maxLength) {   

			if(opts.minLenth && opts.minLenth>values.length){

				checkboxgroup.addClass('validatebox-invalid');
				error(target,'最少选择'+opts.minLenth+'项');
				return false;
			}

			if(opts.maxLength && opts.maxLength<values.length){
				
				checkboxgroup.addClass('validatebox-invalid');
				error(target,'最多只能选择'+opts.maxLength+'项');
				return false;
			}
		}
		
		checkboxgroup.removeClass("validatebox-invalid");   
		error(target,'','destroy');   
		return true;
    }

    //加载本地数据
	function loadData(target, data){

		var state = $.data(target, 'checkboxgroup');
        state.data = data;
		var opts = state.options;
        var targetId = target.id;
        var checkboxgroup = state.checkboxgroup;
        checkboxgroup.find('.radio-item').remove();
        checkboxgroup.find(".radio-value").remove();
//      <div class="checkbox-custom checkbox-inline checkbox-primary pull-left">
//	    <input type="checkbox" id="remember" name="remember">
//	    <label for="remember">自动登录</label>
//	</div>   
        var i = 0;
        data.forEach(function(element) {
        	i++;
            var text = element[opts.textField];
            var value = element[opts.valueField];
            var itemId=targetId+'_'+value;
            var radioItem = $('<span class="check-item checkbox-custom checkbox-inline checkbox-primary pull-left">\
            		<input type="checkbox" id="'+itemId+'"\
             name="'+targetId+'" value="'+value+'">\
             <label for="'+itemId+'">'+text+'</label>\
             </span>').appendTo(checkboxgroup);
            
            if(opts.rowCount){

                if(i%opts.rowCount==0){
                	$('<br><br>').appendTo(checkboxgroup);
                }
            }
            
            if(opts.itemMinWidth){
            
            	radioItem.css({minWidth:opts.itemMinWidth});
            }

             var radio =  radioItem.find('input[type=checkbox]');
             if(opts.value == value){
               radio.prop('checked',true);
             }

             //绑定click事件
             radio.click(function(){

				var checked = $(this).prop('checked');
				if(checked===true){

					select(target,value);
					//opts.onSelect(element);
				}else{

					unSelect(target,value);	
				}
				validate(target);
             });

		}, this);
		
        var hidden = $("<input type=\"hidden\" class=\"checkbox-value\">").appendTo(checkboxgroup);
        hidden.val(opts.value);
	}

	/**
	 * 创建组件dom
	 */
	function create(target){

		var state = $.data(target, 'checkboxgroup');
		var opts = state.options;
		$(target).addClass('checkboxgroup-f validatebox-text').hide();
		var span = $("<span class=\"checkboxgroup\"></span>").insertAfter(target);
		state.checkboxgroup = span;
	}

	
	$.fn.checkboxgroup = function(options, param){

		if (typeof options == 'string'){

			var method = $.fn.checkboxgroup.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.validatebox(options, param);
			}
		}

		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'checkboxgroup');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'checkboxgroup', {
					options: $.extend({}, $.fn.checkboxgroup.defaults, $.fn.checkboxgroup.parseOptions(this), options),
					checkboxgroup: $(this).addClass('checkboxgroup'),
					data: []
				});
			}

			//初始化控件
			create(this);

			//加载数据
			if (state.options.data){
				loadData(this, state.options.data);
			} else {
				var data = $.fn.checkboxgroup.parseData(this);
				if (data.length){
					loadData(this, data);
				}
			}
		});
	};
	
	
	$.fn.checkboxgroup.methods = {
		options: function(jq){
			return $.data(jq[0], 'checkboxgroup').options;
		},
		getData: function(jq){
			return $.data(jq[0], 'checkboxgroup').data;
		},
		setValue: function(jq, value){
			return jq.each(function(){
				setValue(this, value);
			});
        },
        getValue: function(jq){
			return getValue(jq[0]);
        },
        setValues: function(jq, value){
			return jq.each(function(){
				setValues(this, value);
			});
        },
        getValues: function(jq){
			return getValues(jq[0]);
		},
		clear: function(jq){
			return jq.each(function(){
				clear(this);
			});
		},
		loadData: function(jq, data){
			return jq.each(function(){
				loadData(this, data);
			});
		},
		select: function(jq, value){
			return jq.each(function(){
				select(this, value);
			});
        },
     
		selects: function(jq, values){
			return jq.each(function(){
				selects(this, values);
			});
        },
 		unSelect: function(jq, value){
			return jq.each(function(){
				unSelect(this, value);
			});
        },   
        unSelects: function(jq, values){
			return jq.each(function(){
				unSelects(this, values);
			});
        }, 
        initValue: function(jq, value){
			return jq.each(function(){
				initValue(this, value);
			});
		},
		validate:function(jq){
			return jq.each(function() {
                validate(this, true);
            });
		}
	};

	//转换数据
	$.fn.checkboxgroup.parseData = function(target){

		var data = [];
		var opts = $(target).checkboxgroup('options');
		$(target).children().each(function(){
			_parseItem(this);
		});
		return data;
		
		function _parseItem(el){
			var t = $(el);
			var row = {};
			row[opts.valueField] = t.attr('value')!=undefined ? t.attr('value') : t.text().trim();
			row[opts.textField] = t.text().trim();
			row['selected'] = t.is(':selected');
			row['disabled'] = t.is(':disabled');
			data.push(row);
		}
	};

	//转换配置
	$.fn.checkboxgroup.parseOptions = function(target){
		//return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['sharedCalendar']));
		var t = $(target);
		return $.extend({},
			$.fn.validatebox.parseOptions(target),
			$.parser.parseOptions(target, ['valueField','textField','disabled',{selected:'number'}]),
			{
				disabled : (t.attr("disabled") ? true : undefined)
			}
			);
	};

	//默认配置
	$.fn.checkboxgroup.defaults = $.extend({}, $.fn.validatebox.defaults, {
		width: 'auto',
		height: 'auto',
		valueField : "value",
        textField : "text",
		selected: 0,
		rowCount:null,
		itemMinWidth:null,
		disabled : false,
		required : false,
		minLenth:null,
		maxLength:null,
        data:null,
		value : "",
		onChange : function(newValue, oldValue) {},
		onSelect: function(record){}
	});
	
})(jQuery);
$.parser.plugins.push("checkboxgroup");

