package org.netsharp.wx.sdk.mp.message.request.event;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class EventRequest extends RequestMessage
{
	private String eventKey;
	private String event;
    private String encrypt;
	
	public final String getEventKey()
	{
		return eventKey;
	}
	public final void setEventKey(String value)
	{
		eventKey = value;
	}

	
	public final String getEvent()
	{
		return event;
	}
	public final void setEvent(String value)
	{
		event = value;
	}

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    @Override
	public String toString()
	{
		String baseString = super.toString();
        baseString = baseString + ";Event:" + this.getEvent() + ";EventKey:" + getEventKey();
        if (encrypt != null && getEncrypt().trim().length() > 0) {
            baseString += ";Encrypt:" + getEncrypt();
        }
		return baseString;
	}
}