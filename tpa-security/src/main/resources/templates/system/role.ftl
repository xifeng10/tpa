<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript">

    $(document).ready(function () {

        var editOptions = {
            mtype: "PUT",
            closeAfterEdit: true,
            closeAfterAdd: true,
            onclickSubmit: function (params, postdata) {
                params.url = URL + '/' + postdata.id + ".json?${_csrf.parameterName!''}=${_csrf.token!''}";
            }
        };
        var addOptions = {
            mtype: "POST",
            closeAfterAdd: true,
            onclickSubmit: function (params, postdata) {
                params.url = URL + ".json?${_csrf.parameterName!''}=${_csrf.token!''}";
            }
        };
        var delOptions = {
            mtype: "DELETE",
            onclickSubmit: function (params, postdata) {
                params.url = URL + '/' + postdata + ".json?${_csrf.parameterName!''}=${_csrf.token!''}";
            }
        };

        var URL = 'role';
        $("#jqGrid").jqGrid({
            url: 'role/page',
            editurl: URL,
            datatype: "json",
            colModel: [
                {
                    label: 'ID', name: 'id', width: 75, editable: true,
                    editoptions: {disabled: true, size: 5}
                },
                {label: '名称', name: 'name', width: 160, editable: true},
                {
                    label: '创建时间',
                    name: 'createTime',
                    width: 80,
                    editable: false,
                    formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}
                }
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
                {}, //options
                editOptions,
                addOptions,
                delOptions,
                {} // search options
        );

    });

</script>
</body>
</html>