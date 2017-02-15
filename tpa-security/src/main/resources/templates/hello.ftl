<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <script type="text/ecmascript" src="http://www.guriddo.net/demo/js/bootstrap-datepicker.js"></script>
    <script type="text/ecmascript" src="http://www.guriddo.net/demo/js/bootstrap3-typeahead.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="http://www.guriddo.net/demo/css/bootstrap-datepicker.css" />
    <link rel="stylesheet" type="text/css"
          href="${request.contextPath}/static/css/zTreeStyle/zTreeStyle.css"/>
    <script type="application/javascript" src="${request.contextPath}/static/js/jquery.ztree.all.min.js"></script>
</head>
<body>

<style type="text/css">
    .customelement .radio-inline input {
        margin-left :-30px;
    }
    .typeahead {
        z-index: 10051;
    }
</style>
<div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>

</div>
<script type="text/javascript">

    $(document).ready(function () {

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
        var URL="";
        $("#jqGrid").jqGrid({
            url: 'static/data.json',
            editurl: 'clientArray',
            mtype: "GET",
            datatype: "json",
            page: 1,
            colModel: [
                { label: 'Order ID', name: 'OrderID', key: true, width: 75 },
                {
                    label: 'Order Date',
                    name: 'OrderDate',
                    width: 150,
                    hidden:true,
                    editable: true,
                    edittype:"text",
                    editoptions: {
                        // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                        // use it to place a third party control to customize the toolbar
                        dataInit: function (element) {
                            $(element).datepicker({
                                autoclose: true,
                                format: 'yyyy-mm-dd',
                                orientation : 'bottom'
                            });
                        }
                    }
                },
                {
                    label: 'Customer ID',
                    name: 'CustomerID',
                    width: 150,
                    editable: true,
                    edittype: "select",
                    editoptions: {
                        value: "ALFKI:ALFKI;ANATR:ANATR;ANTON:ANTON;AROUT:AROUT;BERGS:BERGS;BLAUS:BLAUS;BLONP:BLONP;BOLID:BOLID;BONAP:BONAP;BOTTM:BOTTM;BSBEV:BSBEV;CACTU:CACTU;CENTC:CENTC;CHOPS:CHOPS;COMMI:COMMI;CONSH:CONSH;DRACD:DRACD;DUMON:DUMON;EASTC:EASTC;ERNSH:ERNSH;FAMIA:FAMIA;FISSA:FISSA;FOLIG:FOLIG;FOLKO:FOLKO;FRANK:FRANK;FRANR:FRANR;FRANS:FRANS;FURIB:FURIB;GALED:GALED;GODOS:GODOS;GOURL:GOURL;GREAL:GREAL;GROSR:GROSR;HANAR:HANAR;HILAA:HILAA;HUNGC:HUNGC;HUNGO:HUNGO;ISLAT:ISLAT;KOENE:KOENE;LACOR:LACOR;LAMAI:LAMAI;LAUGB:LAUGB;LAZYK:LAZYK;LEHMS:LEHMS;LETSS:LETSS;LILAS:LILAS;LINOD:LINOD;LONEP:LONEP;MAGAA:MAGAA;MAISD:MAISD;MEREP:MEREP;MORGK:MORGK;NORTS:NORTS;OCEAN:OCEAN;OLDWO:OLDWO;OTTIK:OTTIK;PARIS:PARIS;PERIC:PERIC;PICCO:PICCO;PRINI:PRINI;QUEDE:QUEDE;QUEEN:QUEEN;QUICK:QUICK;RANCH:RANCH;RATTC:RATTC;REGGC:REGGC;RICAR:RICAR;RICSU:RICSU;ROMEY:ROMEY;SANTG:SANTG;SAVEA:SAVEA;SEVES:SEVES;SIMOB:SIMOB;SPECD:SPECD;SPLIR:SPLIR;SUPRD:SUPRD;THEBI:THEBI;THECR:THECR;TOMSP:TOMSP;TORTU:TORTU;TRADH:TRADH;TRAIH:TRAIH;VAFFE:VAFFE;VICTE:VICTE;VINET:VINET;WANDK:WANDK;WARTH:WARTH;WELLI:WELLI;WHITC:WHITC;WILMK:WILMK;WOLZA:WOLZA"
                    }
                },
                {
                    label: 'Freigh',
                    name: 'Freight',
                    width: 150,
                    editable: true,
                    edittype: "custom",
                    editoptions: {
                        custom_value: getFreightElementValue,
                        custom_element: createFreightEditElement
                    }
                },
                {
                    label: 'Ship Name',
                    name: 'ShipName',
                    width: 150,
                    editable: true,
                    edittype: "text",
                    editoptions: {
                        // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                        // use it to place a third party control to customize the toolbar
                        dataInit: function (element) {
                            $(element).attr("autocomplete","off").typeahead({
                                appendTo : "body",
                                source: function(query, proxy) {
                                    $.ajax({
                                        url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/autocompletepbs.php?callback=?&acelem=ShipName',
                                        dataType: "jsonp",
                                        data: {term: query},
                                        success : proxy
                                    });
                                }
                            });
                        }
                    }
                }
            ],
            loadonce : true,
            viewrecords: true,
            height: 250,
            rowNum: 10,
            pager: "#jqGridPager"
        });

        $('#jqGrid').navGrid('#jqGridPager',
                {}, //options
                editOptions,
                addOptions,
                delOptions,
                {} // search options
        );
//        $("#jqGrid").navGrid("#jqGridPager",
//                { edit: true, add: false, del: false, search: false, refresh: true, view: false, align: "left" },
//                { closeAfterEdit: true, focusField : 1 }
//        );

        function createFreightEditElement(value, editOptions) {

            var div =$("<div style='margin-bottom:5px;margin-top:-10px;'></div>");
            var label = $("<label class='radio-inline'></label>");
            var radio = $("<input>", { type: "radio", value: "0", name: "freight", id: "zero", checked: (value != 25 && value != 50 && value != 100) });
            label.append(radio).append("0");
            var label1 = $("<label class='radio-inline'></label>");
            var radio1 = $("<input>", { type: "radio", value: "25", name: "freight", id: "twentyfive", checked: value == 25 });
            label1.append(radio1).append("25");
            var label2 = $("<label class='radio-inline'></label>");
            var radio2 = $("<input>", { type: "radio", value: "50", name: "freight", id: "fifty", checked: value == 50 });
            label2.append(radio2).append("50");
            var label3 = $("<label class='radio-inline'></label>");
            var radio3 = $("<input>", { type: "radio", value: "100", name: "freight", id: "hundred", checked: value == 100 });
            label3.append(radio3).append("100");
            div.append(label).append(label1).append(label2).append(label3);

            return div;
        }

        // The javascript executed specified by JQGridColumn.EditTypeCustomGetValue when EditType = EditType.Custom
        // One parameter passed - the custom element created in JQGridColumn.EditTypeCustomCreateElement
        function getFreightElementValue(elem, oper, value) {
            if (oper === "set") {
                var radioButton = $(elem).find("input:radio[value='" + value + "']");
                if (radioButton.length > 0) {
                    radioButton.prop("checked", true);
                }
            }

            if (oper === "get") {
                return $(elem).find("input:radio:checked").val();
            }
        }

    });

</script>


</body>
</html>