
org.netsharp.panda.Workbench = System.Object.Extends({
	ctor : function() {
		
		this.isTabs = $('body').attr('isTabs')=='true';
	},
	invokeService : function(service,month, pars, callback) {
		if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(service, month, pars, callback);
	},
	init:function(){
		
		//创建右击菜单
		var builder = new System.StringBuilder();{
			
			builder.append('<div id="rightPanel" class="easyui-menu">');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeCurrent\');">关闭</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeAll\');">关闭全部</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeOther\');">关闭其他</div>');
			builder.append('<div class="menu-sep"></div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeRight\');">关闭右侧</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeLeft\');">关闭左侧</div>');
			builder.append('</div>');
		}
		var menuStr = builder.toString();
		$(menuStr).appendTo("body").menu();
		
		//渲染菜单树
	    $('ul[isMenu=true]').each(function(index, item) {
	        var json = $(item).attr('data');
	        var data = eval(json);
	        if (data != undefined && data != null) {
	            $(item).tree('loadData', data);
	        }
	    });
	    
	    //打开首页
	    this.openHomePage();
	    //默认打开第1个模块
	    var firstNavItem = $("#nav li").first();
	    if(firstNavItem){
		  this.selectNav(firstNavItem);
	    }
	    
	    this.initOkayNav();
	},
	initOkayNav:function(){
		var navigation = $('#nav-main').okayNav({
			beforeopen: function() {
				//alert('beforeopen');
			},
			open: function() {
				//alert('open');
			},
			beforeclose: function() {
				//alert('beforeclose');
			},
			close: function() {
				//alert('close');
			}
		});
	},
	openHomePage:function(){

	    this.openWorkspace("首页","/nav/panda-bizbase/home",'fa fa-home',false);
	},
    openWorkspace: function(subtitle, url, icon, closable, id,openMode,width,height) {

        if (url == undefined || url == null || url == "") {

            return;
        }

        //弹出窗口
		if(openMode == OpenType.window){
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open(subtitle, url, width, height);
			return;
		}
		
		//打开浏览器窗口
		if(openMode == OpenType.open){
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
			return;
		}
		
		url = System.Url.join(url, "openType="+OpenType.redirect);
        var iframe = this.createFrame(url, id);
        if (this.isTabs) {

            if (!$('#tabs').tabs('exists', subtitle)) {

                $('#tabs').tabs('add', {
                    title: subtitle,
                    content: iframe,
                    closable: closable,
                    selected: true,
                    //iconCls: icon
                });

            } else {

                $('#tabs').tabs('select', subtitle);
                var tab = $('#tabs').tabs('getTab', subtitle);
                $("#tabs").tabs('update', {
                    tab: tab,
                    options: {
                        content: this.createFrame(url, id)
                    }
                });
            }

            if (closable) {
                this.closeWorkspace();
            }

        } else {
        	
            $("#center1").html(iframe);
            $("#center1").panel({
                title: subtitle,
                tools: [{    
                    iconCls:'fa fa-clone',    
                    handler:function(){
                    	window.open(url);
                    }    
                }]
            });
        }
    },
    closeSelectedTab:function(){
    	
    	var currentTab = $('#tabs').tabs('getSelected');
    	var currentTabIndex = $('#tabs').tabs('getTabIndex',currentTab);
    	$('#tabs').tabs('close',currentTabIndex);
    },
    createFrame: function(url, id) {
    	var h = $("body").height()-76;
        var s = '<iframe id="' + id + '" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:'+h+'px;"></iframe>';
        return s;
    },

    closeWorkspace: function() {

        /* 双击关闭TAB选项卡 */
        $(".tabs-inner").dblclick(function() {
        	
            var subtitle = $(this).children(".tabs-closable").text();
            if(subtitle){
                $('#tabs').tabs('close', subtitle);
            }
        });

        /* 为选项卡绑定右键 */
        $(".tabs-inner").bind('contextmenu',function(e) {
        	
        	var isClose = $(this).next(".tabs-close").length > 0
        	if(!isClose){
        		return;
        	}
        	
            $('#rightPanel').menu('show', {
                left: e.pageX,
                top: e.pageY
            });

            var subtitle = $(this).children(".tabs-closable").text();
            $('#rightPanel').data("currtab", subtitle);
            $('#tabs').tabs('select', subtitle);
            return false;
        });
    },
    exit: function() {

    	IMessageBox.confirm('您确定要退出本次登录吗?',function(r){
    		
            if (r) {

                var serviceLocator = new org.netsharp.core.JServiceLocator();
                var service = "org.netsharp.organization.controller.LoginController";
                serviceLocator.invoke(service, "logout", null,function(jMessage) {
                    if (jMessage == "1") {
                        window.location.href = "/nav/panda-bizbase/authorization/login";
                    } else {
                        IMessageBox.error(jMessage.Exception.Message);
                    }
                });
            }
    	});
    },
    changePassword: function() {

        var url = "/panda/system/modify/password/form";
        url = System.Url.getUrl(url);
        
    	layer.open({
		  type: 2,
		  title: '修改密码',
		  fixed: false,
		  maxmin: false,
		  shadeClose:true,
		  area: ['430px', '295px'],
		  btn: ['确定', '取消'],
		  content: url,
		  yes:function(index, layero){
			  
			  var iframeId = 'layui-layer-iframe'+index;
			  document.getElementById(iframeId).contentWindow.controlleremployee.save();
		  }
		});
    },

    selectNav:function(item){

    	var $item = $(item);
    	var is_selected = $item.hasClass("selected");
    	if(is_selected){
    		return;
    	}
    	
    	var west_panel = $(".easyui-layout").layout("panel","west");
    	var options = $(west_panel).panel("options");
//    	options.title = $item.text();
    	$(west_panel).panel(options);
    	
    	var me = this;
    	$item.parent().children().removeClass("selected");
    	$item.addClass("selected").parent();
    	var id = $item.attr("id");
    	var accordionId = "accordion_" + id;
    	var $accordionId = "#" + accordionId;
    	var $accordion = $($accordionId);
    	$(".accordion").hide();
    	if($accordion.length==0){
    		
    		$("#west").append("<div id='"+accordionId+"'></div>");
    		$($accordionId).accordion({
    			fit:true,
    			border:false
    		});
    		
        	var data = $item.attr("data");
        	var items = eval(data);
        	$(items).each(function(index,item){
        		
        		var selected = index==0?true:false;
        		var content = me.getNavItemsHtml(item.children);
        		var iconCls = item.iconCls;
        		$($accordionId).accordion('add', {
        			title: item.text,
        			iconCls:item.iconCls,
        			content: content,
        			selected:selected
        		});

        	});
        	

    	}else{
    		
    		$accordion.show();
    	}
    },
    
    getNavItemsHtml:function(items){

    	var builder = new System.StringBuilder();
    	builder.appendLine('<ul class="accordion_item_nav">');
    	$(items).each(function(index,item){

        	var icon = item.iconCls || 'fa fa-file-text-o';
        	var liStr = '<li><a href="javascript:void(0);" onclick=\'workbench.accordionNavClick(this,"{0}","{1}","{2}",true,"{3}","{4}",{5},{6});\'><span><i class="'+icon+'"></i>{0}</span></a></li>';
    		var li = liStr.format(
					item.text,
    				item.attributes.url,
    				icon,
					true,
					item.id,
					item.attributes.openMode,
					item.attributes.windowWidth,
					item.attributes.windowHeight);
    		builder.appendLine(li);
    	});
    	builder.appendLine('</ul>');
    	return builder.toString();
    },
    accordionNavClick:function(obj,text, url, icon, closable, id,openMode,width,height){
    	
    	$(obj).parent().addClass('selected').siblings().removeClass('selected');
    	workbench.openWorkspace(text,url,icon,true,id,openMode,width,height);
    },
    
    switchWorkbench:function(result){
    	
    	var workbenchList = result.workbenchList;
    	var content = '';
	    $(workbenchList).each(function(index,item){
	    	
	    	content += '<div class="workbench-item"><a href="'+item.path+'">'+(index+1)+'. '+item.name+'</a></div>';
	    });
	    
	    layer.open({
	        type: 1,
	        closeBtn:0,
	        shadeClose : true,
	        anim: 2,
	        title:'切换工作台',
	        area: ['350px', '150px'],
	        content: content
	      });
    	
    },
    feedback:function(){
    	
    	layer.open({
    		  type: 2,
    		  title: '意见反馈',
    		  id:"feedback",
    		  fixed: false,
    		  maxmin: false,
    		  shadeClose:true,
    		  area: ['600px','450px'],
    		  content: '/nav/panda-platform/feedback/feedback',
    		  btn : [ '提交', '取消' ],
    		  btn1 : function(index, layero) {
    			  
    			  $(layero).find("iframe")[0].contentWindow.ctrl.save();
    		  }
    	 });
    }
});


