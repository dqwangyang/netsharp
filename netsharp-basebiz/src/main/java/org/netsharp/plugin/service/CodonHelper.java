package org.netsharp.plugin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.netsharp.plugin.entity.Codonable;
import org.netsharp.util.StringManager;

public class CodonHelper {
	
	public static List<Codonable> sort(List<Codonable> codons){
		
		Collections.sort(codons, new Comparator<Codonable>() {
            public int compare(Codonable arg0, Codonable arg1) {
            	
               return arg0.getSeq().compareTo(arg1.getSeq());
            }
        });
		
		return codons;
	}
	
	public static List<Codonable> generateTree(List<Codonable> codons){
		
		List<Codonable> subs = new ArrayList<Codonable>();
		
		for(Codonable codon : codons){
			if(!StringManager.isNullOrEmpty(codon.getParent())){
				subs.add(codon);
			}
		}
		
		codons.removeAll(subs);
		
		for(Codonable codon : codons){
			populate(codon,subs);
		}
		
		return codons;
	}
	
	private static void populate(Codonable codon,List<Codonable> subs){
		
		String code = codon.getCode();
		
		List<Codonable> hints = new ArrayList<Codonable>();
		
		for(Codonable sub : subs){
			if(StringManager.equals(code, sub.getParent())){
				hints.add(sub);
				codon.getChildrens().add(sub);
			}
		}
		
		subs.removeAll(hints);
		
		for(Codonable hint : hints){
			populate(hint,subs);
		}
	}
}
