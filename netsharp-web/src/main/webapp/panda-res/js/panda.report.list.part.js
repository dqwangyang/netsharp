
org.netsharp.panda.commerce.ReportListPart = org.netsharp.panda.commerce.ListPart.Extends({
	//line线，pie 饼图，column柱状 data: [{"data":[23,44,8],"name":"订单"},{"data":[44,55,9],"name":"定金"}]
	//一个X轴多个项 
	//显示饼图 title:名称,subtitle:副标题， 没有请传null,data: 数组【名字，值】,isAnnular:是否为环形图
//	 var data = [];  
//	 data.push(['Firefox', Math.random()]);  
//	 data.push(['IE', Math.random()]);  
//	 data.push(['Safari', Math.random()]);  
//	 data.push(['Opera', Math.random()]);  
//	 data.push(['Others', Math.random()]);  
	showBieCharts:function(title,subtitle,data,isAnnular){
		
		 if(data.length==0){ 
			 
			 return;
		 }
		 var p='0%';
		 
		 if(isAnnular==true){
		  		p='50%';
		 }
		 var showDiv='charts';
		 if(!divId||divId==null||divId==''){
			 
			 divId='#pandaWindow';
			 
		 }else{
			 showDiv=divId+'charts';
			 divId="#"+divId;
			
		 }
			$(divId).window({
		        title: title,
		        width:innerWidth ,
		        height: innerHeight ,       
		        modal: true,
		        maximized: false,
		        minimizable: false,
		        maximizable: false,
		        shadow: true,
		        cache: false,
		        closed: false,
		        collapsible: false,
		        resizable: true,
		        inline: true,
		        content:"<div id='"+showDiv+"' style='height:100%;width:100%;'></div>"
		    });
		  	
			  
		  	
		 var   chartOptions  = new Highcharts.Chart({
		        chart: {
		            renderTo: showDiv,
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		        	  text: title,
			          verticalAlign:'middle'
		        },
		        subtitle: {
			    		    text:subtitle,
			    		    style:{
			    		        color:"red",
			    		        fontWeight:"bold"
			    		    }
			    },
		        tooltip: {
		                //鼠标停留区域特殊显示效果
		            formatter: function() {
		            	return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 2) +'% ('+
		            	Highcharts.numberFormat(this.y, 2, '.') +')';
		            }
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    formatter: function() {
		                       //保留两位小数
		                    return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 2)+' %';
		                    }
		                }
		            }
		        },
		        //注：该处的[]对应饼行图显示数据个数
		        series: [{
		            type: 'pie',
		            name: 'bieCharts',
		            innerSize:p,
		            data: [
		               
		            ]
		        }]
		    });		   
		  chartOptions.series[0].setData(data);
	},
	 showCharts:function(data,yTitle,title,chartType,divId) {	
		 if(data.length==0) return;
		 var showDiv='charts';
		 if(!divId||divId==null||divId==''){
			 
			 divId='#pandaWindow';
			 
		 }else{
			 showDiv=divId+'charts';
			 divId="#"+divId;
			
		 }
		
		  	$(divId).window({
		        title: title,
		        width:innerWidth ,
		        height: innerHeight ,       
		        modal: true,
		        maximized: false,
		        minimizable: false,
		        maximizable: false,
		        shadow: true,
		        cache: false,
		        closed: false,
		        collapsible: false,
		        resizable: true,
		        inline: true,
		        content:"<div id='"+showDiv+"' style='height:100%;width:100%;'></div>"
		    });
		  	
			  
		 
		 var  chart = new Highcharts.Chart({
		        chart:{
		            renderTo:showDiv,
		            type:chartType ,//显示类型 柱形.line
		            
		        },
		        plotOptions:{
		        	column:{
		        		dataLabels:{
		        			enabled:true 
		        		}
		        	}
		        },
		  
		        title:{
		            text:title //图表的标题
		        },
		        xAxis:{
		            categories:data[0]
		        },
		        yAxis:{
		            title:{
		                text:yTitle //Y轴的名称
		            },
		        },
		        series:this.getData(data[1])		     
		    });		   
	  	    
		    
	},
	    getData:function(data){
  
        var obj=[];
        for(var key in data){
        var entity={};
        entity.data=data[key].data;
        entity.name=data[key].name;
        obj.push(entity);
        }        
        return(obj);
        
},
//X轴单项
showColumnCharts:function(data,yProperty,xProperty,title,yTitle,seriesName,chartType,divId) {	
	 if(data.length==0) return;
	 
	 var showDiv='charts';
	 if(!divId||divId==null||divId==''){
		 
		 divId='#pandaWindow';
		 
	 }else{
		 showDiv=divId+'charts';
		 divId="#"+divId;
		
	 }
	
	 
	  	$(divId).window({
	        title: title,
	        width:innerWidth ,
	        height: innerHeight ,       
	        modal: true,
	        maximized: false,
	        minimizable: false,
	        maximizable: false,
	        shadow: true,
	        cache: false,
	        closed: false,
	        collapsible: false,
	        resizable: true,
	        inline: true,
	        content:"<div id='"+showDiv+"' style='height:100%;width:100%;'></div>"
	    });
	  	
	 var xtext = [];//X轴TEXT		  
	   var json = data;		   
        for(var key in json){	        
            json[key].y =this.getProperty(yProperty,json[key]); //给Y轴赋值
            xtext.push( this.getProperty(xProperty,json[key]));//给X轴TEXT赋值	            
           }
	 var  chart = new Highcharts.Chart({
	        chart:{
	            renderTo:showDiv,
	            type:chartType ,//显示类型 柱形.line
	            
	        },
	        plotOptions:{
	        	column:{
	        		dataLabels:{
	        			enabled:true 
	        		}
	        	}
	        },
	  
	        title:{
	            text:title //图表的标题
	        },
	        xAxis:{
	            categories:xtext
	        },
	        yAxis:{
	            title:{
	                text:yTitle //Y轴的名称
	            },
	        },
	        series:[{
	            name:seriesName
	        }]
	    });		   
     chart.series[0].setData(json);//数据填充到highcharts上面       	    
	    
},
getProperty:function(propertyName,obj){
var	arr = propertyName.split(".");	 
if(arr.length==1) return obj[propertyName];
for(var i =0;i<arr.length;i++){
	obj= obj[arr[i]];
	if(i==arr.length-1){
		return obj;
	}
}	
},
});



