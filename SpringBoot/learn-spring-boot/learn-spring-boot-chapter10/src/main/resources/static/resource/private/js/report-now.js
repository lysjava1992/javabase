$(document).ready(function (){
    echart_one.setOption(option_one);
    Highcharts.setOptions(highcharts_Options);
    init_echartData();
    init_device();
});
var cop=0;
var scop=0;
var imgIds="";
var normalIds="";
function init_echartData(){
    $.ajax({
        type: "post",
        url: "/air/data/realdata",
        data:{
            projectName:$("#projectName").html(),
            openId:$("#openId").html()
        },
        success: function (map) {
            if(map!=null){
                for(var key in map){
                    var  value=map[key];
                    $("#"+key).html(parseFloat(value).toFixed(2));
                }
                if(map["save_energy_system"]!=null){
                    var save=parseFloat(map["save_energy_system"]);
                    $("#save_c").html((save*0.4).toFixed(2));
                    $("#save_dust").html((save*0.272).toFixed(2));
                    $("#save_co2").html((save*0.997).toFixed(2));

                }
                if(map["save_rate_system"]!=null){
                    var value=parseFloat(map["save_rate_system"]);
                    option_one.series[0].data=[{value: value.toFixed(2), name: '系统'}];
                    echart_one.setOption(option_one);
                }
                if(map["save_rate_host"]!=null){
                    var value=parseFloat(map["save_rate_host"]);
                    option_one.series[1].data=[{value: value.toFixed(2), name: '主机'}];
                    echart_one.setOption(option_one);
                }
                if(map["save_rate_other"]!=null){
                    var value=parseFloat(map["save_rate_other"]);
                    option_one.series[2].data=[{value: value.toFixed(2), name: '辅机'}];
                    echart_one.setOption(option_one);
                }
                if(map["cop"]!=null){
                    var value=parseFloat(map["cop"]);
                    highcharts_data_one.title.text="主机COP （"+value+"）";
                    highcharts_data_one.series[0].data[0].y=value;
                    Highcharts.chart('echarts-two',highcharts_data_one );
                }
                if(map["scop"]!=null){
                    var value=parseFloat(map["scop"]);
                    highcharts_data_two.title.text="系统SCOP  （"+value+"）";
                    highcharts_data_two.series[0].data[0].y=value;
                    Highcharts.chart('echarts-two2',highcharts_data_two);
                }
            }
        }
    });
}
function init_device() {
    imgIds="";
    normalIds="";
    $.ajax({
        type: "post",
        url: "/air/data/device",
        data:{
            projectName:$("#projectName").html(),
            openId:$("#openId").html()
        },
        success: function ( list) {
            if(list!=null&&list.length>0){
                    for(var index in list){
                        var device=list[index];
                        imgIds +=device.stateId+"|";
                        normalIds +=device.aid+"|"+device.hzId+"|"+device.powerId+"|";
                        var res='<tr> <td align="center">'
                              +'<img src="'+device.img+'" id="'+device.stateId+'" class="td-img"></td>'
                              +'<td colspan="4" class="td-style" style="height: 100px;width: 100px">'
                              +'<p class="td-text-max">'+device.deviceName+'</p>'
                              +'<p>电流(A)：<span id="'+device.aid+'">-- </span> 频率(hz)：<span id="'+device.hzId+'">--</span></p>'
                              +' <p class="td-p-bottom"> 有功功率(kw·h)：<span id="'+device.powerId+'">-- </span>额定功率(kw·h)：<span>'+device.ratedPower+'</span></p>'
                              +'</td> </tr>';
                        $("#device_id").append(res);
                    }
                    getRealData();
             }
        }
    });

}

