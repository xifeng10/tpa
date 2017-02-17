<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资源管理</title>
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
                url: "${request.contextPath}/system/method/queryAll.json?${_csrf.parameterName!''}=${_csrf.token!''}",
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
                url: "${request.contextPath}/system/method/" + treeNodes[0].id,
                type: "put",
                data: {
                    "id": treeNodes[0].id,
                    "packageName": treeNodes[0].packageName,
                    "className": treeNodes[0].className,
                    "methodName": treeNodes[0].methodName,
                    "paramName": treeNodes[0].paramName
                }
            });
            return true;
        }

        function zTreeDataFormat(treeId, parentNode, responseData) {
            var menuList = responseData;
            if (menuList.length == 0) {
                menuList[menuList.length] = {id: null, name: "", open: true, type: true, isCanDel: false, type: true};
            } else {
                $.each(menuList, function (index, item) {
                    if (item.pId == null) {
                        item.open = true;
                    }
                    if(!item.className||item.className==''){
                        item.name=item.packageName;
                    }else if(!item.methodName||item.methodName==''){
                        item.name = item.className;
                    }else{
                        item.name = item.methodName+"("+item.paramName+")";
                    }
                });
            }

            return menuList;
        }

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            var addStr = "";
            var btn = null;

            if ((treeNode.paramName==null||treeNode.paramName=='') && $("#addBtn_" + treeNode.id).length == 0) {
                addStr = "<span type='button' class='button add' id='addBtn_"
                        + treeNode.id
                        + "' title='新增'></span>";
                sObj.append(addStr);
                btn = $("#addBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        addMethod(treeNode.id,treeNode.packageName,treeNode.className,treeNode.methodName);
                    });
            }

            if (treeNode.id != 0 && $("#editBtn_" + treeNode.id).length == 0) {
                addStr = "<span type='button' class='button edit' id='editBtn_"
                        + treeNode.id
                        + "' title='编辑'></span>";
                sObj.append(addStr);
                btn = $("#editBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        updateMethod(treeNode.id);
                    });
            }

            if ($("#removeBtn_" + treeNode.id).length == 0 && treeNode.isCanDel) {
                addStr = "<span type='button' class='button remove' id='removeBtn_"
                        + treeNode.id
                        + "' title='删除'></span>";
                sObj.append(addStr);
                btn = $("#removeBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        deleteMethod(treeNode.id, treeNode.name);
                    });
            }
        }
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.id).unbind().remove();
            $("#editBtn_" + treeNode.id).unbind().remove();
            $("#removeBtn_" + treeNode.id).unbind().remove();
        }

        function addMethod(parentId,packageName,className,methodName) {
            $("#methodForm")[0].reset();
            $("#methodParentId").val(parentId);
            $("#packageName").val(packageName);
            $("#className").val(className);
            $("#methodName").val(methodName);
            $("#methodId").attr("disabled", "disabled");
            $("#method").attr("value", "post");
            $("#methodForm").attr("action", "${request.contextPath}/system/method.json?${_csrf.parameterName!''}=${_csrf.token!''}");
            $('#myModal').modal('show')
        }

        function updateMethod(id) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var treeNode = zTree.getNodeByParam("id", id, null);
            $("#methodId").val(id);
            $("#methodId").removeAttr("disabled");
            $("#method").attr("value", "put");
            $("#methodParentId").attr("value", treeNode.pId);
            $("#methodForm").attr("action", "${request.contextPath}/system/method/" + id + ".json?${_csrf.parameterName!''}=${_csrf.token!''}");
            $("#packageName").val(treeNode.packageName);
            $("#className").val(treeNode.className);
            $("#methodName").val(treeNode.methodName);
            $("#paramName").val(treeNode.paramName);
            $('#myModal').modal('show')

        }

        function deleteMethod(id, name) {
            if (confirm("确定删除资源：" + name + " 及其子资源吗?")) {
                $.ajax({
                    url: "${request.contextPath}/system/method/" + id + "?${_csrf.parameterName!''}=${_csrf.token!''}",
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

            $(".btn-primary").click(function () {
                var uniqueIdentifier = $("#packageName").val()+"."+$("#className").val()+"#"+$("#methodname").val()+"("+$("#paramName").val()+")";
//                var methodName=$("#methodName").val();
//                if(methodName!=''){
//                    uniqueIdentifier =
//                }
                $("#uniqueIdentifier").val(uniqueIdentifier);
                $('#methodForm').submit();
            })

            function getName(packageName,className,methodName,paramName){
                if(!className||className==''){
                    name=packageName;
                }else if(!methodName||methodName==''){
                    name = className;
                }else{
                    name = methodName+"("+paramName+")";
                }
                return name;
            }

            $('#methodForm').ajaxForm({
                type: 'POST',
                dataType: 'json',
                timeout: 5000,
                success: function (responseText, statusText, xhr, $form) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    if ($form.find("#methodId").attr("disabled") == 'disabled') {
                        node = treeObj.getNodeByParam("id", $form.find("#methodParentId").val(), null);
                        treeObj.addNodes(node, {
                            id: responseText.id,
                            pId: $form.find("#methodParentId").val(),
                            packageName: responseText.packageName,
                            className: responseText.className,
                            methodName: responseText.methodName,
                            paramName: responseText.paramName,
                            name:getName(responseText.packageName,responseText.className,responseText.methodName,responseText.paramName)
                        });

                        treeObj.refresh();
                    }
                    else {
                        node = treeObj.getNodeByParam("id", $form.find("#methodId").val(), null);

                        node.packageName = responseText.packageName;
                        node.className = responseText.className;
                        node.methodName = responseText.methodName;
                        node.paramName = responseText.paramName;
                        node.name=getName(responseText.packageName,responseText.className,responseText.methodName,responseText.paramName)
                        node.pId = $form.find("#methodParentId").val();
                        if (node.type && !responseText.type) {
                            $("#diyBtn1_" + node.id).remove();
                        } else if (!node.type && responseText.type) {
                            $("#diyBtn2_" + node.id).before("<a id='diyBtn1_" + node.id
                                    + "' onclick='addMethod(" + node.id + ")' href='#'><font style=\"color: #000099;\">添加资源</font></a>");
                        }
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
                <h4 class="modal-title" id="myModalLabel">资源管理</h4>
            </div>
            <div class="modal-body">
                <form name="form1" method="post" action="../../system/method.json"
                      id="methodForm">
                    <input name="_method" type="hidden" value="put" id="method"/>
                    <input type="hidden" name="parentId" id="methodParentId" value="">
                    <input type="hidden" disabled="disabled" name="id" value="" id="methodId">
                    <input type="hidden" name="uniqueIdentifier" value="" id="uniqueIdentifier">
                    <div align="center">
                        <table class="table">
                            <tr>
                                <td scope="row">包名<font class="redImportant">＊</font></td>
                                <td><input type="text"
                                           name="packageName" value="" id="packageName"
                                           class="form-control input" maxlength="512">
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">类名</td>
                                <td><input name="className" type="text" class="form-control input"
                                           value="" maxlength="1024" id="className"></td>
                            </tr>
                            <tr>
                                <td scope="row">方法名称<font class="redImportant">＊</font></td>
                                <td><input type="text"
                                           name="methodName" value="" id="methodName"
                                           maxlength="1024" class="form-control input">
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">参数名称<font class="redImportant">＊</font></td>
                                <td><input type="text"
                                           name="paramName" value="" id="paramName"
                                           maxlength="1024" class="form-control input">
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">备注</td>
                                <td><textarea name="remark" rows="4" class="form-control input"
                                              id="methodRemark" maxlength="1024"></textarea></td>
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