/*
 * @Name: jquery.okayNav.js - A progressively responsive navigation
 * @Version: 1.0.1
 *
 * @Copyright (c) 2016 Vergil Penkov
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Project home: https://github.com/VPenkov/okayNav
*/

;(function ( $, window, document, undefined ) {

    // Defaults
    var okayNav = 'okayNav',
        defaults = {
            parent : '', // will call nav's parent() by default
            toggle_icon_class : 'okayNav__menu-toggle',
            toggle_icon_content: '<span /><span /><span />',
            beforeopen : function() {}, // Will trigger before the nav gets opened
            open : function() {}, // Will trigger after the nav gets opened
            beforeclose : function() {}, // Will trigger before the nav gets closed
            close : function() {}, // Will trigger after the nav gets closed
        };

    // Begin
    function Plugin( element, options ) {
        this.options = $.extend( {}, defaults, options) ;
        _okayNav = this; // Plugin

        _invisibleNavState = false; // Is the hidden menu open?
        _options = this.options;

        $document = $(document); // for event triggering
        $body = $('body'); // for controlling the overflow
        $navigation = $(element); // jQuery object

        this.options.parent == '' ? this.options.parent = $navigation.parent() : '';

        // At this point, we have access to the jQuery element and the options
        // via the instance, e.g., $navigation and _options. We can access these
        // anywhere in the plugin.
        _okayNav.init();
    }

    Plugin.prototype = {
        init: function () {
            // Some DOM manipulations
            _okayNav.setupElements($navigation);

            // Cache new elements for further use
            $nav_visible = $navigation.children('.okayNav__nav--visible');
            $nav_invisible = $navigation.children('.okayNav__nav--invisible');
            $nav_toggle_icon = $navigation.children('.' + _options.toggle_icon_class);
            _nav_toggle_icon_width = $nav_toggle_icon.outerWidth(true);
            _last_visible_child_width = 0; // We'll define this later

            // Events are up once everything is set
            _okayNav.initEvents();
        },

        /*
         * Let's setup the elements and attach events
         */
        // Elements
        setupElements: function(el) {
            $body.addClass('okayNav-loaded');

            // Add classes
            $navigation
                .addClass('okayNav loaded')
                .children('ul').addClass('okayNav__nav--visible');

            // Append elements
            $navigation
                .append('<ul class="okayNav__nav--invisible" />')
                .append('<a href="#" class="' + _options.toggle_icon_class + '">' + _options.toggle_icon_content + '</a>')
        },

        // Events
        initEvents: function() {
            // Toggle hidden nav when hamburger icon is clicked
            $document.on('click.okayNav', '.' + _options.toggle_icon_class, function(event) {
                event.preventDefault();
                _okayNav.toggleInvisibleNav();
            });

            // Collapse hidden nav on click outside the header
            $document.on('click.okayNav', function(event) {
                if (_invisibleNavState === true) {
                    var _target = $(event.target);
                    if (!_target.parents().hasClass('okayNav'))
                        _okayNav.closeInvisibleNav();
                }
            });

            $(window).on('load.okayNav resize.okayNav', function(event) {
                _okayNav.recalcNav();
            });
        },

        /*
         * A few methods to allow working with elements
         */
        getParent: function () {
            return _options.parent;
        },

        getVisibleNav: function() { // Visible navigation
            return $nav_visible;
        },

        getInvisibleNav: function() { // Hidden behind the kebab icon
            return $nav_invisible;
        },

        getNavToggleIcon: function() { // Kebab icon
            return $nav_toggle_icon;
        },

        /*
         * Operations
         */
        openInvisibleNav: function() {
            _options.beforeopen.call();
            $nav_toggle_icon.addClass('icon--active');
            $nav_invisible.addClass('nav-open');
            _invisibleNavState = true;
            _options.open.call();
            $document.trigger('okayNav:open');
        },

        closeInvisibleNav: function() {
            _options.beforeclose.call();
            $nav_toggle_icon.removeClass('icon--active');
            $nav_invisible.removeClass('nav-open');
            _invisibleNavState = false;
            _options.close.call();
            $document.trigger('okayNav:close');
        },

        toggleInvisibleNav: function() {
            if (!_invisibleNavState) {
                _invisibleNavState = true;
                _okayNav.openInvisibleNav();
            }
            else {
                _invisibleNavState = false;
                _okayNav.closeInvisibleNav();
            }
        },


        /*
         * Math stuff
         */
        getParentWidth: function(el) {
            var parent = el || _options.parent;
            var parent_width = $(parent).outerWidth(true);

            return parent_width;
        },

        getChildrenWidth: function(el) {
            var children_width = 0;
            $(el).children().each(function() {
                children_width += $(this).outerWidth(true);
            });

            return children_width;
        },

        countNavItems: function(el) {
            var $menu = $(el);
            var items = $('li', $menu).length;

            return items;
        },

        recalcNav: function() {
            var wrapper_width = $(_options.parent).outerWidth(true);
            var nav_full_width = $navigation.outerWidth(true);
            var visible_nav_items = _okayNav.countNavItems($nav_visible);

            var collapse_width = $nav_visible.outerWidth(true) + _nav_toggle_icon_width - 1;
            var expand_width = _okayNav.getChildrenWidth(_options.parent) + _last_visible_child_width + _nav_toggle_icon_width;
            /* _okayNav.getChildrenWidth(_options.parent) gets the total
               width of the <nav> element and its siblings. */


            if (visible_nav_items > 0 && nav_full_width <= collapse_width)
                _okayNav.collapseNavItem();

            if (wrapper_width > expand_width)
                _okayNav.expandNavItem();


            // Hide the kebab icon if no items are hidden
            $('li', $nav_invisible).length == 0 ? $nav_toggle_icon.hide() : $nav_toggle_icon.show();
        },

        collapseNavItem: function() {
            var $last_child = $('li:last-child', $nav_visible);
            _last_visible_child_width = $last_child.outerWidth(true);
            $last_child.detach().prependTo($nav_invisible);

            // All nav items are visible by default
            // so we only need recursion when collapsing
            _okayNav.recalcNav();
            $document.trigger('okayNav:collapseItem');
        },


        expandNavItem: function() {
            $('li:first-child', $nav_invisible).detach().appendTo($nav_visible);
            $document.trigger('okayNav:expandItem');
        },

        destroy: function() {
            $('li', $nav_invisible).appendTo($nav_visible);
            $nav_invisible.remove();
            $nav_visible.removeClass('okayNav__nav--visible');
            $nav_toggle_icon.remove();

            $document.unbind('.okayNav');
            $(window).unbind('.okayNav');
        }

    }

    // Plugin wrapper
    $.fn[okayNav] = function ( options ) {
        var args = arguments;

        if (options === undefined || typeof options === 'object') {
            return this.each(function () {
                if (!$.data(this, 'plugin_' + okayNav)) {
                    $.data(this, 'plugin_' + okayNav, new Plugin( this, options ));
                }
            });

        } else if (typeof options === 'string' && options[0] !== '_' && options !== 'init') {

            var returns;
            this.each(function () {
                var instance = $.data(this, 'plugin_' + okayNav);
                if (instance instanceof Plugin && typeof instance[options] === 'function') {
                    returns = instance[options].apply( instance, Array.prototype.slice.call( args, 1 ) );
                }

                if (options === 'destroy') {
                  $.data(this, 'plugin_' + okayNav, null);
                }
            });

            return returns !== undefined ? returns : this;
        }
    };

}(jQuery, window, document));
