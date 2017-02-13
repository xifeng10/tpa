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
                url: "${request.contextPath}/system/resource/queryAll.json?${_csrf.parameterName!''}=${_csrf.token!''}",
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
                url: "${request.contextPath}/system/resource/" + treeNodes[0].id,
                type: "put",
                data: {
                    "id": treeNodes[0].id,
                    "type": treeNodes[0].type,
                    "securityUrl": treeNodes[0].securityUrl,
                    "enable": treeNodes[0].enable,
                    "method": treeNodes[0].method,
                    "name": treeNodes[0].name,
                    "remark": treeNodes[0].remark,
                    "showIndex": treeNodes[0].index,
                    "isCanDel": treeNodes[0].isCanDel,
                    "url": treeNodes[0].url,
                    "SResource.id": targetNode.id
                }
            });
            return true;
        }
        ;

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

            if (treeNode.type && $("#addBtn_" + treeNode.id).length == 0) {
                addStr = "<span type='button' class='button add' id='addBtn_"
                        + treeNode.id
                        + "' title='新增'></span>";
                sObj.append(addStr);
                btn = $("#addBtn_" + treeNode.id);
                if (btn)
                    btn.bind("click", function () {
                        addResource(treeNode.id);
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
                        updateResource(treeNode.id);
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
                        deleteResource(treeNode.id, treeNode.name);
                    });
            }
        }
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.id).unbind().remove();
            $("#editBtn_" + treeNode.id).unbind().remove();
            $("#removeBtn_" + treeNode.id).unbind().remove();
        }

        function addResource(parentId) {
            $("#resourceForm")[0].reset();
            $("#resourceParentId").val(parentId);
            $("#resourceId").attr("disabled", "disabled");
            $("#method").attr("value", "post");
            $("#resourceForm").attr("action", "${request.contextPath}/system/resource.json?${_csrf.parameterName!''}=${_csrf.token!''}");
            $('#myModal').modal('show')
        }

        function updateResource(id) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var treeNode = zTree.getNodeByParam("id", id, null);
            $("#resourceId").val(id);
            $("#resourceId").removeAttr("disabled");
            $("#method").attr("value", "put");
            $("#resourceParentId").attr("value", treeNode.pId);
            $("#resourceForm").attr("action", "${request.contextPath}/system/resource/" + id + ".json?${_csrf.parameterName!''}=${_csrf.token!''}");
            $("#resourceName").val(treeNode.name);
            $("#showIndex").val(treeNode.index);
            $("#resourceUrl").val(treeNode.url);
            $("#securityUrl").val(treeNode.securityUrl);
            $("#resourceMethod").val(treeNode.method);
            $("input[name='type'][value='" + treeNode.type + "']").attr("checked", "checked");
            $("input[name='enable'][value='" + treeNode.enable + "']").attr("checked", "checked");
            $("input[name='isCanDel'][value='" + treeNode.isCanDel + "']").attr("checked", "checked");
            $("#resourceRemark").val(treeNode.remark);
            $('#myModal').modal('show')

        }

        function deleteResource(id, name) {
            if (confirm("确定删除资源：" + name + " 及其子资源吗?")) {
                $.ajax({
                    url: "${request.contextPath}/system/resource/" + id+"?${_csrf.parameterName!''}=${_csrf.token!''}",
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
                $('#resourceForm').submit();
            })

            $('#resourceForm').ajaxForm({
                type: 'POST',
                dataType: 'json',
                timeout: 5000,
                success: function (responseText, statusText, xhr, $form) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    if ($form.find("#resourceId").attr("disabled") == 'disabled') {
                        node = treeObj.getNodeByParam("id", $form.find("#resourceParentId").val(), null);
                        treeObj.addNodes(node, {
                            id: responseText.id,
                            pId: $form.find("#resourceParentId").val(),
                            name: responseText.name,
                            index: responseText.showIndex,
                            url: responseText.menuUrl,
                            securityUrl: responseText.securityUrl,
                            remark: responseText.remark,
                            method: responseText.method,
                            enable: responseText.enable,
                            type: responseText.type,
                            isCanDel: responseText.isCanDel
                        });
                        treeObj.refresh();
                    }
                    else {
                        node = treeObj.getNodeByParam("id", $form.find("#resourceId").val(), null);
                        node.name = responseText.name;
                        node.index = responseText.showIndex;
                        node.url = responseText.url;
                        node.securityUrl = responseText.securityUrl;
                        node.remark = responseText.remark;
                        node.method = responseText.method;
                        node.enable = responseText.enable;
                        node.pId = $form.find("#resourceParentId").val();
                        if (node.type && !responseText.type) {
                            $("#diyBtn1_" + node.id).remove();
                        } else if (!node.type && responseText.type) {
                            $("#diyBtn2_" + node.id).before("<a id='diyBtn1_" + node.id
                                    + "' onclick='addResource(" + node.id + ")' href='#'><font style=\"color: #000099;\">添加资源</font></a>");
                        }
                        node.type = responseText.type;
                        node.isCanDel = responseText.isCanDel
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
                <form name="form1" method="post" action="../../system/resource.json"
                      id="resourceForm">
                    <input name="_method" type="hidden" value="put" id="method"/>
                    <input type="hidden" name="parentId" id="resourceParentId" value="">
                    <input type="hidden" disabled="disabled" name="id" value="" id="resourceId">
                    <div align="center">
                        <table class="table">
                            <tr>
                                <td scope="row">资源名称<font class="redImportant">＊</font></td>
                                <td colspan="3"><input type="text"
                                                       name="name" value="" id="resourceName"
                                                       class="form-control input" maxlength="512">
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">资源路径</td>
                                <td colspan="3"><input name="url" type="text" class="form-control input"
                                                       value="" maxlength="1024" id="resourceUrl"></td>
                            </tr>
                            <tr>
                                <td scope="row">权限路径<font class="redImportant">＊</font></td>
                                <td colspan="3"><input type="text"
                                                       name="securityUrl" value="" id="securityUrl"
                                                       maxlength="1024" class="form-control input">
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">显示顺序<font class="redImportant">＊</font></td>
                                <td width="180"><input type="text"
                                                       name="showIndex" value="" id="showIndex"
                                                       class="form-control input" maxlength="11">
                                </td>
                                <td scope="row">访问方法</td>
                                <td><select class="form-control input"
                                            name="method" id="resourceMethod" style="width: 98%">
                                    <option></option>
                                    <option>GET</option>
                                    <option>POST</option>
                                    <option>PUT</option>
                                    <option>DELETE</option>
                                </select>
                                </td>
                            </tr>
                            <tr>

                            </tr>
                            <tr>
                                <td scope="row">资源类型</td>
                                <td><input type="radio" name="type"
                                           value="true">菜单 <input type="radio"
                                                                  name="type" value="false" checked="checked">非菜单
                                </td>
                                <td scope="row">是否可用</td>
                                <td><input type="radio" name="enable"
                                           value="true" checked="checked">是 <input type="radio"
                                                                                   name="enable" value="false">否
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">是否可以删除</td>
                                <td><input type="radio" name="isCanDel"
                                           value="true">是 <input type="radio"
                                                                  name="isCanDel" value="false" checked="checked">否
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td scope="row">备注</td>
                                <td colspan="3"><textarea name="remark" rows="4" class="form-control input"
                                                          id="resourceRemark" maxlength="1024"></textarea></td>
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