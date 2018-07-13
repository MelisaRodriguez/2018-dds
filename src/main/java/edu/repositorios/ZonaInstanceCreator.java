package edu.repositorios;

import java.awt.geom.Point2D;
import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

public class ZonaInstanceCreator implements InstanceCreator<Point2D> {

	@Override
	public Point2D createInstance(Type arg0) {
		// TODO Auto-generated method stub
		return new Point2D.Double();
	}
	
}