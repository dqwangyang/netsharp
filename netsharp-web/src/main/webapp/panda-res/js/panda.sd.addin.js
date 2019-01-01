/// <reference path="system.js" />
/// <reference path="core.js" />
/// <reference path="panda.js" />
/// <reference path="panda.tree.js" />
/// <reference path="panda.sd.js" />

System.Declare('org.netsharp.panda.Tools');

//======================================================================

org.netsharp.panda.Tools.AddinTreePart = org.netsharp.panda.commerce.ListPart.Extends({
    oldnode: "",
    onClick: function (node) {

        if (!System.isnull(this.oldnode.id)) {
            if (node.id == this.oldnode.id) {
                return;
            }
        }

        if (System.isnull(node.id)) {
            return;
        }

        this.oldnode = node;

        var filter = "IdAddin='" + node.id + "'";
        controllermain.doQuery(filter);
    },
    onDblClick: function (node) {

        if (System.isnull(node.id)) {
            return;
        }

        var id = node.id;
        var url = "/Sd/AddinForm.view?id=" + id;
        url = System.Url.getUrl(url);

        IMessageBox.open("插件属性", url, 900, 650);
//        this.reload();
    }
});

org.netsharp.panda.Tools.PathListPart = org.netsharp.panda.commerce.ListPart.Extends({

});