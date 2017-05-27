<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>题目维护</title>
    <link rel="stylesheet"
          href="${request.contextPath}/static/css/editormd.min.css">
    <script type="application/javascript" src="${request.contextPath}/static/js/editormd.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/zTreeStyle/zTreeStyle.css"/>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.ztree.all.min.js"></script>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.form.min.js"></script>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.page.js"></script>
    <style type="text/css">
        *{ margin:0; padding:0; list-style:none;}
        a{ text-decoration:none;}
        a:hover{ text-decoration:none;}
        .tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
        .tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
        .tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
        .tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
        .tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}

        .menuContent ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            max-height: 360px;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>
</head>
<body>
<div class="col-lg-3" align="center">
    <input type="button" class="btn btn-success" value="+选择题" id="addSelectQuestionBtn" datatype="1">
</div>
<div class="col-lg-3" align="center">
    <input type="button" class="btn btn-success" value="+判断题" id="addTrueOfFalseBtn" datatype="2">
</div>
<div class="col-lg-3" align="center">
    <input type="button" class="btn btn-success" value="+填空题" id="addBlackFullBtn" datatype="3">
</div>
<div class="col-lg-3" align="center">
    <input type="button" class="btn btn-success" value="+简答题" id="addShortAnswerBtn" datatype="4">
</div>
<table class="table table-striped">

<#list questions as q>
    <tr>
        <td>
            <div>
                <form action="${request.contextPath}/manager/question/${q.id}" method="post" id="form_${q.id}">
                    <input type="hidden" name="_method" value="POST" id="editMethod_${q.id}">
                    <div>
                        <div class="col-lg-1">${q.index}</div>
                        <div class="col-lg-11">${q.questionHtml}</div>
                    </div>
                    <#if q.selectItems??>
                        <#list q.selectItems as si>
                            <div class="col-lg-3">${si.indexChar}:${si.item}</div>
                        </#list>
                    </#if>
                    <#if q.answer??>
                        <div>
                            <div class="col-lg-1">答案</div>
                            <div class="col-lg-11">${q.answerHtml}</div>
                        </div>
                    </#if>
                    <#if q.analyse??>
                        <div>
                            <div class="col-lg-1">分析</div>
                            <div class="col-lg-11">${q.analyseHtml}</div>
                        </div>
                    </#if>
                </form>
                <div class="col-lg-12">
                    <div class="col-lg-2"><a class="btn-sm btn-success"
                                             onclick="javascript:$('#editMethod_${q.id}').val('DELETE');$('#form_${q.id}').submit();">删除</a>
                    </div>
                    <div style="display: none">
                        <div id="answer_${q.id!''}">${q.answer!''}</div>
                        <div id="answerHtml_${q.id}">${q.answerHtml!''}</div>
                        <div id="analyse_${q.id}">${q.analyse!''}</div>
                        <div id="analyseHtml_${q.id}">${q.analyseHtml!''}</div>
                    </div>
                    <div class="col-lg-2"><a class="btn-sm btn-success" id="answerBtn_${q.id}" href="#">答案</a></div>
                    <div class="col-lg-2"><a class="btn-sm btn-success" id="analyseBtn_${q.id}" href="#">分析</a>
                    </div>
                    <#if q.type == 1>
                        <div class="col-lg-2">
                            <a class="btn-sm btn-success" id="selectItemBtn_${q.id}" href="#">选项</a>
                        </div>
                    </#if>
                    <#if q.type != 1>
                        <div class="col-lg-2">
                            &nbsp;
                        </div>
                    </#if>

                    <div class="col-lg-4">
                        <form class="form-inline" id="knowledgePointSelectForm_${q.id}" action="${request.contextPath}/manager/question/saveOrUpdateKnowledgePoint/${q.id}" method="post">
                            <div class="form-group">
                                <label class="sr-only" for="exampleInputAmount">选择知识点</label>
                                <input type="hidden" name="pointIds" id="knowledgePointSelectIds_${q.id}" value="${q.pointIds!''}">
                                <#--<input type="hidden" name="questionId" value="${q.id}">-->
                                <div class="input-group">
                                    <div class="input-group-addon">知识点</div>
                                    <input type="text" class="form-control" id="knowledgePointSelect_${q.id}" placeholder="知识点选择">
                                    <div class="input-group-addon btn-success" id="knowledgePointSubmitBtn_${q.id}">提交</div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </td>
    </tr>
</#list>
</table>

<div class="tcdPageCode"></div>

<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; height: 300px;"></ul>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body" id="myModalBody">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="saveBtn">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="selectItemModal" tabindex="-1" role="dialog" aria-labelledby="selectItemModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="selectItemModalLabel">选项编辑</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-hover" id="selectItemTables">
                    <tr>
                        <th class="col-lg-8">选项</th>
                        <th class="col-lg-2">答案</th>
                        <th class="col-lg-2">操作</th>
                    </tr>
                    <tr>
                        <td>选项</td>
                        <td>
                            <span id="selectItemMarkdown_" style="display: none"></span>
                            <input type="checkbox" checked="checked" id="selectItemIsTrue_">
                        </td>
                        <td>
                            <button class="btn btn-success btn-sm" value="删除" id="deleteSelectItem_"></button>
                            <button class="btn btn-success btn-sm" value="修改" id="updateSelectItem_"></button>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="addSelectItemBtn">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="saveSelectItemBtn">Save changes</button>
            </div>
        </div>
    </div>
