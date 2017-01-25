<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工管理</title>
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/jquery-ui.min.css"/>
    <script type="text/javascript" src="${request.contextPath}/static/js/jquery-ui.min.js"></script>
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
            closeAfterAdd: function () {
                return false;
            },
            onclickSubmit: function (params, postdata) {
                params.url = URL + ".json?${_csrf.parameterName!''}=${_csrf.token!''}";
            },
            afterComplete:function (response, postdata, formid) {
                if(response.responseJSON!="success"){
                    bs_error(response.responseJSON.msg);
                }else{
                    bs_info("添加成功！");
                }
            }
        };
        var delOptions = {
            mtype: "DELETE",
            onclickSubmit: function (params, postdata) {
                params.url = URL + '/' + postdata + ".json?${_csrf.parameterName!''}=${_csrf.token!''}";
            }
        };

        var URL = 'employee';
        $("#jqGrid").jqGrid({
            url: 'employee/page',
            editurl: URL,
            datatype: "json",
            colModel: [
                {
                    label: 'ID', name: 'id', width: 75, editable: true,editoptions:{disabled: true}
                }, {
                    name: "姓名",
                    index: "realName",
                    editable: true,
                    editoptions: {
                        size: 75,
                        dataInit: function (e) {
                            $(e).autocomplete({
                                source: function (request, response) {
                                    $.ajax({
                                        url: "person/autocomplete.json?${_csrf.parameterName!''}=${_csrf.token!''}",
                                        type: "post",
                                        dataType: "json",
                                        beforeSend: function () {
                                        },
                                        data: {
                                            "realName": function () {
                                                return $.trim($(e).val());
                                            }
                                        },
                                        success: function (data) {
                                            response($.map(data, function (item) {
                                                return {
                                                    label: (item.employeeCode ? item.employeeCode + "-" : "") + item.realName,
                                                    value: item.id + "/" + item.realName + "/" + (item.phone ? item.phone : "")
                                                };
                                            }));
                                        }
                                    });
                                },
                                minLength: 1,

                                search: function (event, ui) {
                                    $("input#id").val("");
                                    return true;
                                },
                                select: function (event, ui) {
                                    var terms = ui.item.value.split("/");
                                    $(e).val(terms[1]);
                                    $("input#id").val(terms[0]);
                                    return false;
                                },
                                open: function () {
                                    $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
                                },
                                close: function () {
                                    $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
                                }
                            });
                            $('.ui-autocomplete').css('zIndex',1000);
                        }
                    },
                    editrules: {
                        edithidden: true,
                        required: false
                    },
                    edittype: "text",
                    hidden: true,
                    width: 75
                },
                {label: 'birthPlaceId', name: 'birthPlaceId', width: 160, editable: true,hidden:true},
                {label: '用户名', name: 'username', width: 160, editable: true},
                {label: '密码', name: 'password', width: 160, editable: true, hidden: true},
                {label: '工号', name: 'employeeCode', width: 160, editable: true},
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