require.config({
    paths: {
        'echarts': 'client/js',
        'echarts/theme/dark': 'client/js/theme/dark'
    }
});
require(
    [
        'echarts',
        'echarts/theme/dark',
        'echarts/chart/radar', //按需加载图表关于线性图的部分
        'echarts/chart/eventRiver',
        'echarts/chart/pie',
        'echarts/chart/bar',
        'echarts/chart/map'//按需加载图表关于地图的部分
    ],
    DrawCharts
);

function DrawCharts(ec, dark) {

    var countChart = require('echarts').init(document.getElementById('scount'), "macarons");
    var hotChart = require('echarts').init(document.getElementById('hot'), "macarons");
    var radarChart = require('echarts').init(document.getElementById('radar'), "macarons");
    var serverChart = require('echarts').init(document.getElementById('server'), dark);
    setTimeout(function () {
        getRealm();
    }, 200);
    setTimeout(function () {
        DrawCountEChart(countChart);
    }, 200);
    setTimeout(function () {
        DrawHotEChart(hotChart);
    }, 200);
    setTimeout(function () {
        DrawRealmEChart(radarChart);
    }, 200);
    setTimeout(function () {
        hot();
    }, 200);
    setTimeout(function () {
        DrawServerEChart(serverChart);
    }, 200);
    setTimeout(function () {
        search();
    }, 200);
    setInterval(function () {
        DrawCountEChart(countChart);
    }, 50 * 1000);
    setInterval(function () {
        DrawHotEChart(hotChart);
    }, 50 * 1000);
    setInterval(function () {
        DrawRealmEChart(radarChart);
    }, 50 * 1000);
    setInterval(function () {
        hot();
    }, 50 * 1000);
    setInterval(function () {
        search();
    }, 50 * 1000);
    /*setInterval(function () {
     DrawServerEChart(serverChart);
     }, 6 * 1000);*/
}
function DrawCountEChart(countChart) {

    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxsearchcount',
        dataType: 'json',
        success: function (json) {
            countChart.setOption(json);
        }
    });

}

function DrawRealmEChart(radarChart) {

    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxrealm',
        dataType: 'json',
        success: function (json) {
            radarChart.setOption(json);
        }
    });

}

function DrawHotEChart(hotChart) {

    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxkeyword',
        dataType: 'json',
        success: function (json) {
            hotChart.setOption(json);
        }
    });
}
//server
function DrawServerEChart(serverChart) {

    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxserver',
        dataType: 'json',
        success: function (json) {
            serverChart.setOption(json);
        }
    });
}

var hotList = eval([{"id": 0}]);
function hot() {
    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxhot',
        dataType: 'json',
        success: function (json) {
            var dataObj = eval(json);

            //console.log(dataObj[0].id);
            //console.log(jj[0].id);
            if (hotList[0].id == dataObj[0].id) {

            } else {
                //console.log("有新的");
                $("#rotate").empty();

                $.each(dataObj, function (key, value) {
                    var r = Math.round(Math.random() * (255 - 1) + 1);
                    var g = Math.round(Math.random() * (255 - 1) + 1);
                    var b = Math.round(Math.random() * (255 - 1) + 1);
                    var rgb = 'rgb(' + r + ',' + g + ',' + b + ')';
                    //console.log(rgb);
                    var top = "<a href=" + value.url + " style=color:" + rgb + " target=_blank>" + value.title + "</a>";
                    $("#rotate").append(top);
                });
                scroll();
                hotList = dataObj;
            }
        }
    });
}

function getRealm() {
    $.ajax({
        type: 'post',
        async: false,
        url: '/ajaxgetrealm',
        dataType: 'json',
        success: function (json) {
            var dataObj = eval(json);
            //$("#myStateButton").empty();
            $.each(dataObj, function (key, value) {
                var top = "<li vid="+value.id+">"+value.name+"</li>";
                $("#ullist").append(top);
            });

        }
    });
}

function search() {
    //alert($("#keyword").val());
    //alert($("#btntext").attr("value"));

    var k;
    if($("#keyword").val().length==0){
        k=0;
    }else{
        k = $("#keyword").val();
        k = encodeURI(k);
    }
    var r = $("#btntext").attr("value");
    var url = '/ajaxinfos/'+ k +"-"+r;

        console.log('R=' + r);
        console.log('K=' + k);
        $.ajax({
            type: 'post',
            async: false,
            url: url,
            dataType: 'json',
            success: function (json) {
                var dataObj = eval(json);
                $("#search").empty();
                $.each(dataObj, function (key, value) {
                    //console.log(value.state);
                    if (value.state == 1) {
                        var top = "<li class=alert-danger><a href=" + value.url + " target=_blank>" + value.title + "</a></li>";
                    } else {
                        var top = "<li class=alert-success><a href=" + value.url + " target=_blank>" + value.title + "</a></li>";
                    }
                    $("#search").append(top);
                });

            }
        });

}

function scroll() {
    var element = $('#rotate a');
    var offset = 0;
    var stepping = 0.03;
    var list = $('#rotate');
    var $list = $(list);
    $list.mousemove(function (e) {
        var topOfList = $list.eq(0).offset().top;
        var listHeight = $list.height();
        stepping = (e.clientY - topOfList) / listHeight * 0.2 - 0.1;
    });

    for (var i = element.length - 1; i >= 0; i--) {
        element[i].elemAngle = i * Math.PI * 2 / element.length;
    }

    setInterval(render, 120);

    function render() {
        for (var i = element.length - 1; i >= 0; i--) {
            var angle = element[i].elemAngle + offset;
            x = 120 + Math.sin(angle) * 30;
            y = 45 + Math.cos(angle) * 40;
            var size = Math.round(10 - Math.sin(angle) * 10);
            var elementCenter = $(element[i]).width() / 2;
            //var leftValue = (($list.width() / 2) * x / 100 - elementCenter) + "px";
            $(element[i]).css("fontSize", size + "pt");
            $(element[i]).css("opacity",size/10);
            $(element[i]).css("zIndex", size);
            //$(element[i]).css("left" ,leftValue);
            $(element[i]).css("top", y + "%");
        }
        offset += stepping;
    }
}





