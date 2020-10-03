package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawSolution;

/**
 * 
 * @author anacleto
 *
 */
public class ProductionUpdate extends FlawSolution 
{
	private Decision production;
	private double previousAmount;
	private double amount;
	
	/**
	 * 
	 * @param flaw
	 * @param amount
	 * @param cost
	 */
	protected ProductionUpdate(ProductionFlaw flaw, double amount, double cost) {
		super(flaw, cost);
		this.previousAmount = flaw.getProducedAmount();
		this.amount = amount;
		this.production = flaw.getProduction();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPreviousAmount() {
		return previousAmount;
	}
	
	/**
	 * 
	 * @return
	 */
	public Decision getProduction() {
		return production;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "{ \"type\": \"PRODUCTION_UPDATE\", "
				+ "\"new-amount\": " + this.amount + ", "
				+ "\"old-amount\": " + this.previousAmount + ", "
				+ "\"production\": " + this.production  + " }";
	}
}
