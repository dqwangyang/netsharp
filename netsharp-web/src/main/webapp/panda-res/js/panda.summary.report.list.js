/// <reference path="core.js"/>
/// <reference path="panda.js"/>
/// <reference path="pandasd.js"/>

org.netsharp.panda.commerce.ReportSummaryPart = org.netsharp.panda.commerce.ListPart.Extends({

    onload: function () {

        var height = $("#" + this.context.id).parent().parent().parent().parent().parent().height() - 32;
        $(".easyui-datagrid").datagrid({ "height": height });

    }
});
