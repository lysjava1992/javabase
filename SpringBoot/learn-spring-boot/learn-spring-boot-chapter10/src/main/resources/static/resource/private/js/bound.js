var codeReg= /^[0-9A-Z]{6}$/;

$(document).ready(function () {
    $("#submitNumber").on("click",function () {
             var code=$("#code-id").val();
             if(!codeReg.test(code)){
                 alert("验证码格式错误");
                 return;
             }
             var openId=$("#open-id").val();
        $.ajax({
            type: "post",
            url: "/wx/view/bound",
            data:{
                code:code,
                openId:openId
            },
            success: function (data) {
                alert(data.msg);
            }
        });
    })

})

