var System = {};

(function() {
	var _init = false;

	System.Object = function(prop) {
		var _base = this.prototype;
		_init = true;
		var proto = new this();
		_init = false;
		for ( var name in prop) {
			proto[name] = typeof prop[name] == "function"
					&& typeof _base[name] == "function" ? (function(name, fn) {
				return function() {
					var tmp = this.base;
					this.base = _base[name];
					var ret = fn.apply(this, arguments);
					this.base = tmp;
					return ret;
				};
			})(name, prop[name]) : prop[name];
		}

		function cls() {
			if (!_init && this.ctor)
				this.ctor.apply(this, arguments);
		}
		cls.prototype = proto;
		cls.constructor = cls;
		
		cls.Extends = arguments.callee;
		return cls;
	}.apply(Object, []);
})();

// System.List
(function() {
	var _init = false;

	System.List = function(prop) {
		var _base = this.prototype;
		_init = true;
		var proto = new this();

		_init = false;
		for ( var name in prop) {
			proto[name] = typeof prop[name] == "function"
					&& typeof _base[name] == "function" ? (function(name, fn) {
				return function() {
					var tmp = this.base;
					this.base = _base[name];
					var ret = fn.apply(this, arguments);
					this.base = tmp;
					return ret;
				};
			})(name, prop[name]) : prop[name];
		}

		function cls() {
			if (!_init && this.ctor)
				this.ctor.apply(this, arguments);
		}
		cls.prototype = proto;
		cls.constructor = cls;
		cls.Extends = arguments.callee;
		return cls;
	}.apply(Array, []);
})();

System.isnull = function(a) {
	if (typeof (a) == "undefined") {
		return true;
	} else if (a === "") {
		return true;
	} else if (a == null) {
		return true;
	}

	return false;
};

System.getProperty = function(obj, propertyName) {
	var ps = propertyName.split(".");

	var item = obj;
	for (var i = 0; i < ps.length; i++) {
		item = item[ps[i]];
		if (System.isnull(item)) {
			return null;
		}
	}

	return item;
};

System.setProperty = function(obj, propertyName, value) {
	var ps = propertyName.split(".");

	var item = obj;
	for (var i = 0; i < ps.length - 1; i++) {
		item = item[ps[i]];
		if (System.isnull(item)) {
			return null;
		}
	}

	return item[ps[ps.length - 1]] = value;
};

System.isDateTime = function(reObj) {

	var regex = new RegExp(
			"^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");

	var match = regex.exec(reObj);

	return match != null && match.length > 0;

};

System.Declare = function(ns) {
	// /<summary>声明命名空间</summary>
	// /<param name="ns" type="string">命名空间，如：org.netsharp.core</param>
	var ar = ns.split('.');
	var root = window;
	for (var i = 0, len = ar.length; i < len; ++i) {
		var n = ar[i];
		if (!root[n]) {
			root[n] = {};
			root = root[n];
		} else {
			root = root[n];
		}
	}
};

// ----------------------基础类------------

System.KeyValuePair = System.Object.Extends({
	ctor : function(key, value) {
		if (key && value) {
			this.key = key;
			this.value = value;
		}
	},
	key : null,
	value : null
});

System.Dictionary = System.Object.Extends({

	ctor : function() {
		this.innerValues = new Array();
	},

	byKey : function(key) {
		for (var i = 0; i < this.innerValues.length; i++) {

			var kv = this.innerValues[i];
			if (kv.key == key) {

				return kv.value;
			}
		}

		return null;
	},
	byIndex : function(index) {

		return this.innerValues[index];

	},

	add : function(key, value) {

		if (this.byKey(key) != null) {
			return;
		}

		var kv = new System.KeyValuePair();
		kv.key = key;
		kv.value = value;

		this.innerValues.push(kv);
	},

	tryGet : function(key, value) {

		for (var i = 0; i < this.innerValues.length; i++) {

			var kv = this.innerValues[i];
			if (kv.key == key) {

				value = kv.value;

				return true;
			}
		}

		return false;
	},

	getLength : function() {
		return this.innerValues.length;
	},

	count : function(filter) {
		return this.getLength();
	}
});

