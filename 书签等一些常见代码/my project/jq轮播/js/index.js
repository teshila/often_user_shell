$(function(){
    var  timer = null;
    var  i = 0;
    var nums = $('#box .con li').size();

    // 遍历获取下标并触发点击事件
    $('#box .con li').on('click', function(){
        // 获取当前元素的下标
        var index = $(this).index();
        // console.log(index);
        show(index);
    });

    // 淡入淡出
    function show(a){
        // 通过传递进来的下标改变相应的图片
        $('#box .nav li').eq(a).addClass('active').siblings().removeClass('active');
        // 通过传递进来的下标改变相应的样式
        $('#box .con li').eq(a).addClass('active').siblings().removeClass('active');
        // $('#box .con li').eq(a).css('background','#f00').siblings().css('background','#eee');

    };

    // 自动轮播

    function autoPlay(){
        timer = setInterval(function(){
            if( i < nums-1){
                i++;
                show(i);
            }else {
                console.log(1);
                i = 0;
                show(i);
            }
        },1000);
    };
    autoPlay();

    $('#wrap').on('mouseover',function(){
        clearInterval(timer);

    });
    $('#wrap').on('mouseout',function(){
        autoPlay();
    });

    // 下一页
    $('#next').on('click',function(){
        if( i < nums-1){
            i++;
            show(i);
        }else {
            console.log(1);
            i = 0;
            show(i);
        }
    });

    // 上一页
    $('#prev').on('click',function(){
       if(i > 0){
            i--;
            show(i);
            console.log(i);

        } else{
            i = nums -1;
            show(i);
        }
    });
});