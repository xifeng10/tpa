<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资源管理</title>
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/zTreeStyle/zTreeStyle.css"/>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.ztree.all.min.js"></script>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.form.min.js"></script>
    <SCRIPT type="text/javascript">
        var IDMark_Switch = "_switch", IDMark_Icon = "_ico", IDMark_Span = "_span";
        var IDMark_Input = "_input", IDMark_Check = "_check", IDMark_Edit = "_edit";
        var IDMark_Remove = "_remove", IDMark_Ul = "_ul", IDMark_A = "_a";
        var setting = {
                    data : {
                        simpleData : {
                            enable : true
                        }
                    },
                    async : {
                        enable : true,
                        url : "resource/queryAll.json?${_csrf.parameterName!''}=${_csrf.token!''}"
                    },
                    view : {
                        addDiyDom : addDiyDom,
                        dblClickExpand : dblClickExpand
                    },
                    edit : {
                        enable : true,
                        showRemoveBtn : false,
                        showRenameBtn : false,
                        drag:{
                            isMove:true
                        }
                    },
                callback : {
                    beforeDrop : zTreebeforeDrop,
                    onDrop :  zTreeOnDrop
                }
        };

        function dblClickExpand(treeId, treeNode) {
            return treeNode.level > 0;
        }

        function zTreebeforeDrop(treeId, treeNodes, targetNode, moveType) {
            return confirm("确认改变 节点  -- " + treeNodes[0].name + "的结构位置 吗？");
        }

        function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
            alert(treeNodes[0].name);
            $.post("resource!saveOrUpdate.action",
                    {"resource.id":treeNodes[0].id,
                        "resource.GResource.id":targetNode.id,
                        "changeParentFlg":true
                    });
            return true;
        };

        function addDiyDom(treeId, treeNode) {
            if (treeNode.parentNode && treeNode.parentNode.id != 2)
                return;
            var aObj = $("#" + treeNode.tId + IDMark_A);
            var editStr = "";
            if(treeNode.resourceType){
                editStr += "<a id='diyBtn1_" + treeNode.id
                        + "' onclick='addResource("+treeNode.id+")' href='#'><font style=\"color: #000099;\">添加资源</font></a>";
            }
            editStr += "<a id='diyBtn2_" + treeNode.id
                    + "' href='#' onclick='updateResource("+treeNode.id+")'><font style=\"color: #000099;\">修改资源</font></a>";
            editStr += "<a id='diyBtn3_" + treeNode.id
                    + "' href='#' onclick='deleteResource("+treeNode.id+",\""+treeNode.name+"\")'><font style=\"color: #000099;\">删除资源</font></a>";
            aObj.after(editStr);
        }

        function addResource(parentId){
            $("#resourceForm")[0].reset();
            $("#resourceParentId").val(parentId);
            $("#resourceId").attr("disabled","disabled");
            $("#resourceDiv").dialog("open");
        }

        function updateResource(id){
            $("#resourceId").val(id);
            $("#resourceId").removeAttr("disabled");
            $.post("resource!queryForUpdate.action",{"resource.id":id},
                    function(resource){
                        $("#resourceForm")[0].reset();
                        if(resource.parentId)
                            $("#resourceParentId").val(resource.parentId);
                        $("#resourceName").val(resource.resourceName);
                        $("#resourceUrl").val(resource.url);
                        $("#resourceDisplayIndex").val(resource.displayIndex);
                        $("input[name='resource.resourceType'][value='"+resource.resourceType+"']").attr("checked","checked");
                        $("input[name='resource.enable'][value='"+resource.enable+"']").attr("checked","checked");
                        $("#resourceRemark").val(resource.remark);
                        $("#resourceDiv").dialog("open");
                    },"json");
        }

        //查询资源
        function queryResource(){
            var url = $("#resource_url").val();
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var rootNode=zTree.getNodeByParam("resourceUrl",url,null);
            zTree.selectNode(rootNode);
        }

        //重置
        function resetResource(){
            $("#resource_url").val("");
        }

        function deleteResource(id,name){
            if(confirm("确定删除资源："+name+"?")){
                $.post("resource!delete.action",{"resource.id":id},
                        function(result){
                            if(result.flag){
                                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                                treeNode = zTree.getNodeByParam("id", id, null);
                                zTree.removeChildNodes(treeNode);
                                zTree.removeNode(treeNode);
                            }
                        },"json");
            }
            //location.href="resource!delete.action?resource.id="+id;
        }

        $(document).ready(function() {
            $.fn.zTree.init($("#treeDemo"), setting);
//            $("#resourceDiv").dialog({
//                autoOpen : false,
//                width : 640,
//                height : 340,
//                title : '资源管理',
//                bgiframe : true,
//                buttons : [ {
//                    text : "关闭",
//                    click : function() {
//                        $(this).dialog("close");
//                    }
//                }, {
//                    text : "提交",
//                    click : function() {
//                        //$(this).dialog("close");
//                        $('#resourceForm').submit();
//                    }
//                } ]
//            });

            $('#resourceForm').ajaxForm({
                type:'POST',
                dataType:'json',
                timeout:5000,
                success:function(responseText, statusText, xhr, $form)  {
                    if(responseText.flag){
                        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                        if($form.find("#resourceId").attr("disabled")=='disabled'){
                            node = treeObj.getNodeByParam("id", responseText.resource.parentId, null);
                            treeObj.addNodes(node, {
                                id : responseText.resource.id,
                                pId : responseText.resource.parentId,
                                name : responseText.resource.resourceName,
                                url :  responseText.resource.url,
                                resourceType : responseText.resource.resourceType
                            });
                        }else{
                            node = treeObj.getNodeByParam("id", responseText.resource.id, null);
                            node.name = responseText.resource.resourceName;
                            node.url = responseText.resource.url;
                            if(node.resourceType&&!responseText.resource.resourceType){
                                $("#diyBtn1_"+node.id).remove();
                            }else if(!node.resourceType&&responseText.resource.resourceType){
                                $("#diyBtn2_"+node.id).before("<a id='diyBtn1_" + node.id
                                        + "' onclick='addResource("+node.id+")' href='#'><font style=\"color: #000099;\">添加资源</font></a>");
                            }
                            node.resourceType = responseText.resource.resourceType;
                            treeObj.updateNode(node);
                        }
                        $("#resourceDiv").dialog("close");
                    }
                }
            });
        });
    </SCRIPT>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top"><table width="100%" cellpadding="0"
                                cellspacing="10" bgcolor="#FFFFFF">
            <tr>
                <td>
                    <table width="100%" cellpadding="0" cellspacing="0"
                           class="table1">
                        <tr>
                            <td width="15%">菜单名称：</td>
                            <td width="35%">
                                <input type="text" name="resource.url" id="resource_url" style="font-family:Arial,Helvetica,sans-serif;font-size:12px;border:1px solid #cdcdcd;width:300px;" />
                            </td>
                            <td width="50%">
                                <input type="button" id="queryBtn" value="查询" onclick="queryResource()"/>
                                <input type="button" id="resetBtn" value="重置" onclick="resetResource()"/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </td>
            </tr>
        </table></td>
    </tr>
