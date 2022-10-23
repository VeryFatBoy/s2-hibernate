package com.s2.hibernate.util;

import java.sql.Date;
import java.util.List;

import com.s2.hibernate.dao.TickDao;
import com.s2.hibernate.model.Tick;

public class App {
	public static void main(String[] args) {

		TickDao TickDao = new TickDao();

		Tick Tick = new Tick(Date.valueOf("2013-02-08"), "AAPL", 67.8542);
		TickDao.saveTick(Tick);
		Tick = new Tick(Date.valueOf("2013-02-08"), "ABC", 46.89);
		TickDao.saveTick(Tick);
		Tick = new Tick(Date.valueOf("2013-02-08"), "ADBE", 39.12);
		TickDao.saveTick(Tick);

		List<Tick> Ticks = TickDao.getAllTick();
		Ticks.forEach(oneTick -> System.out.println(
				"Id = " + oneTick.getId() + 
				" Date = " + oneTick.getTs() + 
				" Symbol = " + oneTick.getSymbol() + 
				" Price = " + oneTick.getPrice())
		);

		Tick.setSymbol("ABBV");
		Tick.setPrice(36.25);
		TickDao.updateTick(Tick);

		Ticks = TickDao.getAllTick();
		Ticks.forEach(oneTick -> System.out.println(
				"Id = " + oneTick.getId() + 
				" Date = " + oneTick.getTs() + 
				" Symbol = " + oneTick.getSymbol() + 
				" Price = " + oneTick.getPrice())
		);

		Tick = TickDao.getTick(2);
		TickDao.deleteTick(Tick.getId());

		Ticks = TickDao.getAllTick();
		Ticks.forEach(oneTick -> System.out.println(
				"Id = " + oneTick.getId() +
				" Date = " + oneTick.getTs() +
				" Symbol = " + oneTick.getSymbol() +
				" Price = " + oneTick.getPrice())
		);
	}
}
