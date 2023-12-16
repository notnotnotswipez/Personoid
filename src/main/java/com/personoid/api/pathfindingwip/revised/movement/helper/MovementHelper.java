package com.personoid.api.pathfindingwip.revised.movement.helper;

import com.personoid.api.pathfinding.calc.utils.BlockPos;
import com.personoid.api.pathfindingwip.revised.calc.Node;
import com.personoid.api.pathfindingwip.revised.movement.Movement;

public class MovementHelper<T extends Movement> {
	
	private final T movement;
	
	public MovementHelper(T m) {
		this.movement = m;
	}
	
	public double cost() {
		return 0;
	}
	
	public BlockPos sourcePos() {
		return getSource().blockPos();
	}
	
	public BlockPos destinationPos() {
		return getDestination().blockPos();
	}
	
	public Node getSource() {
		return movement.getSource();
	}
	
	public Node getDestination() {
		return movement.getDestination();
	}
	
	public T getMovement() {
		return movement;
	}
	
}
