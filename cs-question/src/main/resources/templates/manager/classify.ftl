<!DOCTYPE html>
<html>
<head>
    <title>题目类型编辑</title>
    <link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>

    <style type="text/css">
        .menuContent ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;max-height: 360px;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
    </style>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        var zNodes =[
            {id:1, pId:0, name:"北京"},
            {id:2, pId:0, name:"天津"},
            {id:3, pId:0, name:"上海"},
            {id:6, pId:0, name:"重庆"},
            {id:4, pId:0, name:"河北省", open:true},
            {id:41, pId:4, name:"石家庄"},
            {id:42, pId:4, name:"保定"},
            {id:43, pId:4, name:"邯郸"},
            {id:44, pId:4, name:"承德"},
            {id:5, pId:0, name:"广东省", open:true},
            {id:51, pId:5, name:"广州"},
            {id:52, pId:5, name:"深圳"},
            {id:53, pId:5, name:"东莞"},
            {id:54, pId:5, name:"佛山"},
            {id:6, pId:0, name:"福建省", open:true},
            {id:61, pId:6, name:"福州"},
            {id:62, pId:6, name:"厦门"},
            {id:63, pId:6, name:"泉州"},
            {id:64, pId:6, name:"三明"}
        ];

        function beforeClick(treeId, treeNode) {
            var check = (treeNode && !treeNode.isParent);
            if (!check) alert("只能选择城市...");
            return check;
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                v += nodes[i].name + ",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length-1);
            var cityObj = $("#parentClassify");
            cityObj.attr("value", v);
        }

        function showMenu() {
            var cityObj = $("#parentClassify");
            var cityOffset = $("#parentClassify").offset();
            $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "parentClassify" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $("#parentClassify").click(showMenu);
        });
        //-->
    </SCRIPT>
</head>
<body>

<form name="form1" method="post"
      action="${request.contextPath}/classify/${classify.id}">
    <c:if test="${not empty classify}">
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    <div class="form-group">
        <label for="name">类型名称</label>
        <input type="text" name="name" id="name" class="form-control"
               value="${classify.name}" title="">
    </div>
    <div class="form-group">
        <label for="name">父类型</label>
        <input type="text" name="parentClassify.id" id="parentClassify" class="form-control"
               value="${classify.parentClassify.id}" title="">
    </div>

    <button class="btn btn-success" type="submit">提交</button>
</form>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>
