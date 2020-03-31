

$(document).ready(function (){
    echart_one.setOption(option_one);
    echart_two.setOption(option_two);
    echart_three.setOption(option_three);
    initEchartThreeData();
    initEchartOneAndTwoData();
});

var pie=[];
var type=[];
function initEchartThreeData(){
    $.ajax({
        url:'/air/data/subsystem',
        type:'post',
        data:{
            date:$("#date").html().trim(),
            projectName:$("#projectName").html().trim(),
            openId:$("#openId").html().trim(),
        },
        success:function (result) {
            pie=[];
            type=[];
            for(var index in result){
                pie.push({value:(result[index].value).toFixed(2), name: result[index].type});
                type.push(result[index].type);
            }
            option_three.series[0].data=pie;
            option_three.legend.data=type;
            echart_three.setOption(option_three);
        }
    })

}
var arryTime=[];
var arryEnergy=[];
var arrySave=[];
var arryCop=[];
var arrScop=[];
function initEchartOneAndTwoData(){
    $.ajax({
        type:'post',
        url:'/air/data/getdatalist',
        data:{
            date:$("#date").html().trim(),
            projectName:$("#projectName").html().trim(),
            openId:$("#openId").html().trim(),
        },
        success:function (result) {
            if(result.length>0){
                arryTime=[];
                arryEnergy=[];
                arrySave=[];
                arryCop=[];
                arrScop=[];
                for(var res in result){
                    var time=result[res].time.substring(10,13) ;
                    arryTime.push(time);
                    arryEnergy.push(result[res].systemEnergy);
                    arrySave.push(result[res].saveEnergy);
                    arryCop.push(result[res].cop);
                    arrScop.push(result[res].scop);
                }
                option_one.xAxis[0].data=arryTime;
                option_one.series[0].data=arrySave;
                option_one.series[1].data=arryEnergy;
                option_two.xAxis.data=arryTime;
                option_two.series[0].data=arryCop;
                option_two.series[1].data=arrScop;
                echart_one.setOption(option_one);
                echart_two.setOption(option_two);
            }
        },
        error: function (e, jqxhr, settings, exception) {
            $("#spin-div").text("请求发生错误...");
        }

    });
}
var echart_one = echarts.init(document.getElementById("echarts-one"));
var echart_two = echarts.init(document.getElementById("echarts-two"));
var echart_three = echarts.init(document.getElementById("echarts-three"));
var option_one= {
    color: ['#17E248', '#F71A16'],
    title : {
        text: '机房用能/节能详情',
        subtext: 'kw·h',
        x:'center',
        textStyle: {
            color:"#00FFFF",
            fontSize: 45         // 图例文字颜色
        }
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }

    },
    legend: {
        show:true,
        orient: 'horizontal',
        x : 'left',
        data:['节能','用能'],
        textStyle: {
            color: '#00FFFF'          // 图例文字颜色
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    calculable : true,
    xAxis : [
        {
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff",
                    fontSize:25
                },
            },
            splitLine:{
                show:false
            },
            axisLine: {
                lineStyle: {
                    type: 'solid',
                    color: '#0595EB',
                }
            },
            type : 'category',
            data : ['01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00',
                '09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00',
                '17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
        },

    ],
    yAxis : [
        {
            type : 'value',
            splitLine:{
                show:false
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff",
                    fontSize:25
                }
            },
            axisLine: {
                lineStyle: {
                    type: 'solid',
                    color: '#0595EB',
                }
            },
        }
    ],
    series : [
        {

            name:'节能',
            type:'bar',
            barWidth : 5,
            stack: '用能',
            data:[0, 0, 0, 0, 0, 0, 0,0,
                  0, 0, 0, 0, 0, 0, 0,0,
                  0, 0, 0, 0, 0, 0, 0,0]
        },
        {
            name:'用能',
            type:'bar',
            stack: '用能',
            data:[0, 0, 0, 0, 0, 0, 0,0,
                0, 0, 0, 0, 0, 0, 0,0,
                0, 0, 0, 0, 0, 0, 0,0]
        },
    ]
};
var option_two = {
    color: ['#17E248', '#0DF8F6'],
    title : {
        text: '机房能效趋势',
        x:'center',
        textStyle: {
            color:"#00FFFF",
            fontSize: 45
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        x : 'left',
        data:['cop','scop'],
        textStyle: {
            color: '#00FFFF'          // 图例文字颜色
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        axisLabel: {
            show: true,
            textStyle: {
                color: "#ffffff",
                fontSize:25
            },
        },
        splitLine:{
            show:false
        },
        axisLine: {
            lineStyle: {
                type: 'solid',
                color: '#0595EB',
            }
        },
        type: 'category',
        boundaryGap: false,
        data: ['01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00',
            '09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00',
            '17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
    },
    yAxis: {
        type: 'value',

        axisLabel: {
            show: true,
            textStyle: {
                color: "#ffffff",
                fontSize:25
            }
        },
        axisLine: {
            lineStyle: {
                type: 'solid',
                color: '#0595EB',
            }
        },
    },
    series: [
        {
            name:'cop',
            type:'line',
            data:[0, 0, 0, 0, 0, 0, 0,0,
                0, 0, 0, 0, 0, 0, 0,0,
                0, 0, 0, 0, 0, 0, 0,0]
        },
        {
            name:'scop',
            type:'line',
            data:[0, 0, 0, 0, 0, 0, 0,0,
                  0, 0, 0, 0, 0, 0, 0,0,
                  0, 0, 0, 0, 0, 0, 0,0]
        },

    ]
};
var option_three= {
    color: ['#23B7E5','#E6E429', '#2381E5','#2975E6','#F71A16'],
    title : {
        text: '机房能耗占比',
        subtext: 'kw·h',
        x:'center',
        textStyle: {
            color:"#00FFFF",
            fontSize: 45        // 图例文字颜色
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['其他','冷却塔','冷却泵','冷冻泵','主机'],
        textStyle: {
            color: '#00FFFF'          // 图例文字颜色
        }
    },
    calculable : true,
    series : [
        {
            name:'能耗',
            type:'pie',
            radius : '80%',
            center: ['50%', '60%'],
            label: {
                normal: {
                   // show:true,
                   // position:'inner', //标签的位置
                    textStyle: {
                        color:'#FFF',
                        fontSize:30
                    },
                    formatter:'{b} : {d}%'
                }
            },
            data:[
                {value:1, name:'其他'},
                {value:1, name:'冷却塔'},
                {value:1, name:'冷却泵'},
                {value:1, name:'冷冻泵'},
                {value:1, name:'主机'}
            ]
        }
    ]
};

