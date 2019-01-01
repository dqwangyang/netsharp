package org.netsharp.persistence.oql.languangeEngine;


    public abstract class LaguangeStep implements ILaguangeStep
    {
    	ILanguange engine;
    	
        public ILanguange getEngine(){
        	return engine;
        }
        
        public void setEngine(ILanguange engine){
        	this.engine=engine;
        }

        public abstract void execute();
    }
