/**
 * Created by xifeng on 2017/1/22.
 */
$(function () {

    $.extend($.jgrid.defaults, {
        datatype: 'json',
        // jsonReader: {
        //     repeatitems: false,
        //     total: function (result) {
        //         //Total number of pages
        //         return Math.ceil(result.total / result.max);
        //     },
        //     records: function (result) {
        //         //Total number of records
        //         return result.total;
        //     }
        // },
        prmNames: {
            page: "currentPage", rows: "sizeOfPerPage", sort: "orderBy", order: "sort"
        },
        sortorder: "desc",
        jsonReader: {
            repeatitems: false
        },
        height: 'auto',
        viewrecords: true,
        rowNum: 30,
        rownumbers: true,
        rowList: [20, 50, 100, 500],
        altRows: true
    });

});