System.StringBuilder = System.Object.Extends({

	ctor : function() {
		this.innerValue = "";
		this.newLine = "\r\n";

	},

	append : function(value) {
		this.innerValue += value;
	},

	appendLine : function(value) {
		this.innerValue += (value + this.newLine);
	},

	toString : function() {
		return this.innerValue;
	}
});

System.Url = {
	getUrl : function(url) {
		if (url.indexOf("https://") == 0) {
			return url;
		} else if (url.indexOf("www.") == 0) {
			return url;
		} else if (url.indexOf("/panda") < 0) {
			url = "/panda" + url;
		}
		return url;
	},
	join : function(url1, url2) {
		if (url1.indexOf("?") >= 0) {
			return url1 + "&" + url2;
		} else {
			return url1 + "?" + url2;
		}
	},
	getParameter : function(key) {
		var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			var x = unescape(r[2]);
			return decodeURI(x);
		} else {
			return null;
		}
	}
};

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
String.prototype.format = function () {
	
    var args = arguments;
    var reg = /\{(\d+)\}/g;
    return this.replace(reg, function (g0, g1) {   
        return args[+g1];      
    });
};

// 日期格式化
Date.prototype.format = function(format) {

	/* eg:format="yyyy-MM-dd hh:mm:ss"; */

	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	;

	return format;
};


Date.prototype.getDays = function (){
	//构造当前日期对象
	var date = this;

	//获取年份
	var year = date.getFullYear();

	//获取当前月份
	var mouth = date.getMonth() + 1;

	//定义当月的天数；
	var days ;

	//当月份为二月时，根据闰年还是非闰年判断天数
	if(mouth == 2){
	        days= year % 4 == 0 ? 29 : 28;
	        
	    }
	    else if(mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12){
	        //月份为：1,3,5,7,8,10,12 时，为大月.则天数为31；
	        days= 31;
	    }
	    else{
	        //其他月份，天数为：30.
	        days= 30;
	        
	    }
	    return days;
};

