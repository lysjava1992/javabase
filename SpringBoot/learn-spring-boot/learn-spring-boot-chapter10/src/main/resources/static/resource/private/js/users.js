$(document).ready(function () {
    initData();
});

function initData() {
    $('#example').DataTable({
        "ajax": {
            "url": "/wx/manage/getUsers",
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
    });
}

var colums = [
    {"data": "nickname"},
    {"data": "subscribe"},
    {"data": "createDate"},
    {"data": "lock"},
    {"data": "openid", "defaultContent": "--"},
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
        }
};
var columnDefs= [
    {
        "render": function ( data, type, row ) {
            if(data){
                return  '<span style="color: #1aad19">已关注</span>';
            }
            return '<span style="color: red">已取关</span>';
        },
        "data" : null,
        "targets": 1
    },
    {
        "render": function ( data, type, row ) {
            if(data){
                return  '<span style="color: red">已锁定</span>';
            }
            return '<span style="color: #1aad19">正常</span>';
        },
        "data" : null,
        "targets": 3
    },
        {
            "render": function ( data, type, row ) {
                return '<a href="/wx/manage/user-one/'+data+'"><i class="fa fa-edit">查看</a>';
            },
            "data" : null,
            "targets": 4
        },

];