</div>
<form id="saveForm" action="${request.contextPath}/manager/question" method="post">
    <input type="hidden" id="saveFormType" value="" name="type">
    <input type="hidden" id="saveFormQuestion" value="" name="question">
    <input type="hidden" id="saveFormQuestionHtml" value="" name="questionHtml">
</form>
<form id="updateForm" action="question" method="post">
    <input type="hidden" value="PUT" name="_method">
    <input type="hidden" id="updateFormQuestion" value="" name="question">
    <input type="hidden" id="updateFormQuestionHtml" value="" name="questionHtml">
    <input type="hidden" id="updateFormAnswer" value="" name="answer">
    <input type="hidden" id="updateFormAnswerHtml" value="" name="answerHtml">
    <input type="hidden" id="updateFormAnalyse" value="" name="analyse">
    <input type="hidden" id="updateFormAnalyseHtml" value="" name="analyseHtml">
</form>

<script type="application/javascript">
    $(function () {
        $(".tcdPageCode").createPage({
            pageCount:${page.totalPages},
            current:${page.currentPage},
            backFn:function(p){
                console.log(p);
                window.location.href="${request.contextPath}/manager/question?currentPage="+p;
            }
        });

        var questionType = 0;
        var markdownDoc = null;
        $("input[id^=add]").click(function () {
            $('#myModal').modal('show');
            markdownDoc = "";
            questionType = $(this).attr("datatype");
            //$(".modal-body").html($("#test-editormd").html());
        });
        var id = null;
        var modalSubmitType = "save";
        $("a[id^=answerBtn_]").click(function () {
            id = $(this).attr("id").split("_")[1];
            markdownDoc = $("#answer_" + id).html();
            $('#myModal').modal('show');
            modalSubmitType = "updateAnswer";
        });
        $("a[id^=analyseBtn_]").click(function () {
            id = $(this).attr("id").split("_")[1];
            markdownDoc = $("#analyse_" + id).html();
            $('#myModal').modal('show');
            modalSubmitType = "updateAnalyse";
        });
        $("input[id^=knowledgePointSelect_]").click(function () {
            console.log($(this).attr("id"));
            showMenu($(this).attr("id"));
        });
        $("div[id^=knowledgePointSubmitBtn_]").click(function () {
//            console.log($(this).attr("id"));
//            ajaxForm($(this).closest("form"));
//            showMenu($(this).attr("id"));
            $(this).closest("form").submit();
        });
        $("form[id^=knowledgePointSelectForm_]").ajaxForm({
            type:"post",
            dataType:"json",
            success:function (responseJson) {
                if("success"==responseJson){
                    bs_info("成功添加知识点！");
                }
                console.log(responseJson);
            }
        })
        $("a[id^=selectItemBtn_]").click(function () {
            id = $(this).attr("id").split("_")[1];
            $.get("${request.contextPath}/manager/question/"+id+".json",null,function (responseJson) {
                selectItems=responseJson.selectItems;
            },"json");
            $("#selectItemModal").modal("show");
        });

        $("#addSelectItemBtn").click(function () {
            $("#selectItemModal").modal("hide");
            markdownDoc = "";
            $('#myModal').modal('show');
            modalSubmitType = "addSelectItem";
        });
        var editor;
        $('#myModal').on('shown.bs.modal', function (e) {
            if (editor) {
                editor.editor.remove();
                editor = null;
            }
            $("#myModalBody").append("<div id=\"my-editormd\"></div>");
            // do something...
            editor = editormd("my-editormd", {
                width: "100%",
                height: 360,
                markdown: markdownDoc,
                syncScrolling: "single",
                //你的lib目录的路径，我这边用JSP做测试的
                path: "${request.contextPath}/static/lib/",
                //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                saveHTMLToTextarea: true
            });
        })

        var selectItems = [];

        $('#selectItemModal').on('shown.bs.modal', function (e) {
            $("#selectItemTables").find("tr:gt(0)").remove();
            var trHtml = "";
            $.each(selectItems, function (index, item) {
                trHtml =
                        "<tr>\n" +
                        "    <td>" + item.itemHtml + "</td>\n" +
                        "    <td>\n" +
                        "        <span id=\"selectItemMarkdown_" + index + "\" style=\"display: none\">" + item.item + "</span>\n";
                if (item.isRight) {
                    trHtml += "        <input type=\"checkbox\" checked=\"checked\" id=\"selectItemIsTrue_" + index + "\">\n";
                } else {
                    trHtml += "        <input type=\"checkbox\" id=\"selectItemIsTrue_" + index + "\">\n";
                }

                trHtml += "    </td>\n" +
                        "<td>\n" +
                        "    <button class=\"btn btn-success btn-sm\" value=\"修改\" id=\"updateSelectItem_\">修改</button>\n" +
                        "    <button class=\"btn btn-success btn-sm\" value=\"删除\" id=\"deleteSelectItem_\">删除</button>\n" +
                        "</td></tr>";

                $("#selectItemTables").append(trHtml);

            })
        })
        $("#saveBtn").click(function () {
            if (modalSubmitType == "save") {
                $("#saveFormType").val(questionType);
                $("#saveFormQuestion").val(editor.getMarkdown());
                $("#saveFormQuestionHtml").val(editor.getHTML());
                $("#saveForm").submit();
            } else if (modalSubmitType == "updateAnswer") {
                $("#updateFormAnswer").val(editor.getMarkdown());
                $("#updateFormAnswerHtml").val(editor.getHTML());
                $("#updateForm").attr("action", "${request.contextPath}/manager/question/" + id);
                $("#updateForm").submit();
            } else if (modalSubmitType == "updateAnalyse") {
                $("#updateFormAnalyse").val(editor.getMarkdown());
                $("#updateFormAnalyseHtml").val(editor.getHTML());
                $("#updateForm").attr("action", "${request.contextPath}/manager/question/" + id);
                $("#updateForm").submit();
            } else if (modalSubmitType == "addSelectItem") {
                selectItems.push({item: editor.getMarkdown(), itemHtml: editor.getHTML()});
                $("#myModal").modal("hide");
                $('#selectItemModal').modal('show');
            }
        });
        $("#saveSelectItemBtn").click(function () {
            var jsonObj = {id: id};
            $.each(selectItems, function (index, item) {
                jsonObj['selectItems[' + index + '].item'] = item.item;
                jsonObj['selectItems[' + index + '].itemHtml'] = item.itemHtml;
                jsonObj['selectItems[' + index + '].isRight'] = $("#selectItemIsTrue_" + index).prop('checked');
            });
            $.post("${request.contextPath}/manager/question/saveOrUpdateSelectItems.json", jsonObj, function (responseJSON) {
                if ("success" == responseJSON) {
                    $('#selectItemModal').modal('hide');
                } else {
                    bs.error("更新失败！");
                }
            }, "json");
        });
        $.fn.zTree.init($("#treeDemo"), setting);
    });

    var setting = {
        check: {
            enable: true,
            chkboxType : { "Y" : "p", "N" : "s" }
        },
        async: {
            enable: true,
            url:"${request.contextPath}/manager/knowledgePoint/queryKnowledgePointByClassifyId?classifyId=${Session.classifyId!''}",
//            autoParam:["id", "name=n", "level=lv"],
//            otherParam:{"otherParam":"zTreeAsyncTest"},
            dataFilter: filter
        },
        callback: {
            beforeClick: beforeClick,
            onCheck: onCheck
        }
    };

    var knowledgePointSelectId = null;

    function beforeClick(treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    }

    function onCheck(e, treeId, treeNode) {
        selectId = knowledgePointSelectId.split("_")[1];
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true);

        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });

        var id = "",v="";
        for ( var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";
            id += nodes[i].id + ",";
        }
        if (v.length > 0) {
            v = v.substring(0, v.length - 1);
            id = id.substring(0, id.length - 1);
        }
        $("#knowledgePointSelectIds_"+selectId).val(id);
        var cityObj = $("#"+knowledgePointSelectId);
        cityObj.attr("value", v);
    }

    function showMenu(pointSelectId) {
        var pointSelect = $("#"+pointSelectId);
        $("#treeDemo").css("width", pointSelect.outerWidth());
        knowledgePointSelectId = pointSelectId;
        var ids = $("#knowledgePointSelectIds_"+knowledgePointSelectId.split("_")[1]).val();

        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        for (var i=0, l=nodes.length; i < l; i++) {
            treeObj.checkNode(nodes[i], false, true);
        }

        if(ids!=""){
            var node=null;
            $.each(ids.split(","),function (index,item) {
                node = treeObj.getNodeByParam("id", item, null);
                treeObj.checkNode(node, true, true);
            })
        }

        var cityOffset = pointSelect.offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + pointSelect.outerHeight() + "px",
            'z-index' : 99999999}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == knowledgePointSelectId || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        var d={};
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            d["ABC-"+childNodes[i].id] = childNodes[i].name;
        }
        var obj=null;
        var ids=null;
        var v="";
        $("input[id^=knowledgePointSelectIds_]").each(function (i,item) {
            ids = $(this).val();
           if(ids !=""){
                v="";
                obj = $("#knowledgePointSelect_"+$(this).attr("id").split("_")[1]);
                $.each(ids.split(","),function (i1,item1) {
                    v+=","+d["ABC-"+item1];
                })
               obj.attr("value",v.substr(1));
           }
        });
        return childNodes;
    }
</script>
</body>
</html>