// 获取第二天日期
Date.prototype.getday = function(day) {
	var today = this;
	var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
	today.setTime(targetday_milliseconds);
	var tyear = today.getFullYear();
	var tmonth = today.getMonth() + 1;
	if (tmonth.toString().length == 1) {
		tmonth = "0" + tmonth;
	}

	var tdate = today.getDate();
	if (tdate.toString().length == 1) {
		tdate = "0" + tdate;
	}

	return tyear + "-" + tmonth + "-" + tdate;
};
System.Cookies = {
	// getCookieAdapter: function () {
	// (function () { }).apply(jquery, arguments);
	// },
	get : function(name) {

		var arr = document.cookie.match(new RegExp("(^| )" + name
				+ "=([^;]*)(;|$)"));

		if (arr != null)
			return unescape(arr[2]);
		return null;
	},
	set : function(name, value, expires) {

		this.remove(name);

		var Days = expires || 7;

		var exp = new Date();

		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);

		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString() + ";path=/";
	},
	remove : function(name) {

		var exp = new Date();

		exp.setTime(exp.getTime() - 1);

		var cval = this.get(name);

		if (cval != null) {
			cval = '';
			document.cookie = name + "=" + cval + ";expires="
					+ exp.toGMTString() + ";path=/";
		}
	}
};
// 金额大写转换函数
System.DX = function(n) {

	if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
		return "数据非法";
	var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	n += "00";
	var p = n.indexOf('.');
	if (p >= 0)
		n = n.substring(0, p) + n.substr(p + 1, 2);
	unit = unit.substr(unit.length - n.length);
	for (var i = 0; i < n.length; i++)
		str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(
			/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(
			/^元零?|零分/g, "").replace(/元$/g, "元整");
}

/*人民币：元分处理*/
System.RMB = {
		
	yuanToFen:function(value){
		
		return value*100;
	},
	fenToYuan:function(value){
		
		return (value/100).toFixed(2);
	}
}
System.dateTimeShortcutChange = function(newValue, oldValue, startControlId,
		endControlId) {

	var startControl = $("#" + startControlId);
	var endControl = $("#" + endControlId);
	var date = new Date();
	switch (newValue) {
	// 清空
	case 'clear':
		startControl.datebox("clear");
		endControl.datebox("clear");
		break;
	// 今天
	case 'today':
		var today = date.getday(0);
		startControl.datebox("setValue", today);
		endControl.datebox("setValue", today);
		break;
	// 昨天
	case 'yesterday':
		var yesterDay = date.getday(-1);
		startControl.datebox("setValue", yesterDay);
		endControl.datebox("setValue", yesterDay);
		break;
	// 明天
	case 'tomorrow':
		var yesterDay = date.getday(1);
		startControl.datebox("setValue", yesterDay);
		endControl.datebox("setValue", yesterDay);
		break;
	// 前两天
	case 'nearlyTwoDays':
		var nearlyTwoDay = date.getday(-2);
		date = new Date();
		var today = date.getday(0);
		startControl.datebox("setValue", nearlyTwoDay);
		endControl.datebox("setValue", today);
		break;
	// 前三天
	case 'nearlyThreeDays':
		var nearlyThreeDay = date.getday(-3);
		date = new Date();
		var today = date.getday(0);
		startControl.datebox("setValue", nearlyThreeDay);
		endControl.datebox("setValue", today);
		break;
	// 本周
	case 'thisWeek':
		date = new Date();
		var nowYear = date.getYear(); // 当前年
		nowYear += (nowYear < 2000) ? 1900 : 0; // 

		var nowDay = date.getDate(); // 当前日
		var nowMonth = date.getMonth(); // 当前月
		var nowDayOfWeek = date.getDay();

		var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);

		var weekEndDate = new Date(nowYear, nowMonth, nowDay
				+ (6 - nowDayOfWeek));

		startControl.datebox("setValue", weekStartDate.getday(0));
		endControl.datebox("setValue", weekEndDate.getday(0));
		break;
	// 上周
	case 'lastWeek':
		date = new Date();
		var nowYear = date.getYear(); // 当前年
		nowYear += (nowYear < 2000) ? 1900 : 0; // 

		var nowDay = date.getDate(); // 当前日
		var nowMonth = date.getMonth(); // 当前月
		var nowDayOfWeek = date.getDay();

		var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);

		var weekEndDate = new Date(nowYear, nowMonth, nowDay
				+ (6 - nowDayOfWeek));

		startControl.datebox("setValue", weekStartDate.getday(-7));
		endControl.datebox("setValue", weekEndDate.getday(-7));

		break;
	// 本月
	case 'thisMonth':
		var date = new Date();
		var nowYear = date.getYear(); // 当前年
		nowYear += (nowYear < 2000) ? 1900 : 0; // 

		var nowDay = date.getDate(); // 当前日
		var nowMonth = date.getMonth(); // 当前月
		var monthStartDate = new Date(nowYear, nowMonth, 1);

		var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
		function getMonthDays(myMonth) {
			var monthStartDate = new Date(nowYear, myMonth, 1);
			var monthEndDate = new Date(nowYear, myMonth + 1, 1);
			var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
			return days;
		}
		startControl.datebox("setValue", monthStartDate.getday(0));
		endControl.datebox("setValue", monthEndDate.getday(0));
		break;
	// 上月
	case 'lastMonth':
		var lastMonthDate = new Date(); // 上月日期
		lastMonthDate.setDate(1);
		lastMonthDate.setMonth(lastMonthDate.getMonth() - 1);
		var lastYear = lastMonthDate.getYear();
		var lastMonth = lastMonthDate.getMonth();
		lastYear += (lastYear < 2000) ? 1900 : 0;
		var lastMonthStartDate = new Date(lastYear, lastMonth, 1);
		startControl.datebox("setValue", lastMonthStartDate.getFullYear() + '-'
				+ (lastMonthStartDate.getMonth() + 1) + '-01');
		var monthStartDate = new Date(lastMonthDate.getYear(), lastMonth, 1);
		var monthEndDate = new Date(lastMonthDate.getYear(), lastMonth + 1, 1);
		var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
		endControl.datebox("setValue", lastMonthStartDate.getFullYear() + '-'
				+ (lastMonthStartDate.getMonth() + 1) + '-' + days);
		break;
	// 本季度
	case 'thisQuarter':
		date = new Date();
		var nowMonth = date.getMonth() + 1;
		var remainder = nowMonth % 4;
		var die = Math.round(nowMonth / 4);
		var thisQuarter = 1;
		if (die == 0) {
			thisQuarter = 1;
		} else if (remainder > 0 && die == 1) {
			thisQuarter = 2;
		} else if (remainder > 0 && die == 2) {
			thisQuarter = 3;
		} else if (remainder > 0 && die == 3) {
			thisQuarter = 4;
		}

		var startDate = "";
		var endDate = "";
		if (thisQuarter == 1) {
			startDate = date.getFullYear() + '-01-01';
			endDate = date.getFullYear() + '-03-31';
		} else if (thisQuarter == 2) {
			startDate = date.getFullYear() + '-04-01';
			endDate = date.getFullYear() + '-06-30';
		} else if (thisQuarter == 3) {
			startDate = date.getFullYear() + '-07-01';
			endDate = date.getFullYear() + '-09-30';
		} else if (thisQuarter == 4) {
			startDate = date.getFullYear() + '-10-01';
			endDate = date.getFullYear() + '-12-31';
		}
		startControl.datebox("setValue", startDate);
		endControl.datebox("setValue", endDate);
		break;
	// 上季度
	case 'lastQuarter':
		date = new Date();
		var nowMonth = date.getMonth() + 1;
		var remainder = nowMonth % 4;
		var die = Math.round(nowMonth / 4);
		var thisQuarter = 1;
		if (die == 0) {
			thisQuarter = 1;
		} else if (remainder > 0 && die == 1) {
			thisQuarter = 2;
		} else if (remainder > 0 && die == 2) {
			thisQuarter = 3;
		} else if (remainder > 0 && die == 3) {
			thisQuarter = 4;
		}

		var startDate = "";
		var endDate = "";
		if (thisQuarter == 1) {
			startDate = date.getFullYear() + '-10-01';
			endDate = date.getFullYear() + '-12-31';
		} else if (thisQuarter == 2) {
			startDate = date.getFullYear() + '-01-01';
			endDate = date.getFullYear() + '-03-31';
		} else if (thisQuarter == 3) {
			startDate = date.getFullYear() + '-04-01';
			endDate = date.getFullYear() + '-06-30';
		} else if (thisQuarter == 4) {
			startDate = date.getFullYear() + '-07-01';
			endDate = date.getFullYear() + '-09-30';
		}
		startControl.datebox("setValue", startDate);
		endControl.datebox("setValue", endDate);
		break;
	// 今年
	case 'thisYear':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-01-01');
		endControl.datebox("setValue", date.getFullYear() + '-12-31');
		break;
	// 去年
	case 'lastYear':
		var date = new Date();
		startControl.datebox("setValue", (date.getFullYear() - 1) + '-01-01');
		endControl.datebox("setValue", (date.getFullYear() - 1) + '-12-31');
		break;
	// 上半年
	case 'firstHalfYear':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-01-01');
		endControl.datebox("setValue", date.getFullYear() + '-06-30');
		break;
	// 下半年
	case 'secondHalfYear':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-06-30');
		endControl.datebox("setValue", date.getFullYear() + '-12-31');
		break;
	// 第一季度
	case 'firstQuarter':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-01-01');
		endControl.datebox("setValue", date.getFullYear() + '-03-31');
		break;
	// 第二季度
	case 'secondQuarter':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-04-01');
		endControl.datebox("setValue", date.getFullYear() + '-06-30');
		break;
	// 第三季度
	case 'threeQuarter':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-07-01');
		endControl.datebox("setValue", date.getFullYear() + '-09-30');
		break;
	// 第四季度
	case 'fourQuarter':
		var date = new Date();
		startControl.datebox("setValue", date.getFullYear() + '-10-01');
		endControl.datebox("setValue", date.getFullYear() + '-12-31');
		break;

	default:
		break;
	}
};


