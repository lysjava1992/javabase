var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;

$(document).ready(function () {
    $("#code-id").val("");
    $("#get-code").on("click",function(){
        var list = [];
        $('input[name="optionName"]:checked').each(function() {
            var project={};
            project.projectName=$(this).val();
            project.projectId=$(this).attr("id");
            list.push(project);
        });
      if(list.length<=0){
          alert("未选择中任何项目");
          return;
      }
        $.ajax({
            type: "post",
            url: "/wx/manage/getCode",
            data: JSON.stringify(list),
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                $("#code-id").val(data.msg);
            },
            error:function () {
                console.log("err");
            }
        });
        timing(this);
    });
    $("#set-code").on("click",function () {
        var code= $("#code-id").val();
        var phone=$("#phone-id").val();
        if(code==""||code=="-1"){
            alert('无效验证码！');
          $("#code-id").val("");
            return false;
        }
        if(!phoneReg .test(phone))
        {
            alert('请输入有效的手机号码！');
            return false;
        }

        $.ajax({
            type: "post",
            url: "/wx/manage/setCode",
            data:{
                code:code,
                phone:phone
            },
            success: function (data) {
              alert(data.msg)
            }
        });

    })

})



/*** 全选*/
$('#allOptionId').on('ifChecked', function(event){
    $('input').iCheck('check');
});
/**反选*/
$('#allOptionId').on('ifUnchecked', function(event){
    $('input').iCheck('uncheck');
});

var waitTime = 60;
function  timing(ele) {
    if (waitTime == 0) {
        ele.disabled=false;
        ele.innerHTML = "获取验证码";
        waitTime = 60;// 恢复计时
    } else {
        ele.disabled=true;
        ele.innerHTML = waitTime + "秒";
        waitTime--;
        setTimeout(function() {
            timing(ele)// 关键处-定时循环调用
        }, 1000)
    }
}