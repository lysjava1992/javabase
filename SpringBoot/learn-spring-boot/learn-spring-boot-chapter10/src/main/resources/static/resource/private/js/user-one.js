var elem = document.querySelector('.js-switch-red');
$(document).ready(function () {
   initData();
    var lockSwitch =new Switchery(elem,{ color: 'red',secondaryColor: '#64BD63'});

    $("#islock").change(function() {
        var flag = $(this).is(':checked');
        if(flag){
            if(confirm("是否锁定该用户")){
                changeIsLock(true);
            }else {
                $("#islock").prop("checked", false);
                lockSwitch.element.checked = false;
                lockSwitch.setPosition();
            }
        }else {
            if(confirm("是否解锁该用户")){
                changeIsLock(false);
            }else {
                $("#islock").prop("checked", true);
                lockSwitch.element.checked = true;
                lockSwitch.setPosition();
            }
        };
    });
});
var table;
function initData() {
    table= $('#example').DataTable({
        "ajax": {
            "url": "/wx/manage/getProsById",
            "type":"POST",
            "data":{
                openid: $("#openid").val().trim()
            },
            "dataSrc": function (result) {
                if (result.state) {
                    return result.data;
                }
                return null;
            },
        },
        "lengthMenu": [30, 50, 100],
        "columns": colums,
        "oLanguage": translate,
        "columnDefs":columnDefs,
        "fnInitComplete": function(oSettings, json) {
            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
            elems.forEach(function (value) {new Switchery(value,{ color: '#64BD63',secondaryColor: 'red'});});
            $('.bound').change(function () {isBound(this);});
            $('.push').change(function () {isPush(this);});
        }
    });

}
function updateInfo() {
    var info=$("#info").val().trim();
    if(info==""||info==null){
        alert("内容不能为空");
        return;
    }
    $.ajax({
        url:'/wx/manage/userInfo',
        type:'post',
        data:{
            openid:$("#openid").val(),
            info:info
        },
        success:function (data) {
            alert("修改成功");
        }
    })

}
function changeIsLock(bol) {
    $.ajax({
        url:'/wx/manage/islock',
        type:'post',
        data:{
            openid:$("#openid").val(),
            bol:bol
        },
        success:function (data) {
            alert(data.msg);
        }
    })
}
function isBound(value) {
    var Row = $(value).parents('tr')[0];//通过获取该td所在的tr，即td的父级元素，取出第一列序号元素
    var data = $("#example").dataTable().fnGetData(Row);//得到这一行的json数据
        if(confirm("是否解绑")){
            //解绑
              $.ajax({
                 url:'/wx/manage/unbind',
                 type:'post',
                  data:{
                     openid:data.openid,
                      projectId:data.projectId
                     },
                     success:function (data) {    location.reload();}
                     })
        }
}
function isPush(value) {
    var row = $(value).parents('tr')[0];
    var data = $("#example").dataTable().fnGetData(row);
    $.ajax({
        url:'/wx/manage/push',
        type:'post',
        data:{
            openid:data.openid,
            projectId:data.projectId,
            push:$(value).prop("checked")
        },
        success:function (data) {   }
    })

}
var colums = [
    {"data": "projectName"},
    {"data": "bound"},
    {"data": "push"},
];
var translate = {//下面是一些汉语翻译
    'Showing 0 to 0 of 0 entries': "暂无数据",
    "sSearch": "搜索",
    "sLengthMenu": "每页显示 _MENU_ 条记录",
    "sZeroRecords": "没有检索到数据",
    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
    "sInfoEmtpy": "没有数据",
    //"sProcessing": "正在加载数据...",
    "sInfoEmpty": "累计0条记录",
    "sProcessing": "<img src='/image/loading.gif' />", //这里是给服务器发请求后到等待时间显示的 加载gif
    "oPaginate":
        {
            "sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "末页"
        },
};
var columnDefs= [
    {
        "render": function ( data, type, row ) {
            if(data){
                return  '  <input type="checkbox" class="js-switch bound" checked/>';
            }
            return '<span style="color: red">已解绑</span>';
        },
        "data" : null,
        "targets": 1
    },
    {
        "render": function ( data, type, row ) {
            if(row.bound){
                if(data){
                    return  '<input type="checkbox" class="js-switch push" checked />';
                }else {
                    return  '<input type="checkbox" class="js-switch push"  />';
                }
            }
            return '<span>无法操作</span>';
        },
        "data" : null,
        "targets":2
    },
];






