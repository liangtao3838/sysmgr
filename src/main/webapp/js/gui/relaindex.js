var echarts;
var optionData={
    backgroundColor: '#1a4377',
    tooltip: {},
    animationDurationUpdate: 1500,
    animationEasingUpdate: 'quinticInOut',
    color:['#83e0ff','#45f5ce','#b158ff'],
    legend: {
        show: true,
        data: [
            {name: '人',textStyle:{color:'#fff'} },
            {name: '物证',textStyle:{color:'#fff'}},
            {name: '不明物体',textStyle:{color:'#fff'}}
        ]
    },
    series: [
        {
            type: 'graph',
            layout: 'force',
            force:{
                repulsion:1000,
                edgeLength:150
            },
            symbolSize: 50,
            edgeSymbol: ['none', 'arrow'],
            roam: true,
            label: {
                normal: {
                    show: true
                }
            },
            edgeSymbolSize: [4, 10],
            edgeLabel: {
                normal: {
                    show:true,
                    textStyle: {
                        fontSize: 13
                    },
                    formatter: "{c}"
                }
            },

            data: [
                {
                    name: '毛发',
                    symbolSize: 100,
                    draggable: true,
                    category: 1,
                    itemStyle: {
                        normal: {
                            borderColor: '#04f2a7',
                            borderWidth: 6,
                            shadowBlur: 20,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    }
                },
                {
                    name: '刀',
                    symbolSize: 70,
                    draggable: true,
                    itemStyle: {
                        normal: {
                            borderColor: '#04f2a7',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    },
                    category: 1,

                },
                {
                    name: '指纹',
                    symbolSize: 70,
                    category: 1,
                    itemStyle: {
                        normal: {
                            borderColor: '#04f2a7',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    },

                },
                {
                    name: '张三',
                    symbolSize: 70,
                    category: 0,
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    },

                },
                {
                    name: '李四',
                    symbolSize: 70,
                    category: 0,
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    },

                },
                {
                    name: '张三2',
                    category: 0,
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43',
                        }
                    },

                },
                {
                    name: '无名尸',
                    category: 2,
                    itemStyle: {
                        normal: {
                            borderColor: '#b457ff',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#b457ff',
                            color: '#001c43'
                        }
                    },

                },
                {
                    name: '赖子',
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43'

                        }
                    },
                    category: 0,

                },
                {
                    name: '王五',
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43'
                        }
                    },
                    category: 0,

                },
                {
                    name: '刘大',
                    category: 0,
                    itemStyle: {
                        normal: {
                            borderColor: '#82dffe',
                            borderWidth: 4,
                            shadowBlur: 10,
                            shadowColor: '#04f2a7',
                            color: '#001c43'
                        }
                    },

                }],
            links: [
                {
                    source: '毛发',
                    target: '刀',
                    value: '案件ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#e0f55a' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#639564' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    }

                },
                {
                    source: '毛发',
                    target: '指纹',
                    value: 'DNA',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#eda553' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#7c785b' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                },
                {
                    source: '毛发',
                    target: '张三',
                    value: 'DNA',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#eda553' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#7c785b' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                },
                {
                    source: '毛发',
                    target: '李四',
                    value: '案件ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#e0f55a' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#639564' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    }
                },
                {
                    source: '毛发',
                    target: '张三'
                },
                {
                    source: '刀',
                    target: '张三2',
                    value: '案件ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#e0f55a' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#639564' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    }
                },
                {
                    source: '刀',
                    target: '无名尸', value: '案件ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#e0f55a' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#639564' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    }

                },
                {
                    source: '李四',
                    target: '赖子',
                    value: '案件ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#e0f55a' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#639564' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    }
                },
                {
                    source: '李四',
                    target: '王五',
                    value: '身份证ID',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#df6f30' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#915034' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                },
                {
                    source: '王五',
                    target: '刘大',
                    value: 'DNA',
                    lineStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#eda553' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#7c785b' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                }
            ],
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 5,
                    curveness: 0
                }
            },
            categories:[
                {name: '人'},
                {name: '物证'},
                {name: '不明物体'}
            ]
        }
    ]
};
/*$(function () {
 //monitor();
 //graphData();
 });*/

function monitor() {
    var monitortime = $("#monitortime").val();
    $.ajax({
        url: "/syscallrelagui/getsyscount.do",
        dataType: "json",
        type: "post",
        async: false,
        data: {monitortime: monitortime},
        success: function (data) {
            var nodes = data.result.count;
            var content = "";
            $.each(nodes, function (i, item) {
                var arr = item.split(",");
                content += '<span><a href="/sysservicegui/index.do?nodecode=' + arr[0] + '">' + arr[0] + '系统</a></span></br>';
                content += '<input type="text" readonly value="' + arr[1] + '">';
                content += '<input type="text" readonly value="' + arr[2] + '">';
                content += '</br>'
            })
            $("#sysrole").html(content);
        },
    });
}
function graphData() {
    $.ajax({
        url: "/syscallrelagui/getsysname.do",
        dataType: "json",
        type: "post",
        async: false,
        data: {},
        success: function (data) {
            optionData = data.result;
        },
    });
}

require([
    'echarts'
], function (ec) {
    echarts = ec;
    option = optionData;
    testHelper.createChart(echarts, 'right', option);
});

/*var dom = document.getElementById("right");
 var myChart = echarts.init(dom);
 var app = {};
 option = null;
 app.title = '力引导布局';
 myChart.showLoading();
 $.get('/syscallrelagui/getsysname.do', function (xml) {
 myChart.hideLoading();
 var graph = echarts.dataTool.gexf.parse(xml);
 var categories = [];
 for (var i = 0; i < graph.nodes.length; i++) {
 categories[i] = {
 name: graph.nodes[i].name
 };
 }
 graph.nodes.forEach(function (node) {
 node.itemStyle = null;
 node.symbolSize = 50;
 node.value = "";
 node.x = node.y = null;
 node.draggable = true;
 node.onclick = function(){
 }
 });
 option = {
 title: {
 text: '',
 subtext: '',
 top: 'bottom',
 left: 'right'
 },
 tooltip: {},
 legend: [{
 data: categories.map(function (a) {
 return a.name;
 })
 }],
 animation: false,
 series : [
 {
 name: '',
 type: 'graph',
 layout: 'force',
 data: graph.nodes,
 links: graph.links,
 categories: categories,
 roam: true,
 label: {
 normal: {
 position: 'right'
 }
 },
 force: {
 repulsion: 1000
 }
 }
 ]
 };

 myChart.setOption(option);
 }, 'xml');;
 if (option && typeof option === "object") {
 myChart.setOption(option, true);
 }*/