function getRealData() {
    if(imgIds!=""){
        $.ajax({
            type: "post",
            url: "/air/data/realtime",
            data:{
                projectName:$("#projectName").html(),
                openId:$("#openId").html(),
                ids:imgIds
            },
            success: function (map) {
                if(map!=null){
                    for(var key in map){
                        var img=$("#"+key).attr("src");
                        img=img.split('-')[0]+"-"+map[key]+".png";
                        $("#"+key).attr("src",img);
                    }
                }
            }
        });
    }
    if(normalIds!=""){
        $.ajax({
            type: "post",
            url: "/air/data/realtime",
            data:{
                projectName:$("#projectName").html(),
                openId:$("#openId").html(),
                ids:normalIds
            },
            success: function ( map) {
                if(map!=null){
                    for(var key in  map){
                        $("#"+key).html(map[key]);
                    }
                }
            }
        });
    }

}
var echart_one = echarts.init(document.getElementById("echarts-one"));
var option_one= {
    series : [
        {
            name:'系统',
            type:'gauge',
            radius : '70%',
            min:0,
            max:100,
            splitNumber:10,
            title : {
                textStyle: {       // 其余属性默认使用全局文本样式
                    fontSize: 35,
                    color:'#00FFFF'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff",
                    fontSize:20
                },
            },
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [ [0.3, '#ff4500'],[0.8, '#228b22'],[1 , '#4488BB' ] ],
                    width: 8
                }
            },
            axisTick: {            // 坐标轴小标记
                length :15,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length :20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            detail : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                }
            },
            data:[{value: 0, name: '系统'}]
        },
        {
            name:'主机',
            type:'gauge',
            center : ['25%', '55%'],    // 默认全局居中
            radius : '60%',
            min:0,
            max:100,
            endAngle:75,
            splitNumber:10
            ,
            title : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    offsetCenter: [0.8,0.8],
                    fontSize: 35,
                    color:'#00FFFF'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff",
                    fontSize:20
                },
            },
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [ [0.3, '#ff4500'],[0.8, '#228b22'],[1 , '#4488BB' ] ],
                    width: 8
                }
            },
            axisTick: {            // 坐标轴小标记
                splitNumber:5,
                length :10,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length :15,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            pointer: {
                width:2
            },
            detail : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                }
            },
            data:[{value: 0, name: '主机'}]
        },
        {
            name:'辅机',
            type:'gauge',
            center : ['75%', '55%'],    // 默认全局居中
            radius : '60%',
            min:0,
            max:100,
            startAngle:105,
            // endAngle:45,
            splitNumber:10,
            title : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE

                    fontSize: 35,
                    color:'#00FFFF'
                }
            },
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [ [0.3, '#ff4500'],[0.8, '#228b22'],[1 , '#4488BB' ] ],
                    width: 8
                }
            },
            axisTick: {            // 坐标轴小标记
                splitNumber:5,
                length :10,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff",
                    fontSize:20
                },
            },
            splitLine: {           // 分隔线
                length :15,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            pointer: {
                width:2
            },
            detail : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                }
            },
            data:[{value: 0, name: '辅机'}]
        },
    ]
};
var highcharts_Options={
    chart: {
        inverted: true,
        marginLeft: 100,  //左边距
        type: 'bullet',
        backgroundColor: '#011C2F'  , //背景颜色
        borderRadius:10
    },
    title: {
        text: null
    },
    legend: {
        enabled: false
    },
    yAxis: {
        gridLineWidth: 0
    },
    plotOptions: {
        series: {
            pointPadding: 0.5,  //中间指针线的粗细 越大越细
            //borderRadius: 10, //边角
            borderWidth: 2,
            color: '#FFF',   //中间指针线颜色
            targetOptions: {
                width: '0%'  //目标位置标签 越大越明显
            }
        }
    },
    credits: {
        enabled: false
    },
    exporting: {
        enabled: false
    }
};
var highcharts_data_one={
    chart: {
        marginTop: 100
    },
    title: {
        text: '主机COP',
        style:{ "color": "#FFF", "fontSize": "45px" }

    },
    xAxis: {
        categories: ['<span class="hc-cat-title">cop</span><br/> '],
        labels: {
            style: {
                color: '#FFF',
                fontSize: "15px"
            }
        }
    },
    yAxis: {
        plotBands: [{
            from: 0,
            to: 3,
            color: '#FD0400'
        }, {
            from: 3,
            to: 4,
            color: '#F5EF2A'
        },
            {
                from: 4,
                to: 5,
                color: '#02FD02'
            },
            {
                from: 5,
                to: 10,
                color: '#0100FE'
            }],
        title: null,
        labels: {
            style: {
                color: '#FFF',
                fontSize: "10px"
            }
        }
    },
    series: [{
        data: [{
            y: 0,
            target:6.5
        }]
    }],
    tooltip: {
        pointFormat: '<b>{point.y}</b> （目标值 {point.target}）'
    }
};
var highcharts_data_two={
    chart: {
        marginTop: 100
    },
    title: {
        text: '系统SCOP',
        style:{ "color": "#FFF", "fontSize": "45px" }
    },
    xAxis: {
        categories: ['<span class="hc-cat-title">scop</span><br/> '],
        labels: {
            style: {
                color: '#FFF',
                fontSize: "15px"
            }
        }
    },
    yAxis: {
        plotBands: [{
            from: 0,
            to: 3,
            color: '#FD0400'
        }, {
            from: 3,
            to: 4,
            color: '#F5EF2A'
        },
            {
                from: 4,
                to: 5,
                color: '#02FD02'
            },
            {
                from: 5,
                to: 10,
                color: '#0100FE'
            }],
        title: null,
        labels: {
            style: {
                color: '#FFF',
                fontSize: "10px"
            }
        }
    },
    series: [{
        data: [{
            y: 0,
            target:5.6
        }]
    }],
    tooltip: {
        pointFormat: '<b>{point.y}</b> （目标值 {point.target}）'
    }
};

