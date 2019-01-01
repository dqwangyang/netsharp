
/***************************************************************
 * 
 * 扩展单选分组
 * 
 * */
(function($){


	/**
	 * 选中一项
	 */
	function select(target, value){

        setValue(target, value);
    }
    
    /**
	 * 设置新值(不触发改变事件)
	 */
	function initValue(target, value){

        var state = $.data(target,"radiogroupbox");
        state.radiogroupbox.find(".radio-value").val(value);
        if(value !=null && value !=undefined && value !==''){

            var radioItem = state.radiogroupbox.find('input[type=radio][value='+value+']');
            radioItem.prop('checked',true);
        }else{
        	state.radiogroupbox.find('input[type=radio]').prop('checked',false);
        }
        //选中
    }

	/**
	 * 设置新值
	 */
	function setValue(target, value){

        var opts = $.data(target, 'radiogroupbox').options;
		var oldValue = $(target).radiogroupbox('getValue');
        initValue(target, value);
        
        opts.onChange.call(target, value,oldValue);
    }
    
    //取值
    function getValue(target){

        var value = $.data(target,"radiogroupbox").radiogroupbox.find(".radio-value").val();
        return value;
    }
/**
	 * 显示错误提示
	 * @param {*} target 
	 * @param {*} message 
	 * @param {*} type 
	 */
	function error(target,message,type){
		
		var state = $.data(target, "radiogroupbox");
		var radiogroupbox = state.radiogroupbox;
		if(type=='hide'){
			$(radiogroupbox).tooltip('hide');
		}if(type=='destroy'){
			$(radiogroupbox).tooltip('destroy');
		}else{
			
			$(radiogroupbox).tooltip({
				position: 'right',    
				content: message,
				onShow: function(){       
					
					$(this).tooltip('tip').css({
						backgroundColor: '#ffffcc',
						borderColor: '#cc9933',
					});
				}
			});
			$(radiogroupbox).tooltip('show');
		}
	}
			
	/**
	 * 验证
	 * @param {*} target 
	 * @param {*} doit 
	 */
	function validate(target, doit) {

		var state = $.data(target, "radiogroupbox");
		var opts = state.options;
		var radiogroupbox = state.radiogroupbox;

		function setTipMessage(msg) {   
			$.data(target, "radiogroupbox").message = msg;   
		};   
		if(opts.required){

			var value = getValue(target);
			if(!value){

				radiogroupbox.addClass('validatebox-invalid');
				error(target,'最少选择1项');
				return false;
			}else{
				radiogroupbox.removeClass('validatebox-invalid');
			}
		}
		
		radiogroupbox.removeClass("validatebox-invalid");   
		error(target,'','destroy');   
		return true;
	}
		
    //加载本地数据
	function loadData(target, data){

		var state = $.data(target, 'radiogroupbox');
		var radiogroupbox = state.radiogroupbox;
		var opts = state.options;
        var targetId = target.id;
		radiogroupbox.find('.radio-item').remove();
		radiogroupbox.find(".radio-value").val('');
		var i = 0;
        state.data = data;
        data.forEach(function(element) {
//	        <div class="radio-custom radio-primary">
//	            <input type="radio" id="inputRadiosUnchecked" name="inputRadios">
//	            <label for="inputRadiosUnchecked">未选中</label>
//	        </div>         
            var text = element[opts.textField];
			var value = element[opts.valueField];
			var itemId=targetId+'_'+value;
            var radioItem = $('<span class="radio-item radio-custom radio-primary"><input type="radio" id="'+itemId+'"\
             name="'+targetId+'" value="'+value+'">\
             <label for="'+itemId+'">'+text+'</label>\
             </span>').appendTo(radiogroupbox);
            if(opts.rowCount){

                if(i%opts.rowCount==0){
                	$('<br><br>').appendTo(checkboxgroup);
                }
            }
            
            if(opts.itemMinWidth){
            
            	radioItem.css({minWidth:opts.itemMinWidth});
            }
             var radio =  radioItem.find('input[type=radio]');
             if(opts.value == element.id){
               radio.prop('checked',true);
             }

             //绑定click事件
             radio.click(function(){

 				var oldValue = $(target).radiogroupbox('getValue');
                var state = $.data(target,"radiogroupbox");
                state.radiogroupbox.find(".radio-value").val(value);
				opts.onClick(element);
				opts.onChange.call(target, value,oldValue);
				validate(target);
             });

        }, this);
	}
	
	/**
	 * 创建组件dom
	 */
	function create(target){

		var state = $.data(target, 'radiogroupbox');
		var opts = state.options;
		$(target).addClass('radiogroupbox-f validatebox-text').hide();
		var span = $("<span class=\"radiogroupbox\"></span>").insertAfter(target);
        var hidden = $("<input type=\"hidden\" class=\"radio-value\">").appendTo(span);
        state.radiogroupbox = span;
        hidden.val(opts.value);
	}

	
	$.fn.radiogroupbox = function(options, param){

		if (typeof options == 'string'){
			
			var method = $.fn.radiogroupbox.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.validatebox(options, param);
			}
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'radiogroupbox');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'radiogroupbox', {
					options: $.extend({}, $.fn.radiogroupbox.defaults, $.fn.radiogroupbox.parseOptions(this), options),
					radiogroupbox: $(this).addClass('radiogroupbox'),
					data: []
				});
			}

			//初始化控件
			create(this);

			//加载数据
			if (state.options.data){
				loadData(this, state.options.data);
			} else {
				var data = $.fn.radiogroupbox.parseData(this);
				if (data.length){
					loadData(this, data);
				}
			}
		});
	};
	
	
	$.fn.radiogroupbox.methods = {
		options: function(jq){
			return $.data(jq[0], 'radiogroupbox').options;
		},
		getData: function(jq){
			return $.data(jq[0], 'radiogroupbox').data;
		},
		setValue: function(jq, value){
			return jq.each(function(){
				setValue(this, value);
			});
        },
        getValue: function(jq){
			return getValue(jq[0]);
		},
		clear: function(jq){
			return jq.each(function(){
				setValue(this,'');
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
	$.fn.radiogroupbox.parseData = function(target){

		var data = [];
		var opts = $(target).radiogroupbox('options');
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
	$.fn.radiogroupbox.parseOptions = function(target){
		
		var t = $(target);
		return $.extend(
			$.fn.validatebox.parseOptions(target),
			$.parser.parseOptions(target, ['valueField','textField','disabled',{selected:'number'}]),
			{
				disabled : (t.attr("disabled") ? true : undefined)
			}
			);
	};

	//默认配置
	$.fn.radiogroupbox.defaults = $.extend({}, $.fn.validatebox.defaults, {
		width: 'auto',
		height: 'auto',
		valueField : "value",
        textField : "text",
		selected: 0,
		rowCount:null,
		itemMinWidth:null,
		disabled : false,
		required:false,
        data:null,
		value : "",
		onChange : function(newValue, oldValue) {},
		onSelect: function(newValue){},
		onClick: function(record){}
	});
})(jQuery);
$.parser.plugins.push("radiogroupbox");

