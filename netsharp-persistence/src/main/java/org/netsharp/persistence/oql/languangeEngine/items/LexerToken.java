package org.netsharp.persistence.oql.languangeEngine.items;

public abstract class LexerToken {

	public boolean isIgnoreCase() {
        return true;
	}

	public LexerToken newInstance() {
		try {
			return (LexerToken) this.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
