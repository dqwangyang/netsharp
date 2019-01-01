package org.netsharp.persistence.oql.languangeEngine.items;

import java.util.Iterator;

import org.netsharp.persistence.oql.languangeEngine.EngineContext;


public class Tokenizer implements Iterable<LexerToken>
{
    public String Input ;

    public EngineContext Context ;

    public Tokenizer(EngineContext context)
    {
        this.Input = context.LexerCode;
        this.Context = context;
    }

	@Override
	public Iterator<LexerToken> iterator() {
		
		return new TokenizerEnumator(this);
	}
}
