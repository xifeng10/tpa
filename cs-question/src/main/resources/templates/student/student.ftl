<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>题目维护</title>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.page.js"></script>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        a {
            text-decoration: none;
        }

        a:hover {
            text-decoration: none;
        }

        .tcdPageCode {
            padding: 15px 20px;
            text-align: left;
            color: #ccc;
            text-align: center;
        }

        .tcdPageCode a {
            display: inline-block;
            color: #428bca;
            display: inline-block;
            height: 25px;
            line-height: 25px;
            padding: 0 10px;
            border: 1px solid #ddd;
            margin: 0 2px;
            border-radius: 4px;
            vertical-align: middle;
        }

        .tcdPageCode a:hover {
            text-decoration: none;
            border: 1px solid #428bca;
        }

        .tcdPageCode span.current {
            display: inline-block;
            height: 25px;
            line-height: 25px;
            padding: 0 10px;
            margin: 0 2px;
            color: #fff;
            background-color: #428bca;
            border: 1px solid #428bca;
            border-radius: 4px;
            vertical-align: middle;
        }

        .tcdPageCode span.disabled {
            display: inline-block;
            height: 25px;
            line-height: 25px;
            padding: 0 10px;
            margin: 0 2px;
            color: #bfbfbf;
            background: #f2f2f2;
            border: 1px solid #bfbfbf;
            border-radius: 4px;
            vertical-align: middle;
        }

    </style>
</head>
<body>
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
            </div>
            </div>
        </td>
    </tr>
</#list>
</table>

<div class="tcdPageCode"></div>

<script type="application/javascript">
    $(function () {
        $(".tcdPageCode").createPage({
            pageCount:${page.totalPages},
            current:${page.currentPage},
            backFn: function (p) {
                console.log(p);
                window.location.href = "${request.contextPath}/student/${type}?currentPage=" + p;
            }
        });
    });
</script>
</body>
</html>
