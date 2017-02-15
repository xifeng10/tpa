<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人员管理</title>
    <script src="${request.contextPath}/static/js/DatePicker/WdatePicker.js" type="text/JavaScript"></script>

</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript">

    $(document).ready(function () {

        $(".navbar-nav li").removeClass("active");
        $("#personManagerLi").addClass("active");
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

        var URL = 'person';
        $("#jqGrid").jqGrid({
            url: 'person/page',
            editurl: URL,
            datatype: "json",
            colModel: [
                {
                    label: 'ID', name: 'id', width: 75, editable: true,
                    editoptions: {disabled: true, size: 5}
                },
                {label: '姓名', name: 'realName', width: 160, editable: true},
                {label: '姓名拼音', name: 'nameSpell', width: 160, editable: true},
                {label: '拼音缩写', name: 'spellAbbreviation', width: 160, editable: true},
                {
                    label: '性别', name: 'sex', width: 160, editable: true,
                    edittype: "select",formatter: "select",
                    editoptions: {value:"true:女;false:男"}
                },
                {
                    label: '生日', name: 'birthday', width: 160, editable: true,
                    edittype: "text",
                    editoptions: {
                        dataInit: function (element) {
                            $(element).click(function(){
                                WdatePicker();
                            });
                        }
                    },
                    formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d'}
                },
                {label: '电话', name: 'phone', width: 160, editable: true},
                {label: '邮箱', name: 'email', width: 160, editable: true},
                {
                    label: '创建时间',
                    name: 'createTime',
                    width: 180,
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