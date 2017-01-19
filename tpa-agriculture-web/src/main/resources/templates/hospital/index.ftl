<!DOCTYPE html>

<html lang="en">
<head>
    <title>Mybatis分页插件 - 测试页面</title>
<#--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>-->
</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript">

    $(document).ready(function () {

        var template = "<div style='margin-left:15px;'><div> ID <sup>*</sup>:</div><div> {id} </div>";
        template += "<div> 代码: </div><div>{code} </div>";
        template += "<div> 名称: </div><div>{name} </div>";
        template += "<div> 地区: </div><div>{areaName} </div>";
        template += "<div> 联系人:</div><div> {contract} </div>";
        template += "<div> 联系方式:</div><div> {phone} </div>";
        template += "<hr style='width:100%;'/>";
        template += "<div> {sData} {cData}  </div></div>";
        $("#jqGrid").jqGrid({
            url: 'hospital/page',
            datatype: "json",
            colModel: [
                {label: 'ID', name: 'id', width: 75, editable: true},
                {label: '代码', name: 'code', width: 90, editable: true},
                {label: '名称', name: 'name', width: 160, editable: true},
                {label: '地区', name: 'areaName', width: 50, editable: true},
                {label: '联系人', name: 'contract', width: 80, editable: true},
                {label: '联系方式', name: 'phone', width: 80, editable: true}
            ],
            viewrecords: true, // show the current page, data rang and total records on the toolbar
            rowNum: 30,
            rownumbers: true,
            sortorder: "desc",
            jsonReader: {
                repeatitems: false
            },
//            loadonce: true, // this is just for the demo
            pager: "#jqGridPager"
        });

        $('#jqGrid').navGrid('#jqGridPager',
        {
            edit: true,
            add: true,
            del: true,
            search: false,
            refresh: false,
            view: false,
            position: "left",
            cloneToTop: false
        },
        // options for the Edit Dialog
        {
            editCaption: "The Edit Dialog",
            template: template,
            errorTextFormat: function (data) {
                return 'Error: ' + data.responseText
            }
        },
        // options for the Add Dialog
        {
            template: template,
            errorTextFormat: function (data) {
                return 'Error: ' + data.responseText
            }
        },
        // options for the Delete Dailog
        {
            errorTextFormat: function (data) {
                return 'Error: ' + data.responseText
            }
        });

    });

</script>
</body>
</html>