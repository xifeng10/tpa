<!DOCTYPE html>
<html>
<head>
    <title>类型选择</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <#--<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/easyui/bootstrap/easyui.css">-->
    <#--<script type="text/javascript" src="${request.contextPath}/static/js/jquery.easyui.min.js"></script>-->
    <#--<link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/jqgrid/ui.jqgrid-bootstrap.css" />-->
    <#--<script type="text/javascript" src="${request.contextPath}/static/js/jqgrid/jquery.jqGrid.min.js"></script>-->
    <#--<script type="text/javascript" src="${request.contextPath}/static/js/i18n/grid.locale-cn.js" ></script>-->
    <#--<script type="text/javascript" src="${request.contextPath}/static/js/jqgrid/jqgrid-responsive.js"></script>-->

    <script>
        $.jgrid.defaults.width = 780;
        $.jgrid.defaults.responsive = true;
        $.jgrid.defaults.styleUI = 'Bootstrap';
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: '${request.contextPath}/manager/classify/page.json',
                mtype: "GET",
                styleUI : 'Bootstrap',
                datatype: "json",
                colModel: [
                    { label: 'id', name: 'id', key: true, width: 75,editable:true},
                    { label: 'name', name: 'name', width: 150,editable:true }
                ],
                viewrecords: true,
                height: 250,
                rowNum: 20,
                pager: "#jqGridPager"
            });
            $("#jqGrid").jqGrid('navGrid', '#jqGridPager',
                {
                    edit:true,
                    edittext:"选择",
                    add:false,
                    del:false,
                    view:false,
                    search:false,
                    refreshtext:"刷新",
                    editfunc : function(rowId){
                        $.post("${request.contextPath}/manager/classify/selectClassify.json",{classifyId:rowId},function (responseJSON) {
                            if(responseJSON.flag){
                                bs_info("成功修改类型！");
                            }
                        },"json")
                    }
                }
            );
        });

    </script>
</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
</body>
</html>