</table>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">资源维护</h4>
            </div>
            <div class="modal-body">
            <form name="form1" method="post" action="resource!saveOrUpdate.action"
                  id="resourceForm">
                <input type="hidden" name="resource.GResource.id"
                       id="resourceParentId" value=""> <input type="hidden"
                                                              disabled="disabled" name="resource.id" value="" id="resourceId">
                <div align="center">
                    <table class="table">
                        <tr>
                            <td scope="row">资源名称<font class="redImportant">＊</font></td>
                            <td colspan="3"><input type="text"
                                                   name="resource.resourceName" value="" id="resourceName"
                                                   class="form-control input" maxlength="16" style="width: 98%"></td>
                        </tr>
                        <tr>
                            <td scope="row">资源路径<font class="redImportant">＊</font></td>
                            <td colspan="3"><input name="resource.url" type="text"
                                                   value="" class="form-control input" maxlength="256" id="resourceUrl"
                                                   style="width: 98%"></td>
                        </tr>
                        <tr>
                            <td scope="row">显示顺序<font class="redImportant">＊</font></td>
                            <td colspan="3"><input name="resource.displayIndex"
                                                   type="text" value="" class="form-control input" maxlength="8"
                                                   id="resourceDisplayIndex" style="width: 98%"></td>
                        </tr>
                        <tr>
                            <td scope="row">资源类型</td>
                            <td><input type="radio" name="resource.resourceType"
                                       value="true">菜单 <input type="radio"
                                                              name="resource.resourceType" value="false" checked="checked">非菜单
                            </td>
                            <td scope="row">是否可用</td>
                            <td ><input type="radio" name="resource.enable"
                                                   value="true" checked="checked">是 <input type="radio"
                                                                                           name="resource.enable" value="false">否</td>
                        </tr>
                        <tr>
                            <td scope="row">备注</td>
                            <td colspan="3"><textarea name="resource.remark" rows="5" class="form-control input"
                                                      id="resourceRemark" style="width: 98%"></textarea></td>
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