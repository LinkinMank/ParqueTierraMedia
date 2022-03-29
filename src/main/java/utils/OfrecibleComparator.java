package utils;

import java.util.Comparator;

import models.Ofrecible;

public class OfrecibleComparator implements Comparator<Ofrecible>{
	
	public int compare(Ofrecible o1, Ofrecible o2) {
		if(o1.esPromocion() && o2.esPromocion()) {
			if(Integer.compare(o1.getCosto(), o2.getCosto()) == 0) {
				return -Double.compare(o1.getTiempo(), o2.getTiempo());
			} else {
				return -Integer.compare(o1.getCosto(), o2.getCosto());
			}
		}
		if(!o1.esPromocion() && !o2.esPromocion()) {
			if(Integer.compare(o1.getCosto(), o2.getCosto()) == 0) {
				return -Double.compare(o1.getTiempo(), o2.getTiempo());
			} else {
				return -Integer.compare(o1.getCosto(), o2.getCosto());
			}
		} else {
			return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
		}
	}
}
