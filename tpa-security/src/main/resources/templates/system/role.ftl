<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/zTreeStyle/zTreeStyle.css"/>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.ztree.all.min.js"></script>
</head>
<body>

<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        $(".navbar-nav li").removeClass("active");
        $("#roleManagerLi").addClass("active");
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
                    label: 'ID', name: 'id', width: 160, editable: true,
                    editoptions: {disabled: true, size: 5}
                },
                {label: '名称', name: 'name', width: 260, editable: true},
                {
                    label: '创建时间',
                    name: 'createTime',
                    width: 360,
                    editable: false,
                    formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}
                },
                {
                    label: '资源选择', name: 'resourceIds', hidden: true, editable: true,edittype: "custom",
                    editoptions: {
                        custom_value: getFreightElementValue,
                        custom_element: createFreightEditElement
                    },
                    editrules: {
                        edithidden: true,
                        required: false
                    }
                }
            ],
            viewrecords: true, // show the current page, data rang and total records on the toolbar
            rowNum: 30,
            rownumbers: true,
            sortorder: "desc",
            jsonReader: {
                repeatitems: false
            },
            shrinkToFit:false,
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

        function createFreightEditElement(value, editOptions) {
            var div =$("<div style='margin-bottom:5px;margin-top:-16px;'><ul id='roleResources' class='ztree'></ul></div>");
            var setting = {
                check : {
                    enable : true,
                    chkboxType : { "Y" : "ps", "N" : "s" }
                },
                async : {
                    enable : true,
                    type: "get",
                    url : "${request.contextPath}/system/resource/queryAll.json?${_csrf.parameterName!''}=${_csrf.token!''}",
                    dataFilter : zTreeDataFormat
                },
                data : {
                    simpleData : {
                        enable : true
                    }
                }
            };
            $.fn.zTree.init(div.find("#roleResources"), setting);
            var rowId = editOptions.rowId;
            if("_empty"!=rowId){
                var rowData = $("#jqGrid").jqGrid("getRowData", rowId);
                checkNodeByRoleId(rowData.id);
            }
            return div;
        }

        function zTreeDataFormat(treeId, parentNode, responseData) {
            var menuList = responseData;
            if(menuList.length>0) {
                $.each(menuList,function (index,item) {
                    if(item.pId==null){
                        item.open=true;
                    }
                });
            }

            return menuList;
        }
        // The javascript executed specified by JQGridColumn.EditTypeCustomGetValue when EditType = EditType.Custom
        // One parameter passed - the custom element created in JQGridColumn.EditTypeCustomCreateElement
        function getFreightElementValue(elem, oper, value) {
            if (oper === "set") {

                var id=$("#id").val();
                if(id) {
                    checkNodeByRoleId(id);
                }
            }

            if (oper === "get") {
                var treeObj = $.fn.zTree.getZTreeObj("roleResources");
                var nodes = treeObj.getCheckedNodes(true)
                var ids=new Array();
                $.each(nodes,function(index,item){
                    ids.push(item.id);
                })
                return ids.join();
            }
        }
        function checkNodeByRoleId(v_roleId) {
            $.post("${request.contextPath}/system/resource/queryByRoleId.json?${_csrf.parameterName!''}=${_csrf.token!''}", {roleId: v_roleId}, function (response, status, xhr) {
                var treeObj = $.fn.zTree.getZTreeObj("roleResources");
                treeObj.checkAllNodes(false);
                var node;
                $.each(response,function (index, item) {
                    node  = treeObj.getNodeByParam("id", item.id, null);
                    if(node){
                        if(!node.isParent)
                            treeObj.checkNode(node, true, true);
                    }
                })
            }, "json")
        }
    });

</script>
</body>
</html>