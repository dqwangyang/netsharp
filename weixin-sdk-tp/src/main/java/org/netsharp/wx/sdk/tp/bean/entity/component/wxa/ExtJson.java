package org.netsharp.wx.sdk.tp.bean.entity.component.wxa;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/6/25 14:41
 */
public class ExtJson {

    private String extAppid;
    private Ext ext;


    public String getExtAppid() {
        return extAppid;
    }

    public void setExtAppid(String extAppid) {
        this.extAppid = extAppid;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }

    public static class Ext{
        private String extAppid;

        public String getExtAppid() {
            return extAppid;
        }

        public void setExtAppid(String extAppid) {
            this.extAppid = extAppid;
        }
    }

}