/*
 * 功能：生成一个GUID码，其中GUID以14个以下的日期时间及18个以上的16进制随机数组成，GUID存在一定的重复概率，但重复概率极低，理论上重复概率为每10ms有1/(16^18)，即16的18次方分之1，重复概率低至可忽略不计
 * 免责声明：此代码为作者学习专用，如在使用者在使用过程中因代码问题造成的损失，与作者没有任何关系
 * 日期：2014年9月4日
 * 作者：wyc
 */
 
  
function GUID() {
  this.date = new Date();
 
  /* 判断是否初始化过，如果初始化过以下代码，则以下代码将不再执行，实际中只执行一次 */
  if (typeof this.newGUID != 'function') {
     
    /* 生成GUID码 */
    GUID.prototype.newGUID = function() {
      this.date = new Date();
      var guidStr = '';
        sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
        sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
      for (var i = 0; i < 9; i++) {
        guidStr += Math.floor(Math.random()*16).toString(16);
      }
      guidStr += sexadecimalDate;
      guidStr += sexadecimalTime;
      while(guidStr.length < 32) {
        guidStr += Math.floor(Math.random()*16).toString(16);
      }
      return this.formatGUID(guidStr);
    }
 
    /*
     * 功能：获取当前日期的GUID格式，即8位数的日期：19700101
     * 返回值：返回GUID日期格式的字条串
     */
    GUID.prototype.getGUIDDate = function() {
      return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
    }
 
    /*
     * 功能：获取当前时间的GUID格式，即8位数的时间，包括毫秒，毫秒为2位数：12300933
     * 返回值：返回GUID日期格式的字条串
     */
    GUID.prototype.getGUIDTime = function() {
      return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero( parseInt(this.date.getMilliseconds() / 10 ));
    }
 
    /*
    * 功能: 为一位数的正整数前面添加0，如果是可以转成非NaN数字的字符串也可以实现
     * 参数: 参数表示准备再前面添加0的数字或可以转换成数字的字符串
     * 返回值: 如果符合条件，返回添加0后的字条串类型，否则返回自身的字符串
     */
    GUID.prototype.addZero = function(num) {
      if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
        return '0' + Math.floor(num);
      } else {
        return num.toString();
      }
    }
 
    /* 
     * 功能：将y进制的数值，转换为x进制的数值
     * 参数：第1个参数表示欲转换的数值；第2个参数表示欲转换的进制；第3个参数可选，表示当前的进制数，如不写则为10
     * 返回值：返回转换后的字符串
     */
    GUID.prototype.hexadecimal = function(num, x, y) {
      if (y != undefined) {
        return parseInt(num.toString(), y).toString(x);
      } else {
        return parseInt(num.toString()).toString(x);
      }
    }
 
    /*
     * 功能：格式化32位的字符串为GUID模式的字符串
     * 参数：第1个参数表示32位的字符串
     * 返回值：标准GUID格式的字符串
     */
    GUID.prototype.formatGUID = function(guidStr) {
      var str1 = guidStr.slice(0, 8) + '-',
        str2 = guidStr.slice(8, 12) + '-',
        str3 = guidStr.slice(12, 16) + '-',
        str4 = guidStr.slice(16, 20) + '-',
        str5 = guidStr.slice(20);
      return str1 + str2 + str3 + str4 + str5;
    }
  }
}


System.GUID = {
	
	newGUID:function(){
		var guid = new GUID();
		return guid.newGUID();　
	}
};

