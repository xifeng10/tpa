<!DOCTYPE html>
<html>
<head>
    <title>知识点编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/zTreeStyle/zTreeStyle.css"/>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.ztree.all.min.js"></script>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.form.min.js"></script>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
    <SCRIPT type="text/javascript">
        var IDMark_Switch = "_switch", IDMark_Icon = "_ico", IDMark_Span = "_span";
        var IDMark_Input = "_input", IDMark_Check = "_check", IDMark_Edit = "_edit";
        var IDMark_Remove = "_remove", IDMark_Ul = "_ul", IDMark_A = "_a";
        var setting = {
            data: {
                key: {
                    url: ""
                },
                simpleData: {
                    enable: true
                }
            },
            edit: {
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false,
                drag: {
                    isMove: true
                }
            },
            callback: {
                beforeDrop: zTreebeforeDrop,
                onDrop: zTreeOnDrop
            },
            async: {
                enable: true,
                url: "${request.contextPath}/manager/knowledgePoint/queryKnowledgePointByClassifyId?classifyId=${classifyId!''}",
                type: "get",
                dataFilter: zTreeDataFormat
            },
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false
            }
        };

        function zTreebeforeDrop(treeId, treeNodes, targetNode, moveType) {
            if (targetNode.type) {
                if (targetNode.id == treeNodes[0].pId) {
                    return false;
                }
                else {
                    return confirm("确认改变 节点  -- " + treeNodes[0].name + "的结构位置 吗？");
                }
            }
            else {
                alert("目标节点不是菜单");
                return false;
            }
        }

        function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
            $.ajax({
                url: "${request.contextPath}/manager/knowledgePoint/" + treeNodes[0].id,
                type: "put",
                data: {
                    "id": treeNodes[0].id,
                    "name": treeNodes[0].name
                }
            });
            return true;
        }

        function zTreeDataFormat(treeId, parentNode, responseData) {
            var menuList = responseData;
            if(menuList.length==0) {
                menuList[menuList.length] = {id: null, name: "", open: true, type: true, isCanDel: false, type: true};
            }else{
                $.each(menuList,function (index,item) {
                    if(item.pId==null){
                        item.open=true;
                    }
                });
            }

            return menuList;
        }

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            var addStr = "";
            var btn = null;

            if ($("#addBtn_" + treeNode.id).length == 0) {
                addStr = "<span type='button' class='button add' id='addBtn_"
                        + treeNode.id
                        + "' title='新增'></span>";
                sObj.append(addStr);
                btn = $("#addBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        addpoint(treeNode.id);
                    });
            }

            if (treeNode.id != -1 && $("#editBtn_" + treeNode.id).length == 0) {
                addStr = "<span type='button' class='button edit' id='editBtn_"
                        + treeNode.id
                        + "' title='编辑'></span>";
                sObj.append(addStr);
                btn = $("#editBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        updatepoint(treeNode.id);
                    });
            }

            if (treeNode.id != -1 && $("#removeBtn_" + treeNode.id).length == 0 ) {
                addStr = "<span type='button' class='button remove' id='removeBtn_"
                        + treeNode.id
                        + "' title='删除'></span>";
                sObj.append(addStr);
                btn = $("#removeBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        deletepoint(treeNode.id, treeNode.name);
                    });
            }
        }

        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.id).unbind().remove();
            $("#editBtn_" + treeNode.id).unbind().remove();
            $("#removeBtn_" + treeNode.id).unbind().remove();
        }

        function addpoint(parentId) {
            $("#pointForm")[0].reset();
            $("#pointParentId").val(parentId);
            $("#pointId").attr("disabled", "disabled");
            $("#method").attr("value", "post");
            $("#pointForm").attr("action", "${request.contextPath}/manager/knowledgePoint.json");
            $('#myModal').modal('show')
        }

        function updatepoint(id) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var treeNode = zTree.getNodeByParam("id", id, null);
            $("#pointId").val(id);
            $("#pointId").removeAttr("disabled");
            $("#method").attr("value", "put");
            $("#pointParentId").attr("value", treeNode.pId);
            $("#pointForm").attr("action", "${request.contextPath}/manager/knowledgePoint/" + id + ".json");
            $("#pointName").val(treeNode.name);
            $('#myModal').modal('show')

        }

        function deletepoint(id, name) {
            if (confirm("确定删除知识点：" + name + " 及其子知识点吗?")) {
                $.ajax({
                    url: "${request.contextPath}/manager/knowledgePoint/" + id,
                    type: "delete",
                    success: function (result) {
                        if (true) {
                            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                            treeNode = zTree.getNodeByParam("id", id, null);
                            zTree.removeChildNodes(treeNode);
                            zTree.removeNode(treeNode);
                        }
                    }
                });
            }
        }

        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting);

            $(".btn-primary").click(function(){
                $('#pointForm').submit();
            })

            $('#pointForm').ajaxForm({
                type: 'POST',
                dataType: 'json',
                timeout: 5000,
                success: function (responseText, statusText, xhr, $form) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    if ($form.find("#pointId").attr("disabled") == 'disabled') {
                        node = treeObj.getNodeByParam("id", $form.find("#pointParentId").val(), null);
                        treeObj.addNodes(node, {
                            id: responseText.id,
                            name : responseText.name,
                            pId: $form.find("#pointParentId").val(),
                        });
                        treeObj.refresh();
                    }
                    else {
                        node = treeObj.getNodeByParam("id", $form.find("#pointId").val(), null);
                        node.name = responseText.name;
                        node.pId = $form.find("#pointParentId").val();
                        treeObj.updateNode(node);
                    }
                    $('#myModal').modal('hide');

                }
            });
        });
    </SCRIPT>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top">
            <table width="100%" cellpadding="0"
                   cellspacing="10" bgcolor="#FFFFFF">
                <tr>
                    <td>
                        <div class="zTreeDemoBackground left">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">知识点管理</h4>
            </div>
            <div class="modal-body">
                <form name="form1" method="post" action="../../system/point.json"
                      id="pointForm">
                    <input name="_method" type="hidden" value="put" id="method"/>
                    <input type="hidden" name="parentId" id="pointParentId" value="">
                    <input type="hidden" disabled="disabled" name="id" value="" id="pointId">
                    <input type="hidden" name="classifyId" value="${classifyId!''}" id="classifyId">
                    <div align="center">
                        <table class="table">
                            <tr>
                                <td scope="row">知识点名称<font class="redImportant">＊</font></td>
                                <td colspan="3"><input type="text"
                                                       name="name" value="" id="pointName"
                                                       class="form-control input" maxlength="512">
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>