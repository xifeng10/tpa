<!DOCTYPE html>
<html>
<head>

    <title>类型管理</title>
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
                    { label: 'name', name: 'name', width: 150,editable:true },
                    { label: '知识点', name: 'id',
                        formatter: formatKnowledgePoint }
                ],
                viewrecords: true,
                height: 250,
                rowNum: 20,
//                jsonReader : {
//                    root: "page.results",	// json中代表实际模型数据的入口
//                    page: "page.currentPage",	// json中代表当前页码的数据
//                    total: "page.totalPages",	// json中代表页码总数的数据
//                    records: "page.totalSize", // json中代表数据行总数的数据
//                    repeatitems: false // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
//                },
                pager: "#jqGridPager"
            });
            $("#jqGrid").jqGrid('navGrid', '#jqGridPager',
                    {
                        edit:true,
                        edittext:"编辑",
                        add:true,
                        addtext:"添加",
                        del:true,
                        deltext:"删除",
                        view:false,
                        search:false,
                        refreshtext:"刷新",
                        addfunc : function(){
                            var addParams = {
                                url:"${request.contextPath}/manager/classify",
                                checkOnSubmit:false,
                                submit:false,
                                closeAfterAdd:true
                            };
                            $("#jqGrid").jqGrid("editGridRow","new",addParams);
                        },
                        editfunc : function(rowid){
                            var addParams = {
                                url:"${request.contextPath}/manager/classify/"+rowid+"?_method=PUT",
                                checkOnSubmit:false,
                                submit:false,
                                closeAfterEdit:true
                            };
                            $("#jqGrid").jqGrid("editGridRow",rowid,addParams);
                        },
                        delfunc : function(rowid){
                            $.post("${request.contextPath}/manager/classify/"+rowid+".json",{"_method":"DELETE"},function(responseJSON){
                                console.log(responseJSON);
                                $("#jqGrid").trigger("reloadGrid");
                            },"json")
                        }
                    }
            );
        });
        function formatKnowledgePoint(cellValue, options, rowObject) {
            var knowledgePointUpdateHtml = "<a href='${request.contextPath}/manager/knowledgePoint?classifyId=" + cellValue + "'>知识点<a>";
            return knowledgePointUpdateHtml;
        }
    </script>
</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
</body>
</html>
