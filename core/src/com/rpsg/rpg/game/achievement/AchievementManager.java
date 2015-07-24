package com.rpsg.rpg.game.achievement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.rpsg.rpg.utils.xml.AchReader;

public class AchievementManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<BaseAchievement> Achs = new ArrayList<BaseAchievement>();
	public static List<String> compare;
	public static TreeMap<String, List<BaseAchievement>> judge = new TreeMap<String, List<BaseAchievement>>();
	public static boolean flag = false;

	public AchievementManager() {
		Achs = AchReader.get("xml/Ach.xml");
		compareupdate();
	}

	public static void compareupdate() {
		for (BaseAchievement ach : Achs) {
			if (ach.status == 0 && ach.type != 2) {
				if (judge.containsKey(ach.compare)) {
					judge.get(ach.compare).add(ach);
				} else {
					List<BaseAchievement> l = new ArrayList<BaseAchievement>();
					l.add(ach);
					judge.put(ach.compare, l);
				}
			}
		}
	}

	public static void determine(String comparefield) {
		for (BaseAchievement ach : AchievementManager.judge.get(comparefield)) {
			if (ach.status == 1)
				continue;

			ach.judge();
		}
		if (AchievementManager.flag) {
			compareupdate();
		}
	}

}
